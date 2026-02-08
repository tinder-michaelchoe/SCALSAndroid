package com.example.scalsandroid.scals.document

import org.junit.Assert.*
import org.junit.Test

class ComponentSerializerTest {

    @Test
    fun `decode simple component`() {
        val json = """{"type": "text", "text": "Hello"}"""
        val result = ScalsJson.decodeFromString(Component.serializer(), json)
        assertEquals(ComponentKind("text"), result.type)
        assertEquals("Hello", result.text)
        assertTrue(result.additionalProperties.isEmpty())
    }

    @Test
    fun `decode component with additional properties`() {
        val json = """{"type": "custom", "text": "Hi", "customProp": "value", "customNum": 42}"""
        val result = ScalsJson.decodeFromString(Component.serializer(), json)
        assertEquals(ComponentKind("custom"), result.type)
        assertEquals("Hi", result.text)
        assertEquals(StateValue.StringValue("value"), result.additionalProperties["customProp"])
        assertEquals(StateValue.IntValue(42L), result.additionalProperties["customNum"])
    }

    @Test
    fun `decode component with style`() {
        val json = """{
            "type": "button",
            "text": "Click me",
            "style": {"backgroundColor": "#FF0000", "cornerRadius": 8}
        }"""
        val result = ScalsJson.decodeFromString(Component.serializer(), json)
        assertEquals(ComponentKind("button"), result.type)
        assertEquals("#FF0000", result.style?.backgroundColor)
        assertEquals(8.0, result.style?.cornerRadius!!, 0.001)
    }

    @Test
    fun `decode component with actions`() {
        val json = """{
            "type": "button",
            "actions": {"onTap": "handleTap"}
        }"""
        val result = ScalsJson.decodeFromString(Component.serializer(), json)
        assertTrue(result.actions?.onTap is ActionBinding.Reference)
    }

    @Test
    fun `decode component with dimension values`() {
        val json = """{
            "type": "image",
            "width": 100,
            "height": {"fractional": 0.5}
        }"""
        val result = ScalsJson.decodeFromString(Component.serializer(), json)
        assertEquals(DimensionValue.Absolute(100.0), result.width)
        assertEquals(DimensionValue.Fractional(0.5), result.height)
    }

    @Test
    fun `round trip component with additional properties`() {
        val component = Component(
            type = ComponentKind("custom"),
            text = "Test",
            additionalProperties = mapOf(
                "extra" to StateValue.StringValue("data"),
                "count" to StateValue.IntValue(3L),
            )
        )
        val json = ScalsJson.encodeToString(Component.serializer(), component)
        val decoded = ScalsJson.decodeFromString(Component.serializer(), json)
        assertEquals(component, decoded)
    }

    @Test
    fun `round trip component without additional properties`() {
        val component = Component(
            type = ComponentKind("text"),
            text = "Hello",
            backgroundColor = "#FFFFFF",
        )
        val json = ScalsJson.encodeToString(Component.serializer(), component)
        val decoded = ScalsJson.decodeFromString(Component.serializer(), json)
        assertEquals(component, decoded)
    }
}
