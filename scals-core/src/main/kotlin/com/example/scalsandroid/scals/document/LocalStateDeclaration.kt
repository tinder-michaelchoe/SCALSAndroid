package com.example.scalsandroid.scals.document

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = LocalStateDeclarationSerializer::class)
@JvmInline
value class LocalStateDeclaration(val entries: Map<String, StateValue>)

internal object LocalStateDeclarationSerializer : KSerializer<LocalStateDeclaration> {
    private val delegate = MapSerializer(String.serializer(), StateValueSerializer)

    override val descriptor: SerialDescriptor = delegate.descriptor

    override fun serialize(encoder: Encoder, value: LocalStateDeclaration) {
        delegate.serialize(encoder, value.entries)
    }

    override fun deserialize(decoder: Decoder): LocalStateDeclaration {
        return LocalStateDeclaration(delegate.deserialize(decoder))
    }
}
