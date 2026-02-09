package com.example.scalsandroid.scals.components.nodes

import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.ir.RenderNodeData
import com.example.scalsandroid.scals.ir.RenderNodeKind

data class ShapeNode(
    override val id: String? = null,
    override val styleId: String? = null,
    val shapeType: String = "rectangle",
    val fillColor: IR.Color? = null,
    val strokeColor: IR.Color? = null,
    val strokeWidth: Double = 0.0,
    val padding: IR.EdgeInsets = IR.EdgeInsets.ZERO,
    val width: IR.DimensionValue? = null,
    val height: IR.DimensionValue? = null,
) : RenderNodeData {
    override val nodeKind: RenderNodeKind get() = RenderNodeKinds.SHAPE
}
