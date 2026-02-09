package com.example.scalsandroid.scals.components.bundles

import com.example.scalsandroid.scals.components.compose.rendering.ComposeNodeRendering
import com.example.scalsandroid.scals.components.compose.renderers.ToggleComposeRenderer
import com.example.scalsandroid.scals.components.manifests.ComponentBundle
import com.example.scalsandroid.scals.components.nodes.RenderNodeKinds
import com.example.scalsandroid.scals.components.resolvers.ToggleResolver
import com.example.scalsandroid.scals.document.ComponentKind
import com.example.scalsandroid.scals.ir.RenderNodeKind
import com.example.scalsandroid.scals.ir.resolution.ComponentResolving

object ToggleBundle : ComponentBundle {
    override val componentKind = ComponentKind("toggle")
    override val nodeKind: RenderNodeKind = RenderNodeKinds.TOGGLE
    override fun makeResolver(): ComponentResolving = ToggleResolver()
    override fun makeComposeRenderer(): ComposeNodeRendering = ToggleComposeRenderer()
}
