package com.example.scalsandroid.scals.components.compose.renderers

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.scalsandroid.scals.components.compose.extensions.applyContainerStyle
import com.example.scalsandroid.scals.components.compose.extensions.toComposeColor
import com.example.scalsandroid.scals.components.compose.rendering.ComposeNodeRendering
import com.example.scalsandroid.scals.components.compose.rendering.ComposeRenderContext
import com.example.scalsandroid.scals.components.nodes.RenderNodeKinds
import com.example.scalsandroid.scals.components.nodes.TextFieldNode
import com.example.scalsandroid.scals.ir.RenderNode
import com.example.scalsandroid.scals.ir.RenderNodeKind

class TextFieldComposeRenderer : ComposeNodeRendering {
    override val nodeKind: RenderNodeKind = RenderNodeKinds.TEXT_FIELD

    @Composable
    override fun render(node: RenderNode, context: ComposeRenderContext) {
        val data = node.data<TextFieldNode>() ?: return
        val path = data.bindingPath ?: return

        val value = context.stateStore.getString(path)

        val modifier = Modifier.applyContainerStyle(
            padding = data.padding,
            backgroundColor = data.backgroundColor,
            cornerRadius = data.cornerRadius,
            border = data.border,
            shadow = data.shadow,
            width = data.width,
            height = data.height,
        )

        OutlinedTextField(
            value = value,
            onValueChange = { context.stateStore.set(path, it) },
            modifier = modifier,
            placeholder = if (data.placeholder.isNotEmpty()) {
                { Text(data.placeholder) }
            } else null,
        )
    }
}
