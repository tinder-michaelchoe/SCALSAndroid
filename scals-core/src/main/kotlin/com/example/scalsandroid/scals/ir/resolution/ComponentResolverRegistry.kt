package com.example.scalsandroid.scals.ir.resolution

import com.example.scalsandroid.scals.document.Component
import com.example.scalsandroid.scals.document.ComponentKind

class ComponentResolverRegistry {

    private val resolvers: MutableMap<ComponentKind, ComponentResolving> = mutableMapOf()

    fun register(resolver: ComponentResolving) {
        resolvers[resolver.componentKind] = resolver
    }

    fun unregister(kind: ComponentKind) {
        resolvers.remove(kind)
    }

    fun resolve(component: Component, context: ResolutionContext): ComponentResolutionResult {
        val resolver = resolvers[component.type]
            ?: throw ComponentResolutionError.UnknownKind(component.type)
        return resolver.resolve(component, context)
    }

    fun hasResolver(kind: ComponentKind): Boolean = kind in resolvers

    val registeredKinds: List<ComponentKind> get() = resolvers.keys.toList()
}

sealed class ComponentResolutionError(message: String) : Exception(message) {
    class UnknownKind(val kind: ComponentKind) :
        ComponentResolutionError("No resolver registered for component kind: ${kind.rawValue}")
}
