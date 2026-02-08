package com.example.scalsandroid.scals.document

import org.junit.Assert.*
import org.junit.Test

class StateValueSerializerTest {

    @Test
    fun `decode null`() {
        val result = ScalsJson.decodeFromString(StateValue.serializer(), "null")
        assertEquals(StateValue.NullValue, result)
    }

    @Test
    fun `decode string`() {
        val result = ScalsJson.decodeFromString(StateValue.serializer(), "\"hello\"")
        assertEquals(StateValue.StringValue("hello"), result)
        assertEquals("hello", result.stringValue)
    }

    @Test
    fun `decode boolean true`() {
        val result = ScalsJson.decodeFromString(StateValue.serializer(), "true")
        assertEquals(StateValue.BoolValue(true), result)
        assertEquals(true, result.boolValue)
    }

    @Test
    fun `decode boolean false`() {
        val result = ScalsJson.decodeFromString(StateValue.serializer(), "false")
        assertEquals(StateValue.BoolValue(false), result)
        assertEquals(false, result.boolValue)
    }

    @Test
    fun `decode integer`() {
        val result = ScalsJson.decodeFromString(StateValue.serializer(), "42")
        assertEquals(StateValue.IntValue(42L), result)
        assertEquals(42L, result.intValue)
    }

    @Test
    fun `decode double`() {
        val result = ScalsJson.decodeFromString(StateValue.serializer(), "3.14")
        assertEquals(StateValue.DoubleValue(3.14), result)
        assertEquals(3.14, result.doubleValue!!, 0.001)
    }

    @Test
    fun `decode array`() {
        val result = ScalsJson.decodeFromString(StateValue.serializer(), "[1, \"two\", true]")
        val arr = result.arrayValue!!
        assertEquals(3, arr.size)
        assertEquals(StateValue.IntValue(1L), arr[0])
        assertEquals(StateValue.StringValue("two"), arr[1])
        assertEquals(StateValue.BoolValue(true), arr[2])
    }

    @Test
    fun `decode object`() {
        val result = ScalsJson.decodeFromString(StateValue.serializer(), """{"key": "value", "num": 5}""")
        val obj = result.objectValue!!
        assertEquals(StateValue.StringValue("value"), obj["key"])
        assertEquals(StateValue.IntValue(5L), obj["num"])
    }

    @Test
    fun `decode nested structure`() {
        val json = """{"items": [1, 2, 3], "meta": {"active": true}}"""
        val result = ScalsJson.decodeFromString(StateValue.serializer(), json)
        val obj = result.objectValue!!
        val items = obj["items"]!!.arrayValue!!
        assertEquals(3, items.size)
        val meta = obj["meta"]!!.objectValue!!
        assertEquals(StateValue.BoolValue(true), meta["active"])
    }

    @Test
    fun `round trip all types`() {
        val values = listOf(
            StateValue.NullValue,
            StateValue.BoolValue(true),
            StateValue.IntValue(123L),
            StateValue.DoubleValue(1.5),
            StateValue.StringValue("test"),
            StateValue.ArrayValue(listOf(StateValue.IntValue(1L), StateValue.IntValue(2L))),
            StateValue.ObjectValue(mapOf("a" to StateValue.BoolValue(false))),
        )
        for (value in values) {
            val json = ScalsJson.encodeToString(StateValue.serializer(), value)
            val decoded = ScalsJson.decodeFromString(StateValue.serializer(), json)
            assertEquals(value, decoded)
        }
    }

    @Test
    fun `accessors return null for wrong type`() {
        val str = StateValue.StringValue("hi")
        assertNull(str.intValue)
        assertNull(str.doubleValue)
        assertNull(str.boolValue)
        assertNull(str.arrayValue)
        assertNull(str.objectValue)
        assertFalse(str.isNull)
    }

    @Test
    fun `bool decoded before int - true is not 1`() {
        val result = ScalsJson.decodeFromString(StateValue.serializer(), "true")
        assertTrue(result is StateValue.BoolValue)
        assertNull(result.intValue)
    }
}
