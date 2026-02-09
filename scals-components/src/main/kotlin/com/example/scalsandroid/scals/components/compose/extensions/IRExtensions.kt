package com.example.scalsandroid.scals.components.compose.extensions

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.scalsandroid.scals.document.FontWeight as DocFontWeight
import com.example.scalsandroid.scals.document.TextAlignment
import com.example.scalsandroid.scals.ir.IR

fun IR.Color.toComposeColor(): Color {
    return Color(
        red = red.toFloat(),
        green = green.toFloat(),
        blue = blue.toFloat(),
        alpha = alpha.toFloat(),
    )
}

fun IR.EdgeInsets.toPaddingValues(): PaddingValues {
    return PaddingValues(
        start = leading.dp,
        top = top.dp,
        end = trailing.dp,
        bottom = bottom.dp,
    )
}

fun IR.PositionedEdgeInsets.toComposePaddingValues(): PaddingValues {
    return PaddingValues(
        start = (leading?.value ?: 0.0).dp,
        top = (top?.value ?: 0.0).dp,
        end = (trailing?.value ?: 0.0).dp,
        bottom = (bottom?.value ?: 0.0).dp,
    )
}

fun DocFontWeight.toComposeFontWeight(): FontWeight = when (this) {
    DocFontWeight.ULTRA_LIGHT -> FontWeight.Thin
    DocFontWeight.THIN -> FontWeight.ExtraLight
    DocFontWeight.LIGHT -> FontWeight.Light
    DocFontWeight.REGULAR -> FontWeight.Normal
    DocFontWeight.MEDIUM -> FontWeight.Medium
    DocFontWeight.SEMIBOLD -> FontWeight.SemiBold
    DocFontWeight.BOLD -> FontWeight.Bold
    DocFontWeight.HEAVY -> FontWeight.ExtraBold
    DocFontWeight.BLACK -> FontWeight.Black
}

fun TextAlignment.toComposeTextAlign(): TextAlign = when (this) {
    TextAlignment.LEADING -> TextAlign.Start
    TextAlignment.CENTER -> TextAlign.Center
    TextAlignment.TRAILING -> TextAlign.End
}

fun IR.UnitPoint.toOffset(): Offset {
    return Offset(x.toFloat(), y.toFloat())
}

/**
 * Apply a DimensionValue as a width modifier in a RowScope context.
 * Fractional values will use weight(), absolute values will use width().
 */
fun RowScope.applyWidth(modifier: Modifier, dimension: IR.DimensionValue?): Modifier {
    return when (dimension) {
        is IR.DimensionValue.Absolute -> modifier.width(dimension.value.dp)
        is IR.DimensionValue.Fractional -> modifier.weight(dimension.value.toFloat())
        null -> modifier
    }
}

/**
 * Apply a DimensionValue as a height modifier in a ColumnScope context.
 * Fractional values will use weight(), absolute values will use height().
 */
fun ColumnScope.applyHeight(modifier: Modifier, dimension: IR.DimensionValue?): Modifier {
    return when (dimension) {
        is IR.DimensionValue.Absolute -> modifier.height(dimension.value.dp)
        is IR.DimensionValue.Fractional -> modifier.weight(dimension.value.toFloat())
        null -> modifier
    }
}
