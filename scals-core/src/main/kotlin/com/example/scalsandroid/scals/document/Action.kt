package com.example.scalsandroid.scals.document

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

@Serializable
@JvmInline
value class ActionKind(val rawValue: String)

@Serializable(with = ActionSerializer::class)
data class Action(
    val type: ActionKind,
    val parameters: Map<String, StateValue> = emptyMap(),
)

internal object ActionSerializer : KSerializer<Action> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Action")

    override fun serialize(encoder: Encoder, value: Action) {
        val jsonEncoder = encoder as? JsonEncoder
            ?: throw SerializationException("Action can only be serialized to JSON")
        jsonEncoder.encodeJsonElement(buildJsonObject {
            put("type", value.type.rawValue)
            for ((k, v) in value.parameters) {
                put(k, v.toJsonElement())
            }
        })
    }

    override fun deserialize(decoder: Decoder): Action {
        val jsonDecoder = decoder as? JsonDecoder
            ?: throw SerializationException("Action can only be deserialized from JSON")
        val obj = jsonDecoder.decodeJsonElement().jsonObject
        val type = ActionKind(obj["type"]?.jsonPrimitive?.content
            ?: throw SerializationException("Action missing 'type' field"))
        val parameters = mutableMapOf<String, StateValue>()
        for ((key, value) in obj) {
            if (key != "type") {
                parameters[key] = StateValueSerializer.fromJsonElement(value)
            }
        }
        return Action(type, parameters)
    }
}
