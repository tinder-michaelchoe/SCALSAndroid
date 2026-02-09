package com.example.scalsandroid.scals.components.manifests

import com.example.scalsandroid.scals.actions.ActionHandler
import com.example.scalsandroid.scals.document.ActionKind
import com.example.scalsandroid.scals.ir.resolution.ActionResolverRegistry
import com.example.scalsandroid.scals.ir.resolution.ActionResolving

class ActionBundleDefinition private constructor(
    val kind: ActionKind,
    private val resolver: ActionResolving?,
    private val resolverFactory: ((ActionResolverRegistry) -> ActionResolving)?,
    val handler: ActionHandler? = null,
) {
    constructor(kind: ActionKind, resolver: ActionResolving, handler: ActionHandler? = null) :
        this(kind, resolver, null, handler)

    constructor(
        kind: ActionKind,
        resolverFactory: (ActionResolverRegistry) -> ActionResolving,
        handler: ActionHandler? = null,
    ) : this(kind, null, resolverFactory, handler)

    fun makeResolver(registry: ActionResolverRegistry): ActionResolving {
        return resolver ?: resolverFactory?.invoke(registry)
            ?: throw IllegalStateException("ActionBundleDefinition has neither resolver nor factory")
    }
}
