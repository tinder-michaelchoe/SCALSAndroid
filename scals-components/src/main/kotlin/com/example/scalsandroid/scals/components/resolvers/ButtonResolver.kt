package com.example.scalsandroid.scals.components.resolvers

import com.example.scalsandroid.scals.components.nodes.*
import com.example.scalsandroid.scals.document.Component
import com.example.scalsandroid.scals.document.ComponentKind
import com.example.scalsandroid.scals.document.FontWeight
import com.example.scalsandroid.scals.document.Style
import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.ir.RenderNode
import com.example.scalsandroid.scals.ir.resolution.*
import com.example.scalsandroid.scals.ir.toIR
import com.example.scalsandroid.scals.state.StateValueConverter

class ButtonResolver : ComponentResolving {
    override val componentKind = ComponentKind("button")

    override fun resolve(component: Component, context: ResolutionContext): ComponentResolutionResult {
        val viewNode = beginTracking(component, context)

        val contentResult = ContentResolver.resolve(component, context, viewNode)
        val buttonStyles = resolveButtonStyles(component, context)

        // Handle isSelected binding
        val isSelectedBinding = component.binding?.also {
            context.tracker?.recordRead(it)
        }

        endTracking(context)

        val label = contentResult.content.ifEmpty { component.label ?: component.text ?: "" }

        // Parse imagePlacement from additionalProperties
        val imagePlacement = component.additionalProperties["imagePlacement"]?.stringValue?.let { placement ->
            when (placement.lowercase()) {
                "leading" -> ButtonImagePlacement.LEADING
                "trailing" -> ButtonImagePlacement.TRAILING
                "top" -> ButtonImagePlacement.TOP
                "bottom" -> ButtonImagePlacement.BOTTOM
                else -> ButtonImagePlacement.LEADING
            }
        } ?: ButtonImagePlacement.LEADING

        // Parse imageSpacing from additionalProperties
        val imageSpacing = component.additionalProperties["imageSpacing"]?.let {
            when (it) {
                is com.example.scalsandroid.scals.document.StateValue.IntValue -> it.value.toDouble()
                is com.example.scalsandroid.scals.document.StateValue.DoubleValue -> it.value
                else -> 8.0
            }
        } ?: 8.0

        val renderNode = RenderNode(ButtonNode(
            id = component.id,
            styleId = component.styleId,
            label = label,
            styles = buttonStyles,
            isSelectedBinding = isSelectedBinding,
            fillWidth = false,
            onTap = component.actions?.onTap,
            image = component.image,
            imagePlacement = imagePlacement,
            imageSpacing = imageSpacing,
        ))

        return ComponentResolutionResult(renderNode, viewNode)
    }

    private fun resolveButtonStyles(component: Component, context: ResolutionContext): ButtonStyles {
        val normal = resolveStateStyle(component, context, component.styles?.normal ?: component.style)
        val selected = component.styles?.selected?.let { resolveStateStyle(component, context, it) }
        val disabled = component.styles?.disabled?.let { resolveStateStyle(component, context, it) }

        return ButtonStyles(normal = normal, selected = selected, disabled = disabled)
    }

    private fun resolveStateStyle(
        component: Component,
        context: ResolutionContext,
        inlineStyle: Style?,
    ): ButtonStateStyle {
        val resolved = context.styleResolver.resolve(component.styleId, inlineStyle)
        val padding = IRInitializers.edgeInsetsFrom(component.padding, resolved)
        val shadow = IRInitializers.shadowFrom(resolved)
        val border = IRInitializers.borderFrom(resolved)

        return ButtonStateStyle(
            textColor = resolved.textColor ?: IR.Color.white,
            fontSize = resolved.fontSize ?: 17.0,
            fontWeight = resolved.fontWeight ?: FontWeight.SEMIBOLD,
            backgroundColor = resolved.backgroundColor,
            cornerRadius = resolved.cornerRadius ?: 0.0,
            border = border,
            shadow = shadow,
            tintColor = resolved.tintColor,
            width = component.width?.toIR() ?: resolved.width,
            height = component.height?.toIR() ?: resolved.height,
            minWidth = component.minWidth?.toIR() ?: resolved.minWidth,
            minHeight = component.minHeight?.toIR() ?: resolved.minHeight,
            maxWidth = component.maxWidth?.toIR() ?: resolved.maxWidth,
            maxHeight = component.maxHeight?.toIR() ?: resolved.maxHeight,
            padding = padding,
        )
    }
}
