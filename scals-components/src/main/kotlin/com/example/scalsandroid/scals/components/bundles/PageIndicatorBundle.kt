package com.example.scalsandroid.scals.components.bundles

import com.example.scalsandroid.scals.components.compose.rendering.ComposeNodeRendering
import com.example.scalsandroid.scals.components.compose.renderers.PageIndicatorComposeRenderer
import com.example.scalsandroid.scals.components.manifests.ComponentBundle
import com.example.scalsandroid.scals.components.nodes.RenderNodeKinds
import com.example.scalsandroid.scals.components.resolvers.PageIndicatorResolver
import com.example.scalsandroid.scals.document.ComponentKind
import com.example.scalsandroid.scals.ir.RenderNodeKind
import com.example.scalsandroid.scals.ir.resolution.ComponentResolving

object PageIndicatorBundle : ComponentBundle {
    override val componentKind = ComponentKind("pageindicator")
    override val nodeKind: RenderNodeKind = RenderNodeKinds.PAGE_INDICATOR
    override fun makeResolver(): ComponentResolving = PageIndicatorResolver()
    override fun makeComposeRenderer(): ComposeNodeRendering = PageIndicatorComposeRenderer()
}
