package com.example.scalsandroid.scals.components.nodes

import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.ir.RenderNodeData
import com.example.scalsandroid.scals.ir.RenderNodeKind

data class GradientColorStop(
    val color: IR.Color,
    val location: Double,
)

data class GradientNode(
    override val id: String? = null,
    override val styleId: String? = null,
    val colors: List<GradientColorStop> = emptyList(),
    val startPoint: IR.UnitPoint = IR.UnitPoint.top,
    val endPoint: IR.UnitPoint = IR.UnitPoint.bottom,
    val padding: IR.EdgeInsets = IR.EdgeInsets.ZERO,
    val cornerRadius: Double = 0.0,
    val width: IR.DimensionValue? = null,
    val height: IR.DimensionValue? = null,
) : RenderNodeData {
    override val nodeKind: RenderNodeKind get() = RenderNodeKinds.GRADIENT
}
