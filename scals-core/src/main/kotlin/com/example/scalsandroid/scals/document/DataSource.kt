package com.example.scalsandroid.scals.document

import kotlinx.serialization.Serializable

@Serializable
data class DataSource(
    val type: DataSourceKind,
    val value: StateValue? = null,
    val path: String? = null,
    val template: String? = null,
)

@Serializable
data class DataReference(
    val type: DataReferenceType,
    val value: StateValue? = null,
    val path: String? = null,
    val template: String? = null,
)
