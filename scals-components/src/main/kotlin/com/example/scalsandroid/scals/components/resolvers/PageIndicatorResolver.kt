package com.example.scalsandroid.scals.components.resolvers

import com.example.scalsandroid.scals.components.nodes.PageIndicatorNode
import com.example.scalsandroid.scals.document.Component
import com.example.scalsandroid.scals.document.ComponentKind
import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.ir.RenderNode
import com.example.scalsandroid.scals.ir.resolution.*

class PageIndicatorResolver : ComponentResolving {
    override val componentKind = ComponentKind("pageindicator")

    override fun resolve(component: Component, context: ResolutionContext): ComponentResolutionResult {
        val viewNode = beginTracking(component, context)

        val resolvedStyle = context.styleResolver.resolve(component.styleId, component.style)

        val pageCountStr = component.additionalProperties["pageCount"]
        val pageCount = pageCountStr?.intValue?.toInt() ?: 0

        val currentPageBinding = component.binding
        if (currentPageBinding != null) {
            context.tracker?.recordRead(currentPageBinding)
            context.tracker?.recordWrite(currentPageBinding)
        }

        endTracking(context)

        val padding = IRInitializers.edgeInsetsFrom(component.padding, resolvedStyle)
        val currentColorHex = component.additionalProperties["currentColor"]?.stringValue
        val otherColorHex = component.additionalProperties["otherColor"]?.stringValue

        val renderNode = RenderNode(PageIndicatorNode(
            id = component.id,
            styleId = component.styleId,
            pageCount = pageCount,
            currentPageBinding = currentPageBinding,
            currentColor = currentColorHex?.let { IR.Color.fromHex(it) } ?: IR.Color.black,
            otherColor = otherColorHex?.let { IR.Color.fromHex(it) } ?: IR.Color(0.0, 0.0, 0.0, 0.3),
            padding = padding,
        ))

        return ComponentResolutionResult(renderNode, viewNode)
    }
}
