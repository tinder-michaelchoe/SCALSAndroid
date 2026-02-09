package com.example.scalsandroid.scals.actions

import com.example.scalsandroid.scals.document.ActionBinding
import com.example.scalsandroid.scals.ir.IR
import com.example.scalsandroid.scals.state.StateStore

data class AlertButton(
    val label: String,
    val style: String, // "default", "cancel", "destructive"
    val action: (() -> Unit)? = null
)

data class AlertConfig(
    val title: String,
    val message: String,
    val buttons: List<AlertButton>
)

class ActionContext(
    val stateStore: StateStore,
    val resolvedActions: Map<String, IR.ActionDefinition>,
    val actionRegistry: ActionRegistry,
    var onDismiss: (() -> Unit)? = null,
    var onShowAlert: ((AlertConfig) -> Unit)? = null,
    var onNavigate: ((String) -> Unit)? = null,
) {
    fun executeAction(id: String) {
        val definition = resolvedActions[id] ?: return
        executeDefinition(definition)
    }

    fun executeBinding(binding: ActionBinding?) {
        when (binding) {
            is ActionBinding.Reference -> executeAction(binding.name)
            is ActionBinding.Inline -> {
                // Inline actions should have been resolved to definitions during resolution.
                // This is a fallback; in practice the render tree stores resolved definitions.
            }
            null -> {}
        }
    }

    fun executeDefinition(definition: IR.ActionDefinition) {
        val handler = actionRegistry.handler(definition.kind) ?: return
        handler.execute(definition, this)
    }
}
