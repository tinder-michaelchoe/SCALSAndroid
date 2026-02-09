package com.example.scalsandroid.scals.components.compose.rendering

import androidx.compose.runtime.Composable
import com.example.scalsandroid.scals.actions.ActionContext
import com.example.scalsandroid.scals.components.compose.ComposeStateStore
import com.example.scalsandroid.scals.ir.RenderNode

class ComposeRenderContext(
    val stateStore: ComposeStateStore,
    val actionContext: ActionContext,
    val rendererRegistry: ComposeNodeRendererRegistry,
) {
    @Composable
    fun render(node: RenderNode) {
        rendererRegistry.render(node, this)
    }
}
