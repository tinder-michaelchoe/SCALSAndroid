package com.example.scalsandroid.scals.viewtree

import com.example.scalsandroid.scals.state.KeypathAccessor

class ViewNode(
    val id: String,
    children: List<ViewNode> = emptyList(),
) {
    var parent: ViewNode? = null
    var children: List<ViewNode> = children
        set(value) {
            field = value
            value.forEach { it.parent = this }
        }

    var readPaths: Set<String> = emptySet()
    var writePaths: Set<String> = emptySet()
    var localState: MutableMap<String, Any?>? = null
    var needsUpdate: Boolean = false

    init {
        children.forEach { it.parent = this }
    }

    fun findNode(id: String): ViewNode? {
        if (this.id == id) return this
        for (child in children) {
            child.findNode(id)?.let { return it }
        }
        return null
    }

    fun allDescendants(): List<ViewNode> {
        val result = mutableListOf<ViewNode>()
        for (child in children) {
            result.add(child)
            result.addAll(child.allDescendants())
        }
        return result
    }

    fun pathFromRoot(): List<ViewNode> {
        val path = mutableListOf<ViewNode>()
        var current: ViewNode? = this
        while (current != null) {
            path.add(0, current)
            current = current.parent
        }
        return path
    }

    fun nearestLocalStateScope(): ViewNode? {
        var current: ViewNode? = this
        while (current != null) {
            if (current.localState != null) return current
            current = current.parent
        }
        return null
    }

    fun getLocalState(path: String): Any? {
        val scope = nearestLocalStateScope() ?: return null
        val state = scope.localState ?: return null
        return KeypathAccessor.get(path, state)
    }

    fun setLocalState(path: String, value: Any?) {
        val scope = nearestLocalStateScope() ?: return
        val state = scope.localState ?: return
        KeypathAccessor.set(path, value, state)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ViewNode) return false
        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()

    override fun toString(): String = "ViewNode(id=$id, children=${children.size}, reads=$readPaths, writes=$writePaths)"
}
