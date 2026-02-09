package com.example.scalsandroid.scals.components.compose.extensions

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.example.scalsandroid.scals.ir.IR

fun Modifier.applyContainerStyle(
    padding: IR.EdgeInsets? = null,
    backgroundColor: IR.Color? = null,
    cornerRadius: Double = 0.0,
    border: IR.Border? = null,
    shadow: IR.Shadow? = null,
    width: IR.DimensionValue? = null,
    height: IR.DimensionValue? = null,
    minWidth: IR.DimensionValue? = null,
    minHeight: IR.DimensionValue? = null,
    maxWidth: IR.DimensionValue? = null,
    maxHeight: IR.DimensionValue? = null,
): Modifier {
    var m = this

    // Padding
    if (padding != null && !padding.isEmpty) {
        m = m.padding(padding.toPaddingValues())
    }

    // Shadow (must be before clip)
    if (shadow != null && shadow.radius > 0) {
        val shape = if (cornerRadius > 0) RoundedCornerShape(cornerRadius.dp) else RoundedCornerShape(0.dp)
        m = m.shadow(
            elevation = shadow.radius.dp,
            shape = shape,
            ambientColor = shadow.color.toComposeColor(),
            spotColor = shadow.color.toComposeColor(),
        )
    }

    // Clip
    if (cornerRadius > 0) {
        m = m.clip(RoundedCornerShape(cornerRadius.dp))
    }

    // Background
    if (backgroundColor != null) {
        m = m.background(backgroundColor.toComposeColor())
    }

    // Border
    if (border != null && border.width > 0) {
        val shape = if (cornerRadius > 0) RoundedCornerShape(cornerRadius.dp) else RoundedCornerShape(0.dp)
        m = m.border(
            width = border.width.dp,
            color = border.color.toComposeColor(),
            shape = shape,
        )
    }

    // Dimensions
    m = m.applyDimensions(width, height, minWidth, minHeight, maxWidth, maxHeight)

    return m
}
