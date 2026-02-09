package com.example.scalsandroid.scals.components.sectionlayouts

import com.example.scalsandroid.scals.document.SectionLayoutConfig
import com.example.scalsandroid.scals.document.SectionType
import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.ir.resolution.SectionLayoutConfigResolving
import com.example.scalsandroid.scals.ir.resolution.SectionLayoutConfigResult
import com.example.scalsandroid.scals.ir.toIR

class GridLayoutConfigResolver : SectionLayoutConfigResolving {
    override val layoutType = SectionType.GRID

    override fun resolve(config: SectionLayoutConfig): SectionLayoutConfigResult {
        val columns = config.columns?.toIR() ?: IR.ColumnConfig.Fixed(2)
        val sectionType = IR.SectionType.Grid(columns)

        return SectionLayoutConfigResult(
            sectionType = sectionType,
            sectionConfig = buildConfig(config),
        )
    }
}
