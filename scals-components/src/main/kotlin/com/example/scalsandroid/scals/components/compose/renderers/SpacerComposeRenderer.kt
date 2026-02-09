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
import com.example.scalsandroid.scals.ir.RenderNode
import com.example.scalsandroid.scals.ir.RenderNodeKind

class SpacerComposeRenderer : ComposeNodeRendering {
    override val nodeKind: RenderNodeKind = RenderNodeKinds.SPACER

    @Composable
    override fun render(node: RenderNode, context: ComposeRenderContext) {
        val data = node.data<SpacerNode>() ?: return

        var modifier = Modifier as Modifier

        data.height?.let { modifier = modifier.height(it.dp) }
        data.width?.let { modifier = modifier.width(it.dp) }
        data.minLength?.let { modifier = modifier.defaultMinSize(minHeight = it.dp) }

        Spacer(modifier = modifier)
    }
}
