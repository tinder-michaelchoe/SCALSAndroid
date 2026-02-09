package com.example.scalsandroid.scals.ir

import com.example.scalsandroid.scals.viewtree.ViewNode
import com.example.scalsandroid.scals.viewtree.ViewTreeUpdater

data class ResolutionResult(
    val renderTree: RenderTree,
    val viewTreeRoot: ViewNode,
    val treeUpdater: ViewTreeUpdater,
)
