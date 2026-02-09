package com.example.scalsandroid.scals.components.bundles

import com.example.scalsandroid.scals.components.compose.rendering.ComposeNodeRendering
import com.example.scalsandroid.scals.components.compose.renderers.ShapeComposeRenderer
import com.example.scalsandroid.scals.components.manifests.ComponentBundle
import com.example.scalsandroid.scals.components.nodes.RenderNodeKinds
import com.example.scalsandroid.scals.components.resolvers.ShapeResolver
import com.example.scalsandroid.scals.document.ComponentKind
import com.example.scalsandroid.scals.ir.RenderNodeKind
import com.example.scalsandroid.scals.ir.resolution.ComponentResolving

object ShapeBundle : ComponentBundle {
    override val componentKind = ComponentKind("shape")
    override val nodeKind: RenderNodeKind = RenderNodeKinds.SHAPE
    override fun makeResolver(): ComponentResolving = ShapeResolver()
    override fun makeComposeRenderer(): ComposeNodeRendering = ShapeComposeRenderer()
}
