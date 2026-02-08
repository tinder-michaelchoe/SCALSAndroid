package com.example.scalsandroid.scals.document

import kotlinx.serialization.Serializable

@Serializable
data class DocumentVersion(
    val major: Int,
    val minor: Int,
    val patch: Int,
) {
    companion object {
        val current = DocumentVersion(major = 0, minor = 1, patch = 0)
        val currentIR = DocumentVersion(major = 0, minor = 1, patch = 0)
    }

    override fun toString(): String = "$major.$minor.$patch"
}
