package com.example.scalsandroid.scals.ir

import com.example.scalsandroid.scals.document.DocumentVersion
import com.example.scalsandroid.scals.state.StateStore

data class RenderTree(
    val irVersion: DocumentVersion = DocumentVersion.currentIR,
    val root: RootNode,
    val stateStore: StateStore,
    val actions: Map<String, IR.ActionDefinition> = emptyMap(),
)
