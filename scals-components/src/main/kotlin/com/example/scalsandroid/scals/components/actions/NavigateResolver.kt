package com.example.scalsandroid.scals.components.actions

import com.example.scalsandroid.scals.document.Action
import com.example.scalsandroid.scals.document.ActionKind
import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.ir.resolution.ActionResolving
import com.example.scalsandroid.scals.ir.resolution.ResolutionContext

class NavigateResolver : ActionResolving {
    override val actionKind = ActionKind("navigate")

    override fun resolve(action: Action, context: ResolutionContext): IR.ActionDefinition {
        val data = mutableMapOf<String, Any>()
        action.parameters["destination"]?.stringValue?.let { data["destination"] = it }
        action.parameters["presentation"]?.stringValue?.let { data["presentation"] = it }
        return IR.ActionDefinition(kind = actionKind, executionData = data)
    }
}
