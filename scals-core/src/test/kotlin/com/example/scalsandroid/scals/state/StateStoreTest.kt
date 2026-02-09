package com.example.scalsandroid.scals.state

import com.example.scalsandroid.scals.document.StateValue
import org.junit.Test
import org.junit.Assert.*

class StateStoreTest {

    @Test
    fun testInitializeFromDocument() {
        val store = StateStore()
        val initialState = mapOf(
            "name" to StateValue.StringValue("John"),
            "age" to StateValue.IntValue(30),
            "active" to StateValue.BoolValue(true)
        )
        store.initialize(initialState)
        assertEquals("John", store.getValue("name"))
        assertEquals(30L, store.getValue("age"))
        assertEquals(true, store.getValue("active"))
    }

    @Test
    fun testGetSet() {
        val store = StateStore()
        store.set("name", "Alice")
        assertEquals("Alice", store.getValue("name"))

        store.set("count", 42)
        assertEquals(42, store.getValue("count"))
    }

    @Test
    fun testNestedGetSet() {
        val store = StateStore()
        store.set("user.name", "Bob")
        assertEquals("Bob", store.getValue("user.name"))
    }

    @Test
    fun testArrayOps() {
        val store = StateStore()
        store.set("items", listOf("a", "b"))

        store.appendToArray("items", "c")
        val items = store.getArray("items")
        assertEquals(3, items?.size)
        assertTrue(items?.contains("c") ?: false)

        store.removeFromArray("items", "b")
        val items2 = store.getArray("items")
        assertEquals(2, items2?.size)
        assertFalse(items2?.contains("b") ?: true)

        store.toggleInArray("items", "d")
        assertTrue(store.arrayContains("items", "d"))

        store.toggleInArray("items", "d")
        assertFalse(store.arrayContains("items", "d"))
    }

    @Test
    fun testDirtyTracking() {
        val store = StateStore()
        assertFalse(store.hasDirtyPaths)

        store.set("name", "Test")
        assertTrue(store.hasDirtyPaths)
        assertTrue(store.isDirty("name"))

        val dirtyPaths = store.consumeDirtyPaths()
        assertTrue(dirtyPaths.contains("name"))
        assertFalse(store.hasDirtyPaths)
    }

    @Test
    fun testCallbacks() {
        val store = StateStore()
        var called = false
        var capturedPath = ""
        var capturedNewValue: Any? = null

        val id = store.onStateChange { path, _, newValue ->
            called = true
            capturedPath = path
            capturedNewValue = newValue
        }

        store.set("name", "Test")
        assertTrue(called)
        assertEquals("name", capturedPath)
        assertEquals("Test", capturedNewValue)

        called = false
        store.removeStateChangeCallback(id)
        store.set("name", "Test2")
        assertFalse(called)
    }

    @Test
    fun testSnapshotRestore() {
        val store = StateStore()
        store.set("name", "Alice")
        store.set("age", 30)

        val snapshot = store.snapshot()
        assertEquals("Alice", snapshot["name"])
        assertEquals(30, snapshot["age"])

        store.set("name", "Bob")
        assertEquals("Bob", store.getValue("name"))

        store.restore(snapshot)
        assertEquals("Alice", store.getValue("name"))
        assertEquals(30, store.getValue("age"))
    }
}
