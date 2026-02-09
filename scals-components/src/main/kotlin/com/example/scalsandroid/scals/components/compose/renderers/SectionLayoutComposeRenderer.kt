package com.example.scalsandroid.scals.components.compose.renderers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.scalsandroid.scals.components.compose.extensions.applyContainerStyle
import com.example.scalsandroid.scals.components.compose.extensions.toPaddingValues
import com.example.scalsandroid.scals.components.compose.rendering.ComposeNodeRendering
import com.example.scalsandroid.scals.components.compose.rendering.ComposeRenderContext
import com.example.scalsandroid.scals.components.nodes.RenderNodeKinds
import com.example.scalsandroid.scals.components.nodes.SectionLayoutNode
import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.ir.RenderNode
import com.example.scalsandroid.scals.ir.RenderNodeKind

class SectionLayoutComposeRenderer : ComposeNodeRendering {
    override val nodeKind: RenderNodeKind = RenderNodeKinds.SECTION_LAYOUT

    @Composable
    override fun render(node: RenderNode, context: ComposeRenderContext) {
        val data = node.data<SectionLayoutNode>() ?: return

        var modifier = Modifier as Modifier
        if (!data.padding.isEmpty) {
            modifier = modifier.padding(data.padding.toPaddingValues())
        }
        if (data.backgroundColor != null) {
            modifier = Modifier.applyContainerStyle(backgroundColor = data.backgroundColor)
        }

        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(data.sectionSpacing.dp),
        ) {
            for (section in data.sections) {
                // Header
                section.header?.let { header ->
                    item(key = "${section.id ?: ""}_header") {
                        context.render(header)
                    }
                }

                // Children
                items(
                    items = section.children,
                    key = { child -> child.id ?: child.hashCode().toString() },
                ) { child ->
                    context.render(child)
                }

                // Footer
                section.footer?.let { footer ->
                    item(key = "${section.id ?: ""}_footer") {
                        context.render(footer)
                    }
                }
            }
        }
    }
}
