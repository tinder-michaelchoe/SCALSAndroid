package com.example.scalsandroid.scals.document

import kotlinx.serialization.Serializable

@Serializable
data class Spacer(
    val minLength: Double? = null,
    val width: Double? = null,
    val height: Double? = null,
) : LayoutNode
