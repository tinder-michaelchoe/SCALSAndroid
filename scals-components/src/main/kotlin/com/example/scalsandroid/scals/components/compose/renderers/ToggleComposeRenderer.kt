package com.example.scalsandroid.scals.components.compose.renderers

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.scalsandroid.scals.components.compose.extensions.applyDimensions
import com.example.scalsandroid.scals.components.compose.extensions.toComposeColor
import com.example.scalsandroid.scals.components.compose.extensions.toPaddingValues
import com.example.scalsandroid.scals.components.compose.rendering.ComposeNodeRendering
import com.example.scalsandroid.scals.components.compose.rendering.ComposeRenderContext
import com.example.scalsandroid.scals.components.nodes.RenderNodeKinds
import com.example.scalsandroid.scals.components.nodes.ToggleNode
import com.example.scalsandroid.scals.ir.RenderNode
import com.example.scalsandroid.scals.ir.RenderNodeKind

class ToggleComposeRenderer : ComposeNodeRendering {
    override val nodeKind: RenderNodeKind = RenderNodeKinds.TOGGLE

    @Composable
    override fun render(node: RenderNode, context: ComposeRenderContext) {
        val data = node.data<ToggleNode>() ?: return
        val path = data.bindingPath ?: return

        val checked = context.stateStore.getBoolean(path)

        var modifier = Modifier as Modifier
        if (!data.padding.isEmpty) {
            modifier = modifier.padding(data.padding.toPaddingValues())
        }
        modifier = modifier.applyDimensions(width = data.width, height = data.height)

        val colors = if (data.tintColor != null) {
            SwitchDefaults.colors(checkedTrackColor = data.tintColor.toComposeColor())
        } else {
            SwitchDefaults.colors()
        }

        Switch(
            checked = checked,
            onCheckedChange = { context.stateStore.set(path, it) },
            modifier = modifier,
            colors = colors,
        )
    }
}
