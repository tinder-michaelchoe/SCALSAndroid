package com.example.scalsandroid.scals.viewtree

import org.junit.Test
import org.junit.Assert.*

class DependencyTrackerTest {

    @Test
    fun testTracking() {
        val tracker = DependencyTracker()
        val node = ViewNode("test")

        assertFalse(tracker.isTracking)

        tracker.beginTracking(node)
        assertTrue(tracker.isTracking)

        tracker.recordRead("name")
        tracker.recordWrite("count")

        tracker.endTracking()
        assertFalse(tracker.isTracking)

        assertTrue(node.readPaths.contains("name"))
        assertTrue(node.readPaths.contains("count"))
        assertTrue(node.writePaths.contains("count"))
        assertFalse(node.writePaths.contains("name"))
    }

    @Test
    fun testTrackConvenience() {
        val tracker = DependencyTracker()
        val node = ViewNode("test")

        tracker.track(node) {
            tracker.recordRead("a")
            tracker.recordWrite("b")
        }

        assertTrue(node.readPaths.contains("a"))
        assertTrue(node.readPaths.contains("b"))
        assertTrue(node.writePaths.contains("b"))
        assertFalse(tracker.isTracking)
    }
}
