package com.example.scalsandroid.scals.ir

import com.example.scalsandroid.scals.document.Padding
import com.example.scalsandroid.scals.document.Alignment as DocAlignment
import com.example.scalsandroid.scals.document.HorizontalAlignment as DocHAlignment
import com.example.scalsandroid.scals.document.VerticalAlignment as DocVAlignment
import com.example.scalsandroid.scals.document.DimensionValue as DocDimensionValue
import com.example.scalsandroid.scals.document.ColumnConfig as DocColumnConfig
import com.example.scalsandroid.scals.document.SnapBehavior as DocSnapBehavior

fun Padding.toEdgeInsets(): IR.EdgeInsets = IR.EdgeInsets(
    top = resolvedTop,
    leading = resolvedLeading,
    bottom = resolvedBottom,
    trailing = resolvedTrailing,
)

fun DocHAlignment.toIR(): IR.HorizontalAlignment = when (this) {
    DocHAlignment.LEADING -> IR.HorizontalAlignment.LEADING
    DocHAlignment.CENTER -> IR.HorizontalAlignment.CENTER
    DocHAlignment.TRAILING -> IR.HorizontalAlignment.TRAILING
}

fun DocVAlignment.toIR(): IR.VerticalAlignment = when (this) {
    DocVAlignment.TOP -> IR.VerticalAlignment.TOP
    DocVAlignment.CENTER -> IR.VerticalAlignment.CENTER
    DocVAlignment.BOTTOM -> IR.VerticalAlignment.BOTTOM
}

fun DocAlignment.toIR(): IR.Alignment = IR.Alignment(
    horizontal = horizontal?.toIR() ?: IR.HorizontalAlignment.CENTER,
    vertical = vertical?.toIR() ?: IR.VerticalAlignment.CENTER,
)

fun DocDimensionValue.toIR(): IR.DimensionValue = when (this) {
    is DocDimensionValue.Absolute -> IR.DimensionValue.Absolute(value)
    is DocDimensionValue.Fractional -> IR.DimensionValue.Fractional(value)
}

fun DocColumnConfig.toIR(): IR.ColumnConfig = when (this) {
    is DocColumnConfig.Fixed -> IR.ColumnConfig.Fixed(count)
    is DocColumnConfig.Adaptive -> IR.ColumnConfig.Adaptive(minWidth)
}

fun DocSnapBehavior.toIR(): IR.SnapBehavior = when (this) {
    DocSnapBehavior.NONE -> IR.SnapBehavior.NONE
    DocSnapBehavior.VIEW_ALIGNED -> IR.SnapBehavior.VIEW_ALIGNED
    DocSnapBehavior.PAGING -> IR.SnapBehavior.PAGING
}
