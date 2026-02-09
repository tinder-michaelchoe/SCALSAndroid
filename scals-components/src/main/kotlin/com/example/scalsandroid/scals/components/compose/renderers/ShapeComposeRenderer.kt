package com.example.scalsandroid.scals.components.compose.renderers

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.example.scalsandroid.scals.components.compose.extensions.applyDimensions
import com.example.scalsandroid.scals.components.compose.extensions.toComposeColor
import com.example.scalsandroid.scals.components.compose.extensions.toPaddingValues
import com.example.scalsandroid.scals.components.compose.rendering.ComposeNodeRendering
import com.example.scalsandroid.scals.components.compose.rendering.ComposeRenderContext
import com.example.scalsandroid.scals.components.nodes.RenderNodeKinds
import com.example.scalsandroid.scals.components.nodes.ShapeNode
import com.example.scalsandroid.scals.ir.RenderNode
import com.example.scalsandroid.scals.ir.RenderNodeKind

class ShapeComposeRenderer : ComposeNodeRendering {
    override val nodeKind: RenderNodeKind = RenderNodeKinds.SHAPE

    @Composable
    override fun render(node: RenderNode, context: ComposeRenderContext) {
        val data = node.data<ShapeNode>() ?: return

        val shape: Shape = when (data.shapeType) {
            "circle" -> CircleShape
            "capsule" -> RoundedCornerShape(50)
            "roundedRectangle" -> RoundedCornerShape(8.dp)
            else -> RectangleShape
        }

        var modifier = Modifier as Modifier

        if (!data.padding.isEmpty) {
            modifier = modifier.padding(data.padding.toPaddingValues())
        }

        modifier = modifier.clip(shape)

        if (data.fillColor != null) {
            modifier = modifier.background(data.fillColor.toComposeColor(), shape)
        }

        if (data.strokeColor != null && data.strokeWidth > 0) {
            modifier = modifier.border(
                width = data.strokeWidth.dp,
                color = data.strokeColor.toComposeColor(),
                shape = shape,
            )
        }

        modifier = modifier.applyDimensions(width = data.width, height = data.height)

        Box(modifier = modifier)
    }
}
