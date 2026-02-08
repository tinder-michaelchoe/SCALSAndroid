package com.example.scalsandroid.scals.document

import kotlinx.serialization.Serializable

@Serializable
data class Definition(
    val id: String,
    val version: DocumentVersion? = null,
    val designSystem: StateValue? = null,
    val state: Map<String, StateValue>? = null,
    val styles: Map<String, Style>? = null,
    val dataSources: Map<String, DataSource>? = null,
    val actions: Map<String, Action>? = null,
    val root: RootComponent,
) {
    companion object {
        fun fromJson(jsonString: String): Definition =
            ScalsJson.decodeFromString(serializer(), jsonString)
    }

    fun toJson(): String =
        ScalsJson.encodeToString(serializer(), this)
}
