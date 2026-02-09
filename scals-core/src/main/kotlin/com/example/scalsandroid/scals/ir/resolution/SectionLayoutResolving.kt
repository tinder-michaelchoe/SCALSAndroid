package com.example.scalsandroid.scals.ir.resolution

import com.example.scalsandroid.scals.document.SectionLayout
import com.example.scalsandroid.scals.document.SectionLayoutConfig
import com.example.scalsandroid.scals.document.SectionType
import com.example.scalsandroid.scals.ir.IR

interface SectionLayoutResolving {
    fun resolve(sectionLayout: SectionLayout, context: ResolutionContext): NodeResolutionResult
}

data class SectionLayoutConfigResult(
    val sectionType: IR.SectionType,
    val sectionConfig: IR.SectionConfig,
)

interface SectionLayoutConfigResolving {
    val layoutType: SectionType
    fun resolve(config: SectionLayoutConfig): SectionLayoutConfigResult
}

class SectionLayoutConfigResolverRegistry {

    private val resolvers: MutableMap<SectionType, SectionLayoutConfigResolving> = mutableMapOf()

    fun register(resolver: SectionLayoutConfigResolving) {
        resolvers[resolver.layoutType] = resolver
    }

    fun resolve(config: SectionLayoutConfig): SectionLayoutConfigResult? {
        return resolvers[config.type]?.resolve(config)
    }
}
