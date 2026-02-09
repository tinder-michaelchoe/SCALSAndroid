package com.example.scalsandroid.scals.components.resolvers

import com.example.scalsandroid.scals.components.nodes.TextFieldNode
import com.example.scalsandroid.scals.document.Component
import com.example.scalsandroid.scals.document.ComponentKind
import com.example.scalsandroid.scals.document.FontWeight
import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.ir.RenderNode
import com.example.scalsandroid.scals.ir.resolution.*
import com.example.scalsandroid.scals.ir.toIR

class TextFieldResolver : ComponentResolving {
    override val componentKind = ComponentKind("textfield")

    override fun resolve(component: Component, context: ResolutionContext): ComponentResolutionResult {
        val viewNode = beginTracking(component, context)

        val resolvedStyle = context.styleResolver.resolve(component.styleId, component.style)

        // Resolve binding path - records both READ and WRITE
        val bindingPath = component.binding
        if (bindingPath != null) {
            context.tracker?.recordRead(bindingPath)
            context.tracker?.recordWrite(bindingPath)
        }

        endTracking(context)

        val padding = IRInitializers.edgeInsetsFrom(component.padding, resolvedStyle)
        val border = IRInitializers.borderFrom(resolvedStyle)
        val shadow = IRInitializers.shadowFrom(resolvedStyle)

        val renderNode = RenderNode(TextFieldNode(
            id = component.id,
            styleId = component.styleId,
            placeholder = component.placeholder ?: "",
            bindingPath = bindingPath,
            textColor = resolvedStyle.textColor ?: IR.Color.black,
            fontSize = resolvedStyle.fontSize ?: 17.0,
            fontWeight = resolvedStyle.fontWeight ?: FontWeight.REGULAR,
            backgroundColor = resolvedStyle.backgroundColor,
            cornerRadius = resolvedStyle.cornerRadius ?: component.cornerRadius ?: 0.0,
            border = border,
            shadow = shadow,
            padding = padding,
            width = component.width?.toIR() ?: resolvedStyle.width,
            height = component.height?.toIR() ?: resolvedStyle.height,
        ))

        return ComponentResolutionResult(renderNode, viewNode)
    }
}
