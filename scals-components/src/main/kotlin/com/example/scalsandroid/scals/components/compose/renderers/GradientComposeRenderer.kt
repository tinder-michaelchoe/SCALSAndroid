package com.example.scalsandroid.scals.components.compose.renderers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.example.scalsandroid.scals.components.compose.extensions.applyDimensions
import com.example.scalsandroid.scals.components.compose.extensions.toComposeColor
import com.example.scalsandroid.scals.components.compose.extensions.toOffset
import com.example.scalsandroid.scals.components.compose.extensions.toPaddingValues
import com.example.scalsandroid.scals.components.compose.rendering.ComposeNodeRendering
import com.example.scalsandroid.scals.components.compose.rendering.ComposeRenderContext
import com.example.scalsandroid.scals.components.nodes.GradientNode
import com.example.scalsandroid.scals.components.nodes.RenderNodeKinds
import com.example.scalsandroid.scals.ir.RenderNode
import com.example.scalsandroid.scals.ir.RenderNodeKind

class GradientComposeRenderer : ComposeNodeRendering {
    override val nodeKind: RenderNodeKind = RenderNodeKinds.GRADIENT

    @Composable
    override fun render(node: RenderNode, context: ComposeRenderContext) {
        val data = node.data<GradientNode>() ?: return

        val colorStops = data.colors.map { stop ->
            stop.location.toFloat() to stop.color.toComposeColor()
        }.toTypedArray()

        if (colorStops.isEmpty()) return

        val brush = Brush.linearGradient(
            colorStops = colorStops,
            start = data.startPoint.toOffset(),
            end = data.endPoint.toOffset(),
        )

        var modifier = Modifier as Modifier
        if (!data.padding.isEmpty) {
            modifier = modifier.padding(data.padding.toPaddingValues())
        }
        if (data.cornerRadius > 0) {
            modifier = modifier.clip(RoundedCornerShape(data.cornerRadius.dp))
        }
        modifier = modifier.background(brush)
        modifier = modifier.applyDimensions(width = data.width, height = data.height)

        Box(modifier = modifier)
    }
}
