package com.example.scalsandroid.scals.components.manifests

import com.example.scalsandroid.scals.components.compose.rendering.ComposeNodeRendering
import com.example.scalsandroid.scals.document.ComponentKind
import com.example.scalsandroid.scals.ir.RenderNodeKind
import com.example.scalsandroid.scals.ir.resolution.ComponentResolving

interface ComponentBundle {
    val componentKind: ComponentKind
    val nodeKind: RenderNodeKind
    fun makeResolver(): ComponentResolving
    fun makeComposeRenderer(): ComposeNodeRendering
}

interface LayoutBundle {
    val nodeKind: RenderNodeKind
    fun makeComposeRenderer(): ComposeNodeRendering
}
