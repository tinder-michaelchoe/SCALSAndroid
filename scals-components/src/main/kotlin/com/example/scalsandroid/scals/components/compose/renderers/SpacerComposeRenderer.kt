package com.example.scalsandroid.scals.components.compose.renderers

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.scalsandroid.scals.components.compose.rendering.ComposeNodeRendering
import com.example.scalsandroid.scals.components.compose.rendering.ComposeRenderContext
import com.example.scalsandroid.scals.components.nodes.RenderNodeKinds
import com.example.scalsandroid.scals.components.nodes.SpacerNode
import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.ir.RenderNode
import com.example.scalsandroid.scals.ir.RenderNodeKind

class SpacerComposeRenderer : ComposeNodeRendering {
    override val nodeKind: RenderNodeKind = RenderNodeKinds.SPACER

    @Composable
    override fun render(node: RenderNode, context: ComposeRenderContext) {
        val data = node.data<SpacerNode>() ?: return

        var modifier = Modifier as Modifier

        // Handle width - fractional widths are handled by parent container with weight()
        when (val w = data.width) {
            is IR.DimensionValue.Absolute -> modifier = modifier.width(w.value.dp)
            is IR.DimensionValue.Fractional -> {} // Weight applied by parent container
            null -> {}
        }

        // Handle height - fractional heights are handled by parent container with weight()
        when (val h = data.height) {
            is IR.DimensionValue.Absolute -> modifier = modifier.height(h.value.dp)
            is IR.DimensionValue.Fractional -> {} // Weight applied by parent container
            null -> {}
        }

        data.minLength?.let { modifier = modifier.defaultMinSize(minHeight = it.dp) }

        Spacer(modifier = modifier)
    }
}
