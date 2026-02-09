package com.example.scalsandroid.scals.components.actions.handlers

import com.example.scalsandroid.scals.actions.ActionContext
import com.example.scalsandroid.scals.actions.ActionHandler
import com.example.scalsandroid.scals.document.ActionKind
import com.example.scalsandroid.scals.ir.IR

class SetStateHandler : ActionHandler {
    override val actionKind = ActionKind("setState")

    override fun execute(definition: IR.ActionDefinition, context: ActionContext) {
        val path = definition.getString("path") ?: return
        val expression = definition.getString("expression")
        if (expression != null) {
            val result = context.stateStore.evaluate(expression)
            context.stateStore.set(path, result)
        } else {
            val value = definition.executionData["value"]
            context.stateStore.set(path, value)
        }
    }
}
