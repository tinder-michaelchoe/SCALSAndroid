package com.example.scalsandroid.scals.document

import kotlinx.serialization.Serializable

@Serializable
data class Shadow(
    val color: String? = null,
    val radius: Double? = null,
    val x: Double? = null,
    val y: Double? = null,
)

@Serializable
data class Padding(
    val top: Double? = null,
    val bottom: Double? = null,
    val leading: Double? = null,
    val trailing: Double? = null,
    val horizontal: Double? = null,
    val vertical: Double? = null,
    val all: Double? = null,
) {
    val resolvedTop: Double get() = top ?: vertical ?: all ?: 0.0
    val resolvedBottom: Double get() = bottom ?: vertical ?: all ?: 0.0
    val resolvedLeading: Double get() = leading ?: horizontal ?: all ?: 0.0
    val resolvedTrailing: Double get() = trailing ?: horizontal ?: all ?: 0.0
}

@Serializable
data class Style(
    val inherits: String? = null,
    val fontFamily: String? = null,
    val fontSize: Double? = null,
    val fontWeight: FontWeight? = null,
    val textColor: String? = null,
    val textAlignment: TextAlignment? = null,
    val backgroundColor: String? = null,
    val cornerRadius: Double? = null,
    val borderWidth: Double? = null,
    val borderColor: String? = null,
    val shadow: Shadow? = null,
    val tintColor: String? = null,
    val width: DimensionValue? = null,
    val height: DimensionValue? = null,
    val minWidth: DimensionValue? = null,
    val minHeight: DimensionValue? = null,
    val maxWidth: DimensionValue? = null,
    val maxHeight: DimensionValue? = null,
    val padding: Padding? = null,
)
