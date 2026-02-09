package com.example.scalsandroid.scals.components.compose

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableIntStateOf
import com.example.scalsandroid.scals.state.StateStore
import java.util.UUID

@Stable
class ComposeStateStore(val backing: StateStore) {

    private val _version = mutableIntStateOf(0)
    private val version: Int get() = _version.intValue

    private val callbackId: UUID = backing.onStateChange { _, _, _ ->
        _version.intValue++
    }

    /** Read the version to subscribe the calling composable to state changes. */
    private fun observe() {
        @Suppress("UNUSED_VARIABLE")
        val v = version
    }

    fun getValue(keypath: String): Any? {
        observe()
        return backing.getValue(keypath)
    }

    fun getString(keypath: String): String {
        observe()
        val value = backing.getValue(keypath)
        return when (value) {
            is String -> value
            null -> ""
            else -> value.toString()
        }
    }

    fun getBoolean(keypath: String): Boolean {
        observe()
        val value = backing.getValue(keypath)
        return value as? Boolean ?: false
    }

    fun getDouble(keypath: String): Double {
        observe()
        val value = backing.getValue(keypath)
        return when (value) {
            is Double -> value
            is Number -> value.toDouble()
            else -> 0.0
        }
    }

    fun getFloat(keypath: String): Float {
        return getDouble(keypath).toFloat()
    }

    fun getInt(keypath: String): Int {
        observe()
        val value = backing.getValue(keypath)
        return when (value) {
            is Int -> value
            is Number -> value.toInt()
            else -> 0
        }
    }

    fun interpolate(template: String): String {
        observe()
        return backing.interpolate(template)
    }

    fun set(keypath: String, value: Any?) {
        backing.set(keypath, value)
    }

    fun dispose() {
        backing.removeStateChangeCallback(callbackId)
    }
}
