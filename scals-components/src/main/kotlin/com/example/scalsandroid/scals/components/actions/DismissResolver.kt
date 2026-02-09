package com.example.scalsandroid.scals.components.actions

import com.example.scalsandroid.scals.document.Action
import com.example.scalsandroid.scals.document.ActionKind
import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.ir.resolution.ActionResolving
import com.example.scalsandroid.scals.ir.resolution.ResolutionContext

class DismissResolver : ActionResolving {
    override val actionKind = ActionKind("dismiss")

    override fun resolve(action: Action, context: ResolutionContext): IR.ActionDefinition {
        return IR.ActionDefinition(kind = actionKind)
    }
}
