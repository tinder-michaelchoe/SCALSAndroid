package com.example.scalsandroid.scals.ir.resolution

import com.example.scalsandroid.scals.document.Style

class StyleResolver(
    private val styles: Map<String, Style>,
    private val designSystemProvider: DesignSystemProvider? = null,
) {
    fun resolve(styleId: String?): ResolvedStyle {
        return resolve(styleId, inline = null)
    }

    fun resolve(styleId: String?, inline: Style?): ResolvedStyle {
        val resolved = ResolvedStyle()

        // Resolve named style with inheritance
        if (styleId != null) {
            resolveInheritanceChain(styleId, resolved, mutableSetOf())
        }

        // Merge inline style on top
        if (inline != null) {
            resolved.mergeFrom(inline)
        }

        return resolved
    }

    private fun resolveInheritanceChain(
        styleId: String,
        resolved: ResolvedStyle,
        visited: MutableSet<String>,
    ) {
        if (styleId in visited) return // cycle detection
        visited.add(styleId)

        // Check design system styles (prefixed with @)
        if (styleId.startsWith("@")) {
            val dsStyle = designSystemProvider?.resolveStyle(styleId)
            if (dsStyle != null) {
                // Copy all properties from design system resolved style
                copyResolvedStyle(dsStyle, resolved)
            }
            return
        }

        val style = styles[styleId] ?: return

        // First resolve the parent style
        style.inherits?.let { parentId ->
            resolveInheritanceChain(parentId, resolved, visited)
        }

        // Then merge this style on top
        resolved.mergeFrom(style)
    }

    private fun copyResolvedStyle(from: ResolvedStyle, to: ResolvedStyle) {
        from.fontFamily?.let { to.fontFamily = it }
        from.fontSize?.let { to.fontSize = it }
        from.fontWeight?.let { to.fontWeight = it }
        from.textColor?.let { to.textColor = it }
        from.textAlignment?.let { to.textAlignment = it }
        from.backgroundColor?.let { to.backgroundColor = it }
        from.cornerRadius?.let { to.cornerRadius = it }
        from.borderWidth?.let { to.borderWidth = it }
        from.borderColor?.let { to.borderColor = it }
        from.shadowColor?.let { to.shadowColor = it }
        from.shadowRadius?.let { to.shadowRadius = it }
        from.shadowX?.let { to.shadowX = it }
        from.shadowY?.let { to.shadowY = it }
        from.tintColor?.let { to.tintColor = it }
        from.width?.let { to.width = it }
        from.height?.let { to.height = it }
        from.minWidth?.let { to.minWidth = it }
        from.minHeight?.let { to.minHeight = it }
        from.maxWidth?.let { to.maxWidth = it }
        from.maxHeight?.let { to.maxHeight = it }
        from.paddingTop?.let { to.paddingTop = it }
        from.paddingBottom?.let { to.paddingBottom = it }
        from.paddingLeading?.let { to.paddingLeading = it }
        from.paddingTrailing?.let { to.paddingTrailing = it }
    }
}
