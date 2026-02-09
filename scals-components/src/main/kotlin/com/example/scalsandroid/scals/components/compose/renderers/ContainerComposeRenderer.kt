package com.example.scalsandroid.scals.components.compose.renderers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.scalsandroid.scals.components.compose.extensions.applyContainerStyle
import com.example.scalsandroid.scals.components.compose.rendering.ComposeNodeRendering
import com.example.scalsandroid.scals.components.compose.rendering.ComposeRenderContext
import com.example.scalsandroid.scals.components.nodes.ContainerNode
import com.example.scalsandroid.scals.components.nodes.RenderNodeKinds
import com.example.scalsandroid.scals.components.nodes.SpacerNode
import com.example.scalsandroid.scals.document.LayoutType
import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.ir.RenderNode
import com.example.scalsandroid.scals.ir.RenderNodeKind

class ContainerComposeRenderer : ComposeNodeRendering {
    override val nodeKind: RenderNodeKind = RenderNodeKinds.CONTAINER

    @Composable
    override fun render(node: RenderNode, context: ComposeRenderContext) {
        val data = node.data<ContainerNode>() ?: return

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

        when (data.layoutType) {
            LayoutType.VSTACK -> {
                Column(
                    modifier = modifier,
                    verticalArrangement = Arrangement.spacedBy(data.spacing.dp),
                    horizontalAlignment = data.alignment.horizontal.toComposeHorizontalAlignment(),
                ) {
                    for (child in data.children) {
                        renderChildInColumn(child, context)
                    }
                }
            }
            LayoutType.HSTACK -> {
                Row(
                    modifier = modifier,
                    horizontalArrangement = Arrangement.spacedBy(data.spacing.dp),
                    verticalAlignment = data.alignment.vertical.toComposeVerticalAlignment(),
                ) {
                    for (child in data.children) {
                        renderChildInRow(child, context)
                    }
                }
            }
            LayoutType.ZSTACK -> {
                Box(
                    modifier = modifier,
                    contentAlignment = data.alignment.toComposeAlignment(),
                ) {
                    for (child in data.children) {
                        context.render(child)
                    }
                }
            }
        }
    }
}

private fun IR.HorizontalAlignment.toComposeHorizontalAlignment(): Alignment.Horizontal = when (this) {
    IR.HorizontalAlignment.LEADING -> Alignment.Start
    IR.HorizontalAlignment.CENTER -> Alignment.CenterHorizontally
    IR.HorizontalAlignment.TRAILING -> Alignment.End
}

private fun IR.VerticalAlignment.toComposeVerticalAlignment(): Alignment.Vertical = when (this) {
    IR.VerticalAlignment.TOP -> Alignment.Top
    IR.VerticalAlignment.CENTER -> Alignment.CenterVertically
    IR.VerticalAlignment.BOTTOM -> Alignment.Bottom
}

private fun IR.Alignment.toComposeAlignment(): Alignment = when {
    horizontal == IR.HorizontalAlignment.LEADING && vertical == IR.VerticalAlignment.TOP -> Alignment.TopStart
    horizontal == IR.HorizontalAlignment.CENTER && vertical == IR.VerticalAlignment.TOP -> Alignment.TopCenter
    horizontal == IR.HorizontalAlignment.TRAILING && vertical == IR.VerticalAlignment.TOP -> Alignment.TopEnd
    horizontal == IR.HorizontalAlignment.LEADING && vertical == IR.VerticalAlignment.CENTER -> Alignment.CenterStart
    horizontal == IR.HorizontalAlignment.CENTER && vertical == IR.VerticalAlignment.CENTER -> Alignment.Center
    horizontal == IR.HorizontalAlignment.TRAILING && vertical == IR.VerticalAlignment.CENTER -> Alignment.CenterEnd
    horizontal == IR.HorizontalAlignment.LEADING && vertical == IR.VerticalAlignment.BOTTOM -> Alignment.BottomStart
    horizontal == IR.HorizontalAlignment.CENTER && vertical == IR.VerticalAlignment.BOTTOM -> Alignment.BottomCenter
    horizontal == IR.HorizontalAlignment.TRAILING && vertical == IR.VerticalAlignment.BOTTOM -> Alignment.BottomEnd
    else -> Alignment.Center
}

/**
 * Render a child node in a Row context, applying weight modifiers for spacers
 */
@Composable
private fun RowScope.renderChildInRow(child: RenderNode, context: ComposeRenderContext) {
    val spacerData = child.data<SpacerNode>()

    if (spacerData != null) {
        // Determine weight for spacer
        val weight = when (val w = spacerData.width) {
            is IR.DimensionValue.Fractional -> w.value.toFloat()
            is IR.DimensionValue.Absolute -> null // Fixed width, no weight
            null -> 1f // No width specified = flexible spacer (like SwiftUI)
        }

        if (weight != null) {
            // Apply weight modifier in RowScope for flexible spacers
            Box(modifier = Modifier.weight(weight)) {
                context.render(child)
            }
        } else {
            // Fixed width spacer
            context.render(child)
        }
    } else {
        // Not a spacer - regular rendering
        context.render(child)
    }
}

/**
 * Render a child node in a Column context, applying weight modifiers for spacers
 */
@Composable
private fun ColumnScope.renderChildInColumn(child: RenderNode, context: ComposeRenderContext) {
    val spacerData = child.data<SpacerNode>()

    if (spacerData != null) {
        // Determine weight for spacer
        val weight = when (val h = spacerData.height) {
            is IR.DimensionValue.Fractional -> h.value.toFloat()
            is IR.DimensionValue.Absolute -> null // Fixed height, no weight
            null -> 1f // No height specified = flexible spacer (like SwiftUI)
        }

        if (weight != null) {
            // Apply weight modifier in ColumnScope for flexible spacers
            Box(modifier = Modifier.weight(weight)) {
                context.render(child)
            }
        } else {
            // Fixed height spacer
            context.render(child)
        }
    } else {
        // Not a spacer - regular rendering
        context.render(child)
    }
}
