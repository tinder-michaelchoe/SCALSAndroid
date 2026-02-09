package com.example.scalsandroid.scals.state

import org.junit.Test
import org.junit.Assert.*

class KeypathAccessorTest {

    @Test
    fun testSimpleGet() {
        val map = mapOf("name" to "John", "age" to 30)
        val result = KeypathAccessor.get("name", map)
        assertEquals("John", result)
    }

    @Test
    fun testNestedGet() {
        val map = mapOf("user" to mapOf("name" to "Jane", "age" to 25))
        val result = KeypathAccessor.get("user.name", map)
        assertEquals("Jane", result)
    }

    @Test
    fun testArrayIndexGet() {
        val map = mapOf("items" to listOf("apple", "banana", "cherry"))
        val result = KeypathAccessor.get("items[0]", map)
        assertEquals("apple", result)

        val result2 = KeypathAccessor.get("items[1]", map)
        assertEquals("banana", result2)
    }

    @Test
    fun testSimpleSet() {
        val map = mutableMapOf<String, Any?>("name" to "John")
        KeypathAccessor.set("name", "Jane", map)
        assertEquals("Jane", map["name"])
    }

    @Test
    fun testNestedSet() {
        val map = mutableMapOf<String, Any?>("user" to mapOf("name" to "John"))
        KeypathAccessor.set("user.name", "Jane", map)

        @Suppress("UNCHECKED_CAST")
        val user = map["user"] as? Map<String, Any?>
        assertEquals("Jane", user?.get("name"))
    }

    @Test
    fun testParentPaths() {
        val paths = KeypathAccessor.parentPaths("a.b.c")
        assertEquals(2, paths.size)
        assertEquals("a", paths[0])
        assertEquals("a.b", paths[1])
    }
}
