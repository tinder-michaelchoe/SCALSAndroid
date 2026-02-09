package com.example.scalsandroid.scals.components.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.scalsandroid.scals.actions.ActionContext
import com.example.scalsandroid.scals.components.compose.extensions.toComposeColor
import com.example.scalsandroid.scals.components.compose.rendering.ComposeRenderContext
import com.example.scalsandroid.scals.components.manifests.CoreManifest
import com.example.scalsandroid.scals.components.manifests.ScalsRegistries
import com.example.scalsandroid.scals.document.Definition

@Composable
fun ScalsView(
    json: String,
    modifier: Modifier = Modifier,
    onDismiss: (() -> Unit)? = null,
    onShowAlert: ((com.example.scalsandroid.scals.actions.AlertConfig) -> Unit)? = null,
    onNavigate: ((String) -> Unit)? = null,
) {
    val parseResult = remember(json) {
        try {
            ParseResult.Success(Definition.fromJson(json))
        } catch (e: Exception) {
            android.util.Log.e("ScalsView", "Failed to parse JSON", e)
            android.util.Log.e("ScalsView", "JSON: $json")
            ParseResult.Error(e)
        }
    }

    when (parseResult) {
        is ParseResult.Success -> ScalsView(
            definition = parseResult.definition,
            modifier = modifier,
            onDismiss = onDismiss,
            onShowAlert = onShowAlert,
            onNavigate = onNavigate
        )
        is ParseResult.Error -> ErrorView(error = parseResult.exception, json = json, modifier = modifier)
    }
}

private sealed interface ParseResult {
    data class Success(val definition: Definition) : ParseResult
    data class Error(val exception: Exception) : ParseResult
}

@Composable
private fun ErrorView(error: Exception, json: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFFFEBEE))
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "⚠️ Failed to parse SCALS JSON",
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xFFC62828)
        )
        Text(
            text = "\n${error.javaClass.simpleName}: ${error.message}",
            style = MaterialTheme.typography.bodyMedium,
            color = Color(0xFFD32F2F)
        )
        if (error.cause != null) {
            Text(
                text = "\nCaused by: ${error.cause?.message}",
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFFD32F2F)
            )
        }
        Text(
            text = "\nStack trace:",
            style = MaterialTheme.typography.labelSmall,
            color = Color(0xFF666666),
            modifier = Modifier.padding(top = 8.dp)
        )
        Text(
            text = error.stackTraceToString().take(2000),
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = Color(0xFF333333),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

@Composable
fun ScalsView(
    definition: Definition,
    modifier: Modifier = Modifier,
    onDismiss: (() -> Unit)? = null,
    onShowAlert: ((com.example.scalsandroid.scals.actions.AlertConfig) -> Unit)? = null,
    onNavigate: ((String) -> Unit)? = null,
) {
    val renderResult = remember(definition) {
        try {
            val registries = CoreManifest.createRegistries()
            val resolver = CoreManifest.createResolver(definition)
            val renderTree = resolver.resolve()
            RenderResult.Success(registries, renderTree)
        } catch (e: Exception) {
            android.util.Log.e("ScalsView", "Failed to resolve definition", e)
            RenderResult.Error(e)
        }
    }

    when (renderResult) {
        is RenderResult.Success -> {
            ScalsViewContent(
                registries = renderResult.registries,
                renderTree = renderResult.renderTree,
                modifier = modifier,
                onDismiss = onDismiss,
                onShowAlert = onShowAlert,
                onNavigate = onNavigate
            )
        }
        is RenderResult.Error -> {
            ErrorView(
                error = renderResult.exception,
                json = definition.toJson(),
                modifier = modifier
            )
        }
    }
}

private sealed interface RenderResult {
    data class Success(
        val registries: ScalsRegistries,
        val renderTree: com.example.scalsandroid.scals.ir.RenderTree
    ) : RenderResult
    data class Error(val exception: Exception) : RenderResult
}

@Composable
private fun ScalsViewContent(
    registries: ScalsRegistries,
    renderTree: com.example.scalsandroid.scals.ir.RenderTree,
    modifier: Modifier,
    onDismiss: (() -> Unit)? = null,
    onShowAlert: ((com.example.scalsandroid.scals.actions.AlertConfig) -> Unit)? = null,
    onNavigate: ((String) -> Unit)? = null,
) {
    val composeStateStore = remember(renderTree) {
        ComposeStateStore(renderTree.stateStore)
    }

    val actionContext = remember(renderTree, onDismiss, onShowAlert, onNavigate) {
        ActionContext(
            stateStore = renderTree.stateStore,
            resolvedActions = renderTree.actions,
            actionRegistry = registries.actionRegistry,
            onDismiss = onDismiss,
            onShowAlert = onShowAlert,
            onNavigate = onNavigate,
        )
    }

    val renderContext = remember(composeStateStore, actionContext) {
        ComposeRenderContext(
            stateStore = composeStateStore,
            actionContext = actionContext,
            rendererRegistry = registries.composeRendererRegistry,
        )
    }

    DisposableEffect(composeStateStore) {
        onDispose {
            composeStateStore.dispose()
        }
    }

    val backgroundColor = renderTree.root.backgroundColor

    if (backgroundColor != null) {
        Surface(
            modifier = modifier,
            color = backgroundColor.toComposeColor(),
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                for (child in renderTree.root.children) {
                    renderContext.render(child)
                }
            }
        }
    } else {
        Column(modifier = modifier.fillMaxWidth()) {
            for (child in renderTree.root.children) {
                renderContext.render(child)
            }
        }
    }
}
