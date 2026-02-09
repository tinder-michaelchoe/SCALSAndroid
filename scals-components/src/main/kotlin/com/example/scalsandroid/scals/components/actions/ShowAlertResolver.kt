package com.example.scalsandroid.scals.components.actions

import com.example.scalsandroid.scals.document.Action
import com.example.scalsandroid.scals.document.ActionKind
import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.ir.resolution.ActionResolving
import com.example.scalsandroid.scals.ir.resolution.ResolutionContext
import com.example.scalsandroid.scals.state.StateValueConverter

class ShowAlertResolver : ActionResolving {
    override val actionKind = ActionKind("showAlert")

    override fun resolve(action: Action, context: ResolutionContext): IR.ActionDefinition {
        val data = mutableMapOf<String, Any>()
        action.parameters["title"]?.stringValue?.let { data["title"] = it }
        action.parameters["message"]?.stringValue?.let { data["message"] = it }
        action.parameters["buttons"]?.let { buttons ->
            data["buttons"] = StateValueConverter.unwrap(buttons) ?: emptyList<Any>()
        }
        return IR.ActionDefinition(kind = actionKind, executionData = data)
    }
}
