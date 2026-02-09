package com.example.scalsandroid.scals.components.compose.renderers

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.scalsandroid.scals.components.compose.extensions.applyContainerStyle
import com.example.scalsandroid.scals.components.compose.extensions.toComposeColor
import com.example.scalsandroid.scals.components.compose.rendering.ComposeNodeRendering
import com.example.scalsandroid.scals.components.compose.rendering.ComposeRenderContext
import com.example.scalsandroid.scals.components.icons.IconResolver
import com.example.scalsandroid.scals.components.nodes.ImageNode
import com.example.scalsandroid.scals.components.nodes.RenderNodeKinds
import com.example.scalsandroid.scals.document.ImagePlaceholder
import com.example.scalsandroid.scals.ir.RenderNode
import com.example.scalsandroid.scals.ir.RenderNodeKind

/**
 * Helper function to render an ImagePlaceholder (used for loading/placeholder states)
 */
@Composable
private fun RenderImageSource(
    source: ImagePlaceholder,
    modifier: Modifier = Modifier,
    tintColor: com.example.scalsandroid.scals.ir.IR.Color? = null
) {
    when {
        source.activityIndicator == true -> {
            CircularProgressIndicator(
                modifier = modifier,
                color = tintColor?.toComposeColor() ?: Color.Unspecified
            )
        }
        else -> {
            val iconIdentifier = source.icon ?: source.sfsymbol
            val icon = IconResolver.resolve(iconIdentifier)

            if (icon != null) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    modifier = modifier,
                    tint = tintColor?.toComposeColor() ?: Color.Unspecified
                )
            }
        }
    }
}

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

        when {
            // Activity indicator takes priority
            data.source.activityIndicator == true -> {
                CircularProgressIndicator(
                    modifier = modifier,
                    color = data.tintColor?.toComposeColor() ?: Color.Unspecified
                )
            }
            // URL images with loading/placeholder support
            data.source.url != null -> {
                if (data.loading != null || data.placeholder != null) {
                    // Use SubcomposeAsyncImage for custom loading/placeholder
                    SubcomposeAsyncImage(
                        model = data.source.url,
                        contentDescription = null,
                        modifier = modifier,
                        contentScale = ContentScale.Fit,
                        loading = {
                            data.loading?.let { loadingSource ->
                                Box(contentAlignment = Alignment.Center) {
                                    RenderImageSource(loadingSource, modifier, data.tintColor)
                                }
                            }
                        },
                        error = {
                            data.placeholder?.let { placeholderSource ->
                                Box(contentAlignment = Alignment.Center) {
                                    RenderImageSource(placeholderSource, modifier, data.tintColor)
                                }
                            }
                        },
                        success = { SubcomposeAsyncImageContent() }
                    )
                } else {
                    // Simple AsyncImage without custom loading states
                    AsyncImage(
                        model = data.source.url,
                        contentDescription = null,
                        modifier = modifier,
                        contentScale = ContentScale.Fit,
                    )
                }
            }
            // Icon images
            else -> {
                val iconIdentifier = data.source.icon ?: data.source.sfsymbol
                val icon = IconResolver.resolve(iconIdentifier)

                if (icon != null) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        modifier = modifier,
                        tint = data.tintColor?.toComposeColor() ?: Color.Unspecified
                    )
                }
            }
        }
    }
}
