package com.example.scalsandroid.scals.components.compose.renderers

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.scalsandroid.scals.components.compose.extensions.applyContainerStyle
import com.example.scalsandroid.scals.components.compose.extensions.toComposeColor
import com.example.scalsandroid.scals.components.compose.extensions.toComposeFontWeight
import com.example.scalsandroid.scals.components.compose.extensions.toComposeTextAlign
import com.example.scalsandroid.scals.components.compose.rendering.ComposeNodeRendering
import com.example.scalsandroid.scals.components.compose.rendering.ComposeRenderContext
import com.example.scalsandroid.scals.components.nodes.RenderNodeKinds
import com.example.scalsandroid.scals.components.nodes.TextNode
import com.example.scalsandroid.scals.ir.RenderNode
import com.example.scalsandroid.scals.ir.RenderNodeKind

class TextComposeRenderer : ComposeNodeRendering {
    override val nodeKind: RenderNodeKind = RenderNodeKinds.TEXT

    @Composable
    override fun render(node: RenderNode, context: ComposeRenderContext) {
        val data = node.data<TextNode>() ?: return

        val displayText = when {
            data.bindingTemplate != null -> context.stateStore.interpolate(data.bindingTemplate)
            data.bindingPath != null -> context.stateStore.getString(data.bindingPath)
            else -> data.content
        }

        val modifier = Modifier.applyContainerStyle(
            padding = data.padding,
            backgroundColor = data.backgroundColor,
            cornerRadius = data.cornerRadius,
            border = data.border,
            shadow = data.shadow,
            width = data.width,
            height = data.height,
        )

        Text(
            text = displayText,
            modifier = modifier,
            color = data.textColor.toComposeColor(),
            fontSize = data.fontSize.sp,
            fontWeight = data.fontWeight.toComposeFontWeight(),
            textAlign = data.textAlignment.toComposeTextAlign(),
        )
    }
}
