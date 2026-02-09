package com.example.scalsandroid.scals.viewtree

class DependencyTracker {

    private var currentNode: ViewNode? = null
    private var accessedPaths: MutableSet<String> = mutableSetOf()
    private var writtenPaths: MutableSet<String> = mutableSetOf()
    val isTracking: Boolean get() = currentNode != null

    fun beginTracking(node: ViewNode) {
        currentNode = node
        accessedPaths = mutableSetOf()
        writtenPaths = mutableSetOf()
    }

    fun endTracking() {
        val node = currentNode ?: return
        node.readPaths = accessedPaths.toSet()
        node.writePaths = writtenPaths.toSet()
        currentNode = null
        accessedPaths = mutableSetOf()
        writtenPaths = mutableSetOf()
    }

    fun recordRead(path: String) {
        if (currentNode != null) {
            accessedPaths.add(path)
        }
    }

    fun recordWrite(path: String) {
        if (currentNode != null) {
            writtenPaths.add(path)
            // Writes imply reads
            accessedPaths.add(path)
        }
    }

    fun recordLocalRead(path: String) {
        recordRead("local.$path")
    }

    fun recordLocalWrite(path: String) {
        recordWrite("local.$path")
    }

    fun track(node: ViewNode, block: () -> Unit) {
        beginTracking(node)
        try {
            block()
        } finally {
            endTracking()
        }
    }
}
