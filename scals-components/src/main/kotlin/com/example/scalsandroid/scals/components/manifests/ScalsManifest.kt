package com.example.scalsandroid.scals.components.manifests

import com.example.scalsandroid.scals.ir.resolution.SectionLayoutConfigResolving

interface ScalsManifest {
    val components: List<ComponentBundle>
    val layouts: List<LayoutBundle>
    val actions: List<ActionBundleDefinition>
    val sectionLayouts: List<SectionLayoutConfigResolving>
    fun createRegistries(): ScalsRegistries
}
