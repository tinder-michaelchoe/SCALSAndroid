package com.example.scalsandroid.scals.document

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

@Serializable(with = ActionBindingSerializer::class)
sealed class ActionBinding {
    data class Reference(val name: String) : ActionBinding()
    data class Inline(val action: Action) : ActionBinding()
}

internal object ActionBindingSerializer : KSerializer<ActionBinding> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("ActionBinding")

    override fun serialize(encoder: Encoder, value: ActionBinding) {
        val jsonEncoder = encoder as? JsonEncoder
            ?: throw SerializationException("ActionBinding can only be serialized to JSON")
        val element = when (value) {
            is ActionBinding.Reference -> JsonPrimitive(value.name)
            is ActionBinding.Inline -> ScalsJson.encodeToJsonElement(ActionSerializer, value.action)
        }
        jsonEncoder.encodeJsonElement(element)
    }

    override fun deserialize(decoder: Decoder): ActionBinding {
        val jsonDecoder = decoder as? JsonDecoder
            ?: throw SerializationException("ActionBinding can only be deserialized from JSON")
        return when (val element = jsonDecoder.decodeJsonElement()) {
            is JsonPrimitive -> ActionBinding.Reference(element.content)
            is JsonObject -> ActionBinding.Inline(
                ScalsJson.decodeFromJsonElement(ActionSerializer, element)
            )
            else -> throw SerializationException("Unexpected JSON for ActionBinding: $element")
        }
    }
}
