package com.example.scalsandroid.scals.state

object ExpressionEvaluator {

    private val templatePattern = Regex("""\$\{([^}]+)\}""")

    fun containsExpression(string: String): Boolean = templatePattern.containsMatchIn(string)

    fun isPureExpression(string: String): Boolean {
        val trimmed = string.trim()
        if (!trimmed.startsWith("\${") || !trimmed.endsWith("}")) return false
        // Check that after removing outer ${...}, there are no other template markers
        val inner = trimmed.substring(2, trimmed.length - 1)
        return !templatePattern.containsMatchIn(inner)
    }

    fun unwrapExpression(string: String): String? {
        val trimmed = string.trim()
        if (!trimmed.startsWith("\${") || !trimmed.endsWith("}")) return null
        return trimmed.substring(2, trimmed.length - 1)
    }

    fun evaluate(expression: String, stateReader: StateValueReading): Any {
        val trimmed = expression.trim()

        // Ternary expression: condition ? trueValue : falseValue
        parseTernary(trimmed, stateReader)?.let { return it }

        // Array .count / .isEmpty / .first / .last
        if (trimmed.endsWith(".count")) {
            val path = trimmed.substringBeforeLast(".count")
            return stateReader.getArrayCount(path)
        }
        if (trimmed.endsWith(".isEmpty")) {
            val path = trimmed.substringBeforeLast(".isEmpty")
            return stateReader.getArrayCount(path) == 0
        }
        if (trimmed.endsWith(".first")) {
            val path = trimmed.substringBeforeLast(".first")
            return stateReader.getArray(path)?.firstOrNull() ?: ""
        }
        if (trimmed.endsWith(".last")) {
            val path = trimmed.substringBeforeLast(".last")
            return stateReader.getArray(path)?.lastOrNull() ?: ""
        }

        // Array .contains(...)
        val containsMatch = Regex("""^(.+)\.contains\((.+)\)$""").find(trimmed)
        if (containsMatch != null) {
            val arrayPath = containsMatch.groupValues[1]
            val valueArg = containsMatch.groupValues[2].trim()
            val checkValue = resolveValue(valueArg, stateReader)
            return stateReader.arrayContains(arrayPath, checkValue)
        }

        // Arithmetic expression
        if (containsArithmeticOperator(trimmed)) {
            return evaluateArithmetic(trimmed, stateReader)
        }

        // Array indexing with variable: items[currentIndex]
        val arrayIndexMatch = Regex("""^(\w[\w.]*)?\[([^\]]+)\](.*)$""").find(trimmed)
        if (arrayIndexMatch != null) {
            val arrayPath = arrayIndexMatch.groupValues[1]
            val indexExpr = arrayIndexMatch.groupValues[2]
            val rest = arrayIndexMatch.groupValues[3]
            val index = resolveToNumber(indexExpr, stateReader)?.toInt()
            if (index != null) {
                val array = stateReader.getArray(arrayPath)
                val element = array?.getOrNull(index)
                if (rest.isNotEmpty() && rest.startsWith(".") && element is Map<*, *>) {
                    @Suppress("UNCHECKED_CAST")
                    val map = element as Map<String, Any?>
                    return KeypathAccessor.get(rest.substring(1), map) ?: ""
                }
                return element ?: ""
            }
        }

        // Simple keypath lookup
        return stateReader.getValue(trimmed) ?: ""
    }

    fun interpolate(template: String, stateReader: StateValueReading): String {
        val matches = templatePattern.findAll(template).toList()
        if (matches.isEmpty()) return template

        val result = StringBuilder(template)
        // Process in reverse to maintain correct indices
        for (match in matches.reversed()) {
            val expression = match.groupValues[1]
            val resolved = evaluate(expression, stateReader)
            result.replace(match.range.first, match.range.last + 1, valueToString(resolved))
        }
        return result.toString()
    }

    private fun parseTernary(expression: String, stateReader: StateValueReading): Any? {
        val questionIdx = findTernaryQuestionMark(expression) ?: return null
        val colonIdx = findTernaryColon(expression, questionIdx + 1) ?: return null

        val condition = expression.substring(0, questionIdx).trim()
        val trueExpr = expression.substring(questionIdx + 1, colonIdx).trim()
        val falseExpr = expression.substring(colonIdx + 1).trim()

        val conditionValue = evaluate(condition, stateReader)
        val isTrue = isTruthy(conditionValue)

        val resultExpr = if (isTrue) trueExpr else falseExpr
        return resolveValue(resultExpr, stateReader)
    }

    private fun findTernaryQuestionMark(expression: String): Int? {
        var parenDepth = 0
        for (i in expression.indices) {
            when (expression[i]) {
                '(' -> parenDepth++
                ')' -> parenDepth--
                '?' -> if (parenDepth == 0) return i
            }
        }
        return null
    }

    private fun findTernaryColon(expression: String, start: Int): Int? {
        var parenDepth = 0
        for (i in start until expression.length) {
            when (expression[i]) {
                '(' -> parenDepth++
                ')' -> parenDepth--
                ':' -> if (parenDepth == 0) return i
            }
        }
        return null
    }

    private fun isTruthy(value: Any?): Boolean = when (value) {
        null -> false
        is Boolean -> value
        is Number -> value.toDouble() != 0.0
        is String -> value.isNotEmpty() && value != "false" && value != "0"
        is List<*> -> value.isNotEmpty()
        else -> true
    }

    private fun resolveValue(expr: String, stateReader: StateValueReading): Any {
        val trimmed = expr.trim()
        // String literal (single or double quotes)
        if ((trimmed.startsWith("'") && trimmed.endsWith("'")) ||
            (trimmed.startsWith("\"") && trimmed.endsWith("\""))) {
            return trimmed.substring(1, trimmed.length - 1)
        }
        // Numeric literal
        trimmed.toDoubleOrNull()?.let { d ->
            if (d == d.toLong().toDouble()) return d.toLong()
            return d
        }
        // Boolean literal
        if (trimmed == "true") return true
        if (trimmed == "false") return false
        // Sub-expression or keypath
        return evaluate(trimmed, stateReader)
    }

    private fun containsArithmeticOperator(expr: String): Boolean {
        var parenDepth = 0
        var inQuotes = false
        var quoteChar = ' '
        for (c in expr) {
            when {
                !inQuotes && (c == '\'' || c == '"') -> { inQuotes = true; quoteChar = c }
                inQuotes && c == quoteChar -> inQuotes = false
                !inQuotes && c == '(' -> parenDepth++
                !inQuotes && c == ')' -> parenDepth--
                !inQuotes && parenDepth == 0 && c in "+-*/%".toCharArray() -> return true
            }
        }
        return false
    }

    private fun evaluateArithmetic(expression: String, stateReader: StateValueReading): Any {
        val tokens = tokenize(expression)
        val rpn = shuntingYard(tokens)
        return evaluateRPN(rpn, stateReader)
    }

    private sealed class Token {
        data class NumberLiteral(val value: Double) : Token()
        data class Identifier(val name: String) : Token()
        data class Operator(val op: Char) : Token()
        data object LeftParen : Token()
        data object RightParen : Token()
    }

    private fun precedence(op: Char): Int = when (op) {
        '+', '-' -> 1
        '*', '/', '%' -> 2
        else -> 0
    }

    private fun tokenize(expression: String): List<Token> {
        val tokens = mutableListOf<Token>()
        var i = 0
        while (i < expression.length) {
            val c = expression[i]
            when {
                c.isWhitespace() -> i++
                c == '(' -> { tokens.add(Token.LeftParen); i++ }
                c == ')' -> { tokens.add(Token.RightParen); i++ }
                c in "+-*/%".toCharArray() -> {
                    // Unary minus: if at start or after operator/left paren
                    if (c == '-' && (tokens.isEmpty() || tokens.last() is Token.Operator || tokens.last() is Token.LeftParen)) {
                        // Read number
                        i++
                        val start = i
                        while (i < expression.length && (expression[i].isDigit() || expression[i] == '.')) i++
                        if (i > start) {
                            tokens.add(Token.NumberLiteral(-expression.substring(start, i).toDouble()))
                        }
                    } else {
                        tokens.add(Token.Operator(c))
                        i++
                    }
                }
                c.isDigit() || c == '.' -> {
                    val start = i
                    while (i < expression.length && (expression[i].isDigit() || expression[i] == '.')) i++
                    tokens.add(Token.NumberLiteral(expression.substring(start, i).toDouble()))
                }
                c.isLetter() || c == '_' -> {
                    val start = i
                    while (i < expression.length && (expression[i].isLetterOrDigit() || expression[i] == '_' || expression[i] == '.' || expression[i] == '[' || expression[i] == ']')) i++
                    tokens.add(Token.Identifier(expression.substring(start, i)))
                }
                else -> i++
            }
        }
        return tokens
    }

    private fun shuntingYard(tokens: List<Token>): List<Token> {
        val output = mutableListOf<Token>()
        val operators = ArrayDeque<Token>()
        for (token in tokens) {
            when (token) {
                is Token.NumberLiteral, is Token.Identifier -> output.add(token)
                is Token.Operator -> {
                    while (operators.isNotEmpty()) {
                        val top = operators.last()
                        if (top is Token.Operator && precedence(top.op) >= precedence(token.op)) {
                            output.add(operators.removeLast())
                        } else break
                    }
                    operators.addLast(token)
                }
                is Token.LeftParen -> operators.addLast(token)
                is Token.RightParen -> {
                    while (operators.isNotEmpty() && operators.last() !is Token.LeftParen) {
                        output.add(operators.removeLast())
                    }
                    if (operators.isNotEmpty()) operators.removeLast() // remove LeftParen
                }
            }
        }
        while (operators.isNotEmpty()) {
            output.add(operators.removeLast())
        }
        return output
    }

    private fun evaluateRPN(tokens: List<Token>, stateReader: StateValueReading): Any {
        val stack = ArrayDeque<Double>()
        for (token in tokens) {
            when (token) {
                is Token.NumberLiteral -> stack.addLast(token.value)
                is Token.Identifier -> {
                    val value = stateReader.getValue(token.name)
                    stack.addLast(toDouble(value))
                }
                is Token.Operator -> {
                    if (stack.size < 2) return 0.0
                    val b = stack.removeLast()
                    val a = stack.removeLast()
                    val result = when (token.op) {
                        '+' -> a + b
                        '-' -> a - b
                        '*' -> a * b
                        '/' -> if (b != 0.0) a / b else 0.0
                        '%' -> if (b != 0.0) a % b else 0.0
                        else -> 0.0
                    }
                    stack.addLast(result)
                }
                else -> {}
            }
        }
        val result = stack.lastOrNull() ?: 0.0
        // Return Int if whole number
        if (result == result.toLong().toDouble()) return result.toLong()
        return result
    }

    private fun resolveToNumber(expr: String, stateReader: StateValueReading): Double? {
        expr.trim().toDoubleOrNull()?.let { return it }
        val value = stateReader.getValue(expr.trim())
        return toDouble(value).takeIf { it != 0.0 || value != null }
    }

    private fun toDouble(value: Any?): Double = when (value) {
        is Number -> value.toDouble()
        is String -> value.toDoubleOrNull() ?: 0.0
        is Boolean -> if (value) 1.0 else 0.0
        else -> 0.0
    }

    private fun valueToString(value: Any?): String = when (value) {
        null -> ""
        is String -> value
        is Long -> value.toString()
        is Int -> value.toString()
        is Double -> {
            if (value == value.toLong().toDouble()) value.toLong().toString()
            else value.toString()
        }
        is Boolean -> value.toString()
        is List<*> -> value.joinToString(", ")
        else -> value.toString()
    }
}
