package com.example.scalsandroid.scals.components.bundles

import com.example.scalsandroid.scals.components.compose.rendering.ComposeNodeRendering
import com.example.scalsandroid.scals.components.compose.renderers.SliderComposeRenderer
import com.example.scalsandroid.scals.components.manifests.ComponentBundle
import com.example.scalsandroid.scals.components.nodes.RenderNodeKinds
import com.example.scalsandroid.scals.components.resolvers.SliderResolver
import com.example.scalsandroid.scals.document.ComponentKind
import com.example.scalsandroid.scals.ir.RenderNodeKind
import com.example.scalsandroid.scals.ir.resolution.ComponentResolving

object SliderBundle : ComponentBundle {
    override val componentKind = ComponentKind("slider")
    override val nodeKind: RenderNodeKind = RenderNodeKinds.SLIDER
    override fun makeResolver(): ComponentResolving = SliderResolver()
    override fun makeComposeRenderer(): ComposeNodeRendering = SliderComposeRenderer()
}
