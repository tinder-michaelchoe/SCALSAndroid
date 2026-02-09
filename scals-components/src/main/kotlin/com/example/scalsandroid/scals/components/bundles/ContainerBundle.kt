package com.example.scalsandroid.scals.components.bundles

import com.example.scalsandroid.scals.components.compose.rendering.ComposeNodeRendering
import com.example.scalsandroid.scals.components.compose.renderers.ContainerComposeRenderer
import com.example.scalsandroid.scals.components.manifests.LayoutBundle
import com.example.scalsandroid.scals.components.nodes.RenderNodeKinds
import com.example.scalsandroid.scals.ir.RenderNodeKind

object ContainerBundle : LayoutBundle {
    override val nodeKind: RenderNodeKind = RenderNodeKinds.CONTAINER
    override fun makeComposeRenderer(): ComposeNodeRendering = ContainerComposeRenderer()
}
