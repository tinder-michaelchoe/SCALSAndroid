package com.example.scalsandroid.scals.components.nodes

import com.example.scalsandroid.scals.ir.RenderNodeData
import com.example.scalsandroid.scals.ir.RenderNodeKind

data class SpacerNode(
    val minLength: Double? = null,
    val width: Double? = null,
    val height: Double? = null,
) : RenderNodeData {
    override val nodeKind: RenderNodeKind get() = RenderNodeKinds.SPACER
}
