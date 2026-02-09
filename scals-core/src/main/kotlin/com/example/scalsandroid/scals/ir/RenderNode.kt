package com.example.scalsandroid.scals.ir

data class RenderNode(
    val kind: RenderNodeKind,
    val nodeData: RenderNodeData,
) {
    constructor(data: RenderNodeData) : this(data.nodeKind, data)

    inline fun <reified T : RenderNodeData> data(): T? = nodeData as? T

    val id: String? get() = nodeData.id
    val styleId: String? get() = nodeData.styleId
}
