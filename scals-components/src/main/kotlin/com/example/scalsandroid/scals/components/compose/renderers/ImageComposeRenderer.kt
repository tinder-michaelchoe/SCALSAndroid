package com.example.scalsandroid.scals.components.compose.renderers

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.scalsandroid.scals.components.compose.extensions.applyContainerStyle
import com.example.scalsandroid.scals.components.compose.rendering.ComposeNodeRendering
import com.example.scalsandroid.scals.components.compose.rendering.ComposeRenderContext
import com.example.scalsandroid.scals.components.nodes.ImageNode
import com.example.scalsandroid.scals.components.nodes.RenderNodeKinds
import com.example.scalsandroid.scals.ir.RenderNode
import com.example.scalsandroid.scals.ir.RenderNodeKind

class ImageComposeRenderer : ComposeNodeRendering {
    override val nodeKind: RenderNodeKind = RenderNodeKinds.IMAGE

    @Composable
    override fun render(node: RenderNode, context: ComposeRenderContext) {
        val data = node.data<ImageNode>() ?: return

        val modifier = Modifier.applyContainerStyle(
            padding = data.padding,
            backgroundColor = data.backgroundColor,
            cornerRadius = data.cornerRadius,
            border = data.border,
            shadow = data.shadow,
            width = data.width,
            height = data.height,
            minWidth = data.minWidth,
            minHeight = data.minHeight,
            maxWidth = data.maxWidth,
            maxHeight = data.maxHeight,
        )

        val url = data.source.url
        if (url != null) {
            AsyncImage(
                model = url,
                contentDescription = null,
                modifier = modifier,
                contentScale = ContentScale.Fit,
            )
        }
        // Named assets (sfsymbol/asset) are not rendered in this MVP
    }
}
