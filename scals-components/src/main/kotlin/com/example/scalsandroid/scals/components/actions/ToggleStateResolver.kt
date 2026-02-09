package com.example.scalsandroid.scals.components.actions

import com.example.scalsandroid.scals.document.Action
import com.example.scalsandroid.scals.document.ActionKind
import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.ir.resolution.ActionResolving
import com.example.scalsandroid.scals.ir.resolution.ResolutionContext

class ToggleStateResolver : ActionResolving {
    override val actionKind = ActionKind("toggleState")

    override fun resolve(action: Action, context: ResolutionContext): IR.ActionDefinition {
        val path = action.parameters["path"]?.stringValue ?: ""
        return IR.ActionDefinition(
            kind = actionKind,
            executionData = mapOf("path" to path),
        )
    }
}
