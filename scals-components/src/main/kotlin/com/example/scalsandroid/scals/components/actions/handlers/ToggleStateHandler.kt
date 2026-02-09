package com.example.scalsandroid.scals.components.actions.handlers

import com.example.scalsandroid.scals.actions.ActionContext
import com.example.scalsandroid.scals.actions.ActionHandler
import com.example.scalsandroid.scals.document.ActionKind
import com.example.scalsandroid.scals.ir.IR

class ToggleStateHandler : ActionHandler {
    override val actionKind = ActionKind("toggleState")

    override fun execute(definition: IR.ActionDefinition, context: ActionContext) {
        val path = definition.getString("path") ?: return
        val current = context.stateStore.getValue(path)
        val boolValue = when (current) {
            is Boolean -> current
            else -> false
        }
        context.stateStore.set(path, !boolValue)
    }
}
