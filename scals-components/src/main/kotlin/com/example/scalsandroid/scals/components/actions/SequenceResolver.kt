package com.example.scalsandroid.scals.components.actions

import com.example.scalsandroid.scals.document.Action
import com.example.scalsandroid.scals.document.ActionKind
import com.example.scalsandroid.scals.document.StateValue
import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.ir.resolution.ActionResolverRegistry
import com.example.scalsandroid.scals.ir.resolution.ActionResolving
import com.example.scalsandroid.scals.ir.resolution.ResolutionContext
import com.example.scalsandroid.scals.state.StateValueConverter

class SequenceResolver(
    private val registry: ActionResolverRegistry,
) : ActionResolving {
    override val actionKind = ActionKind("sequence")

    override fun resolve(action: Action, context: ResolutionContext): IR.ActionDefinition {
        val stepsParam = action.parameters["steps"]
        val resolvedSteps = mutableListOf<IR.ActionDefinition>()

        if (stepsParam is StateValue.ArrayValue) {
            for (stepValue in stepsParam.value) {
                if (stepValue is StateValue.ObjectValue) {
                    val stepMap = stepValue.objectValue ?: continue
                    val typeStr = stepMap["type"]?.stringValue ?: continue
                    val params = stepMap.filterKeys { it != "type" }
                    val subAction = Action(
                        type = ActionKind(typeStr),
                        parameters = params,
                    )
                    resolvedSteps.add(registry.resolve(subAction, context))
                }
            }
        }

        return IR.ActionDefinition(
            kind = actionKind,
            executionData = mapOf("steps" to resolvedSteps),
        )
    }
}
