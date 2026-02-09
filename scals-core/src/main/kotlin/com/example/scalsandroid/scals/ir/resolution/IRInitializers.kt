package com.example.scalsandroid.scals.ir.resolution

import com.example.scalsandroid.scals.document.Padding
import com.example.scalsandroid.scals.ir.IR

object IRInitializers {

    fun edgeInsetsFrom(componentPadding: Padding?, resolvedStyle: ResolvedStyle): IR.EdgeInsets {
        return IR.EdgeInsets(
            top = componentPadding?.resolvedTop ?: resolvedStyle.paddingTop ?: 0.0,
            leading = componentPadding?.resolvedLeading ?: resolvedStyle.paddingLeading ?: 0.0,
            bottom = componentPadding?.resolvedBottom ?: resolvedStyle.paddingBottom ?: 0.0,
            trailing = componentPadding?.resolvedTrailing ?: resolvedStyle.paddingTrailing ?: 0.0,
        )
    }

    fun shadowFrom(resolvedStyle: ResolvedStyle): IR.Shadow? {
        if (!resolvedStyle.hasShadow) return null
        return IR.Shadow(
            color = resolvedStyle.shadowColor ?: IR.Color.black,
            radius = resolvedStyle.shadowRadius ?: 0.0,
            x = resolvedStyle.shadowX ?: 0.0,
            y = resolvedStyle.shadowY ?: 0.0,
        )
    }

    fun borderFrom(resolvedStyle: ResolvedStyle): IR.Border? {
        if (!resolvedStyle.hasBorder) return null
        return IR.Border(
            color = resolvedStyle.borderColor ?: IR.Color.black,
            width = resolvedStyle.borderWidth ?: 0.0,
        )
    }
}
