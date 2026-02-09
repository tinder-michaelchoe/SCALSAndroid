package com.example.scalsandroid.scals.components.bundles

import com.example.scalsandroid.scals.components.compose.rendering.ComposeNodeRendering
import com.example.scalsandroid.scals.components.compose.renderers.DividerComposeRenderer
import com.example.scalsandroid.scals.components.manifests.ComponentBundle
import com.example.scalsandroid.scals.components.nodes.RenderNodeKinds
import com.example.scalsandroid.scals.components.resolvers.DividerResolver
import com.example.scalsandroid.scals.document.ComponentKind
import com.example.scalsandroid.scals.ir.RenderNodeKind
import com.example.scalsandroid.scals.ir.resolution.ComponentResolving

object DividerBundle : ComponentBundle {
    override val componentKind = ComponentKind("divider")
    override val nodeKind: RenderNodeKind = RenderNodeKinds.DIVIDER
    override fun makeResolver(): ComponentResolving = DividerResolver()
    override fun makeComposeRenderer(): ComposeNodeRendering = DividerComposeRenderer()
}
