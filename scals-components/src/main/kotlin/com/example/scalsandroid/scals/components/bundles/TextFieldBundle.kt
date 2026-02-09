package com.example.scalsandroid.scals.components.bundles

import com.example.scalsandroid.scals.components.compose.rendering.ComposeNodeRendering
import com.example.scalsandroid.scals.components.compose.renderers.TextFieldComposeRenderer
import com.example.scalsandroid.scals.components.manifests.ComponentBundle
import com.example.scalsandroid.scals.components.nodes.RenderNodeKinds
import com.example.scalsandroid.scals.components.resolvers.TextFieldResolver
import com.example.scalsandroid.scals.document.ComponentKind
import com.example.scalsandroid.scals.ir.RenderNodeKind
import com.example.scalsandroid.scals.ir.resolution.ComponentResolving

object TextFieldBundle : ComponentBundle {
    override val componentKind = ComponentKind("textfield")
    override val nodeKind: RenderNodeKind = RenderNodeKinds.TEXT_FIELD
    override fun makeResolver(): ComponentResolving = TextFieldResolver()
    override fun makeComposeRenderer(): ComposeNodeRendering = TextFieldComposeRenderer()
}
