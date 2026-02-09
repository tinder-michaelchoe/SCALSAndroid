package com.example.scalsandroid.scals.state

import com.example.scalsandroid.scals.document.StateValue
import java.util.UUID
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

typealias StateChangeCallback = (path: String, oldValue: Any?, newValue: Any?) -> Unit

class StateStore : StateValueReading {

    private val lock = ReentrantLock()
    private val values: MutableMap<String, Any?> = mutableMapOf()
    private val dirtyPaths: MutableSet<String> = mutableSetOf()
    private val changeCallbacks: MutableMap<UUID, StateChangeCallback> = mutableMapOf()

    // MARK: - StateValueReading

    override fun getValue(keypath: String): Any? = lock.withLock {
        KeypathAccessor.get(keypath, values)
    }

    override fun getArray(keypath: String): List<Any>? {
        val value = getValue(keypath)
        @Suppress("UNCHECKED_CAST")
        return value as? List<Any>
    }

    override fun arrayContains(keypath: String, value: Any): Boolean {
        val array = getArray(keypath) ?: return false
        return array.any { elementsEqual(it, value) }
    }

    override fun getArrayCount(keypath: String): Int {
        return getArray(keypath)?.size ?: 0
    }

    // MARK: - Typed getter

    @Suppress("UNCHECKED_CAST")
    fun <T> get(keypath: String, type: Class<T>): T? {
        val value = getValue(keypath)
        return value as? T
    }

    inline fun <reified T> get(keypath: String): T? = get(keypath, T::class.java)

    // MARK: - Mutators

    fun set(keypath: String, value: Any?) {
        val oldValue: Any?
        val callbacks: List<StateChangeCallback>
        lock.withLock {
            oldValue = KeypathAccessor.get(keypath, values)
            KeypathAccessor.set(keypath, value, values)
            dirtyPaths.add(keypath)
            // Also dirty parent paths
            KeypathAccessor.parentPaths(keypath).forEach { dirtyPaths.add(it) }
            callbacks = changeCallbacks.values.toList()
        }
        // Invoke callbacks outside lock
        callbacks.forEach { it(keypath, oldValue, value) }
    }

    fun appendToArray(keypath: String, value: Any) {
        val oldValue: Any?
        val newValue: List<Any?>
        val callbacks: List<StateChangeCallback>
        lock.withLock {
            oldValue = KeypathAccessor.get(keypath, values)
            @Suppress("UNCHECKED_CAST")
            val array = (oldValue as? List<Any?>)?.toMutableList() ?: mutableListOf()
            array.add(value)
            newValue = array
            KeypathAccessor.set(keypath, newValue, values)
            dirtyPaths.add(keypath)
            KeypathAccessor.parentPaths(keypath).forEach { dirtyPaths.add(it) }
            callbacks = changeCallbacks.values.toList()
        }
        callbacks.forEach { it(keypath, oldValue, newValue) }
    }

    fun removeFromArray(keypath: String, value: Any) {
        val oldValue: Any?
        val newValue: List<Any?>
        val callbacks: List<StateChangeCallback>
        lock.withLock {
            oldValue = KeypathAccessor.get(keypath, values)
            @Suppress("UNCHECKED_CAST")
            val array = (oldValue as? List<Any?>)?.toMutableList() ?: return
            array.removeAll { elementsEqual(it, value) }
            newValue = array
            KeypathAccessor.set(keypath, newValue, values)
            dirtyPaths.add(keypath)
            KeypathAccessor.parentPaths(keypath).forEach { dirtyPaths.add(it) }
            callbacks = changeCallbacks.values.toList()
        }
        callbacks.forEach { it(keypath, oldValue, newValue) }
    }

    fun toggleInArray(keypath: String, value: Any) {
        if (arrayContains(keypath, value)) {
            removeFromArray(keypath, value)
        } else {
            appendToArray(keypath, value)
        }
    }

    // MARK: - Dirty Tracking

    val hasDirtyPaths: Boolean get() = lock.withLock { dirtyPaths.isNotEmpty() }

    fun consumeDirtyPaths(): Set<String> = lock.withLock {
        val paths = dirtyPaths.toSet()
        dirtyPaths.clear()
        paths
    }

    fun isDirty(path: String): Boolean = lock.withLock { path in dirtyPaths }

    fun clearDirtyPaths() = lock.withLock { dirtyPaths.clear() }

    // MARK: - Callbacks

    fun onStateChange(callback: StateChangeCallback): UUID {
        val id = UUID.randomUUID()
        lock.withLock { changeCallbacks[id] = callback }
        return id
    }

    fun removeStateChangeCallback(id: UUID) {
        lock.withLock { changeCallbacks.remove(id) }
    }

    fun removeAllCallbacks() {
        lock.withLock { changeCallbacks.clear() }
    }

    // MARK: - Initialization

    fun initialize(from: Map<String, StateValue>?) {
        if (from == null) return
        lock.withLock {
            for ((key, stateValue) in from) {
                val unwrapped = StateValueConverter.unwrap(stateValue)
                values[key] = unwrapped
            }
        }
    }

    // MARK: - Expressions

    fun evaluate(expression: String): Any {
        return ExpressionEvaluator.evaluate(expression, this)
    }

    fun interpolate(template: String): String {
        return ExpressionEvaluator.interpolate(template, this)
    }

    // MARK: - Snapshot

    fun snapshot(): Map<String, Any?> = lock.withLock {
        values.toMap()
    }

    fun restore(from: Map<String, Any?>) {
        lock.withLock {
            values.clear()
            values.putAll(from)
        }
    }

    // MARK: - Private

    private fun elementsEqual(a: Any?, b: Any?): Boolean {
        if (a == b) return true
        // Handle numeric comparison (Int/Long/Double)
        if (a is Number && b is Number) {
            return a.toDouble() == b.toDouble()
        }
        return false
    }
}
