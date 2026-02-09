package com.example.scalsandroid.scals.ir.resolution

import com.example.scalsandroid.scals.document.Action
import com.example.scalsandroid.scals.document.ActionKind
import com.example.scalsandroid.scals.ir.IR

interface ActionResolving {
    val actionKind: ActionKind
    fun resolve(action: Action, context: ResolutionContext): IR.ActionDefinition
}
