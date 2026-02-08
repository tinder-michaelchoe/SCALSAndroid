package com.example.scalsandroid.scals.document

import kotlinx.serialization.Serializable

@Serializable
data class ItemDimensions(
    val width: DimensionValue? = null,
    val height: DimensionValue? = null,
    val aspectRatio: Double? = null,
)

@Serializable
data class SectionLayoutConfig(
    val type: SectionType,
    val alignment: SectionAlignment? = null,
    val itemSpacing: Double? = null,
    val lineSpacing: Double? = null,
    val contentInsets: Padding? = null,
    val itemDimensions: ItemDimensions? = null,
    val showsIndicators: Boolean? = null,
    val isPagingEnabled: Boolean? = null,
    val snapBehavior: SnapBehavior? = null,
    val columns: ColumnConfig? = null,
    val showsDividers: Boolean? = null,
)

@Serializable
data class SectionDefinition(
    val id: String? = null,
    val layout: SectionLayoutConfig,
    val header: LayoutNode? = null,
    val footer: LayoutNode? = null,
    val stickyHeader: Boolean? = null,
    val children: List<LayoutNode>? = null,
    val dataSource: DataSource? = null,
    val itemTemplate: LayoutNode? = null,
)

@Serializable
data class SectionLayout(
    val id: String? = null,
    val sectionSpacing: Double? = null,
    val sections: List<SectionDefinition> = emptyList(),
) : LayoutNode
