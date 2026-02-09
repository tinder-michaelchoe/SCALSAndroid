package com.example.scalsandroid.scals.document

import kotlinx.serialization.Serializable

@Serializable
data class Spacer(
    val minLength: Double? = null,
    val width: DimensionValue? = null,
    val height: DimensionValue? = null,
) : LayoutNode
