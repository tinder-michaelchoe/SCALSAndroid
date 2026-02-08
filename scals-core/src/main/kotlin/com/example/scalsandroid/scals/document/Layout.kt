package com.example.scalsandroid.scals.document

import kotlinx.serialization.Serializable

@Serializable
data class Layout(
    val type: LayoutType,
    val alignment: Alignment? = null,
    val spacing: Double? = null,
    val padding: Padding? = null,
    val children: List<LayoutNode>? = null,
    val state: LocalStateDeclaration? = null,
    val styleId: String? = null,
    val style: Style? = null,
) : LayoutNode
