package com.example.scalsandroid.scals.state

import com.example.scalsandroid.scals.document.StateValue

object StateValueConverter {

    fun unwrap(stateValue: StateValue): Any? = when (stateValue) {
        is StateValue.IntValue -> stateValue.value
        is StateValue.DoubleValue -> stateValue.value
        is StateValue.StringValue -> stateValue.value
        is StateValue.BoolValue -> stateValue.value
        is StateValue.NullValue -> null
        is StateValue.ArrayValue -> stateValue.value.map { unwrap(it) }
        is StateValue.ObjectValue -> stateValue.value.mapValues { (_, v) -> unwrap(v) }
    }

    fun anyToStateValue(value: Any?): StateValue = when (value) {
        null -> StateValue.NullValue
        is Boolean -> StateValue.BoolValue(value)
        is Long -> StateValue.IntValue(value)
        is Int -> StateValue.IntValue(value.toLong())
        is Double -> StateValue.DoubleValue(value)
        is Float -> StateValue.DoubleValue(value.toDouble())
        is String -> StateValue.StringValue(value)
        is List<*> -> StateValue.ArrayValue(value.map { anyToStateValue(it) })
        is Map<*, *> -> {
            @Suppress("UNCHECKED_CAST")
            val stringMap = value as Map<String, Any?>
            StateValue.ObjectValue(stringMap.mapValues { (_, v) -> anyToStateValue(v) })
        }
        is StateValue -> value
        else -> StateValue.StringValue(value.toString())
    }
}
