package com.example.scalsandroid.scals.ir.resolution

import com.example.scalsandroid.scals.document.Action
import com.example.scalsandroid.scals.document.ActionKind
import com.example.scalsandroid.scals.ir.IR
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

class ActionResolverRegistry {

    private val lock = ReentrantLock()
    private val resolvers: MutableMap<ActionKind, ActionResolving> = mutableMapOf()

    fun register(resolver: ActionResolving) {
        lock.withLock { resolvers[resolver.actionKind] = resolver }
    }

    fun resolve(action: Action, context: ResolutionContext): IR.ActionDefinition {
        val resolver = lock.withLock { resolvers[action.type] }
            ?: return IR.ActionDefinition(kind = action.type) // passthrough for unknown
        return resolver.resolve(action, context)
    }

    fun resolveAll(actions: Map<String, Action>, context: ResolutionContext): Map<String, IR.ActionDefinition> {
        return actions.mapValues { (_, action) -> resolve(action, context) }
    }
}
