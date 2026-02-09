package com.example.scalsandroid.scals.components.bundles

import com.example.scalsandroid.scals.components.compose.rendering.ComposeNodeRendering
import com.example.scalsandroid.scals.components.compose.renderers.GradientComposeRenderer
import com.example.scalsandroid.scals.components.manifests.ComponentBundle
import com.example.scalsandroid.scals.components.nodes.RenderNodeKinds
import com.example.scalsandroid.scals.components.resolvers.GradientResolver
import com.example.scalsandroid.scals.document.ComponentKind
import com.example.scalsandroid.scals.ir.RenderNodeKind
import com.example.scalsandroid.scals.ir.resolution.ComponentResolving

object GradientBundle : ComponentBundle {
    override val componentKind = ComponentKind("gradient")
    override val nodeKind: RenderNodeKind = RenderNodeKinds.GRADIENT
    override fun makeResolver(): ComponentResolving = GradientResolver()
    override fun makeComposeRenderer(): ComposeNodeRendering = GradientComposeRenderer()
}
