package com.example.scalsandroid.scals.components.compose.rendering

import androidx.compose.runtime.Composable
import com.example.scalsandroid.scals.ir.RenderNode
import com.example.scalsandroid.scals.ir.RenderNodeKind

interface ComposeNodeRendering {
    val nodeKind: RenderNodeKind

    @Composable
    fun render(node: RenderNode, context: ComposeRenderContext)
}
