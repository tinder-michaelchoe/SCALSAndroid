package com.example.scalsandroid.scals.document

import kotlinx.serialization.Serializable

@Serializable
data class GradientColorConfig(
    val color: String? = null,
    val lightColor: String? = null,
    val darkColor: String? = null,
    val location: Double,
)
