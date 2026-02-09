package com.example.scalsandroid.scals.ir.resolution

import com.example.scalsandroid.scals.document.Component
import com.example.scalsandroid.scals.document.DataReferenceType
import com.example.scalsandroid.scals.state.ExpressionEvaluator
import com.example.scalsandroid.scals.state.StateValueConverter
import com.example.scalsandroid.scals.viewtree.ViewNode

data class ContentResolutionResult(
    val content: String,
    val bindingPath: String? = null,
    val bindingTemplate: String? = null,
) {
    val isDynamic: Boolean get() = bindingPath != null || bindingTemplate != null

    companion object {
        fun static(content: String) = ContentResolutionResult(content)
    }
}

object ContentResolver {

    fun resolve(
        component: Component,
        context: ResolutionContext,
        viewNode: ViewNode? = null,
    ): ContentResolutionResult {
        // 1. Check data source reference on component
        component.dataSource?.let { dataRef ->
            return when (dataRef.type) {
                DataReferenceType.STATIC -> {
                    val value = dataRef.value?.let { StateValueConverter.unwrap(it) }
                    ContentResolutionResult.static(valueToString(value))
                }
                DataReferenceType.BINDING -> {
                    resolveBinding(dataRef.path, dataRef.template, context, viewNode)
                }
                DataReferenceType.LOCAL_BINDING -> {
                    resolveLocalBinding(dataRef.path, context, viewNode)
                }
            }
        }

        // 2. Check text property (inline text with possible template)
        component.text?.let { text ->
            if (ExpressionEvaluator.containsExpression(text)) {
                // Record reads for template paths
                recordTemplatePaths(text, context, viewNode)
                val interpolated = context.interpolate(text)
                return ContentResolutionResult(
                    content = interpolated,
                    bindingTemplate = text,
                )
            }
            return ContentResolutionResult.static(text)
        }

        // 3. Check value property
        component.value?.let { stateValue ->
            val unwrapped = StateValueConverter.unwrap(stateValue)
            return ContentResolutionResult.static(valueToString(unwrapped))
        }

        // 4. Check binding property
        component.binding?.let { bindingPath ->
            return resolveBinding(bindingPath, null, context, viewNode)
        }

        // Default: empty
        return ContentResolutionResult.static("")
    }

    private fun resolveBinding(
        path: String?,
        template: String?,
        context: ResolutionContext,
        viewNode: ViewNode?,
    ): ContentResolutionResult {
        if (template != null) {
            recordTemplatePaths(template, context, viewNode)
            val interpolated = context.interpolate(template)
            return ContentResolutionResult(
                content = interpolated,
                bindingPath = path,
                bindingTemplate = template,
            )
        }
        if (path != null) {
            context.tracker?.recordRead(path)
            val value = context.getValue(path)
            return ContentResolutionResult(
                content = valueToString(value),
                bindingPath = path,
            )
        }
        return ContentResolutionResult.static("")
    }

    private fun resolveLocalBinding(
        path: String?,
        context: ResolutionContext,
        viewNode: ViewNode?,
    ): ContentResolutionResult {
        if (path == null) return ContentResolutionResult.static("")
        context.tracker?.recordLocalRead(path)
        val value = viewNode?.getLocalState(path)
        return ContentResolutionResult(
            content = valueToString(value),
            bindingPath = "local.$path",
        )
    }

    private fun recordTemplatePaths(
        template: String,
        context: ResolutionContext,
        viewNode: ViewNode?,
    ) {
        val pattern = Regex("""\$\{([^}]+)\}""")
        pattern.findAll(template).forEach { match ->
            val expr = match.groupValues[1].trim()
            // Don't record iteration variables as state dependencies
            if (!context.iterationVariables.containsKey(expr)) {
                context.tracker?.recordRead(expr)
            }
        }
    }

    private fun valueToString(value: Any?): String = when (value) {
        null -> ""
        is String -> value
        is Number -> {
            if (value is Double && value == value.toLong().toDouble()) {
                value.toLong().toString()
            } else {
                value.toString()
            }
        }
        is Boolean -> value.toString()
        else -> value.toString()
    }
}
