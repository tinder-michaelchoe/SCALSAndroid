package com.example.scalsandroid.scals.components.compose.renderers

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.scalsandroid.scals.components.compose.extensions.applyContainerStyle
import com.example.scalsandroid.scals.components.compose.extensions.applyDimensions
import com.example.scalsandroid.scals.components.compose.extensions.toComposeColor
import com.example.scalsandroid.scals.components.compose.extensions.toComposeFontWeight
import com.example.scalsandroid.scals.components.compose.rendering.ComposeNodeRendering
import com.example.scalsandroid.scals.components.compose.rendering.ComposeRenderContext
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
        ) {
            Text(
                text = data.label,
                color = style.textColor.toComposeColor(),
                fontSize = style.fontSize.sp,
                fontWeight = style.fontWeight.toComposeFontWeight(),
            )
        }
    }
}

private val Double.dp: androidx.compose.ui.unit.Dp
    get() = androidx.compose.ui.unit.Dp(this.toFloat())
