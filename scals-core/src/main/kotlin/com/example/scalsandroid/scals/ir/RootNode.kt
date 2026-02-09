package com.example.scalsandroid.scals.ir

data class RootNode(
    val backgroundColor: IR.Color? = null,
    val edgeInsets: IR.PositionedEdgeInsets? = null,
    val colorScheme: IR.ColorScheme = IR.ColorScheme.SYSTEM,
    val actions: LifecycleActions = LifecycleActions(),
    val children: List<RenderNode> = emptyList(),
    val padding: IR.EdgeInsets = IR.EdgeInsets.ZERO,
    val cornerRadius: Double = 0.0,
    val shadow: IR.Shadow? = null,
    val border: IR.Border? = null,
)
