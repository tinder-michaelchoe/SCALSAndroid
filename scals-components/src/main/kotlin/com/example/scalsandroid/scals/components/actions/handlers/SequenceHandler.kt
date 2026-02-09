package com.example.scalsandroid.scals.components.actions.handlers

import com.example.scalsandroid.scals.actions.ActionContext
import com.example.scalsandroid.scals.actions.ActionHandler
import com.example.scalsandroid.scals.document.ActionKind
import com.example.scalsandroid.scals.ir.IR

class SequenceHandler : ActionHandler {
    override val actionKind = ActionKind("sequence")

    override fun execute(definition: IR.ActionDefinition, context: ActionContext) {
        val steps = definition.getList("steps") ?: return
        for (step in steps) {
            if (step is IR.ActionDefinition) {
                context.executeDefinition(step)
            }
        }
    }
}
