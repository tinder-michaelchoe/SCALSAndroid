package com.example.scalsandroid.scals.document

import org.junit.Assert.*
import org.junit.Test

class ActionSerializerTest {

    @Test
    fun `decode action with type only`() {
        val result = ScalsJson.decodeFromString(Action.serializer(), """{"type": "navigate"}""")
        assertEquals(ActionKind("navigate"), result.type)
        assertTrue(result.parameters.isEmpty())
    }

    @Test
    fun `decode action with parameters`() {
        val result = ScalsJson.decodeFromString(
            Action.serializer(),
            """{"type": "setState", "key": "counter", "value": 42}"""
        )
        assertEquals(ActionKind("setState"), result.type)
        assertEquals(StateValue.StringValue("counter"), result.parameters["key"])
        assertEquals(StateValue.IntValue(42L), result.parameters["value"])
    }

    @Test
    fun `decode action with nested parameters`() {
        val result = ScalsJson.decodeFromString(
            Action.serializer(),
            """{"type": "api", "url": "https://example.com", "headers": {"auth": "token"}}"""
        )
        assertEquals(ActionKind("api"), result.type)
        assertEquals(StateValue.StringValue("https://example.com"), result.parameters["url"])
        val headers = result.parameters["headers"]!!.objectValue!!
        assertEquals(StateValue.StringValue("token"), headers["auth"])
    }

    @Test
    fun `round trip action`() {
        val action = Action(
            type = ActionKind("submit"),
            parameters = mapOf(
                "field" to StateValue.StringValue("email"),
                "required" to StateValue.BoolValue(true),
            )
        )
        val json = ScalsJson.encodeToString(Action.serializer(), action)
        val decoded = ScalsJson.decodeFromString(Action.serializer(), json)
        assertEquals(action, decoded)
    }
}
