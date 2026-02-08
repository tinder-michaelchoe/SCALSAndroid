package com.example.scalsandroid.scals.document

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

@Serializable(with = LayoutNodeSerializer::class)
sealed interface LayoutNode

internal object LayoutNodeSerializer : KSerializer<LayoutNode> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("LayoutNode")

    override fun serialize(encoder: Encoder, value: LayoutNode) {
        val jsonEncoder = encoder as? JsonEncoder
            ?: throw SerializationException("LayoutNode can only be serialized to JSON")
        val element = when (value) {
            is Layout -> ScalsJson.encodeToJsonElement(Layout.serializer(), value)
            is Component -> ScalsJson.encodeToJsonElement(Component.serializer(), value)
            is ForEach -> addType("forEach", ScalsJson.encodeToJsonElement(ForEach.serializer(), value))
            is Spacer -> addType("spacer", ScalsJson.encodeToJsonElement(Spacer.serializer(), value))
            is SectionLayout -> addType("sectionLayout", ScalsJson.encodeToJsonElement(SectionLayout.serializer(), value))
        }
        jsonEncoder.encodeJsonElement(element)
    }

    override fun deserialize(decoder: Decoder): LayoutNode {
        val jsonDecoder = decoder as? JsonDecoder
            ?: throw SerializationException("LayoutNode can only be deserialized from JSON")
        val element = jsonDecoder.decodeJsonElement()
        val type = element.jsonObject["type"]?.jsonPrimitive?.content
        return when (type) {
            "vstack", "hstack", "zstack" -> ScalsJson.decodeFromJsonElement(Layout.serializer(), element)
            "sectionLayout" -> ScalsJson.decodeFromJsonElement(SectionLayout.serializer(), element)
            "forEach" -> ScalsJson.decodeFromJsonElement(ForEach.serializer(), element)
            "spacer" -> ScalsJson.decodeFromJsonElement(Spacer.serializer(), element)
            else -> ScalsJson.decodeFromJsonElement(Component.serializer(), element)
        }
    }

    private fun addType(type: String, element: JsonElement): JsonElement {
        val obj = element.jsonObject.toMutableMap()
        obj["type"] = JsonPrimitive(type)
        return JsonObject(obj)
    }
}
