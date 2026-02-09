package com.example.scalsandroid.scals.components.sectionlayouts

import com.example.scalsandroid.scals.document.SectionLayoutConfig
import com.example.scalsandroid.scals.document.SectionType
import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.ir.resolution.SectionLayoutConfigResolving
import com.example.scalsandroid.scals.ir.resolution.SectionLayoutConfigResult

class FlowLayoutConfigResolver : SectionLayoutConfigResolving {
    override val layoutType = SectionType.FLOW

    override fun resolve(config: SectionLayoutConfig): SectionLayoutConfigResult {
        return SectionLayoutConfigResult(
            sectionType = IR.SectionType.Flow,
            sectionConfig = buildConfig(config),
        )
    }
}
