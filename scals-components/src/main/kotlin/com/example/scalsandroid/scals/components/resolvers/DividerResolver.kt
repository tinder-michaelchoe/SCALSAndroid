package com.example.scalsandroid.scals.components.resolvers

import com.example.scalsandroid.scals.components.nodes.DividerNode
import com.example.scalsandroid.scals.document.Component
import com.example.scalsandroid.scals.document.ComponentKind
import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.ir.RenderNode
import com.example.scalsandroid.scals.ir.resolution.*

class DividerResolver : ComponentResolving {
    override val componentKind = ComponentKind("divider")

    override fun resolve(component: Component, context: ResolutionContext): ComponentResolutionResult {
        val viewNode = beginTracking(component, context)

        val resolvedStyle = context.styleResolver.resolve(component.styleId, component.style)

        endTracking(context)

        val padding = IRInitializers.edgeInsetsFrom(component.padding, resolvedStyle)
        val colorHex = component.foregroundColor ?: component.additionalProperties["color"]?.stringValue
        val thickness = component.additionalProperties["thickness"]?.doubleValue
            ?: component.additionalProperties["thickness"]?.intValue?.toDouble()
            ?: 1.0

        val renderNode = RenderNode(DividerNode(
            id = component.id,
            styleId = component.styleId,
            color = colorHex?.let { IR.Color.fromHex(it) } ?: IR.Color(0.0, 0.0, 0.0, 0.2),
            thickness = thickness,
            padding = padding,
        ))

        return ComponentResolutionResult(renderNode, viewNode)
    }
}
