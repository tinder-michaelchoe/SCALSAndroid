package com.example.scalsandroid.scals.ir

import com.example.scalsandroid.scals.document.LayoutType

object IR {

    // MARK: - Color

    data class Color(
        val red: Double,
        val green: Double,
        val blue: Double,
        val alpha: Double = 1.0,
    ) {
        companion object {
            val clear = Color(0.0, 0.0, 0.0, 0.0)
            val black = Color(0.0, 0.0, 0.0, 1.0)
            val white = Color(1.0, 1.0, 1.0, 1.0)
            val red = Color(1.0, 0.0, 0.0, 1.0)
            val green = Color(0.0, 1.0, 0.0, 1.0)
            val blue = Color(0.0, 0.0, 1.0, 1.0)

            fun fromHex(hex: String): Color? {
                val trimmed = hex.trim()
                if (trimmed.startsWith("rgba(") || trimmed.startsWith("RGBA(")) {
                    return parseRgba(trimmed)
                }
                if (!trimmed.startsWith("#")) return null
                val h = trimmed.substring(1)
                return try {
                    when (h.length) {
                        3 -> {
                            // #RGB
                            val r = h[0].toString().repeat(2).toInt(16) / 255.0
                            val g = h[1].toString().repeat(2).toInt(16) / 255.0
                            val b = h[2].toString().repeat(2).toInt(16) / 255.0
                            Color(r, g, b, 1.0)
                        }
                        6 -> {
                            // #RRGGBB
                            val r = h.substring(0, 2).toInt(16) / 255.0
                            val g = h.substring(2, 4).toInt(16) / 255.0
                            val b = h.substring(4, 6).toInt(16) / 255.0
                            Color(r, g, b, 1.0)
                        }
                        8 -> {
                            // #AARRGGBB
                            val a = h.substring(0, 2).toInt(16) / 255.0
                            val r = h.substring(2, 4).toInt(16) / 255.0
                            val g = h.substring(4, 6).toInt(16) / 255.0
                            val b = h.substring(6, 8).toInt(16) / 255.0
                            Color(r, g, b, a)
                        }
                        else -> null
                    }
                } catch (_: NumberFormatException) {
                    null
                }
            }

            private fun parseRgba(s: String): Color? {
                val inner = s.substringAfter("(").substringBefore(")")
                val parts = inner.split(",").map { it.trim() }
                if (parts.size != 4) return null
                return try {
                    val r = parts[0].toDouble() / 255.0
                    val g = parts[1].toDouble() / 255.0
                    val b = parts[2].toDouble() / 255.0
                    val a = parts[3].toDouble()
                    Color(r, g, b, a)
                } catch (_: NumberFormatException) {
                    null
                }
            }
        }
    }

    // MARK: - Edge Insets

    data class EdgeInsets(
        val top: Double = 0.0,
        val leading: Double = 0.0,
        val bottom: Double = 0.0,
        val trailing: Double = 0.0,
    ) {
        val isEmpty: Boolean get() = top == 0.0 && leading == 0.0 && bottom == 0.0 && trailing == 0.0

        companion object {
            val ZERO = EdgeInsets(0.0, 0.0, 0.0, 0.0)
        }
    }

    // MARK: - Alignment

    enum class HorizontalAlignment {
        LEADING, CENTER, TRAILING
    }

    enum class VerticalAlignment {
        TOP, CENTER, BOTTOM
    }

    data class Alignment(
        val horizontal: HorizontalAlignment,
        val vertical: VerticalAlignment,
    ) {
        companion object {
            val center = Alignment(HorizontalAlignment.CENTER, VerticalAlignment.CENTER)
            val leading = Alignment(HorizontalAlignment.LEADING, VerticalAlignment.CENTER)
            val trailing = Alignment(HorizontalAlignment.TRAILING, VerticalAlignment.CENTER)
            val top = Alignment(HorizontalAlignment.CENTER, VerticalAlignment.TOP)
            val bottom = Alignment(HorizontalAlignment.CENTER, VerticalAlignment.BOTTOM)
            val topLeading = Alignment(HorizontalAlignment.LEADING, VerticalAlignment.TOP)
            val topTrailing = Alignment(HorizontalAlignment.TRAILING, VerticalAlignment.TOP)
            val bottomLeading = Alignment(HorizontalAlignment.LEADING, VerticalAlignment.BOTTOM)
            val bottomTrailing = Alignment(HorizontalAlignment.TRAILING, VerticalAlignment.BOTTOM)
        }
    }

    // MARK: - UnitPoint

    data class UnitPoint(val x: Double, val y: Double) {
        companion object {
            val zero = UnitPoint(0.0, 0.0)
            val center = UnitPoint(0.5, 0.5)
            val top = UnitPoint(0.5, 0.0)
            val bottom = UnitPoint(0.5, 1.0)
            val leading = UnitPoint(0.0, 0.5)
            val trailing = UnitPoint(1.0, 0.5)
            val topLeading = UnitPoint(0.0, 0.0)
            val topTrailing = UnitPoint(1.0, 0.0)
            val bottomLeading = UnitPoint(0.0, 1.0)
            val bottomTrailing = UnitPoint(1.0, 1.0)
        }
    }

    // MARK: - Color Scheme

    enum class ColorScheme {
        LIGHT, DARK, SYSTEM
    }

    // MARK: - Shadow

    data class Shadow(
        val color: Color,
        val radius: Double,
        val x: Double,
        val y: Double,
    ) {
        companion object {
            val NONE = Shadow(Color.clear, 0.0, 0.0, 0.0)
        }
    }

    // MARK: - Border

    data class Border(
        val color: Color,
        val width: Double,
    )

    // MARK: - DimensionValue

    sealed class DimensionValue {
        data class Absolute(val value: Double) : DimensionValue()
        data class Fractional(val value: Double) : DimensionValue()

        fun resolve(containerSize: Double): Double = when (this) {
            is Absolute -> value
            is Fractional -> value * containerSize
        }

        val resolvedAbsolute: Double?
            get() = when (this) {
                is Absolute -> value
                is Fractional -> null
            }
    }

    // MARK: - Snap Behavior

    enum class SnapBehavior {
        NONE, VIEW_ALIGNED, PAGING
    }

    // MARK: - Positioning

    enum class Positioning {
        SAFE_AREA, ABSOLUTE
    }

    data class PositionedEdgeInset(
        val positioning: Positioning,
        val value: Double,
    )

    data class PositionedEdgeInsets(
        val top: PositionedEdgeInset? = null,
        val bottom: PositionedEdgeInset? = null,
        val leading: PositionedEdgeInset? = null,
        val trailing: PositionedEdgeInset? = null,
    )

    // MARK: - Section Types

    sealed class SectionType {
        data object Horizontal : SectionType()
        data object ListType : SectionType()
        data class Grid(val columns: ColumnConfig) : SectionType()
        data object Flow : SectionType()
    }

    sealed class ColumnConfig {
        data class Fixed(val count: Int) : ColumnConfig()
        data class Adaptive(val minWidth: Double) : ColumnConfig()
    }

    // MARK: - Section Config

    data class SectionConfig(
        val alignment: HorizontalAlignment = HorizontalAlignment.LEADING,
        val itemSpacing: Double = 8.0,
        val lineSpacing: Double = 8.0,
        val contentInsets: EdgeInsets = EdgeInsets.ZERO,
        val showsIndicators: Boolean = true,
        val isPagingEnabled: Boolean = false,
        val snapBehavior: SnapBehavior = SnapBehavior.NONE,
        val showsDividers: Boolean = false,
        val itemDimensions: ItemDimensions? = null,
    )

    data class ItemDimensions(
        val width: DimensionValue? = null,
        val height: DimensionValue? = null,
        val aspectRatio: Double? = null,
    )

    // MARK: - Section

    data class Section(
        val id: String? = null,
        val layoutType: SectionType,
        val header: RenderNode? = null,
        val footer: RenderNode? = null,
        val stickyHeader: Boolean = false,
        val config: SectionConfig = SectionConfig(),
        val children: List<RenderNode> = emptyList(),
    )

    // MARK: - ActionDefinition

    data class ActionDefinition(
        val kind: com.example.scalsandroid.scals.document.ActionKind,
        val executionData: Map<String, Any> = emptyMap(),
    ) {
        fun getString(key: String): String? = executionData[key] as? String
        fun getInt(key: String): Int? = executionData[key] as? Int
        fun getDouble(key: String): Double? = executionData[key] as? Double
        fun getBoolean(key: String): Boolean? = executionData[key] as? Boolean
        @Suppress("UNCHECKED_CAST")
        fun getList(key: String): List<Any>? = executionData[key] as? List<Any>
        @Suppress("UNCHECKED_CAST")
        fun getMap(key: String): Map<String, Any>? = executionData[key] as? Map<String, Any>
    }
}
