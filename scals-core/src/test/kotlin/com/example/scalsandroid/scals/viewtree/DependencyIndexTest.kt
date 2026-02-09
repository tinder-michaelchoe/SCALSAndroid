package com.example.scalsandroid.scals.viewtree

import org.junit.Test
import org.junit.Assert.*

class DependencyIndexTest {

    @Test
    fun testRegisterAndLookup() {
        val index = DependencyIndex()
        val node = ViewNode("test")
        node.readPaths = setOf("name", "age")
        node.writePaths = setOf("count")

        index.register(node)

        val readersOfName = index.nodesReading("name")
        assertEquals(1, readersOfName.size)
        assertTrue(readersOfName.contains(node))

        val writersOfCount = index.nodesWriting("count")
        assertEquals(1, writersOfCount.size)
        assertTrue(writersOfCount.contains(node))
    }

    @Test
    fun testNodesAffectedBy() {
        val index = DependencyIndex()

        val node1 = ViewNode("node1")
        node1.readPaths = setOf("items")
        index.register(node1)

        val node2 = ViewNode("node2")
        node2.readPaths = setOf("items.count")
        index.register(node2)

        val node3 = ViewNode("node3")
        node3.readPaths = setOf("items[0].name")
        index.register(node3)

        // Changing "items" should affect all nodes
        val affected = index.nodesAffectedBy(setOf("items"))
        assertEquals(3, affected.size)

        // Changing "items[0].name" should affect node1 and node3
        val affected2 = index.nodesAffectedBy(setOf("items[0].name"))
        assertTrue(affected2.contains(node1))
        assertTrue(affected2.contains(node3))
    }
}
