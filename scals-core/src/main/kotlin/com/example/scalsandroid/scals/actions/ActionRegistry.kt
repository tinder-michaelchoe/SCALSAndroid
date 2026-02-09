package com.example.scalsandroid.scals.actions

import com.example.scalsandroid.scals.document.ActionKind

class ActionRegistry {

    private val handlers: MutableMap<String, ActionHandler> = mutableMapOf()

    fun register(handler: ActionHandler) {
        handlers[handler.actionKind.rawValue] = handler
    }

    fun handler(kind: ActionKind): ActionHandler? {
        return handlers[kind.rawValue]
    }
}
