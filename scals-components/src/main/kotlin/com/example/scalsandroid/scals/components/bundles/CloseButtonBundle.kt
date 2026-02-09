package com.example.scalsandroid.scals.components.bundles

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.scalsandroid.scals.components.compose.rendering.ComposeNodeRendering
import com.example.scalsandroid.scals.components.compose.rendering.ComposeRenderContext
import com.example.scalsandroid.scals.components.manifests.ComponentBundle
import com.example.scalsandroid.scals.components.nodes.RenderNodeKinds
import com.example.scalsandroid.scals.components.resolvers.beginTracking
import com.example.scalsandroid.scals.components.resolvers.endTracking
import com.example.scalsandroid.scals.document.Component
import com.example.scalsandroid.scals.document.ComponentKind
import com.example.scalsandroid.scals.ir.RenderNode
import com.example.scalsandroid.scals.ir.RenderNodeData
import com.example.scalsandroid.scals.ir.RenderNodeKind
import com.example.scalsandroid.scals.ir.resolution.ComponentResolutionResult
import com.example.scalsandroid.scals.ir.resolution.ComponentResolving
import com.example.scalsandroid.scals.ir.resolution.ResolutionContext

/**
 * Custom close button component with circular gray background.
 *
 * JSON usage:
 * ```json
 * {
 *   "type": "closeButton",
 *   "actions": { "onTap": "dismiss" }
 * }
 * ```
 */
object CloseButtonBundle : ComponentBundle {
    override val componentKind = ComponentKind("closeButton")
    override val nodeKind: RenderNodeKind = RenderNodeKinds.CLOSE_BUTTON

    override fun makeResolver(): ComponentResolving = CloseButtonResolver()
    override fun makeComposeRenderer(): ComposeNodeRendering = CloseButtonRenderer()
}

class CloseButtonResolver : ComponentResolving {
    override val componentKind = ComponentKind("closeButton")

    override fun resolve(component: Component, context: ResolutionContext): ComponentResolutionResult {
        val viewNode = beginTracking(component, context)

        endTracking(context)

        val renderNode = RenderNode(CloseButtonNode(
            id = component.id,
            styleId = component.styleId,
            onTap = component.actions?.onTap,
        ))

        return ComponentResolutionResult(renderNode, viewNode)
    }
}

class CloseButtonRenderer : ComposeNodeRendering {
    override val nodeKind: RenderNodeKind = RenderNodeKinds.CLOSE_BUTTON

    @Composable
    override fun render(node: RenderNode, context: ComposeRenderContext) {
        val data = node.data<CloseButtonNode>() ?: return

        Box(
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape)
                .background(Color(0xFFF5F5F5))
                .clickable {
                    data.onTap?.let { binding ->
                        context.actionContext.executeBinding(binding)
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Close",
                tint = Color(0xFF666666),
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

data class CloseButtonNode(
    override val id: String? = null,
    override val styleId: String? = null,
    val onTap: com.example.scalsandroid.scals.document.ActionBinding? = null,
) : RenderNodeData {
    override val nodeKind: RenderNodeKind get() = RenderNodeKinds.CLOSE_BUTTON
}

