package com.example.scalsandroid.scals.components.compose.renderers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scalsandroid.scals.components.compose.extensions.applyContainerStyle
import com.example.scalsandroid.scals.components.compose.extensions.applyDimensions
import com.example.scalsandroid.scals.components.compose.extensions.toComposeColor
import com.example.scalsandroid.scals.components.compose.extensions.toComposeFontWeight
import com.example.scalsandroid.scals.components.compose.rendering.ComposeNodeRendering
import com.example.scalsandroid.scals.components.compose.rendering.ComposeRenderContext
import com.example.scalsandroid.scals.components.icons.IconResolver
import com.example.scalsandroid.scals.components.nodes.ButtonImagePlacement
import com.example.scalsandroid.scals.components.nodes.ButtonNode
import com.example.scalsandroid.scals.components.nodes.RenderNodeKinds
import com.example.scalsandroid.scals.ir.RenderNode
import com.example.scalsandroid.scals.ir.RenderNodeKind

class ButtonComposeRenderer : ComposeNodeRendering {
    override val nodeKind: RenderNodeKind = RenderNodeKinds.BUTTON

    @Composable
    override fun render(node: RenderNode, context: ComposeRenderContext) {
        val data = node.data<ButtonNode>() ?: return

        val isSelected = data.isSelectedBinding?.let {
            context.stateStore.getBoolean(it)
        } ?: false

        val style = data.styles.style(isSelected)

        var modifier = Modifier.applyContainerStyle(
            padding = style.padding,
            shadow = style.shadow,
            width = style.width,
            height = style.height,
            minWidth = style.minWidth,
            minHeight = style.minHeight,
            maxWidth = style.maxWidth,
            maxHeight = style.maxHeight,
        )

        if (data.fillWidth) {
            modifier = modifier.fillMaxWidth()
        }

        val containerColor = style.backgroundColor?.toComposeColor()
            ?: ButtonDefaults.buttonColors().containerColor

        // Use minimal contentPadding for icon-only buttons
        val hasLabel = data.label.isNotEmpty()
        val contentPadding = if (!hasLabel && data.image != null) {
            PaddingValues(0.dp)
        } else {
            ButtonDefaults.ContentPadding
        }

        Button(
            onClick = {
                data.onTap?.let { binding ->
                    context.actionContext.executeBinding(binding)
                }
            },
            modifier = modifier,
            colors = ButtonDefaults.buttonColors(containerColor = containerColor),
            shape = when {
                style.cornerRadius > 0 -> androidx.compose.foundation.shape.RoundedCornerShape(
                    style.cornerRadius.dp
                )
                else -> ButtonDefaults.shape
            },
            contentPadding = contentPadding,
        ) {
            // Prioritize icon over sfsymbol for cross-platform compatibility
            val iconIdentifier = data.image?.icon ?: data.image?.sfsymbol
            val icon = IconResolver.resolve(iconIdentifier)
            val hasLabel = data.label.isNotEmpty()

            if (data.imagePlacement == ButtonImagePlacement.TOP || data.imagePlacement == ButtonImagePlacement.BOTTOM) {
                // Vertical layout for TOP and BOTTOM placements
                Column(
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    // Top icon
                    if (icon != null && data.imagePlacement == ButtonImagePlacement.TOP) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                            tint = style.tintColor?.toComposeColor() ?: style.textColor.toComposeColor()
                        )
                        if (hasLabel) {
                            Spacer(modifier = Modifier.height(data.imageSpacing.dp))
                        }
                    }

                    // Text label
                    if (hasLabel) {
                        Text(
                            text = data.label,
                            color = style.textColor.toComposeColor(),
                            fontSize = style.fontSize.sp,
                            fontWeight = style.fontWeight.toComposeFontWeight(),
                        )
                    }

                    // Bottom icon
                    if (icon != null && data.imagePlacement == ButtonImagePlacement.BOTTOM) {
                        if (hasLabel) {
                            Spacer(modifier = Modifier.height(data.imageSpacing.dp))
                        }
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                            tint = style.tintColor?.toComposeColor() ?: style.textColor.toComposeColor()
                        )
                    }
                }
            } else {
                // Horizontal layout for LEADING and TRAILING placements
                // For icon-only buttons, center the content
                Row(
                    horizontalArrangement = if (!hasLabel && icon != null) {
                        Arrangement.Center
                    } else {
                        when (data.imagePlacement) {
                            ButtonImagePlacement.LEADING -> Arrangement.Start
                            ButtonImagePlacement.TRAILING -> Arrangement.End
                            else -> Arrangement.Start
                        }
                    },
                    verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
                ) {
                    // For icon-only buttons, show icon regardless of placement
                    // For buttons with labels, respect placement
                    val showLeadingIcon = icon != null && (!hasLabel || data.imagePlacement == ButtonImagePlacement.LEADING)
                    val showTrailingIcon = icon != null && hasLabel && data.imagePlacement == ButtonImagePlacement.TRAILING

                    // Leading/Only icon
                    if (showLeadingIcon) {
                        Icon(
                            imageVector = icon,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                            tint = style.tintColor?.toComposeColor() ?: style.textColor.toComposeColor()
                        )
                        if (hasLabel) {
                            Spacer(modifier = Modifier.width(data.imageSpacing.dp))
                        }
                    }

                    // Text label
                    if (hasLabel) {
                        Text(
                            text = data.label,
                            color = style.textColor.toComposeColor(),
                            fontSize = style.fontSize.sp,
                            fontWeight = style.fontWeight.toComposeFontWeight(),
                        )
                    }

                    // Trailing icon
                    if (showTrailingIcon) {
                        Spacer(modifier = Modifier.width(data.imageSpacing.dp))
                        Icon(
                            imageVector = icon!!,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                            tint = style.tintColor?.toComposeColor() ?: style.textColor.toComposeColor()
                        )
                    }
                }
            }
        }
    }
}

private val Double.dp: androidx.compose.ui.unit.Dp
    get() = androidx.compose.ui.unit.Dp(this.toFloat())
