package com.example.scalsandroid.scals.components.nodes

import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.ir.RenderNodeData
import com.example.scalsandroid.scals.ir.RenderNodeKind

data class PageIndicatorNode(
    override val id: String? = null,
    override val styleId: String? = null,
    val pageCount: Int = 0,
    val currentPageBinding: String? = null,
    val currentColor: IR.Color = IR.Color.black,
    val otherColor: IR.Color = IR.Color(0.0, 0.0, 0.0, 0.3),
    val padding: IR.EdgeInsets = IR.EdgeInsets.ZERO,
) : RenderNodeData {
    override val nodeKind: RenderNodeKind get() = RenderNodeKinds.PAGE_INDICATOR
}
