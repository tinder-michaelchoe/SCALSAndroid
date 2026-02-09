package com.example.scalsandroid.scals.ir.resolution

import com.example.scalsandroid.scals.document.Action
import com.example.scalsandroid.scals.document.ActionBinding
import com.example.scalsandroid.scals.document.Definition
import com.example.scalsandroid.scals.ir.IR

class ActionResolver(
    private val registry: ActionResolverRegistry,
) {
    fun resolve(action: Action, context: ResolutionContext): IR.ActionDefinition {
        return registry.resolve(action, context)
    }

    fun resolveBinding(
        binding: ActionBinding?,
        documentActions: Map<String, Action>?,
        context: ResolutionContext,
    ): IR.ActionDefinition? {
        if (binding == null) return null
        return when (binding) {
            is ActionBinding.Reference -> {
                val action = documentActions?.get(binding.name) ?: return null
                resolve(action, context)
            }
            is ActionBinding.Inline -> resolve(binding.action, context)
        }
    }

    fun resolveAll(actions: Map<String, Action>, context: ResolutionContext): Map<String, IR.ActionDefinition> {
        return registry.resolveAll(actions, context)
    }
}
