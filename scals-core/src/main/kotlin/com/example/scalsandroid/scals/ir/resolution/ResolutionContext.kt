package com.example.scalsandroid.scals.ir.resolution

import com.example.scalsandroid.scals.document.Definition
import com.example.scalsandroid.scals.state.ExpressionEvaluator
import com.example.scalsandroid.scals.state.StateStore
import com.example.scalsandroid.scals.state.StateValueReading
import com.example.scalsandroid.scals.viewtree.DependencyTracker
import com.example.scalsandroid.scals.viewtree.ViewNode

class ResolutionContext(
    val document: Definition,
    val styleResolver: StyleResolver,
    val stateStore: StateStore,
    val tracker: DependencyTracker? = null,
    val parentViewNode: ViewNode? = null,
    val iterationVariables: Map<String, Any> = emptyMap(),
) {
    val isTracking: Boolean get() = tracker != null

    fun withParent(viewNode: ViewNode): ResolutionContext {
        return ResolutionContext(
            document = document,
            styleResolver = styleResolver,
            stateStore = stateStore,
            tracker = tracker,
            parentViewNode = viewNode,
            iterationVariables = iterationVariables,
        )
    }

    fun withIterationVariables(vars: Map<String, Any>): ResolutionContext {
        return ResolutionContext(
            document = document,
            styleResolver = styleResolver,
            stateStore = stateStore,
            tracker = tracker,
            parentViewNode = parentViewNode,
            iterationVariables = iterationVariables + vars,
        )
    }

    fun getValue(keypath: String): Any? {
        // Iteration variables take precedence
        iterationVariables[keypath]?.let { return it }

        // Check for nested access on iteration variables: "item.name"
        val dotIndex = keypath.indexOf('.')
        if (dotIndex > 0) {
            val root = keypath.substring(0, dotIndex)
            val rest = keypath.substring(dotIndex + 1)
            val iterVar = iterationVariables[root]
            if (iterVar != null) {
                if (iterVar is Map<*, *>) {
                    @Suppress("UNCHECKED_CAST")
                    return com.example.scalsandroid.scals.state.KeypathAccessor.get(
                        rest,
                        iterVar as Map<String, Any?>,
                    )
                }
                return null
            }
        }

        // Fall through to state store
        tracker?.recordRead(keypath)
        return stateStore.getValue(keypath)
    }

    fun interpolate(template: String): String {
        // Create a combined state reader that checks iteration vars first
        val combinedReader = object : StateValueReading {
            override fun getValue(keypath: String): Any? = this@ResolutionContext.getValue(keypath)
            override fun getArray(keypath: String): List<Any>? = stateStore.getArray(keypath)
            override fun arrayContains(keypath: String, value: Any): Boolean = stateStore.arrayContains(keypath, value)
            override fun getArrayCount(keypath: String): Int = stateStore.getArrayCount(keypath)
        }
        return ExpressionEvaluator.interpolate(template, combinedReader)
    }

    companion object {
        fun withoutTracking(
            document: Definition,
            stateStore: StateStore,
            designSystemProvider: DesignSystemProvider? = null,
        ): ResolutionContext {
            val styleResolver = StyleResolver(
                styles = document.styles ?: emptyMap(),
                designSystemProvider = designSystemProvider,
            )
            return ResolutionContext(
                document = document,
                styleResolver = styleResolver,
                stateStore = stateStore,
            )
        }

        fun withTracking(
            document: Definition,
            stateStore: StateStore,
            tracker: DependencyTracker,
            designSystemProvider: DesignSystemProvider? = null,
        ): ResolutionContext {
            val styleResolver = StyleResolver(
                styles = document.styles ?: emptyMap(),
                designSystemProvider = designSystemProvider,
            )
            return ResolutionContext(
                document = document,
                styleResolver = styleResolver,
                stateStore = stateStore,
                tracker = tracker,
            )
        }
    }
}
