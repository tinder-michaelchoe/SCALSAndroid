package com.example.scalsandroid.scals.components.resolvers

import com.example.scalsandroid.scals.document.Component
import com.example.scalsandroid.scals.ir.resolution.ResolutionContext
import com.example.scalsandroid.scals.state.StateValueConverter
import com.example.scalsandroid.scals.viewtree.ViewNode
import java.util.UUID

internal fun beginTracking(component: Component, context: ResolutionContext): ViewNode? {
    if (!context.isTracking) return null
    val viewNode = ViewNode(id = component.id ?: UUID.randomUUID().toString())
    viewNode.parent = context.parentViewNode
    context.tracker?.beginTracking(viewNode)
    return viewNode
}

internal fun endTracking(context: ResolutionContext) {
    if (context.isTracking) {
        context.tracker?.endTracking()
    }
}

internal fun initializeLocalState(viewNode: ViewNode?, component: Component) {
    // Currently components don't have local state declarations
    // This is a placeholder for future extension
}
