package com.example.scalsandroid.scals.components

import com.example.scalsandroid.scals.components.resolvers.TextResolver
import com.example.scalsandroid.scals.document.Component
import com.example.scalsandroid.scals.document.ComponentKind
import com.example.scalsandroid.scals.document.Definition
import com.example.scalsandroid.scals.document.RootComponent
import com.example.scalsandroid.scals.document.StateValue
import com.example.scalsandroid.scals.ir.resolution.ComponentResolverRegistry
import com.example.scalsandroid.scals.ir.resolution.ComponentResolutionError
import com.example.scalsandroid.scals.ir.resolution.ResolutionContext
import com.example.scalsandroid.scals.state.StateStore
import org.junit.Assert.*
import org.junit.Test

class ComponentResolverRegistryTest {

    @Test
    fun testRegisterAndResolve() {
        // Create a registry and register the TextResolver
        val registry = ComponentResolverRegistry()
        registry.register(TextResolver())

        // Create a simple document and context
        val document = Definition(
            id = "test",
            root = RootComponent()
        )
        val stateStore = StateStore()
        val context = ResolutionContext.withoutTracking(document, stateStore)

        // Create a label component
        val component = Component(
            type = ComponentKind("label"),
            text = "Hello"
        )

        // Resolve the component
        val result = registry.resolve(component, context)

        // Verify the result
        assertNotNull(result)
        assertNotNull(result.renderNode)
    }

    @Test
    fun testUnknownKindThrows() {
        // Create an empty registry
        val registry = ComponentResolverRegistry()

        // Create a simple document and context
        val document = Definition(
            id = "test",
            root = RootComponent()
        )
        val stateStore = StateStore()
        val context = ResolutionContext.withoutTracking(document, stateStore)

        // Create a component with an unknown type
        val component = Component(
            type = ComponentKind("unknownType"),
            text = "Hello"
        )

        // Try to resolve and expect an exception
        try {
            registry.resolve(component, context)
            fail("Expected ComponentResolutionError.UnknownKind to be thrown")
        } catch (e: ComponentResolutionError.UnknownKind) {
            assertEquals("unknownType", e.kind.rawValue)
        }
    }
}
