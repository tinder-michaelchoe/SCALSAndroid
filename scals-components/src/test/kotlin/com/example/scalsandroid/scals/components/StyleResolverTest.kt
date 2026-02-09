package com.example.scalsandroid.scals.components

import com.example.scalsandroid.scals.document.FontWeight
import com.example.scalsandroid.scals.document.Style
import com.example.scalsandroid.scals.ir.resolution.StyleResolver
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Test

class StyleResolverTest {

    @Test
    fun testSimpleStyle() {
        // Create styles
        val styles = mapOf(
            "heading" to Style(
                fontSize = 24.0,
                fontWeight = FontWeight.BOLD,
                textColor = "#000000"
            )
        )
        val resolver = StyleResolver(styles)

        // Resolve the style
        val resolved = resolver.resolve("heading")

        // Verify the result
        assertNotNull(resolved)
        assertEquals(24.0, resolved.fontSize)
        assertEquals(FontWeight.BOLD, resolved.fontWeight)
        assertNotNull(resolved.textColor)
    }

    @Test
    fun testInheritance() {
        // Create styles with inheritance
        val styles = mapOf(
            "base" to Style(
                fontSize = 16.0,
                textColor = "#333333"
            ),
            "heading" to Style(
                inherits = "base",
                fontSize = 24.0,
                fontWeight = FontWeight.BOLD
            )
        )
        val resolver = StyleResolver(styles)

        // Resolve the child style
        val resolved = resolver.resolve("heading")

        // Verify that both parent and child properties are present
        assertNotNull(resolved)
        assertEquals(24.0, resolved.fontSize) // Child overrides parent
        assertEquals(FontWeight.BOLD, resolved.fontWeight)
        assertNotNull(resolved.textColor) // Inherited from base
    }

    @Test
    fun testInlineOverride() {
        // Create a named style
        val styles = mapOf(
            "heading" to Style(
                fontSize = 24.0,
                fontWeight = FontWeight.BOLD,
                textColor = "#000000"
            )
        )
        val resolver = StyleResolver(styles)

        // Create an inline style that overrides fontSize
        val inlineStyle = Style(
            fontSize = 32.0
        )

        // Resolve with inline override
        val resolved = resolver.resolve("heading", inlineStyle)

        // Verify that inline overrides named style
        assertNotNull(resolved)
        assertEquals(32.0, resolved.fontSize) // Overridden by inline
        assertEquals(FontWeight.BOLD, resolved.fontWeight) // From named style
    }

    @Test
    fun testCycleDetection() {
        // Create styles with circular inheritance
        val styles = mapOf(
            "styleA" to Style(
                inherits = "styleB",
                fontSize = 16.0
            ),
            "styleB" to Style(
                inherits = "styleA",
                textColor = "#000000"
            )
        )
        val resolver = StyleResolver(styles)

        // Resolve a style with circular inheritance
        // This should not cause an infinite loop
        val resolved = resolver.resolve("styleA")

        // Verify that resolution completes without hanging
        assertNotNull(resolved)
        // The cycle is detected by the visited set, so inheritance chain stops
    }
}
