package com.example.scalsandroid.scals.components.bundles

import com.example.scalsandroid.scals.components.compose.rendering.ComposeNodeRendering
import com.example.scalsandroid.scals.components.compose.renderers.SectionLayoutComposeRenderer
import com.example.scalsandroid.scals.components.manifests.LayoutBundle
import com.example.scalsandroid.scals.components.nodes.RenderNodeKinds
import com.example.scalsandroid.scals.ir.RenderNodeKind

object SectionLayoutBundle : LayoutBundle {
    override val nodeKind: RenderNodeKind = RenderNodeKinds.SECTION_LAYOUT
    override fun makeComposeRenderer(): ComposeNodeRendering = SectionLayoutComposeRenderer()
}
