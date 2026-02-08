package com.example.scalsandroid.scals.document

import kotlinx.serialization.Serializable

@Serializable
data class LifecycleActions(
    val onAppear: ActionBinding? = null,
    val onDisappear: ActionBinding? = null,
)

@Serializable
data class RootComponent(
    val backgroundColor: String? = null,
    val edgeInsets: EdgeInsets? = null,
    val styleId: String? = null,
    val colorScheme: String? = null,
    val actions: LifecycleActions? = null,
    val children: List<LayoutNode> = emptyList(),
)
