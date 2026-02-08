package com.example.scalsandroid.scals.document

import org.junit.Assert.*
import org.junit.Test

class DimensionValueSerializerTest {

    @Test
    fun `decode number shorthand as absolute`() {
        val result = ScalsJson.decodeFromString(DimensionValue.serializer(), "42.0")
        assertEquals(DimensionValue.Absolute(42.0), result)
    }

    @Test
    fun `decode integer as absolute`() {
        val result = ScalsJson.decodeFromString(DimensionValue.serializer(), "100")
        assertEquals(DimensionValue.Absolute(100.0), result)
    }

    @Test
    fun `decode absolute object`() {
        val result = ScalsJson.decodeFromString(DimensionValue.serializer(), """{"absolute": 50}""")
        assertEquals(DimensionValue.Absolute(50.0), result)
    }

    @Test
    fun `decode fractional object`() {
        val result = ScalsJson.decodeFromString(DimensionValue.serializer(), """{"fractional": 0.5}""")
        assertEquals(DimensionValue.Fractional(0.5), result)
    }

    @Test
    fun `round trip absolute`() {
        val value = DimensionValue.Absolute(75.0)
        val json = ScalsJson.encodeToString(DimensionValue.serializer(), value)
        val decoded = ScalsJson.decodeFromString(DimensionValue.serializer(), json)
        assertEquals(value, decoded)
    }

    @Test
    fun `round trip fractional`() {
        val value = DimensionValue.Fractional(0.33)
        val json = ScalsJson.encodeToString(DimensionValue.serializer(), value)
        val decoded = ScalsJson.decodeFromString(DimensionValue.serializer(), json)
        assertEquals(value, decoded)
    }
}
