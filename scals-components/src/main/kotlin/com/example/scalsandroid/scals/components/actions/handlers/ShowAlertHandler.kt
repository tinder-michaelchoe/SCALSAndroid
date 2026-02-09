package com.example.scalsandroid.scals.components.actions.handlers

import com.example.scalsandroid.scals.actions.ActionContext
import com.example.scalsandroid.scals.actions.ActionHandler
import com.example.scalsandroid.scals.actions.AlertButton
import com.example.scalsandroid.scals.actions.AlertConfig
import com.example.scalsandroid.scals.document.ActionKind
import com.example.scalsandroid.scals.ir.IR

class ShowAlertHandler : ActionHandler {
    override val actionKind = ActionKind("showAlert")

    override fun execute(definition: IR.ActionDefinition, context: ActionContext) {
        val title = definition.getString("title") ?: "Alert"
        val message = definition.getString("message") ?: ""
        val buttonsData = definition.executionData["buttons"] as? List<*> ?: emptyList<Any>()

        val buttons = buttonsData.mapNotNull { buttonData ->
            val buttonMap = buttonData as? Map<*, *> ?: return@mapNotNull null
            val label = buttonMap["label"] as? String ?: return@mapNotNull null
            val style = buttonMap["style"] as? String ?: "default"
            val actionId = buttonMap["action"] as? String

            AlertButton(
                label = label,
                style = style,
                action = if (actionId != null) {
                    { context.executeAction(actionId) }
                } else null
            )
        }

        val config = AlertConfig(
            title = title,
            message = message,
            buttons = buttons
        )

        context.onShowAlert?.invoke(config)
    }
}
