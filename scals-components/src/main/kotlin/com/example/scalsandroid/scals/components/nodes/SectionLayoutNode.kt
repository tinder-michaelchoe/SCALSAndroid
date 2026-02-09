package com.example.scalsandroid.scals.components.nodes

import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.ir.RenderNodeData
import com.example.scalsandroid.scals.ir.RenderNodeKind

data class SectionLayoutNode(
    override val id: String? = null,
    val sectionSpacing: Double = 0.0,
    val sections: List<IR.Section> = emptyList(),
    val padding: IR.EdgeInsets = IR.EdgeInsets.ZERO,
    val backgroundColor: IR.Color? = null,
) : RenderNodeData {
    override val nodeKind: RenderNodeKind get() = RenderNodeKinds.SECTION_LAYOUT
}
