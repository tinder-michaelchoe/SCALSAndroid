package com.example.scalsandroid.scals.document

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

@Serializable(with = AlignmentSerializer::class)
data class Alignment(
    val horizontal: HorizontalAlignment? = null,
    val vertical: VerticalAlignment? = null,
) {
    companion object {
        private val shortcuts = mapOf(
            "topLeading" to Alignment(HorizontalAlignment.LEADING, VerticalAlignment.TOP),
            "top" to Alignment(vertical = VerticalAlignment.TOP),
            "topTrailing" to Alignment(HorizontalAlignment.TRAILING, VerticalAlignment.TOP),
            "leading" to Alignment(horizontal = HorizontalAlignment.LEADING),
            "center" to Alignment(HorizontalAlignment.CENTER, VerticalAlignment.CENTER),
            "trailing" to Alignment(horizontal = HorizontalAlignment.TRAILING),
            "bottomLeading" to Alignment(HorizontalAlignment.LEADING, VerticalAlignment.BOTTOM),
            "bottom" to Alignment(vertical = VerticalAlignment.BOTTOM),
            "bottomTrailing" to Alignment(HorizontalAlignment.TRAILING, VerticalAlignment.BOTTOM),
        )

        internal fun fromShortcut(name: String): Alignment? = shortcuts[name]
        internal val reverseShortcuts = shortcuts.entries.associate { (k, v) -> v to k }
    }
}

internal object AlignmentSerializer : KSerializer<Alignment> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Alignment")

    override fun serialize(encoder: Encoder, value: Alignment) {
        val jsonEncoder = encoder as? JsonEncoder
            ?: throw SerializationException("Alignment can only be serialized to JSON")
        val shortcut = Alignment.reverseShortcuts[value]
        if (shortcut != null) {
            jsonEncoder.encodeJsonElement(JsonPrimitive(shortcut))
        } else {
            jsonEncoder.encodeJsonElement(buildJsonObject {
                value.horizontal?.let { put("horizontal", ScalsJson.encodeToJsonElement(HorizontalAlignment.serializer(), it)) }
                value.vertical?.let { put("vertical", ScalsJson.encodeToJsonElement(VerticalAlignment.serializer(), it)) }
            })
        }
    }

    override fun deserialize(decoder: Decoder): Alignment {
        val jsonDecoder = decoder as? JsonDecoder
            ?: throw SerializationException("Alignment can only be deserialized from JSON")
        return when (val element = jsonDecoder.decodeJsonElement()) {
            is JsonPrimitive -> {
                Alignment.fromShortcut(element.content)
                    ?: throw SerializationException("Unknown alignment shortcut: ${element.content}")
            }
            is JsonObject -> {
                val horizontal = element["horizontal"]?.let {
                    ScalsJson.decodeFromJsonElement(HorizontalAlignment.serializer(), it)
                }
                val vertical = element["vertical"]?.let {
                    ScalsJson.decodeFromJsonElement(VerticalAlignment.serializer(), it)
                }
                Alignment(horizontal, vertical)
            }
            else -> throw SerializationException("Unexpected JSON for Alignment: $element")
        }
    }
}
