package com.example.scalsandroid.scals.document

import org.junit.Assert.*
import org.junit.Test

class AlignmentSerializerTest {

    @Test
    fun `decode string shortcut - center`() {
        val result = ScalsJson.decodeFromString(Alignment.serializer(), "\"center\"")
        assertEquals(HorizontalAlignment.CENTER, result.horizontal)
        assertEquals(VerticalAlignment.CENTER, result.vertical)
    }

    @Test
    fun `decode string shortcut - topLeading`() {
        val result = ScalsJson.decodeFromString(Alignment.serializer(), "\"topLeading\"")
        assertEquals(HorizontalAlignment.LEADING, result.horizontal)
        assertEquals(VerticalAlignment.TOP, result.vertical)
    }

    @Test
    fun `decode string shortcut - bottomTrailing`() {
        val result = ScalsJson.decodeFromString(Alignment.serializer(), "\"bottomTrailing\"")
        assertEquals(HorizontalAlignment.TRAILING, result.horizontal)
        assertEquals(VerticalAlignment.BOTTOM, result.vertical)
    }

    @Test
    fun `decode string shortcut - leading (horizontal only)`() {
        val result = ScalsJson.decodeFromString(Alignment.serializer(), "\"leading\"")
        assertEquals(HorizontalAlignment.LEADING, result.horizontal)
        assertNull(result.vertical)
    }

    @Test
    fun `decode string shortcut - top (vertical only)`() {
        val result = ScalsJson.decodeFromString(Alignment.serializer(), "\"top\"")
        assertNull(result.horizontal)
        assertEquals(VerticalAlignment.TOP, result.vertical)
    }

    @Test
    fun `decode string shortcut - bottom`() {
        val result = ScalsJson.decodeFromString(Alignment.serializer(), "\"bottom\"")
        assertNull(result.horizontal)
        assertEquals(VerticalAlignment.BOTTOM, result.vertical)
    }

    @Test
    fun `decode string shortcut - trailing`() {
        val result = ScalsJson.decodeFromString(Alignment.serializer(), "\"trailing\"")
        assertEquals(HorizontalAlignment.TRAILING, result.horizontal)
        assertNull(result.vertical)
    }

    @Test
    fun `decode string shortcut - topTrailing`() {
        val result = ScalsJson.decodeFromString(Alignment.serializer(), "\"topTrailing\"")
        assertEquals(HorizontalAlignment.TRAILING, result.horizontal)
        assertEquals(VerticalAlignment.TOP, result.vertical)
    }

    @Test
    fun `decode string shortcut - bottomLeading`() {
        val result = ScalsJson.decodeFromString(Alignment.serializer(), "\"bottomLeading\"")
        assertEquals(HorizontalAlignment.LEADING, result.horizontal)
        assertEquals(VerticalAlignment.BOTTOM, result.vertical)
    }

    @Test
    fun `decode object form`() {
        val result = ScalsJson.decodeFromString(
            Alignment.serializer(),
            """{"horizontal": "trailing", "vertical": "bottom"}"""
        )
        assertEquals(HorizontalAlignment.TRAILING, result.horizontal)
        assertEquals(VerticalAlignment.BOTTOM, result.vertical)
    }

    @Test
    fun `decode object form partial`() {
        val result = ScalsJson.decodeFromString(
            Alignment.serializer(),
            """{"horizontal": "center"}"""
        )
        assertEquals(HorizontalAlignment.CENTER, result.horizontal)
        assertNull(result.vertical)
    }

    @Test
    fun `round trip shortcut`() {
        val value = Alignment(HorizontalAlignment.CENTER, VerticalAlignment.CENTER)
        val json = ScalsJson.encodeToString(Alignment.serializer(), value)
        assertEquals("\"center\"", json)
        val decoded = ScalsJson.decodeFromString(Alignment.serializer(), json)
        assertEquals(value, decoded)
    }

    @Test
    fun `round trip object form`() {
        val value = Alignment(HorizontalAlignment.CENTER, null)
        val json = ScalsJson.encodeToString(Alignment.serializer(), value)
        val decoded = ScalsJson.decodeFromString(Alignment.serializer(), json)
        assertEquals(value, decoded)
    }
}
