package com.example.scalsandroid.scals.ir.resolution

import com.example.scalsandroid.scals.document.Component
import com.example.scalsandroid.scals.document.ComponentKind
import com.example.scalsandroid.scals.ir.RenderNode
import com.example.scalsandroid.scals.viewtree.ViewNode

data class ComponentResolutionResult(
    val renderNode: RenderNode,
    val viewNode: ViewNode? = null,
) {
    companion object {
        fun renderOnly(node: RenderNode) = ComponentResolutionResult(node)
        fun withTracking(renderNode: RenderNode, viewNode: ViewNode) =
            ComponentResolutionResult(renderNode, viewNode)
    }
}

data class NodeResolutionResult(
    val renderNode: RenderNode,
    val viewNode: ViewNode? = null,
)

interface ComponentResolving {
    val componentKind: ComponentKind
    fun resolve(component: Component, context: ResolutionContext): ComponentResolutionResult
}
