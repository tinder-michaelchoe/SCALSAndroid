package com.example.scalsandroid.scals.components.actions

import com.example.scalsandroid.scals.document.Action
import com.example.scalsandroid.scals.document.ActionKind
import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.ir.resolution.ActionResolving
import com.example.scalsandroid.scals.ir.resolution.ResolutionContext
import com.example.scalsandroid.scals.state.StateValueConverter

class RequestResolver : ActionResolving {
    override val actionKind = ActionKind("request")

    override fun resolve(action: Action, context: ResolutionContext): IR.ActionDefinition {
        val data = mutableMapOf<String, Any>()
        action.parameters["url"]?.stringValue?.let { data["url"] = it }
        action.parameters["method"]?.stringValue?.let { data["method"] = it }
        action.parameters["headers"]?.let { data["headers"] = StateValueConverter.unwrap(it) ?: emptyMap<String, Any>() }
        action.parameters["body"]?.let { data["body"] = StateValueConverter.unwrap(it) ?: "" }
        action.parameters["onSuccess"]?.let { data["onSuccess"] = StateValueConverter.unwrap(it) ?: "" }
        action.parameters["onError"]?.let { data["onError"] = StateValueConverter.unwrap(it) ?: "" }
        return IR.ActionDefinition(kind = actionKind, executionData = data)
    }
}
