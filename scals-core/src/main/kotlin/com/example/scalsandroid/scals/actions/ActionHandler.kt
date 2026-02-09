package com.example.scalsandroid.scals.actions

import com.example.scalsandroid.scals.document.ActionKind
import com.example.scalsandroid.scals.ir.IR

interface ActionHandler {
    val actionKind: ActionKind
    fun execute(definition: IR.ActionDefinition, context: ActionContext)
}
