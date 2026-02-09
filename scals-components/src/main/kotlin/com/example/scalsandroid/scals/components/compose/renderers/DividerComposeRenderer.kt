package com.example.scalsandroid.scals.components.compose.renderers

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.scalsandroid.scals.components.compose.extensions.toComposeColor
import com.example.scalsandroid.scals.components.compose.extensions.toPaddingValues
import com.example.scalsandroid.scals.components.compose.rendering.ComposeNodeRendering
import com.example.scalsandroid.scals.components.compose.rendering.ComposeRenderContext
import com.example.scalsandroid.scals.components.nodes.DividerNode
import com.example.scalsandroid.scals.components.nodes.RenderNodeKinds
import com.example.scalsandroid.scals.ir.RenderNode
import com.example.scalsandroid.scals.ir.RenderNodeKind

class DividerComposeRenderer : ComposeNodeRendering {
    override val nodeKind: RenderNodeKind = RenderNodeKinds.DIVIDER

    @Composable
    override fun render(node: RenderNode, context: ComposeRenderContext) {
        val data = node.data<DividerNode>() ?: return

        var modifier = Modifier as Modifier
        if (!data.padding.isEmpty) {
            modifier = modifier.padding(data.padding.toPaddingValues())
        }

        HorizontalDivider(
            modifier = modifier,
            thickness = data.thickness.dp,
            color = data.color.toComposeColor(),
        )
    }
}
