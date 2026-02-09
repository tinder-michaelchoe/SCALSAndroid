package com.example.scalsandroid.scals.components.resolvers

import com.example.scalsandroid.scals.components.nodes.SectionLayoutNode
import com.example.scalsandroid.scals.document.*
import com.example.scalsandroid.scals.ir.*
import com.example.scalsandroid.scals.ir.resolution.*
import com.example.scalsandroid.scals.state.StateValueConverter
import com.example.scalsandroid.scals.viewtree.ViewNode
import java.util.UUID

class SectionLayoutResolver(
    private val layoutResolver: LayoutResolving,
    private val configRegistry: SectionLayoutConfigResolverRegistry,
) : SectionLayoutResolving {

    override fun resolve(sectionLayout: SectionLayout, context: ResolutionContext): NodeResolutionResult {
        val viewNode = if (context.isTracking) {
            ViewNode(id = sectionLayout.id ?: "sectionLayout-${UUID.randomUUID()}").also {
                it.parent = context.parentViewNode
                context.tracker?.beginTracking(it)
            }
        } else null

        val childContext = if (viewNode != null) context.withParent(viewNode) else context
        val resolvedSections = sectionLayout.sections.map { resolveSection(it, childContext) }

        if (context.isTracking) context.tracker?.endTracking()

        val node = SectionLayoutNode(
            id = sectionLayout.id,
            sectionSpacing = sectionLayout.sectionSpacing ?: 0.0,
            sections = resolvedSections,
        )

        return NodeResolutionResult(RenderNode(node), viewNode)
    }

    private fun resolveSection(
        section: SectionDefinition,
        context: ResolutionContext,
    ): IR.Section {
        // Resolve layout config
        val configResult = configRegistry.resolve(section.layout) ?: defaultConfigResult(section.layout)

        // Resolve header/footer
        val header = section.header?.let { layoutResolver.resolveNode(it, context).renderNode }
        val footer = section.footer?.let { layoutResolver.resolveNode(it, context).renderNode }

        // Resolve children
        val children = resolveChildren(section, context)

        return IR.Section(
            id = section.id,
            layoutType = configResult.sectionType,
            header = header,
            footer = footer,
            stickyHeader = section.stickyHeader ?: false,
            config = configResult.sectionConfig,
            children = children,
        )
    }

    private fun resolveChildren(
        section: SectionDefinition,
        context: ResolutionContext,
    ): List<RenderNode> {
        // Data-driven children
        section.dataSource?.let { dataSource ->
            val items = resolveDataSource(dataSource, context)
            val template = section.itemTemplate ?: return emptyList()

            return items.mapIndexed { index, item ->
                val iterVars = mapOf("item" to item, "index" to index)
                val itemContext = context.withIterationVariables(iterVars)
                layoutResolver.resolveNode(template, itemContext).renderNode
            }
        }

        // Static children
        return section.children?.map { child ->
            layoutResolver.resolveNode(child, context).renderNode
        } ?: emptyList()
    }

    private fun resolveDataSource(
        dataSource: DataSource,
        context: ResolutionContext,
    ): List<Any> {
        return when (dataSource.type) {
            DataSourceKind.STATIC -> {
                val value = dataSource.value?.let { StateValueConverter.unwrap(it) }
                @Suppress("UNCHECKED_CAST")
                (value as? List<Any>) ?: emptyList()
            }
            DataSourceKind.BINDING -> {
                val path = dataSource.path ?: return emptyList()
                context.tracker?.recordRead(path)
                context.stateStore.getArray(path) ?: emptyList()
            }
        }
    }

    private fun defaultConfigResult(config: SectionLayoutConfig): SectionLayoutConfigResult {
        val sectionType = when (config.type) {
            SectionType.HORIZONTAL -> IR.SectionType.Horizontal
            SectionType.LIST -> IR.SectionType.ListType
            SectionType.GRID -> {
                val columns = config.columns?.toIR() ?: IR.ColumnConfig.Fixed(2)
                IR.SectionType.Grid(columns)
            }
            SectionType.FLOW -> IR.SectionType.Flow
        }

        val contentInsets = config.contentInsets?.let {
            IR.EdgeInsets(it.resolvedTop, it.resolvedLeading, it.resolvedBottom, it.resolvedTrailing)
        } ?: IR.EdgeInsets.ZERO

        val itemDimensions = config.itemDimensions?.let {
            IR.ItemDimensions(
                width = it.width?.toIR(),
                height = it.height?.toIR(),
                aspectRatio = it.aspectRatio,
            )
        }

        val sectionConfig = IR.SectionConfig(
            alignment = convertSectionAlignment(config.alignment),
            itemSpacing = config.itemSpacing ?: 8.0,
            lineSpacing = config.lineSpacing ?: 8.0,
            contentInsets = contentInsets,
            showsIndicators = config.showsIndicators ?: true,
            isPagingEnabled = config.isPagingEnabled ?: false,
            snapBehavior = config.snapBehavior?.toIR() ?: IR.SnapBehavior.NONE,
            showsDividers = config.showsDividers ?: false,
            itemDimensions = itemDimensions,
        )

        return SectionLayoutConfigResult(sectionType, sectionConfig)
    }

    private fun convertSectionAlignment(alignment: SectionAlignment?): IR.HorizontalAlignment {
        return when (alignment) {
            SectionAlignment.LEADING -> IR.HorizontalAlignment.LEADING
            SectionAlignment.CENTER -> IR.HorizontalAlignment.CENTER
            SectionAlignment.TRAILING -> IR.HorizontalAlignment.TRAILING
            null -> IR.HorizontalAlignment.LEADING
        }
    }
}
