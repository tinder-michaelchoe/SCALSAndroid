package com.example.scalsandroid.scals.components.compose.extensions

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.scalsandroid.scals.ir.IR

fun Modifier.applyDimensions(
    width: IR.DimensionValue? = null,
    height: IR.DimensionValue? = null,
    minWidth: IR.DimensionValue? = null,
    minHeight: IR.DimensionValue? = null,
    maxWidth: IR.DimensionValue? = null,
    maxHeight: IR.DimensionValue? = null,
): Modifier {
    var m = this

    // Width
    when (width) {
        is IR.DimensionValue.Absolute -> m = m.width(width.value.dp)
        is IR.DimensionValue.Fractional -> m = m.fillMaxWidth(width.value.toFloat())
        null -> {}
    }

    // Height
    when (height) {
        is IR.DimensionValue.Absolute -> m = m.height(height.value.dp)
        is IR.DimensionValue.Fractional -> m = m.fillMaxHeight(height.value.toFloat())
        null -> {}
    }

    // Min/Max constraints
    val minW = (minWidth as? IR.DimensionValue.Absolute)?.value?.dp
    val maxW = (maxWidth as? IR.DimensionValue.Absolute)?.value?.dp
    if (minW != null || maxW != null) {
        m = m.widthIn(
            min = minW ?: 0.dp,
            max = maxW ?: androidx.compose.ui.unit.Dp.Unspecified,
        )
    }

    val minH = (minHeight as? IR.DimensionValue.Absolute)?.value?.dp
    val maxH = (maxHeight as? IR.DimensionValue.Absolute)?.value?.dp
    if (minH != null || maxH != null) {
        m = m.heightIn(
            min = minH ?: 0.dp,
            max = maxH ?: androidx.compose.ui.unit.Dp.Unspecified,
        )
    }

    return m
}
