package com.example.scalsandroid.scals.viewtree

class DependencyIndex {

    private val readDependencies: MutableMap<String, MutableSet<ViewNode>> = mutableMapOf()
    private val writeDependencies: MutableMap<String, MutableSet<ViewNode>> = mutableMapOf()

    fun register(node: ViewNode) {
        for (path in node.readPaths) {
            readDependencies.getOrPut(path) { mutableSetOf() }.add(node)
        }
        for (path in node.writePaths) {
            writeDependencies.getOrPut(path) { mutableSetOf() }.add(node)
        }
    }

    fun unregister(node: ViewNode) {
        for (path in node.readPaths) {
            readDependencies[path]?.remove(node)
        }
        for (path in node.writePaths) {
            writeDependencies[path]?.remove(node)
        }
    }

    fun updateRegistration(node: ViewNode) {
        // Remove old registrations across all paths
        readDependencies.values.forEach { it.remove(node) }
        writeDependencies.values.forEach { it.remove(node) }
        // Re-register with current paths
        register(node)
    }

    fun nodesReading(path: String): List<ViewNode> {
        return readDependencies[path]?.toList() ?: emptyList()
    }

    fun nodesWriting(path: String): List<ViewNode> {
        return writeDependencies[path]?.toList() ?: emptyList()
    }

    fun nodesAffectedBy(paths: Set<String>): Set<ViewNode> {
        val affected = mutableSetOf<ViewNode>()
        for (path in paths) {
            // Exact match
            readDependencies[path]?.let { affected.addAll(it) }
            // Parent path match: if "items" changed, nodes reading "items.count" are affected
            for ((registeredPath, nodes) in readDependencies) {
                if (registeredPath.startsWith("$path.") || registeredPath.startsWith("$path[")) {
                    affected.addAll(nodes)
                }
                // Child path match: if "items[0].name" changed, nodes reading "items" are affected
                if (path.startsWith("$registeredPath.") || path.startsWith("$registeredPath[")) {
                    affected.addAll(nodes)
                }
            }
        }
        return affected
    }

    fun clear() {
        readDependencies.clear()
        writeDependencies.clear()
    }
}
