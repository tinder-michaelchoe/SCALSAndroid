package com.example.scalsandroid.scals.ir

import com.example.scalsandroid.scals.document.Alignment as DocAlignment
import com.example.scalsandroid.scals.document.HorizontalAlignment as DocHAlignment
import com.example.scalsandroid.scals.document.VerticalAlignment as DocVAlignment

object AlignmentConverter {
    fun forVStack(alignment: DocAlignment?): IR.Alignment {
        val h = alignment?.horizontal?.toIR() ?: IR.HorizontalAlignment.CENTER
        return IR.Alignment(h, IR.VerticalAlignment.TOP)
    }

    fun forHStack(alignment: DocAlignment?): IR.Alignment {
        val v = alignment?.vertical?.toIR() ?: IR.VerticalAlignment.CENTER
        return IR.Alignment(IR.HorizontalAlignment.LEADING, v)
    }

    fun forZStack(alignment: DocAlignment?): IR.Alignment {
        return alignment?.toIR() ?: IR.Alignment.center
    }
}

object PaddingConverter {
    fun merge(
        componentPadding: com.example.scalsandroid.scals.document.Padding?,
        stylePaddingTop: Double?,
        stylePaddingBottom: Double?,
        stylePaddingLeading: Double?,
        stylePaddingTrailing: Double?,
    ): IR.EdgeInsets {
        val cp = componentPadding
        return IR.EdgeInsets(
            top = cp?.resolvedTop ?: stylePaddingTop ?: 0.0,
            leading = cp?.resolvedLeading ?: stylePaddingLeading ?: 0.0,
            bottom = cp?.resolvedBottom ?: stylePaddingBottom ?: 0.0,
            trailing = cp?.resolvedTrailing ?: stylePaddingTrailing ?: 0.0,
        )
    }
}

object ColorSchemeConverter {
    fun convert(value: String?): IR.ColorScheme = when (value?.lowercase()) {
        "light" -> IR.ColorScheme.LIGHT
        "dark" -> IR.ColorScheme.DARK
        "system" -> IR.ColorScheme.SYSTEM
        else -> IR.ColorScheme.SYSTEM
    }
}
