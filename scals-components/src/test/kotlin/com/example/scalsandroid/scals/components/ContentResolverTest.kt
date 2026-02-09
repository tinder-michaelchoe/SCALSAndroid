package com.example.scalsandroid.scals.components

import com.example.scalsandroid.scals.document.Component
import com.example.scalsandroid.scals.document.ComponentKind
import com.example.scalsandroid.scals.document.Definition
import com.example.scalsandroid.scals.document.RootComponent
import com.example.scalsandroid.scals.document.StateValue
import com.example.scalsandroid.scals.ir.resolution.ContentResolver
import com.example.scalsandroid.scals.ir.resolution.ResolutionContext
import com.example.scalsandroid.scals.state.StateStore
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ContentResolverTest {

    @Test
    fun testStaticText() {
        // Create a document and context
        val document = Definition(
            id = "test",
            root = RootComponent()
        )
        val stateStore = StateStore()
        val context = ResolutionContext.withoutTracking(document, stateStore)

        // Create a component with static text
        val component = Component(
            type = ComponentKind("label"),
            text = "Hello"
        )

        // Resolve content
        val result = ContentResolver.resolve(component, context)

        // Verify the result
        assertEquals("Hello", result.content)
        assertFalse(result.isDynamic)
    }

    @Test
    fun testBindingResolution() {
        // Create a document with state
        val document = Definition(
            id = "test",
            root = RootComponent(),
            state = mapOf(
                "username" to StateValue.StringValue("John")
            )
        )
        val stateStore = StateStore()
        stateStore.initialize(document.state)
        val context = ResolutionContext.withoutTracking(document, stateStore)

        // Create a component with a binding
        val component = Component(
            type = ComponentKind("label"),
            binding = "username"
        )

        // Resolve content
        val result = ContentResolver.resolve(component, context)

        // Verify the result
        assertEquals("John", result.content)
        assertEquals("username", result.bindingPath)
        assertTrue(result.isDynamic)
    }

    @Test
    fun testTemplateInterpolation() {
        // Create a document with state
        val document = Definition(
            id = "test",
            root = RootComponent(),
            state = mapOf(
                "name" to StateValue.StringValue("World")
            )
        )
        val stateStore = StateStore()
        stateStore.initialize(document.state)
        val context = ResolutionContext.withoutTracking(document, stateStore)

        // Create a component with template interpolation
        val component = Component(
            type = ComponentKind("label"),
            text = "Hello \${name}!"
        )

        // Resolve content
        val result = ContentResolver.resolve(component, context)

        // Verify the result
        assertEquals("Hello World!", result.content)
        assertEquals("Hello \${name}!", result.bindingTemplate)
        assertTrue(result.isDynamic)
    }
}
