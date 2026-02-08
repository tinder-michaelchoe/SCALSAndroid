package com.example.scalsandroid.scals.document

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

@Serializable(with = ColumnConfigSerializer::class)
sealed class ColumnConfig {
    data class Fixed(val count: Int) : ColumnConfig()
    data class Adaptive(val minWidth: Double) : ColumnConfig()
}

internal object ColumnConfigSerializer : KSerializer<ColumnConfig> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("ColumnConfig")

    override fun serialize(encoder: Encoder, value: ColumnConfig) {
        val jsonEncoder = encoder as? JsonEncoder
            ?: throw SerializationException("ColumnConfig can only be serialized to JSON")
        val element = when (value) {
            is ColumnConfig.Fixed -> JsonPrimitive(value.count)
            is ColumnConfig.Adaptive -> buildJsonObject {
                putJsonObject("adaptive") {
                    put("minWidth", value.minWidth)
                }
            }
        }
        jsonEncoder.encodeJsonElement(element)
    }

    override fun deserialize(decoder: Decoder): ColumnConfig {
        val jsonDecoder = decoder as? JsonDecoder
            ?: throw SerializationException("ColumnConfig can only be deserialized from JSON")
        return when (val element = jsonDecoder.decodeJsonElement()) {
            is JsonPrimitive -> ColumnConfig.Fixed(element.int)
            is JsonObject -> {
                val adaptive = element["adaptive"]?.jsonObject
                    ?: throw SerializationException("Unknown ColumnConfig object: $element")
                ColumnConfig.Adaptive(adaptive["minWidth"]!!.jsonPrimitive.double)
            }
            else -> throw SerializationException("Unexpected JSON for ColumnConfig: $element")
        }
    }
}
