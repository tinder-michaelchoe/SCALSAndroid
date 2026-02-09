package com.example.scalsandroid.scals.components.manifests

import com.example.scalsandroid.scals.actions.ActionRegistry
import com.example.scalsandroid.scals.components.actions.*
import com.example.scalsandroid.scals.components.actions.handlers.DismissHandler
import com.example.scalsandroid.scals.components.actions.handlers.SequenceHandler
import com.example.scalsandroid.scals.components.actions.handlers.SetStateHandler
import com.example.scalsandroid.scals.components.actions.handlers.ShowAlertHandler
import com.example.scalsandroid.scals.components.actions.handlers.ToggleStateHandler
import com.example.scalsandroid.scals.components.bundles.*
import com.example.scalsandroid.scals.components.compose.rendering.ComposeNodeRendererRegistry
import com.example.scalsandroid.scals.components.resolvers.LayoutResolver
import com.example.scalsandroid.scals.components.resolvers.SectionLayoutResolver
import com.example.scalsandroid.scals.components.sectionlayouts.*
import com.example.scalsandroid.scals.document.ActionKind
import com.example.scalsandroid.scals.ir.resolution.*

object CoreManifest : ScalsManifest {

    override val components: List<ComponentBundle> = listOf(
        TextBundle,
        ButtonBundle,
        ImageBundle,
        TextFieldBundle,
        ToggleBundle,
        SliderBundle,
        GradientBundle,
        ShapeBundle,
        DividerBundle,
        PageIndicatorBundle,
        CloseButtonBundle,
        PhotoComparisonBundle,
    )

    override val layouts: List<LayoutBundle> = listOf(
        ContainerBundle,
        SpacerBundle,
        SectionLayoutBundle,
    )

    override val actions: List<ActionBundleDefinition> = listOf(
        ActionBundleDefinition(
            kind = ActionKind("setState"),
            resolver = SetStateResolver(),
            handler = SetStateHandler(),
        ),
        ActionBundleDefinition(
            kind = ActionKind("toggleState"),
            resolver = ToggleStateResolver(),
            handler = ToggleStateHandler(),
        ),
        ActionBundleDefinition(
            kind = ActionKind("dismiss"),
            resolver = DismissResolver(),
            handler = DismissHandler(),
        ),
        ActionBundleDefinition(kind = ActionKind("navigate"), resolver = NavigateResolver()),
        ActionBundleDefinition(
            kind = ActionKind("showAlert"),
            resolver = ShowAlertResolver(),
            handler = ShowAlertHandler(),
        ),
        ActionBundleDefinition(kind = ActionKind("openURL"), resolver = OpenURLResolver()),
        ActionBundleDefinition(kind = ActionKind("request"), resolver = RequestResolver()),
        ActionBundleDefinition(
            kind = ActionKind("sequence"),
            resolverFactory = { registry -> SequenceResolver(registry) },
            handler = SequenceHandler(),
        ),
    )

    override val sectionLayouts: List<SectionLayoutConfigResolving> = listOf(
        GridLayoutConfigResolver(),
        FlowLayoutConfigResolver(),
        ListLayoutConfigResolver(),
        HorizontalLayoutConfigResolver(),
    )

    override fun createRegistries(): ScalsRegistries {
        val componentRegistry = ComponentResolverRegistry()
        val actionResolverRegistry = ActionResolverRegistry()
        val sectionLayoutConfigRegistry = SectionLayoutConfigResolverRegistry()
        val composeRendererRegistry = ComposeNodeRendererRegistry()
        val actionRegistry = ActionRegistry()

        // Register components
        for (bundle in components) {
            componentRegistry.register(bundle.makeResolver())
            composeRendererRegistry.register(bundle.makeComposeRenderer())
        }

        // Register layouts
        for (bundle in layouts) {
            composeRendererRegistry.register(bundle.makeComposeRenderer())
        }

        // Register actions
        for (actionDef in actions) {
            actionResolverRegistry.register(actionDef.makeResolver(actionResolverRegistry))
            actionDef.handler?.let { actionRegistry.register(it) }
        }

        // Register section layout configs
        for (configResolver in sectionLayouts) {
            sectionLayoutConfigRegistry.register(configResolver)
        }

        return ScalsRegistries(
            componentRegistry = componentRegistry,
            actionResolverRegistry = actionResolverRegistry,
            sectionLayoutConfigRegistry = sectionLayoutConfigRegistry,
            composeRendererRegistry = composeRendererRegistry,
            actionRegistry = actionRegistry,
        )
    }

    /**
     * Creates a fully configured Resolver ready to resolve a document.
     */
    fun createResolver(
        document: com.example.scalsandroid.scals.document.Definition,
        designSystemProvider: DesignSystemProvider? = null,
    ): Resolver {
        val registries = createRegistries()

        val deferredLayoutResolver = DeferredLayoutResolver()

        val sectionLayoutResolver = SectionLayoutResolver(
            layoutResolver = deferredLayoutResolver,
            configRegistry = registries.sectionLayoutConfigRegistry,
        )

        val layoutResolver = LayoutResolver(
            componentRegistry = registries.componentRegistry,
            sectionLayoutResolver = sectionLayoutResolver,
        )

        // Wire the deferred resolver to the real one, breaking the circular dependency
        deferredLayoutResolver.setReal(layoutResolver)

        return Resolver(
            document = document,
            componentRegistry = registries.componentRegistry,
            actionResolverRegistry = registries.actionResolverRegistry,
            layoutResolver = layoutResolver,
            sectionLayoutResolver = sectionLayoutResolver,
            designSystemProvider = designSystemProvider,
        )
    }
}

/**
 * A layout resolver that defers to the real one; used to break the circular dependency
 * between LayoutResolver and SectionLayoutResolver.
 */
private class DeferredLayoutResolver : LayoutResolving {
    private var realResolver: LayoutResolving? = null

    fun setReal(resolver: LayoutResolving) {
        realResolver = resolver
    }

    override fun resolve(
        layout: com.example.scalsandroid.scals.document.Layout,
        context: ResolutionContext,
    ): NodeResolutionResult {
        return (realResolver ?: throw IllegalStateException("Layout resolver not initialized"))
            .resolve(layout, context)
    }

    override fun resolveNode(
        node: com.example.scalsandroid.scals.document.LayoutNode,
        context: ResolutionContext,
    ): NodeResolutionResult {
        return (realResolver ?: throw IllegalStateException("Layout resolver not initialized"))
            .resolveNode(node, context)
    }
}
