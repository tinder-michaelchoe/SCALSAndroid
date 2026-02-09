package com.example.scalsandroid.scals.components.actions

import com.example.scalsandroid.scals.document.Action
import com.example.scalsandroid.scals.document.ActionKind
import com.example.scalsandroid.scals.document.StateValue
import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.ir.resolution.ActionResolving
import com.example.scalsandroid.scals.ir.resolution.ResolutionContext
import com.example.scalsandroid.scals.state.StateValueConverter

class SetStateResolver : ActionResolving {
    override val actionKind = ActionKind("setState")

    override fun resolve(action: Action, context: ResolutionContext): IR.ActionDefinition {
        val path = action.parameters["path"]?.stringValue ?: ""
        val data = mutableMapOf<String, Any>("path" to path)

        val valueParam = action.parameters["value"]
        if (valueParam != null) {
            // Check for expression: { "$expr": "counter + 1" }
            if (valueParam is StateValue.ObjectValue) {
                val exprValue = valueParam.objectValue?.get("\$expr")?.stringValue
                if (exprValue != null) {
                    data["expression"] = exprValue
                } else {
                    data["value"] = StateValueConverter.unwrap(valueParam) ?: ""
                }
            } else {
                data["value"] = StateValueConverter.unwrap(valueParam) ?: ""
            }
        }

        return IR.ActionDefinition(kind = actionKind, executionData = data)
    }
}
