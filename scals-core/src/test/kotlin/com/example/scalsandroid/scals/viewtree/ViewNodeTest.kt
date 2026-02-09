package com.example.scalsandroid.scals.viewtree

import org.junit.Test
import org.junit.Assert.*

class ViewNodeTest {

    @Test
    fun testFindNode() {
        val child1 = ViewNode("child1")
        val child2 = ViewNode("child2")
        val root = ViewNode("root", listOf(child1, child2))

        assertEquals(root, root.findNode("root"))
        assertEquals(child1, root.findNode("child1"))
        assertEquals(child2, root.findNode("child2"))
        assertNull(root.findNode("nonexistent"))
    }

    @Test
    fun testAllDescendants() {
        val grandchild = ViewNode("grandchild")
        val child1 = ViewNode("child1", listOf(grandchild))
        val child2 = ViewNode("child2")
        val root = ViewNode("root", listOf(child1, child2))

        val descendants = root.allDescendants()
        assertEquals(3, descendants.size)
        assertTrue(descendants.contains(child1))
        assertTrue(descendants.contains(child2))
        assertTrue(descendants.contains(grandchild))
    }

    @Test
    fun testPathFromRoot() {
        val grandchild = ViewNode("grandchild")
        val child = ViewNode("child", listOf(grandchild))
        val root = ViewNode("root", listOf(child))

        val path = grandchild.pathFromRoot()
        assertEquals(3, path.size)
        assertEquals(root, path[0])
        assertEquals(child, path[1])
        assertEquals(grandchild, path[2])
    }

    @Test
    fun testLocalState() {
        val child = ViewNode("child")
        val root = ViewNode("root", listOf(child))
        root.localState = mutableMapOf("count" to 5)

        assertEquals(root, child.nearestLocalStateScope())
        assertEquals(5, child.getLocalState("count"))

        child.setLocalState("count", 10)
        assertEquals(10, root.localState?.get("count"))
    }
}
