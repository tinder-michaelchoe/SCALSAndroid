package com.example.scalsandroid.scals.components.resolvers

import com.example.scalsandroid.scals.components.nodes.ContainerNode
import com.example.scalsandroid.scals.components.nodes.SpacerNode
import com.example.scalsandroid.scals.document.*
import com.example.scalsandroid.scals.ir.*
import com.example.scalsandroid.scals.ir.resolution.*
import com.example.scalsandroid.scals.state.StateValueConverter
import com.example.scalsandroid.scals.viewtree.ViewNode
import java.util.UUID

class LayoutResolver(
    private val componentRegistry: ComponentResolverRegistry,
    private val sectionLayoutResolver: SectionLayoutResolving,
) : LayoutResolving {

    override fun resolve(layout: Layout, context: ResolutionContext): NodeResolutionResult {
        // Create ViewNode for tracking
        val viewNode = if (context.isTracking) {
            ViewNode(id = UUID.randomUUID().toString()).also {
                it.parent = context.parentViewNode
                context.tracker?.beginTracking(it)
            }
        } else null

        val resolvedStyle = context.styleResolver.resolve(layout.styleId, layout.style)

        // Initialize local state if declared
        layout.state?.let { localState ->
            viewNode?.let { vn ->
                vn.localState = mutableMapOf<String, Any?>()
                for ((key, stateValue) in localState.entries) {
                    vn.localState!![key] = StateValueConverter.unwrap(stateValue)
                }
            }
        }

        val childContext = if (viewNode != null) context.withParent(viewNode) else context

        // Resolve children
        val resolvedChildren = mutableListOf<RenderNode>()
        val childViewNodes = mutableListOf<ViewNode>()

        layout.children?.forEach { childNode ->
            val result = resolveNode(childNode, childContext)
            resolvedChildren.add(result.renderNode)
            result.viewNode?.let { childViewNodes.add(it) }
        }

        // End tracking after children resolved
        if (context.isTracking) {
            context.tracker?.endTracking()
        }

        // Build ViewNode tree
        viewNode?.children = childViewNodes

        val alignment = resolveAlignment(layout.type, layout.alignment)
        val padding = IRInitializers.edgeInsetsFrom(layout.padding, resolvedStyle)
        val shadow = IRInitializers.shadowFrom(resolvedStyle)
        val border = IRInitializers.borderFrom(resolvedStyle)

        val containerNode = ContainerNode(
            layoutType = layout.type,
            alignment = alignment,
            spacing = layout.spacing ?: 0.0,
            children = resolvedChildren,
            padding = padding,
            backgroundColor = resolvedStyle.backgroundColor,
            cornerRadius = resolvedStyle.cornerRadius ?: 0.0,
            shadow = shadow,
            border = border,
            width = resolvedStyle.width,
            height = resolvedStyle.height,
            minWidth = resolvedStyle.minWidth,
            minHeight = resolvedStyle.minHeight,
            maxWidth = resolvedStyle.maxWidth,
            maxHeight = resolvedStyle.maxHeight,
        )

        return NodeResolutionResult(RenderNode(containerNode), viewNode)
    }

    override fun resolveNode(node: LayoutNode, context: ResolutionContext): NodeResolutionResult {
        return when (node) {
            is Layout -> resolve(node, context)
            is Component -> {
                val result = componentRegistry.resolve(node, context)
                NodeResolutionResult(result.renderNode, result.viewNode)
            }
            is ForEach -> resolveForEach(node, context)
            is Spacer -> resolveSpacerNode(node)
            is SectionLayout -> sectionLayoutResolver.resolve(node, context)
        }
    }

    private fun resolveForEach(forEach: ForEach, context: ResolutionContext): NodeResolutionResult {
        val viewNode = if (context.isTracking) {
            ViewNode(id = "forEach-${UUID.randomUUID()}").also {
                it.parent = context.parentViewNode
                context.tracker?.beginTracking(it)
            }
        } else null

        // Get items from state
        val items = resolveForEachItems(forEach, context)

        if (items.isEmpty()) {
            // Resolve empty view if provided
            val emptyView = forEach.emptyView
            if (emptyView != null) {
                val emptyContext = if (viewNode != null) context.withParent(viewNode) else context
                val emptyResult = resolveNode(emptyView, emptyContext)
                if (context.isTracking) context.tracker?.endTracking()
                viewNode?.let { vn ->
                    emptyResult.viewNode?.let { vn.children = listOf(it) }
                }
                return NodeResolutionResult(emptyResult.renderNode, viewNode)
            }
            if (context.isTracking) context.tracker?.endTracking()
            val emptyContainer = ContainerNode(
                layoutType = forEach.layout,
                alignment = resolveAlignment(forEach.layout, forEach.alignment),
                spacing = forEach.spacing ?: 0.0,
            )
            return NodeResolutionResult(RenderNode(emptyContainer), viewNode)
        }

        val childContext = if (viewNode != null) context.withParent(viewNode) else context
        val resolvedChildren = mutableListOf<RenderNode>()
        val childViewNodes = mutableListOf<ViewNode>()

        for ((index, item) in items.withIndex()) {
            val iterVars = mapOf(
                forEach.itemVariable to item,
                forEach.indexVariable to index,
            )
            val itemContext = childContext.withIterationVariables(iterVars)

            forEach.template?.let { template ->
                val result = resolveNode(template, itemContext)
                resolvedChildren.add(result.renderNode)
                result.viewNode?.let { childViewNodes.add(it) }
            }
        }

        if (context.isTracking) context.tracker?.endTracking()
        viewNode?.children = childViewNodes

        val padding = forEach.padding?.let {
            IR.EdgeInsets(it.resolvedTop, it.resolvedLeading, it.resolvedBottom, it.resolvedTrailing)
        } ?: IR.EdgeInsets.ZERO

        val containerNode = ContainerNode(
            layoutType = forEach.layout,
            alignment = resolveAlignment(forEach.layout, forEach.alignment),
            spacing = forEach.spacing ?: 0.0,
            children = resolvedChildren,
            padding = padding,
        )

        return NodeResolutionResult(RenderNode(containerNode), viewNode)
    }

    private fun resolveForEachItems(forEach: ForEach, context: ResolutionContext): List<Any> {
        val itemsValue = forEach.items ?: return emptyList()

        return when (itemsValue) {
            is StateValue.ArrayValue -> {
                itemsValue.value.map { StateValueConverter.unwrap(it) ?: "" }
            }
            is StateValue.StringValue -> {
                // It's a binding path
                val path = itemsValue.value
                context.tracker?.recordRead(path)
                context.stateStore.getArray(path) ?: emptyList()
            }
            else -> {
                val unwrapped = StateValueConverter.unwrap(itemsValue)
                if (unwrapped is List<*>) {
                    @Suppress("UNCHECKED_CAST")
                    unwrapped as List<Any>
                } else {
                    emptyList()
                }
            }
        }
    }

    private fun resolveSpacerNode(spacer: Spacer): NodeResolutionResult {
        val node = SpacerNode(
            minLength = spacer.minLength,
            width = spacer.width,
            height = spacer.height,
        )
        return NodeResolutionResult(RenderNode(node))
    }

    private fun resolveAlignment(layoutType: LayoutType, alignment: Alignment?): IR.Alignment {
        return when (layoutType) {
            LayoutType.VSTACK -> AlignmentConverter.forVStack(alignment)
            LayoutType.HSTACK -> AlignmentConverter.forHStack(alignment)
            LayoutType.ZSTACK -> AlignmentConverter.forZStack(alignment)
        }
    }
}
