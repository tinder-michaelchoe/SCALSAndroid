package com.example.scalsandroid.scals.components.actions.handlers

import com.example.scalsandroid.scals.actions.ActionContext
import com.example.scalsandroid.scals.actions.ActionHandler
import com.example.scalsandroid.scals.document.ActionKind
import com.example.scalsandroid.scals.ir.IR

class DismissHandler : ActionHandler {
    override val actionKind = ActionKind("dismiss")

    override fun execute(definition: IR.ActionDefinition, context: ActionContext) {
        context.onDismiss?.invoke()
    }
}
