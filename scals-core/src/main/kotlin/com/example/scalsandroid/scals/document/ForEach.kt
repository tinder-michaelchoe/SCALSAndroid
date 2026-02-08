package com.example.scalsandroid.scals.document

import kotlinx.serialization.Serializable

@Serializable
data class ForEach(
    val items: StateValue? = null,
    val itemVariable: String = "item",
    val indexVariable: String = "index",
    val layout: LayoutType = LayoutType.VSTACK,
    val spacing: Double? = null,
    val alignment: Alignment? = null,
    val padding: Padding? = null,
    val template: LayoutNode? = null,
    val emptyView: LayoutNode? = null,
) : LayoutNode
