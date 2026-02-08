package com.example.scalsandroid.scals.document

import org.junit.Assert.*
import org.junit.Test

class ActionBindingSerializerTest {

    @Test
    fun `decode string as reference`() {
        val result = ScalsJson.decodeFromString(ActionBinding.serializer(), "\"submitForm\"")
        assertTrue(result is ActionBinding.Reference)
        assertEquals("submitForm", (result as ActionBinding.Reference).name)
    }

    @Test
    fun `decode object as inline action`() {
        val result = ScalsJson.decodeFromString(
            ActionBinding.serializer(),
            """{"type": "navigate", "destination": "home"}"""
        )
        assertTrue(result is ActionBinding.Inline)
        val action = (result as ActionBinding.Inline).action
        assertEquals(ActionKind("navigate"), action.type)
        assertEquals(StateValue.StringValue("home"), action.parameters["destination"])
    }

    @Test
    fun `round trip reference`() {
        val value = ActionBinding.Reference("myAction")
        val json = ScalsJson.encodeToString(ActionBinding.serializer(), value)
        val decoded = ScalsJson.decodeFromString(ActionBinding.serializer(), json)
        assertEquals(value, decoded)
    }

    @Test
    fun `round trip inline`() {
        val value = ActionBinding.Inline(Action(ActionKind("test"), mapOf("x" to StateValue.IntValue(1L))))
        val json = ScalsJson.encodeToString(ActionBinding.serializer(), value)
        val decoded = ScalsJson.decodeFromString(ActionBinding.serializer(), json)
        assertEquals(value, decoded)
    }
}
