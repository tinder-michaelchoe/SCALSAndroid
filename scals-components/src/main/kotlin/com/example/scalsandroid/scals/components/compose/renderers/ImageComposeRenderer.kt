package com.example.scalsandroid.scals.components.compose.renderers

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.scalsandroid.scals.components.compose.extensions.applyContainerStyle
import com.example.scalsandroid.scals.components.compose.extensions.toComposeColor
import com.example.scalsandroid.scals.components.compose.rendering.ComposeNodeRendering
import com.example.scalsandroid.scals.components.compose.rendering.ComposeRenderContext
import com.example.scalsandroid.scals.components.icons.IconResolver
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

        // Prioritize icon over sfsymbol for cross-platform compatibility
        val iconIdentifier = data.source.icon ?: data.source.sfsymbol
        val icon = IconResolver.resolve(iconIdentifier)

        when {
            icon != null -> {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = modifier,
                    tint = data.tintColor?.toComposeColor() ?: Color.Unspecified
                )
            }
            data.source.url != null -> {
                AsyncImage(
                    model = data.source.url,
                    contentDescription = null,
                    modifier = modifier,
                    contentScale = ContentScale.Fit,
                )
            }
        }
    }
}
