package com.example.scalsandroid.scals.document

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

@Serializable(with = DimensionValueSerializer::class)
sealed class DimensionValue {
    data class Absolute(val value: Double) : DimensionValue()
    data class Fractional(val value: Double) : DimensionValue()
}

internal object DimensionValueSerializer : KSerializer<DimensionValue> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("DimensionValue")

    override fun serialize(encoder: Encoder, value: DimensionValue) {
        val jsonEncoder = encoder as? JsonEncoder
            ?: throw SerializationException("DimensionValue can only be serialized to JSON")
        val element = when (value) {
            is DimensionValue.Absolute -> JsonPrimitive(value.value)
            is DimensionValue.Fractional -> buildJsonObject {
                put("fractional", value.value)
            }
        }
        jsonEncoder.encodeJsonElement(element)
    }

    override fun deserialize(decoder: Decoder): DimensionValue {
        val jsonDecoder = decoder as? JsonDecoder
            ?: throw SerializationException("DimensionValue can only be deserialized from JSON")
        return when (val element = jsonDecoder.decodeJsonElement()) {
            is JsonPrimitive -> DimensionValue.Absolute(element.double)
            is JsonObject -> {
                element["fractional"]?.let {
                    DimensionValue.Fractional(it.jsonPrimitive.double)
                } ?: element["absolute"]?.let {
                    DimensionValue.Absolute(it.jsonPrimitive.double)
                } ?: throw SerializationException("Unknown DimensionValue object: $element")
            }
            else -> throw SerializationException("Unexpected JSON for DimensionValue: $element")
        }
    }
}
