package com.example.scalsandroid.scals.components.resolvers

import com.example.scalsandroid.scals.components.nodes.ImageNode
import com.example.scalsandroid.scals.document.Component
import com.example.scalsandroid.scals.document.ComponentKind
import com.example.scalsandroid.scals.document.ImageSource
import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.ir.RenderNode
import com.example.scalsandroid.scals.ir.resolution.*
import com.example.scalsandroid.scals.ir.toIR

class ImageResolver : ComponentResolving {
    override val componentKind = ComponentKind("image")

    override fun resolve(component: Component, context: ResolutionContext): ComponentResolutionResult {
        val viewNode = beginTracking(component, context)

        val resolvedStyle = context.styleResolver.resolve(component.styleId, component.style)

        endTracking(context)

        val padding = IRInitializers.edgeInsetsFrom(component.padding, resolvedStyle)
        val shadow = IRInitializers.shadowFrom(resolvedStyle)
        val border = IRInitializers.borderFrom(resolvedStyle)

        val source = component.image ?: ImageSource()

        val renderNode = RenderNode(ImageNode(
            id = component.id,
            styleId = component.styleId,
            source = source,
            placeholder = source.placeholder?.let { ImageSource(sfsymbol = it.sfsymbol, url = it.url, asset = it.asset) },
            onTap = component.actions?.onTap,
            tintColor = resolvedStyle.tintColor,
            backgroundColor = resolvedStyle.backgroundColor,
            cornerRadius = resolvedStyle.cornerRadius ?: component.cornerRadius ?: 0.0,
            border = border,
            shadow = shadow,
            padding = padding,
            width = component.width?.toIR() ?: resolvedStyle.width,
            height = component.height?.toIR() ?: resolvedStyle.height,
            minWidth = component.minWidth?.toIR() ?: resolvedStyle.minWidth,
            minHeight = component.minHeight?.toIR() ?: resolvedStyle.minHeight,
            maxWidth = component.maxWidth?.toIR() ?: resolvedStyle.maxWidth,
            maxHeight = component.maxHeight?.toIR() ?: resolvedStyle.maxHeight,
        ))

        return ComponentResolutionResult(renderNode, viewNode)
    }
}
