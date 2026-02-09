package com.example.scalsandroid.scals.components

import com.example.scalsandroid.scals.components.manifests.CoreManifest
import com.example.scalsandroid.scals.document.Component
import com.example.scalsandroid.scals.document.ComponentKind
import com.example.scalsandroid.scals.document.Definition
import com.example.scalsandroid.scals.document.RootComponent
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class CoreManifestTest {

    @Test
    fun testCreateRegistries() {
        // Create registries from the manifest
        val registries = CoreManifest.createRegistries()

        // Verify that registries are created
        assertNotNull(registries)
        assertNotNull(registries.componentRegistry)
        assertNotNull(registries.actionResolverRegistry)
        assertNotNull(registries.sectionLayoutConfigRegistry)

        // Verify that expected resolvers are registered
        assertTrue(registries.componentRegistry.hasResolver(ComponentKind("label")))
        assertTrue(registries.componentRegistry.hasResolver(ComponentKind("button")))
        assertTrue(registries.componentRegistry.hasResolver(ComponentKind("image")))

        // Verify that unknown resolvers are not registered
        assertFalse(registries.componentRegistry.hasResolver(ComponentKind("unknownType")))
    }

    @Test
    fun testCreateResolver() {
        // Create a simple document
        val doc = Definition(
            id = "test",
            root = RootComponent(
                children = listOf(
                    Component(
                        type = ComponentKind("label"),
                        text = "Hello"
                    )
                )
            )
        )

        // Create a resolver from the manifest
        val resolver = CoreManifest.createResolver(doc)

        // Resolve the document
        val renderTree = resolver.resolve()

        // Verify the render tree
        assertNotNull(renderTree)
        assertNotNull(renderTree.root)
        assertFalse(renderTree.root.children.isEmpty())

        // Verify that the first child was resolved
        val firstChild = renderTree.root.children.first()
        assertNotNull(firstChild)
        assertEquals("text", firstChild.kind.rawValue)
    }
}
