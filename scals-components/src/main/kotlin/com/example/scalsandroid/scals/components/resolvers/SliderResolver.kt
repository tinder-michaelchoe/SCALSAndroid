package com.example.scalsandroid.scals.components.resolvers

import com.example.scalsandroid.scals.components.nodes.SliderNode
import com.example.scalsandroid.scals.document.Component
import com.example.scalsandroid.scals.document.ComponentKind
import com.example.scalsandroid.scals.ir.RenderNode
import com.example.scalsandroid.scals.ir.resolution.*
import com.example.scalsandroid.scals.ir.toIR
import com.example.scalsandroid.scals.state.StateValueConverter

class SliderResolver : ComponentResolving {
    override val componentKind = ComponentKind("slider")

    override fun resolve(component: Component, context: ResolutionContext): ComponentResolutionResult {
        val viewNode = beginTracking(component, context)

        val resolvedStyle = context.styleResolver.resolve(component.styleId, component.style)

        val bindingPath = component.binding
        if (bindingPath != null) {
            context.tracker?.recordRead(bindingPath)
            context.tracker?.recordWrite(bindingPath)
        }

        endTracking(context)

        val padding = IRInitializers.edgeInsetsFrom(component.padding, resolvedStyle)

        val minValue = component.additionalProperties["minValue"]?.doubleValue
            ?: component.additionalProperties["minValue"]?.intValue?.toDouble()
            ?: 0.0
        val maxValue = component.additionalProperties["maxValue"]?.doubleValue
            ?: component.additionalProperties["maxValue"]?.intValue?.toDouble()
            ?: 1.0
        val step = component.additionalProperties["step"]?.doubleValue
            ?: component.additionalProperties["step"]?.intValue?.toDouble()

        val renderNode = RenderNode(SliderNode(
            id = component.id,
            styleId = component.styleId,
            bindingPath = bindingPath,
            minValue = minValue,
            maxValue = maxValue,
            step = step,
            tintColor = resolvedStyle.tintColor,
            padding = padding,
            width = component.width?.toIR() ?: resolvedStyle.width,
            height = component.height?.toIR() ?: resolvedStyle.height,
        ))

        return ComponentResolutionResult(renderNode, viewNode)
    }
}
