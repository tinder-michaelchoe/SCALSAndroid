package com.example.scalsandroid.scals.document

import kotlinx.serialization.json.Json

val ScalsJson = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
    encodeDefaults = false
}
