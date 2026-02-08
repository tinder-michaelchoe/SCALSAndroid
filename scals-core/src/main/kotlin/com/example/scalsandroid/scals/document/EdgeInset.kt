package com.example.scalsandroid.scals.document

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

@Serializable(with = EdgeInsetSerializer::class)
data class EdgeInset(
    val positioning: Positioning = Positioning.SAFE_AREA,
    val value: Double = 0.0,
)

internal object EdgeInsetSerializer : KSerializer<EdgeInset> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("EdgeInset")

    override fun serialize(encoder: Encoder, value: EdgeInset) {
        val jsonEncoder = encoder as? JsonEncoder
            ?: throw SerializationException("EdgeInset can only be serialized to JSON")
        if (value.positioning == Positioning.SAFE_AREA) {
            jsonEncoder.encodeJsonElement(JsonPrimitive(value.value))
        } else {
            jsonEncoder.encodeJsonElement(buildJsonObject {
                put("positioning", ScalsJson.encodeToJsonElement(Positioning.serializer(), value.positioning))
                put("value", value.value)
            })
        }
    }

    override fun deserialize(decoder: Decoder): EdgeInset {
        val jsonDecoder = decoder as? JsonDecoder
            ?: throw SerializationException("EdgeInset can only be deserialized from JSON")
        return when (val element = jsonDecoder.decodeJsonElement()) {
            is JsonPrimitive -> EdgeInset(Positioning.SAFE_AREA, element.double)
            is JsonObject -> {
                val positioning = element["positioning"]?.let {
                    ScalsJson.decodeFromJsonElement(Positioning.serializer(), it)
                } ?: Positioning.SAFE_AREA
                val v = element["value"]?.jsonPrimitive?.double ?: 0.0
                EdgeInset(positioning, v)
            }
            else -> throw SerializationException("Unexpected JSON for EdgeInset: $element")
        }
    }
}

@Serializable
data class EdgeInsets(
    val top: EdgeInset? = null,
    val bottom: EdgeInset? = null,
    val leading: EdgeInset? = null,
    val trailing: EdgeInset? = null,
)
