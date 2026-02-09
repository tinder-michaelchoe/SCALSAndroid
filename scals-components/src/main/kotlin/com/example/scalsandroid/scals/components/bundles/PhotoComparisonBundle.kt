package com.example.scalsandroid.scals.components.bundles

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.scalsandroid.scals.components.compose.rendering.ComposeNodeRendering
import com.example.scalsandroid.scals.components.compose.rendering.ComposeRenderContext
import com.example.scalsandroid.scals.components.manifests.ComponentBundle
import com.example.scalsandroid.scals.components.nodes.RenderNodeKinds
import com.example.scalsandroid.scals.components.resolvers.beginTracking
import com.example.scalsandroid.scals.components.resolvers.endTracking
import com.example.scalsandroid.scals.document.Component
import com.example.scalsandroid.scals.document.ComponentKind
import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.ir.RenderNode
import com.example.scalsandroid.scals.ir.RenderNodeData
import com.example.scalsandroid.scals.ir.RenderNodeKind
import com.example.scalsandroid.scals.ir.resolution.ComponentResolutionResult
import com.example.scalsandroid.scals.ir.resolution.ComponentResolving
import com.example.scalsandroid.scals.ir.resolution.ResolutionContext
import com.example.scalsandroid.scals.ir.toIR

/**
 * Custom component for photo before/after comparison with animated reveal.
 *
 * JSON usage:
 * ```json
 * {
 *   "type": "photoComparison",
 *   "beforeImage": "touchUpBefore",
 *   "afterImage": "touchUpAfter"
 * }
 * ```
 */
object PhotoComparisonBundle : ComponentBundle {
    override val componentKind = ComponentKind("photoComparison")
    override val nodeKind: RenderNodeKind = RenderNodeKinds.PHOTO_COMPARISON

    override fun makeResolver(): ComponentResolving = PhotoComparisonResolver()
    override fun makeComposeRenderer(): ComposeNodeRendering = PhotoComparisonRenderer()
}

class PhotoComparisonResolver : ComponentResolving {
    override val componentKind = ComponentKind("photoComparison")

    override fun resolve(component: Component, context: ResolutionContext): ComponentResolutionResult {
        val viewNode = beginTracking(component, context)

        val beforeImage = component.additionalProperties["beforeImage"]?.stringValue ?: ""
        val afterImage = component.additionalProperties["afterImage"]?.stringValue ?: ""
        val width = component.width?.toIR() ?: IR.DimensionValue.Absolute(260.0)
        val height = component.height?.toIR() ?: IR.DimensionValue.Absolute(350.0)

        endTracking(context)

        val renderNode = RenderNode(PhotoComparisonNode(
            id = component.id,
            styleId = component.styleId,
            beforeImage = beforeImage,
            afterImage = afterImage,
            width = width,
            height = height,
        ))

        return ComponentResolutionResult(renderNode, viewNode)
    }
}

class PhotoComparisonRenderer : ComposeNodeRendering {
    override val nodeKind: RenderNodeKind = RenderNodeKinds.PHOTO_COMPARISON

    @Composable
    override fun render(node: RenderNode, context: ComposeRenderContext) {
        val data = node.data<PhotoComparisonNode>() ?: return

        val widthDp = when (val w = data.width) {
            is IR.DimensionValue.Absolute -> w.value.dp
            else -> 260.dp
        }
        val heightDp = when (val h = data.height) {
            is IR.DimensionValue.Absolute -> h.value.dp
            else -> 350.dp
        }

        PhotoComparisonView(
            beforeImageName = data.beforeImage,
            afterImageName = data.afterImage,
            width = widthDp,
            height = heightDp
        )
    }
}

data class PhotoComparisonNode(
    override val id: String? = null,
    override val styleId: String? = null,
    val beforeImage: String,
    val afterImage: String,
    val width: IR.DimensionValue,
    val height: IR.DimensionValue,
) : RenderNodeData {
    override val nodeKind: RenderNodeKind get() = RenderNodeKinds.PHOTO_COMPARISON
}

@Composable
private fun PhotoComparisonView(
    beforeImageName: String,
    afterImageName: String,
    width: androidx.compose.ui.unit.Dp,
    height: androidx.compose.ui.unit.Dp
) {
    val infiniteTransition = rememberInfiniteTransition(label = "reveal")
    val revealProgress by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "reveal"
    )

    LaunchedEffect(Unit) {
        // Small delay before starting animation
        kotlinx.coroutines.delay(500)
    }

    Box(
        modifier = Modifier
            .size(width, height)
            .clip(RoundedCornerShape(28.dp))
    ) {
        // Before image (bottom layer)
        ImagePlaceholder(
            imageName = beforeImageName,
            modifier = Modifier.fillMaxSize()
        )

        // After image (top layer) with clip mask
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(revealProgress)
        ) {
            ImagePlaceholder(
                imageName = afterImageName,
                modifier = Modifier.fillMaxSize()
            )
        }

        // Vertical divider bar
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(2.dp)
                .offset(x = width * revealProgress)
                .shadow(2.dp)
                .background(Color.White)
        )

        // Comparison icon centered on divider
        Box(
            modifier = Modifier
                .size(36.dp)
                .offset(x = width * revealProgress - width / 2)
                .clip(CircleShape)
                .shadow(4.dp)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(2.dp),
                modifier = Modifier
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(12.dp)
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier.size(12.dp)
                )
            }
        }

        // Sparkle icon at top right
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = (-20).dp, y = 20.dp)
                .size(32.dp)
                .clip(CircleShape)
                .background(Color(0xFFF5F5F5)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = Color.Gray,
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Composable
private fun ImagePlaceholder(imageName: String, modifier: Modifier = Modifier) {
    // Show placeholder for now - images will be loaded from assets in future
    Box(
        modifier = modifier
            .background(
                androidx.compose.ui.graphics.Brush.linearGradient(
                    colors = listOf(
                        Color(0x4D999999),
                        Color(0x80999999)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        // Placeholder - images will be loaded in the future
    }
}

private val Double.dp: androidx.compose.ui.unit.Dp
    get() = androidx.compose.ui.unit.Dp(this.toFloat())

