package com.example.scalsandroid.scals.document

import kotlinx.serialization.Serializable

@Serializable
data class ImagePlaceholder(
    val sfsymbol: String? = null,
    val icon: String? = null,
    val url: String? = null,
    val asset: String? = null,
    val activityIndicator: Boolean? = null,
)

@Serializable
data class ImageSource(
    val sfsymbol: String? = null,
    val icon: String? = null,
    val url: String? = null,
    val asset: String? = null,
    val activityIndicator: Boolean? = null,
    val placeholder: ImagePlaceholder? = null,
    val loading: ImagePlaceholder? = null,
)
