package com.example.scalsandroid.scals.components.nodes

import com.example.scalsandroid.scals.document.ActionBinding
import com.example.scalsandroid.scals.document.ImageSource
import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.ir.RenderNodeData
import com.example.scalsandroid.scals.ir.RenderNodeKind

data class ImageNode(
    override val id: String? = null,
    override val styleId: String? = null,
    val source: ImageSource,
    val placeholder: ImageSource? = null,
    val loading: ImageSource? = null,
    val onTap: ActionBinding? = null,
    val tintColor: IR.Color? = null,
    val backgroundColor: IR.Color? = null,
    val cornerRadius: Double = 0.0,
    val border: IR.Border? = null,
    val shadow: IR.Shadow? = null,
    val padding: IR.EdgeInsets = IR.EdgeInsets.ZERO,
    val width: IR.DimensionValue? = null,
    val height: IR.DimensionValue? = null,
    val minWidth: IR.DimensionValue? = null,
    val minHeight: IR.DimensionValue? = null,
    val maxWidth: IR.DimensionValue? = null,
    val maxHeight: IR.DimensionValue? = null,
) : RenderNodeData {
    override val nodeKind: RenderNodeKind get() = RenderNodeKinds.IMAGE
}
