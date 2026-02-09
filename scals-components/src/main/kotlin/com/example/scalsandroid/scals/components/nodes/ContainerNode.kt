package com.example.scalsandroid.scals.components.nodes

import com.example.scalsandroid.scals.document.LayoutType
import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.ir.RenderNode
import com.example.scalsandroid.scals.ir.RenderNodeData
import com.example.scalsandroid.scals.ir.RenderNodeKind

data class ContainerNode(
    override val id: String? = null,
    override val styleId: String? = null,
    val layoutType: LayoutType = LayoutType.VSTACK,
    val alignment: IR.Alignment = IR.Alignment.center,
    val spacing: Double = 0.0,
    val children: List<RenderNode> = emptyList(),
    val padding: IR.EdgeInsets = IR.EdgeInsets.ZERO,
    val backgroundColor: IR.Color? = null,
    val cornerRadius: Double = 0.0,
    val shadow: IR.Shadow? = null,
    val border: IR.Border? = null,
    val width: IR.DimensionValue? = null,
    val height: IR.DimensionValue? = null,
    val minWidth: IR.DimensionValue? = null,
    val minHeight: IR.DimensionValue? = null,
    val maxWidth: IR.DimensionValue? = null,
    val maxHeight: IR.DimensionValue? = null,
) : RenderNodeData {
    override val nodeKind: RenderNodeKind get() = RenderNodeKinds.CONTAINER
}
