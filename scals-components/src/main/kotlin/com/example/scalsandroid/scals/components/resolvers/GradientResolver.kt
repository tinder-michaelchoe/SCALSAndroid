package com.example.scalsandroid.scals.components.resolvers

import com.example.scalsandroid.scals.components.nodes.GradientColorStop
import com.example.scalsandroid.scals.components.nodes.GradientNode
import com.example.scalsandroid.scals.document.Component
import com.example.scalsandroid.scals.document.ComponentKind
import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.ir.RenderNode
import com.example.scalsandroid.scals.ir.resolution.*
import com.example.scalsandroid.scals.ir.toIR

class GradientResolver : ComponentResolving {
    override val componentKind = ComponentKind("gradient")

    override fun resolve(component: Component, context: ResolutionContext): ComponentResolutionResult {
        val viewNode = beginTracking(component, context)

        val resolvedStyle = context.styleResolver.resolve(component.styleId, component.style)

        endTracking(context)

        val padding = IRInitializers.edgeInsetsFrom(component.padding, resolvedStyle)

        val colorStops = component.gradient?.map { config ->
            val color = config.color?.let { IR.Color.fromHex(it) }
                ?: config.lightColor?.let { IR.Color.fromHex(it) }
                ?: IR.Color.clear
            GradientColorStop(color = color, location = config.location)
        } ?: emptyList()

        val renderNode = RenderNode(GradientNode(
            id = component.id,
            styleId = component.styleId,
            colors = colorStops,
            startPoint = IR.UnitPoint.top,
            endPoint = IR.UnitPoint.bottom,
            padding = padding,
            cornerRadius = resolvedStyle.cornerRadius ?: component.cornerRadius ?: 0.0,
            width = component.width?.toIR() ?: resolvedStyle.width,
            height = component.height?.toIR() ?: resolvedStyle.height,
        ))

        return ComponentResolutionResult(renderNode, viewNode)
    }
}
