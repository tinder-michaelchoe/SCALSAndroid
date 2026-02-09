package com.example.scalsandroid.scals.components.bundles

import com.example.scalsandroid.scals.components.compose.rendering.ComposeNodeRendering
import com.example.scalsandroid.scals.components.compose.renderers.SpacerComposeRenderer
import com.example.scalsandroid.scals.components.manifests.LayoutBundle
import com.example.scalsandroid.scals.components.nodes.RenderNodeKinds
import com.example.scalsandroid.scals.ir.RenderNodeKind

object SpacerBundle : LayoutBundle {
    override val nodeKind: RenderNodeKind = RenderNodeKinds.SPACER
    override fun makeComposeRenderer(): ComposeNodeRendering = SpacerComposeRenderer()
}
