package com.example.scalsandroid.scals.components.nodes

import com.example.scalsandroid.scals.document.ActionBinding
import com.example.scalsandroid.scals.document.FontWeight
import com.example.scalsandroid.scals.document.ImageSource
import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.ir.RenderNodeData
import com.example.scalsandroid.scals.ir.RenderNodeKind

data class ButtonStateStyle(
    val textColor: IR.Color = IR.Color.white,
    val fontSize: Double = 17.0,
    val fontWeight: FontWeight = FontWeight.SEMIBOLD,
    val backgroundColor: IR.Color? = null,
    val cornerRadius: Double = 0.0,
    val border: IR.Border? = null,
    val shadow: IR.Shadow? = null,
    val tintColor: IR.Color? = null,
    val width: IR.DimensionValue? = null,
    val height: IR.DimensionValue? = null,
    val minWidth: IR.DimensionValue? = null,
    val minHeight: IR.DimensionValue? = null,
    val maxWidth: IR.DimensionValue? = null,
    val maxHeight: IR.DimensionValue? = null,
    val padding: IR.EdgeInsets = IR.EdgeInsets.ZERO,
)

data class ButtonStyles(
    val normal: ButtonStateStyle,
    val selected: ButtonStateStyle? = null,
    val disabled: ButtonStateStyle? = null,
) {
    fun style(isSelected: Boolean, isDisabled: Boolean = false): ButtonStateStyle {
        if (isDisabled) return disabled ?: normal
        if (isSelected) return selected ?: normal
        return normal
    }
}

enum class ButtonImagePlacement {
    LEADING, TRAILING
}

data class ButtonShape(
    val type: String,
    val cornerRadius: Double? = null,
)

data class ButtonNode(
    override val id: String? = null,
    override val styleId: String? = null,
    val label: String = "",
    val styles: ButtonStyles,
    val isSelectedBinding: String? = null,
    val fillWidth: Boolean = false,
    val onTap: ActionBinding? = null,
    val image: ImageSource? = null,
    val imagePlacement: ButtonImagePlacement = ButtonImagePlacement.LEADING,
    val imageSpacing: Double = 8.0,
    val buttonShape: ButtonShape? = null,
) : RenderNodeData {
    override val nodeKind: RenderNodeKind get() = RenderNodeKinds.BUTTON
}
