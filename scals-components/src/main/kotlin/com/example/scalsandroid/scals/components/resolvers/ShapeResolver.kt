package com.example.scalsandroid.scals.components.resolvers

import com.example.scalsandroid.scals.components.nodes.ShapeNode
import com.example.scalsandroid.scals.document.Component
import com.example.scalsandroid.scals.document.ComponentKind
import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.ir.RenderNode
import com.example.scalsandroid.scals.ir.resolution.*
import com.example.scalsandroid.scals.ir.toIR

class ShapeResolver : ComponentResolving {
    override val componentKind = ComponentKind("shape")

    override fun resolve(component: Component, context: ResolutionContext): ComponentResolutionResult {
        val viewNode = beginTracking(component, context)

        val resolvedStyle = context.styleResolver.resolve(component.styleId, component.style)

        endTracking(context)

        val padding = IRInitializers.edgeInsetsFrom(component.padding, resolvedStyle)

        val shapeType = component.additionalProperties["shapeType"]?.stringValue ?: "rectangle"
        val fillColorHex = component.additionalProperties["fillColor"]?.stringValue
            ?: component.foregroundColor
        val strokeColorHex = component.additionalProperties["strokeColor"]?.stringValue
        val strokeWidth = component.additionalProperties["strokeWidth"]?.doubleValue
            ?: component.additionalProperties["strokeWidth"]?.intValue?.toDouble()
            ?: 0.0

        val renderNode = RenderNode(ShapeNode(
            id = component.id,
            styleId = component.styleId,
            shapeType = shapeType,
            fillColor = fillColorHex?.let { IR.Color.fromHex(it) },
            strokeColor = strokeColorHex?.let { IR.Color.fromHex(it) },
            strokeWidth = strokeWidth,
            padding = padding,
            width = component.width?.toIR() ?: resolvedStyle.width,
            height = component.height?.toIR() ?: resolvedStyle.height,
        ))

        return ComponentResolutionResult(renderNode, viewNode)
    }
}
