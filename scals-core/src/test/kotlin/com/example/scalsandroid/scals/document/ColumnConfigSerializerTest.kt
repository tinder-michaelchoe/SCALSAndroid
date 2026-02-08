package com.example.scalsandroid.scals.document

import org.junit.Assert.*
import org.junit.Test

class ColumnConfigSerializerTest {

    @Test
    fun `decode integer as fixed`() {
        val result = ScalsJson.decodeFromString(ColumnConfig.serializer(), "3")
        assertEquals(ColumnConfig.Fixed(3), result)
    }

    @Test
    fun `decode adaptive object`() {
        val result = ScalsJson.decodeFromString(ColumnConfig.serializer(), """{"adaptive": {"minWidth": 120}}""")
        assertEquals(ColumnConfig.Adaptive(120.0), result)
    }

    @Test
    fun `round trip fixed`() {
        val value = ColumnConfig.Fixed(4)
        val json = ScalsJson.encodeToString(ColumnConfig.serializer(), value)
        val decoded = ScalsJson.decodeFromString(ColumnConfig.serializer(), json)
        assertEquals(value, decoded)
    }

    @Test
    fun `round trip adaptive`() {
        val value = ColumnConfig.Adaptive(80.0)
        val json = ScalsJson.encodeToString(ColumnConfig.serializer(), value)
        val decoded = ScalsJson.decodeFromString(ColumnConfig.serializer(), json)
        assertEquals(value, decoded)
    }
}
