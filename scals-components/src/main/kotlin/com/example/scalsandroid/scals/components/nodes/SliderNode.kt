package com.example.scalsandroid.scals.components.nodes

import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.ir.RenderNodeData
import com.example.scalsandroid.scals.ir.RenderNodeKind

data class SliderNode(
    override val id: String? = null,
    override val styleId: String? = null,
    val bindingPath: String? = null,
    val minValue: Double = 0.0,
    val maxValue: Double = 1.0,
    val step: Double? = null,
    val tintColor: IR.Color? = null,
    val padding: IR.EdgeInsets = IR.EdgeInsets.ZERO,
    val width: IR.DimensionValue? = null,
    val height: IR.DimensionValue? = null,
) : RenderNodeData {
    override val nodeKind: RenderNodeKind get() = RenderNodeKinds.SLIDER
}
