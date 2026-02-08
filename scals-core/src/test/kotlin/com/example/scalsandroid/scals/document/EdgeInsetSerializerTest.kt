package com.example.scalsandroid.scals.document

import org.junit.Assert.*
import org.junit.Test

class EdgeInsetSerializerTest {

    @Test
    fun `decode number as safeArea`() {
        val result = ScalsJson.decodeFromString(EdgeInset.serializer(), "10.0")
        assertEquals(Positioning.SAFE_AREA, result.positioning)
        assertEquals(10.0, result.value, 0.001)
    }

    @Test
    fun `decode object with positioning`() {
        val result = ScalsJson.decodeFromString(
            EdgeInset.serializer(),
            """{"positioning": "absolute", "value": 20}"""
        )
        assertEquals(Positioning.ABSOLUTE, result.positioning)
        assertEquals(20.0, result.value, 0.001)
    }

    @Test
    fun `round trip safeArea`() {
        val value = EdgeInset(Positioning.SAFE_AREA, 15.0)
        val json = ScalsJson.encodeToString(EdgeInset.serializer(), value)
        val decoded = ScalsJson.decodeFromString(EdgeInset.serializer(), json)
        assertEquals(value, decoded)
    }

    @Test
    fun `round trip absolute`() {
        val value = EdgeInset(Positioning.ABSOLUTE, 25.0)
        val json = ScalsJson.encodeToString(EdgeInset.serializer(), value)
        val decoded = ScalsJson.decodeFromString(EdgeInset.serializer(), json)
        assertEquals(value, decoded)
    }
}
