package com.example.scalsandroid.scals.ir.resolution

import com.example.scalsandroid.scals.document.Definition
import com.example.scalsandroid.scals.document.EdgeInsets as DocEdgeInsets
import com.example.scalsandroid.scals.document.Positioning as DocPositioning
import com.example.scalsandroid.scals.ir.*
import com.example.scalsandroid.scals.state.StateStore
import com.example.scalsandroid.scals.viewtree.ViewNode
import com.example.scalsandroid.scals.viewtree.ViewTreeUpdater

class Resolver(
    private val document: Definition,
    private val componentRegistry: ComponentResolverRegistry,
    private val actionResolverRegistry: ActionResolverRegistry,
    private val layoutResolver: LayoutResolving,
    private val sectionLayoutResolver: SectionLayoutResolving,
    private val designSystemProvider: DesignSystemProvider? = null,
) {
    private val actionResolver = ActionResolver(actionResolverRegistry)

    fun resolve(): RenderTree {
        val stateStore = StateStore()
        return resolve(stateStore, initializeFromDocument = true)
    }

    fun resolve(stateStore: StateStore, initializeFromDocument: Boolean = true): RenderTree {
        if (initializeFromDocument) {
            stateStore.initialize(document.state)
        }

        val context = ResolutionContext.withoutTracking(
            document = document,
            stateStore = stateStore,
            designSystemProvider = designSystemProvider,
        )

        val resolvedActions = document.actions?.let {
            actionResolver.resolveAll(it, context)
        } ?: emptyMap()

        val rootNode = resolveRoot(context)

        return RenderTree(
            root = rootNode,
            stateStore = stateStore,
            actions = resolvedActions,
        )
    }

    fun resolveWithTracking(): ResolutionResult {
        val stateStore = StateStore()
        return resolveWithTracking(stateStore, initializeFromDocument = true)
    }

    fun resolveWithTracking(stateStore: StateStore, initializeFromDocument: Boolean = true): ResolutionResult {
        if (initializeFromDocument) {
            stateStore.initialize(document.state)
        }

        val treeUpdater = ViewTreeUpdater()
        val tracker = treeUpdater.dependencyTracker

        val context = ResolutionContext.withTracking(
            document = document,
            stateStore = stateStore,
            tracker = tracker,
            designSystemProvider = designSystemProvider,
        )

        val rootViewNode = ViewNode(id = "root")
        tracker.beginTracking(rootViewNode)

        val resolvedActions = document.actions?.let {
            actionResolver.resolveAll(it, context)
        } ?: emptyMap()

        val rootNode = resolveRoot(context.withParent(rootViewNode))

        tracker.endTracking()

        treeUpdater.setRoot(rootViewNode)
        treeUpdater.attach(stateStore)

        val renderTree = RenderTree(
            root = rootNode,
            stateStore = stateStore,
            actions = resolvedActions,
        )

        return ResolutionResult(
            renderTree = renderTree,
            viewTreeRoot = rootViewNode,
            treeUpdater = treeUpdater,
        )
    }

    private fun resolveRoot(context: ResolutionContext): RootNode {
        val root = document.root
        val resolvedStyle = context.styleResolver.resolve(root.styleId)

        val backgroundColor = root.backgroundColor?.let { IR.Color.fromHex(it) }
        val colorScheme = ColorSchemeConverter.convert(root.colorScheme)
        val edgeInsets = convertEdgeInsets(root.edgeInsets)

        // Resolve lifecycle actions
        val lifecycleActions = LifecycleActions(
            onAppear = actionResolver.resolveBinding(
                root.actions?.onAppear, document.actions, context,
            ),
            onDisappear = actionResolver.resolveBinding(
                root.actions?.onDisappear, document.actions, context,
            ),
        )

        // Resolve children
        val children = root.children.map { childNode ->
            layoutResolver.resolveNode(childNode, context).also { result ->
                result.viewNode?.let { viewNode ->
                    viewNode.parent = context.parentViewNode
                    context.parentViewNode?.let { parent ->
                        val updatedChildren = parent.children.toMutableList()
                        updatedChildren.add(viewNode)
                        parent.children = updatedChildren
                    }
                }
            }.renderNode
        }

        val padding = IRInitializers.edgeInsetsFrom(null, resolvedStyle)
        val shadow = IRInitializers.shadowFrom(resolvedStyle)
        val border = IRInitializers.borderFrom(resolvedStyle)

        return RootNode(
            backgroundColor = backgroundColor,
            edgeInsets = edgeInsets,
            colorScheme = colorScheme,
            actions = lifecycleActions,
            children = children,
            padding = padding,
            cornerRadius = resolvedStyle.cornerRadius ?: 0.0,
            shadow = shadow,
            border = border,
        )
    }

    companion object {
        fun convertEdgeInsets(docInsets: DocEdgeInsets?): IR.PositionedEdgeInsets? {
            if (docInsets == null) return null
            return IR.PositionedEdgeInsets(
                top = docInsets.top?.let {
                    IR.PositionedEdgeInset(
                        positioning = convertPositioning(it.positioning),
                        value = it.value,
                    )
                },
                bottom = docInsets.bottom?.let {
                    IR.PositionedEdgeInset(
                        positioning = convertPositioning(it.positioning),
                        value = it.value,
                    )
                },
                leading = docInsets.leading?.let {
                    IR.PositionedEdgeInset(
                        positioning = convertPositioning(it.positioning),
                        value = it.value,
                    )
                },
                trailing = docInsets.trailing?.let {
                    IR.PositionedEdgeInset(
                        positioning = convertPositioning(it.positioning),
                        value = it.value,
                    )
                },
            )
        }

        private fun convertPositioning(p: DocPositioning): IR.Positioning = when (p) {
            DocPositioning.SAFE_AREA -> IR.Positioning.SAFE_AREA
            DocPositioning.ABSOLUTE -> IR.Positioning.ABSOLUTE
        }
    }
}

sealed class ResolutionError(message: String) : Exception(message) {
    class UnknownStyle(val styleId: String) : ResolutionError("Unknown style: $styleId")
    class UnknownDataSource(val sourceId: String) : ResolutionError("Unknown data source: $sourceId")
    class UnknownAction(val actionName: String) : ResolutionError("Unknown action: $actionName")
    class InvalidAction(message: String) : ResolutionError(message)
}
