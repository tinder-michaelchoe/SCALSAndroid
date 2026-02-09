package com.example.scalsandroid.scals.viewtree

import com.example.scalsandroid.scals.state.StateStore
import java.util.UUID

class ViewTreeUpdater {

    val dependencyTracker: DependencyTracker = DependencyTracker()
    val dependencyIndex: DependencyIndex = DependencyIndex()
    var root: ViewNode? = null
        private set

    var onNodesNeedUpdate: ((Set<ViewNode>) -> Unit)? = null

    private var stateCallbackId: UUID? = null

    fun setRoot(node: ViewNode) {
        root = node
        dependencyIndex.clear()
        // Register all nodes in the tree
        registerAll(node)
    }

    fun attach(stateStore: StateStore) {
        // Remove previous subscription if any
        stateCallbackId?.let { stateStore.removeStateChangeCallback(it) }

        stateCallbackId = stateStore.onStateChange { path, _, _ ->
            val affected = dependencyIndex.nodesAffectedBy(setOf(path))
            if (affected.isNotEmpty()) {
                affected.forEach { it.needsUpdate = true }
                onNodesNeedUpdate?.invoke(affected)
            }
        }
    }

    fun getMinimalUpdateSet(): Set<ViewNode> {
        val root = this.root ?: return emptySet()
        val allNeedingUpdate = mutableSetOf<ViewNode>()

        fun collect(node: ViewNode) {
            if (node.needsUpdate) {
                allNeedingUpdate.add(node)
            }
            node.children.forEach { collect(it) }
        }
        collect(root)

        // Exclude children of nodes that are already being updated
        return allNeedingUpdate.filter { node ->
            var parent = node.parent
            while (parent != null) {
                if (parent in allNeedingUpdate) return@filter false
                parent = parent.parent
            }
            true
        }.toSet()
    }

    private fun registerAll(node: ViewNode) {
        dependencyIndex.register(node)
        node.children.forEach { registerAll(it) }
    }
}
