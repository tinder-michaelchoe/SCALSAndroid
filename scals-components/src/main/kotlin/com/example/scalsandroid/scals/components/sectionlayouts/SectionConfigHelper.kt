package com.example.scalsandroid.scals.components.sectionlayouts

import com.example.scalsandroid.scals.document.SectionAlignment
import com.example.scalsandroid.scals.document.SectionLayoutConfig
import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.ir.toIR

internal fun buildConfig(config: SectionLayoutConfig): IR.SectionConfig {
    val contentInsets = config.contentInsets?.let {
        IR.EdgeInsets(it.resolvedTop, it.resolvedLeading, it.resolvedBottom, it.resolvedTrailing)
    } ?: IR.EdgeInsets.ZERO

    val itemDimensions = config.itemDimensions?.let {
        IR.ItemDimensions(
            width = it.width?.toIR(),
            height = it.height?.toIR(),
            aspectRatio = it.aspectRatio,
        )
    }

    return IR.SectionConfig(
        alignment = when (config.alignment) {
            SectionAlignment.LEADING -> IR.HorizontalAlignment.LEADING
            SectionAlignment.CENTER -> IR.HorizontalAlignment.CENTER
            SectionAlignment.TRAILING -> IR.HorizontalAlignment.TRAILING
            null -> IR.HorizontalAlignment.LEADING
        },
        itemSpacing = config.itemSpacing ?: 8.0,
        lineSpacing = config.lineSpacing ?: 8.0,
        contentInsets = contentInsets,
        showsIndicators = config.showsIndicators ?: true,
        isPagingEnabled = config.isPagingEnabled ?: false,
        snapBehavior = config.snapBehavior?.toIR() ?: IR.SnapBehavior.NONE,
        showsDividers = config.showsDividers ?: false,
        itemDimensions = itemDimensions,
    )
}
