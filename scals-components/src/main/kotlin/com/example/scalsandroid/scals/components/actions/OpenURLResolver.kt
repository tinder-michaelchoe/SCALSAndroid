package com.example.scalsandroid.scals.components.actions

import com.example.scalsandroid.scals.document.Action
import com.example.scalsandroid.scals.document.ActionKind
import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.ir.resolution.ActionResolving
import com.example.scalsandroid.scals.ir.resolution.ResolutionContext

class OpenURLResolver : ActionResolving {
    override val actionKind = ActionKind("openURL")

    override fun resolve(action: Action, context: ResolutionContext): IR.ActionDefinition {
        val url = action.parameters["url"]?.stringValue ?: ""
        return IR.ActionDefinition(
            kind = actionKind,
            executionData = mapOf("url" to url),
        )
    }
}
