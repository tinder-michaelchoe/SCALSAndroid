package com.example.scalsandroid.scals.components.compose.rendering

import androidx.compose.runtime.Composable
import com.example.scalsandroid.scals.ir.RenderNode
import com.example.scalsandroid.scals.ir.RenderNodeKind

class ComposeNodeRendererRegistry {

    private val renderers: MutableMap<RenderNodeKind, ComposeNodeRendering> = mutableMapOf()

    fun register(renderer: ComposeNodeRendering) {
        renderers[renderer.nodeKind] = renderer
    }

    @Composable
    fun render(node: RenderNode, context: ComposeRenderContext) {
        val renderer = renderers[node.kind]
        if (renderer != null) {
            renderer.render(node, context)
        }
        // Unknown node kinds are silently ignored (rendered as empty)
    }
}
