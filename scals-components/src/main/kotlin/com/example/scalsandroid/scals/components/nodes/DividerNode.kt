package com.example.scalsandroid.scals.components.nodes

import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.ir.RenderNodeData
import com.example.scalsandroid.scals.ir.RenderNodeKind

data class DividerNode(
    override val id: String? = null,
    override val styleId: String? = null,
    val color: IR.Color = IR.Color(0.0, 0.0, 0.0, 0.2),
    val thickness: Double = 1.0,
    val padding: IR.EdgeInsets = IR.EdgeInsets.ZERO,
) : RenderNodeData {
    override val nodeKind: RenderNodeKind get() = RenderNodeKinds.DIVIDER
}
