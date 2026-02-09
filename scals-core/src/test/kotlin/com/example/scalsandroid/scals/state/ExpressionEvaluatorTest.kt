package com.example.scalsandroid.scals.state

import org.junit.Test
import org.junit.Assert.*

class ExpressionEvaluatorTest {

    private class TestStateReader(private val state: Map<String, Any?>) : StateValueReading {
        override fun getValue(keypath: String): Any? = KeypathAccessor.get(keypath, state)

        override fun getArray(keypath: String): List<Any>? {
            @Suppress("UNCHECKED_CAST")
            return getValue(keypath) as? List<Any>
        }

        override fun arrayContains(keypath: String, value: Any): Boolean {
            return getArray(keypath)?.contains(value) ?: false
        }

        override fun getArrayCount(keypath: String): Int {
            return getArray(keypath)?.size ?: 0
        }
    }

    @Test
    fun testSimpleKeypath() {
        val reader = TestStateReader(mapOf("name" to "World"))
        val result = ExpressionEvaluator.evaluate("name", reader)
        assertEquals("World", result)
    }

    @Test
    fun testArithmetic() {
        val reader = TestStateReader(mapOf("count" to 5))
        val result = ExpressionEvaluator.evaluate("count + 1", reader)
        assertEquals(6L, result)
    }

    @Test
    fun testParentheses() {
        val reader = TestStateReader(mapOf("index" to 2))
        val result = ExpressionEvaluator.evaluate("(index + 1) % 3", reader)
        assertEquals(0L, result)
    }

    @Test
    fun testTernary() {
        val reader = TestStateReader(mapOf("isActive" to true))
        val result = ExpressionEvaluator.evaluate("isActive ? 'Yes' : 'No'", reader)
        assertEquals("Yes", result)

        val reader2 = TestStateReader(mapOf("isActive" to false))
        val result2 = ExpressionEvaluator.evaluate("isActive ? 'Yes' : 'No'", reader2)
        assertEquals("No", result2)
    }

    @Test
    fun testInterpolation() {
        val reader = TestStateReader(mapOf("name" to "World"))
        val result = ExpressionEvaluator.interpolate("Hello \${name}!", reader)
        assertEquals("Hello World!", result)
    }

    @Test
    fun testContainsExpression() {
        assertTrue(ExpressionEvaluator.containsExpression("\${x}"))
        assertTrue(ExpressionEvaluator.containsExpression("Hello \${name}"))
        assertFalse(ExpressionEvaluator.containsExpression("plain text"))
    }

    @Test
    fun testArrayCount() {
        val reader = TestStateReader(mapOf("items" to listOf(1, 2, 3)))
        val result = ExpressionEvaluator.evaluate("items.count", reader)
        assertEquals(3, result)
    }
}
