package com.example.scalsandroid.scals.document

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

@Serializable(with = StateValueSerializer::class)
sealed class StateValue {
    data class IntValue(val value: Long) : StateValue()
    data class DoubleValue(val value: Double) : StateValue()
    data class StringValue(val value: String) : StateValue()
    data class BoolValue(val value: Boolean) : StateValue()
    data object NullValue : StateValue()
    data class ArrayValue(val value: List<StateValue>) : StateValue()
    data class ObjectValue(val value: Map<String, StateValue>) : StateValue()

    val intValue: Long? get() = (this as? IntValue)?.value
    val doubleValue: Double? get() = (this as? DoubleValue)?.value
    val stringValue: String? get() = (this as? StringValue)?.value
    val boolValue: Boolean? get() = (this as? BoolValue)?.value
    val isNull: Boolean get() = this is NullValue
    val arrayValue: List<StateValue>? get() = (this as? ArrayValue)?.value
    val objectValue: Map<String, StateValue>? get() = (this as? ObjectValue)?.value
}

internal object StateValueSerializer : KSerializer<StateValue> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("StateValue")

    override fun serialize(encoder: Encoder, value: StateValue) {
        val jsonEncoder = encoder as? JsonEncoder
            ?: throw SerializationException("StateValue can only be serialized to JSON")
        jsonEncoder.encodeJsonElement(value.toJsonElement())
    }

    override fun deserialize(decoder: Decoder): StateValue {
        val jsonDecoder = decoder as? JsonDecoder
            ?: throw SerializationException("StateValue can only be deserialized from JSON")
        return fromJsonElement(jsonDecoder.decodeJsonElement())
    }

    internal fun fromJsonElement(element: JsonElement): StateValue = when (element) {
        is JsonNull -> StateValue.NullValue
        is JsonArray -> StateValue.ArrayValue(element.map { fromJsonElement(it) })
        is JsonObject -> StateValue.ObjectValue(
            element.mapValues { (_, v) -> fromJsonElement(v) }
        )
        is JsonPrimitive -> decodePrimitive(element)
    }

    private fun decodePrimitive(primitive: JsonPrimitive): StateValue {
        if (primitive.isString) return StateValue.StringValue(primitive.content)
        // Boolean check: non-string "true" or "false"
        if (primitive.content == "true") return StateValue.BoolValue(true)
        if (primitive.content == "false") return StateValue.BoolValue(false)
        // Integer check
        primitive.longOrNull?.let { return StateValue.IntValue(it) }
        // Fallback to double
        return StateValue.DoubleValue(primitive.double)
    }
}

internal fun StateValue.toJsonElement(): JsonElement = when (this) {
    is StateValue.NullValue -> JsonNull
    is StateValue.BoolValue -> JsonPrimitive(value)
    is StateValue.IntValue -> JsonPrimitive(value)
    is StateValue.DoubleValue -> JsonPrimitive(value)
    is StateValue.StringValue -> JsonPrimitive(value)
    is StateValue.ArrayValue -> JsonArray(value.map { it.toJsonElement() })
    is StateValue.ObjectValue -> JsonObject(
        value.mapValues { (_, v) -> v.toJsonElement() }
    )
}
