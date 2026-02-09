package com.example.scalsandroid.scals.components.nodes

import com.example.scalsandroid.scals.document.FontWeight
import com.example.scalsandroid.scals.document.TextAlignment
import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.ir.RenderNodeData
import com.example.scalsandroid.scals.ir.RenderNodeKind

data class TextNode(
    override val id: String? = null,
    override val styleId: String? = null,
    val content: String = "",
    val bindingPath: String? = null,
    val bindingTemplate: String? = null,
    val padding: IR.EdgeInsets = IR.EdgeInsets.ZERO,
    val textColor: IR.Color = IR.Color.black,
    val fontSize: Double = 17.0,
    val fontWeight: FontWeight = FontWeight.REGULAR,
    val fontFamily: String? = null,
    val textAlignment: TextAlignment = TextAlignment.LEADING,
    val backgroundColor: IR.Color? = null,
    val cornerRadius: Double = 0.0,
    val shadow: IR.Shadow? = null,
    val border: IR.Border? = null,
    val width: IR.DimensionValue? = null,
    val height: IR.DimensionValue? = null,
) : RenderNodeData {
    override val nodeKind: RenderNodeKind get() = RenderNodeKinds.TEXT
    val isDynamic: Boolean get() = bindingPath != null || bindingTemplate != null
}
