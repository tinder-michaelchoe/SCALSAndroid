package com.example.scalsandroid.scals.document

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = DocumentVersionSerializer::class)
data class DocumentVersion(
    val major: Int,
    val minor: Int,
    val patch: Int,
) {
    companion object {
        val current = DocumentVersion(major = 0, minor = 1, patch = 0)
        val currentIR = DocumentVersion(major = 0, minor = 1, patch = 0)

        fun fromString(version: String): DocumentVersion {
            val parts = version.split(".")
            return DocumentVersion(
                major = parts.getOrNull(0)?.toIntOrNull() ?: 0,
                minor = parts.getOrNull(1)?.toIntOrNull() ?: 0,
                patch = parts.getOrNull(2)?.toIntOrNull() ?: 0
            )
        }
    }

    override fun toString(): String = "$major.$minor.$patch"
}

internal object DocumentVersionSerializer : KSerializer<DocumentVersion> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("DocumentVersion", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: DocumentVersion) {
        encoder.encodeString(value.toString())
    }

    override fun deserialize(decoder: Decoder): DocumentVersion {
        val string = decoder.decodeString()
        return DocumentVersion.fromString(string)
    }
}
