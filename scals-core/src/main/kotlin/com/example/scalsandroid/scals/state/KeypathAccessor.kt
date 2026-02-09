package com.example.scalsandroid.scals.state

object KeypathAccessor {

    internal sealed class PathComponent {
        data class Key(val name: String) : PathComponent()
        data class Index(val index: Int) : PathComponent()
    }

    internal fun parseKeypath(keypath: String): List<PathComponent> {
        val components = mutableListOf<PathComponent>()
        var current = StringBuilder()
        var i = 0
        while (i < keypath.length) {
            val c = keypath[i]
            when {
                c == '.' -> {
                    if (current.isNotEmpty()) {
                        components.add(parseComponent(current.toString()))
                        current = StringBuilder()
                    }
                    i++
                }
                c == '[' -> {
                    if (current.isNotEmpty()) {
                        components.add(PathComponent.Key(current.toString()))
                        current = StringBuilder()
                    }
                    val end = keypath.indexOf(']', i)
                    if (end == -1) {
                        current.append(c)
                        i++
                    } else {
                        val indexStr = keypath.substring(i + 1, end)
                        val index = indexStr.toIntOrNull()
                        if (index != null) {
                            components.add(PathComponent.Index(index))
                        } else {
                            components.add(PathComponent.Key(indexStr))
                        }
                        i = end + 1
                    }
                }
                else -> {
                    current.append(c)
                    i++
                }
            }
        }
        if (current.isNotEmpty()) {
            components.add(parseComponent(current.toString()))
        }
        return components
    }

    private fun parseComponent(s: String): PathComponent {
        val index = s.toIntOrNull()
        return if (index != null) PathComponent.Index(index) else PathComponent.Key(s)
    }

    fun get(keypath: String, from: Map<String, Any?>): Any? {
        val components = parseKeypath(keypath)
        if (components.isEmpty()) return null
        var current: Any? = null
        for ((i, comp) in components.withIndex()) {
            if (i == 0) {
                current = when (comp) {
                    is PathComponent.Key -> from[comp.name]
                    is PathComponent.Index -> return null // can't index into root map
                }
            } else {
                current = navigate(current, comp) ?: return null
            }
        }
        return current
    }

    @Suppress("UNCHECKED_CAST")
    fun set(keypath: String, value: Any?, inMap: MutableMap<String, Any?>) {
        val components = parseKeypath(keypath)
        if (components.isEmpty()) return

        if (components.size == 1) {
            val comp = components[0]
            if (comp is PathComponent.Key) {
                if (value == null) inMap.remove(comp.name) else inMap[comp.name] = value
            }
            return
        }

        val firstComp = components[0]
        if (firstComp !is PathComponent.Key) return

        // Build a chain of snapshots: each is a copy of the container at that level
        val chain = mutableListOf<Any?>()
        chain.add(inMap)

        var current: Any? = inMap[firstComp.name]
        for (i in 1 until components.size - 1) {
            if (current == null) {
                // Create intermediate map
                current = mutableMapOf<String, Any?>()
            }
            chain.add(current)
            current = navigate(current, components[i])
        }

        // current is the container for the last component
        if (current == null && components.last() is PathComponent.Key) {
            current = mutableMapOf<String, Any?>()
        }
        if (current == null) return

        // Set the final value in a mutable copy of the leaf container
        var result: Any? = when {
            current is Map<*, *> -> {
                @Suppress("UNCHECKED_CAST")
                val m = (current as Map<String, Any?>).toMutableMap()
                val last = components.last()
                if (last is PathComponent.Key) {
                    if (value == null) m.remove(last.name) else m[last.name] = value
                }
                m
            }
            current is List<*> -> {
                @Suppress("UNCHECKED_CAST")
                val l = (current as List<Any?>).toMutableList()
                val last = components.last()
                if (last is PathComponent.Index && last.index in l.indices) {
                    l[last.index] = value
                }
                l
            }
            else -> return
        }

        // Rebuild from bottom up
        for (i in components.size - 2 downTo 1) {
            val parent = chain.getOrNull(i) ?: break
            val comp = components[i]
            @Suppress("UNCHECKED_CAST")
            result = when {
                parent is Map<*, *> -> {
                    val m = (parent as Map<String, Any?>).toMutableMap()
                    if (comp is PathComponent.Key) m[comp.name] = result
                    m
                }
                parent is List<*> -> {
                    val l = (parent as List<Any?>).toMutableList()
                    if (comp is PathComponent.Index && comp.index in l.indices) l[comp.index] = result
                    l
                }
                else -> break
            }
        }

        // Set the top-level key
        inMap[firstComp.name] = result
    }

    fun rootKey(keypath: String): String? {
        val dot = keypath.indexOf('.')
        val bracket = keypath.indexOf('[')
        return when {
            dot == -1 && bracket == -1 -> keypath
            dot != -1 && (bracket == -1 || dot < bracket) -> keypath.substring(0, dot)
            bracket != -1 -> keypath.substring(0, bracket)
            else -> keypath
        }
    }

    fun parentPaths(keypath: String): List<String> {
        val paths = mutableListOf<String>()
        val components = parseKeypath(keypath)
        val builder = StringBuilder()
        for (i in 0 until components.size - 1) {
            if (builder.isNotEmpty()) builder.append(".")
            when (val comp = components[i]) {
                is PathComponent.Key -> builder.append(comp.name)
                is PathComponent.Index -> {
                    // Remove trailing dot if present
                    if (builder.endsWith(".")) {
                        builder.deleteCharAt(builder.length - 1)
                    }
                    builder.append("[${comp.index}]")
                }
            }
            paths.add(builder.toString())
        }
        return paths
    }

    @Suppress("UNCHECKED_CAST")
    private fun navigate(value: Any?, component: PathComponent): Any? {
        return when (component) {
            is PathComponent.Key -> {
                (value as? Map<String, Any?>)?.get(component.name)
            }
            is PathComponent.Index -> {
                (value as? List<Any?>)?.getOrNull(component.index)
            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    private fun setInValue(container: Any?, component: PathComponent, value: Any?): Any? {
        return when (component) {
            is PathComponent.Key -> {
                val map = when (container) {
                    is MutableMap<*, *> -> {
                        (container as MutableMap<String, Any?>).also {
                            if (value == null) it.remove(component.name) else it[component.name] = value
                        }
                        return container
                    }
                    is Map<*, *> -> (container as Map<String, Any?>).toMutableMap()
                    else -> return container
                }
                if (value == null) map.remove(component.name) else map[component.name] = value
                map
            }
            is PathComponent.Index -> {
                val list = when (container) {
                    is MutableList<*> -> container as MutableList<Any?>
                    is List<*> -> (container as List<Any?>).toMutableList()
                    else -> return container
                }
                if (component.index in list.indices) {
                    list[component.index] = value
                }
                list
            }
        }
    }
}
