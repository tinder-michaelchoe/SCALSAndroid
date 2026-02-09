package com.example.scalsandroid.scals.ir.resolution

import com.example.scalsandroid.scals.document.FontWeight
import com.example.scalsandroid.scals.document.Style
import com.example.scalsandroid.scals.document.TextAlignment
import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.ir.toIR

class ResolvedStyle {
    // Typography
    var fontFamily: String? = null
    var fontSize: Double? = null
    var fontWeight: FontWeight? = null
    var textColor: IR.Color? = null
    var textAlignment: TextAlignment? = null

    // Background & Border
    var backgroundColor: IR.Color? = null
    var cornerRadius: Double? = null
    var borderWidth: Double? = null
    var borderColor: IR.Color? = null

    // Shadow
    var shadowColor: IR.Color? = null
    var shadowRadius: Double? = null
    var shadowX: Double? = null
    var shadowY: Double? = null

    // Image
    var tintColor: IR.Color? = null

    // Sizing
    var width: IR.DimensionValue? = null
    var height: IR.DimensionValue? = null
    var minWidth: IR.DimensionValue? = null
    var minHeight: IR.DimensionValue? = null
    var maxWidth: IR.DimensionValue? = null
    var maxHeight: IR.DimensionValue? = null

    // Padding (individual edges)
    var paddingTop: Double? = null
    var paddingBottom: Double? = null
    var paddingLeading: Double? = null
    var paddingTrailing: Double? = null

    val hasShadow: Boolean
        get() = shadowRadius != null && shadowRadius != 0.0

    val hasBorder: Boolean
        get() = borderWidth != null && borderWidth != 0.0 && borderColor != null

    fun mergeFrom(style: Style) {
        style.fontFamily?.let { fontFamily = it }
        style.fontSize?.let { fontSize = it }
        style.fontWeight?.let { fontWeight = it }
        style.textColor?.let { textColor = IR.Color.fromHex(it) }
        style.textAlignment?.let { textAlignment = it }
        style.backgroundColor?.let { backgroundColor = IR.Color.fromHex(it) }
        style.cornerRadius?.let { cornerRadius = it }
        style.borderWidth?.let { borderWidth = it }
        style.borderColor?.let { borderColor = IR.Color.fromHex(it) }

        // Shadow: merge individual properties, detect clear
        style.shadow?.let { shadow ->
            val hasAnyProperty = shadow.color != null || shadow.radius != null || shadow.x != null || shadow.y != null
            if (hasAnyProperty) {
                shadow.color?.let { shadowColor = IR.Color.fromHex(it) }
                shadow.radius?.let { shadowRadius = it }
                shadow.x?.let { shadowX = it }
                shadow.y?.let { shadowY = it }
            } else {
                // All nil = clear inherited shadow
                shadowColor = null
                shadowRadius = null
                shadowX = null
                shadowY = null
            }
        }

        style.tintColor?.let { tintColor = IR.Color.fromHex(it) }

        // Sizing
        style.width?.let { width = it.toIR() }
        style.height?.let { height = it.toIR() }
        style.minWidth?.let { minWidth = it.toIR() }
        style.minHeight?.let { minHeight = it.toIR() }
        style.maxWidth?.let { maxWidth = it.toIR() }
        style.maxHeight?.let { maxHeight = it.toIR() }

        // Padding
        style.padding?.let { padding ->
            val hasAny = padding.top != null || padding.bottom != null ||
                padding.leading != null || padding.trailing != null ||
                padding.horizontal != null || padding.vertical != null ||
                padding.all != null
            if (hasAny) {
                paddingTop = padding.top ?: padding.vertical ?: padding.all ?: paddingTop
                paddingBottom = padding.bottom ?: padding.vertical ?: padding.all ?: paddingBottom
                paddingLeading = padding.leading ?: padding.horizontal ?: padding.all ?: paddingLeading
                paddingTrailing = padding.trailing ?: padding.horizontal ?: padding.all ?: paddingTrailing
            } else {
                // All nil = clear inherited padding
                paddingTop = null
                paddingBottom = null
                paddingLeading = null
                paddingTrailing = null
            }
        }
    }
}
