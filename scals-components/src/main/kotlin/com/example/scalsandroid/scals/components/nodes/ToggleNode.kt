package com.example.scalsandroid.scals.components.nodes

import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.ir.RenderNodeData
import com.example.scalsandroid.scals.ir.RenderNodeKind

data class ToggleNode(
    override val id: String? = null,
    override val styleId: String? = null,
    val bindingPath: String? = null,
    val tintColor: IR.Color? = null,
    val padding: IR.EdgeInsets = IR.EdgeInsets.ZERO,
    val width: IR.DimensionValue? = null,
    val height: IR.DimensionValue? = null,
) : RenderNodeData {
    override val nodeKind: RenderNodeKind get() = RenderNodeKinds.TOGGLE
}
