package com.example.scalsandroid.scals.components.resolvers

import com.example.scalsandroid.scals.components.nodes.ToggleNode
import com.example.scalsandroid.scals.document.Component
import com.example.scalsandroid.scals.document.ComponentKind
import com.example.scalsandroid.scals.ir.RenderNode
import com.example.scalsandroid.scals.ir.resolution.*
import com.example.scalsandroid.scals.ir.toIR

class ToggleResolver : ComponentResolving {
    override val componentKind = ComponentKind("toggle")

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

        val renderNode = RenderNode(ToggleNode(
            id = component.id,
            styleId = component.styleId,
            bindingPath = bindingPath,
            tintColor = resolvedStyle.tintColor,
            padding = padding,
            width = component.width?.toIR() ?: resolvedStyle.width,
            height = component.height?.toIR() ?: resolvedStyle.height,
        ))

        return ComponentResolutionResult(renderNode, viewNode)
    }
}
