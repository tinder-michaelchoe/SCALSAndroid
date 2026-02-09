package com.example.scalsandroid.scals.components.bundles

import com.example.scalsandroid.scals.components.compose.rendering.ComposeNodeRendering
import com.example.scalsandroid.scals.components.compose.renderers.TextComposeRenderer
import com.example.scalsandroid.scals.components.manifests.ComponentBundle
import com.example.scalsandroid.scals.components.nodes.RenderNodeKinds
import com.example.scalsandroid.scals.components.resolvers.TextResolver
import com.example.scalsandroid.scals.document.ComponentKind
import com.example.scalsandroid.scals.ir.RenderNodeKind
import com.example.scalsandroid.scals.ir.resolution.ComponentResolving

object TextBundle : ComponentBundle {
    override val componentKind = ComponentKind("label")
    override val nodeKind: RenderNodeKind = RenderNodeKinds.TEXT
    override fun makeResolver(): ComponentResolving = TextResolver()
    override fun makeComposeRenderer(): ComposeNodeRendering = TextComposeRenderer()
}
