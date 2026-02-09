package com.example.scalsandroid.scals.ir

interface RenderNodeData {
    val nodeKind: RenderNodeKind
    val id: String? get() = null
    val styleId: String? get() = null
}
