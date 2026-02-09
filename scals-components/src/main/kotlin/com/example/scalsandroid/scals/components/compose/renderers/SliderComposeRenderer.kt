package com.example.scalsandroid.scals.components.compose.renderers

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.scalsandroid.scals.components.compose.extensions.applyDimensions
import com.example.scalsandroid.scals.components.compose.extensions.toComposeColor
import com.example.scalsandroid.scals.components.compose.extensions.toPaddingValues
import com.example.scalsandroid.scals.components.compose.rendering.ComposeNodeRendering
import com.example.scalsandroid.scals.components.compose.rendering.ComposeRenderContext
import com.example.scalsandroid.scals.components.nodes.RenderNodeKinds
import com.example.scalsandroid.scals.components.nodes.SliderNode
import com.example.scalsandroid.scals.ir.RenderNode
import com.example.scalsandroid.scals.ir.RenderNodeKind

class SliderComposeRenderer : ComposeNodeRendering {
    override val nodeKind: RenderNodeKind = RenderNodeKinds.SLIDER

    @Composable
    override fun render(node: RenderNode, context: ComposeRenderContext) {
        val data = node.data<SliderNode>() ?: return
        val path = data.bindingPath ?: return

        val value = context.stateStore.getFloat(path)

        var modifier = Modifier as Modifier
        if (!data.padding.isEmpty) {
            modifier = modifier.padding(data.padding.toPaddingValues())
        }
        modifier = modifier.applyDimensions(width = data.width, height = data.height)

        val colors = if (data.tintColor != null) {
            SliderDefaults.colors(activeTrackColor = data.tintColor.toComposeColor())
        } else {
            SliderDefaults.colors()
        }

        val steps = data.step?.let {
            if (it > 0) {
                ((data.maxValue - data.minValue) / it).toInt() - 1
            } else 0
        } ?: 0

        Slider(
            value = value,
            onValueChange = { context.stateStore.set(path, it.toDouble()) },
            modifier = modifier,
            valueRange = data.minValue.toFloat()..data.maxValue.toFloat(),
            steps = steps,
            colors = colors,
        )
    }
}
