package com.example.scalsandroid.scals.ir.resolution

import com.example.scalsandroid.scals.document.Layout
import com.example.scalsandroid.scals.document.LayoutNode

interface LayoutResolving {
    fun resolve(layout: Layout, context: ResolutionContext): NodeResolutionResult
    fun resolveNode(node: LayoutNode, context: ResolutionContext): NodeResolutionResult
}
