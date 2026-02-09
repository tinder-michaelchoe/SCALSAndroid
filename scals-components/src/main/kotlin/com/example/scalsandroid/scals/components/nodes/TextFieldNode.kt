package com.example.scalsandroid.scals.components.nodes

import com.example.scalsandroid.scals.document.FontWeight
import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.ir.RenderNodeData
import com.example.scalsandroid.scals.ir.RenderNodeKind

data class TextFieldNode(
    override val id: String? = null,
    override val styleId: String? = null,
    val placeholder: String = "",
    val bindingPath: String? = null,
    val textColor: IR.Color = IR.Color.black,
    val fontSize: Double = 17.0,
    val fontWeight: FontWeight = FontWeight.REGULAR,
    val backgroundColor: IR.Color? = null,
    val cornerRadius: Double = 0.0,
    val border: IR.Border? = null,
    val shadow: IR.Shadow? = null,
    val padding: IR.EdgeInsets = IR.EdgeInsets.ZERO,
    val width: IR.DimensionValue? = null,
    val height: IR.DimensionValue? = null,
) : RenderNodeData {
    override val nodeKind: RenderNodeKind get() = RenderNodeKinds.TEXT_FIELD
}
