package com.example.scalsandroid.scals.components.resolvers

import com.example.scalsandroid.scals.components.nodes.TextNode
import com.example.scalsandroid.scals.document.Component
import com.example.scalsandroid.scals.document.ComponentKind
import com.example.scalsandroid.scals.document.FontWeight
import com.example.scalsandroid.scals.document.TextAlignment
import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.ir.RenderNode
import com.example.scalsandroid.scals.ir.resolution.*
import com.example.scalsandroid.scals.ir.toIR
import com.example.scalsandroid.scals.viewtree.ViewNode
import java.util.UUID

class TextResolver : ComponentResolving {
    override val componentKind = ComponentKind("label")

    override fun resolve(component: Component, context: ResolutionContext): ComponentResolutionResult {
        val viewNode = beginTracking(component, context)

        val resolvedStyle = context.styleResolver.resolve(component.styleId, component.style)
        val contentResult = ContentResolver.resolve(component, context, viewNode)

        endTracking(context)

        val padding = IRInitializers.edgeInsetsFrom(component.padding, resolvedStyle)
        val shadow = IRInitializers.shadowFrom(resolvedStyle)
        val border = IRInitializers.borderFrom(resolvedStyle)

        val renderNode = RenderNode(TextNode(
            id = component.id,
            styleId = component.styleId,
            content = contentResult.content,
            bindingPath = contentResult.bindingPath,
            bindingTemplate = contentResult.bindingTemplate,
            padding = padding,
            textColor = resolvedStyle.textColor ?: IR.Color.black,
            fontSize = resolvedStyle.fontSize ?: 17.0,
            fontWeight = resolvedStyle.fontWeight ?: FontWeight.REGULAR,
            fontFamily = resolvedStyle.fontFamily,
            textAlignment = resolvedStyle.textAlignment ?: TextAlignment.LEADING,
            backgroundColor = resolvedStyle.backgroundColor,
            cornerRadius = resolvedStyle.cornerRadius ?: 0.0,
            shadow = shadow,
            border = border,
            width = component.width?.toIR() ?: resolvedStyle.width,
            height = component.height?.toIR() ?: resolvedStyle.height,
        ))

        return ComponentResolutionResult(renderNode, viewNode)
    }
}
