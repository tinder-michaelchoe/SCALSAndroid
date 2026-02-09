package com.example.scalsandroid.scals.components.manifests

import com.example.scalsandroid.scals.actions.ActionRegistry
import com.example.scalsandroid.scals.components.compose.rendering.ComposeNodeRendererRegistry
import com.example.scalsandroid.scals.ir.resolution.ActionResolverRegistry
import com.example.scalsandroid.scals.ir.resolution.ComponentResolverRegistry
import com.example.scalsandroid.scals.ir.resolution.SectionLayoutConfigResolverRegistry

data class ScalsRegistries(
    val componentRegistry: ComponentResolverRegistry,
    val actionResolverRegistry: ActionResolverRegistry,
    val sectionLayoutConfigRegistry: SectionLayoutConfigResolverRegistry,
    val composeRendererRegistry: ComposeNodeRendererRegistry = ComposeNodeRendererRegistry(),
    val actionRegistry: ActionRegistry = ActionRegistry(),
)
