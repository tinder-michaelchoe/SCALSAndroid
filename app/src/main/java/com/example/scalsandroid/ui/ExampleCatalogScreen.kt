package com.example.scalsandroid.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.scalsandroid.examples.Example
import com.example.scalsandroid.examples.ExampleCategory
import com.example.scalsandroid.examples.ExampleRegistry
import com.example.scalsandroid.examples.PresentationStyle
import kotlinx.coroutines.launch

/**
 * Main screen showing a categorized list of all SCALS examples.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExampleCatalogScreen(
    modifier: Modifier = Modifier,
    onExampleClick: (Example) -> Unit = {}
) {
    var searchQuery by remember { mutableStateOf("") }
    var showSearchBar by remember { mutableStateOf(false) }
    var selectedExample by remember { mutableStateOf<Example?>(null) }
    var showActionSheet by remember { mutableStateOf(false) }
    var showJsonViewer by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(modifier = modifier.fillMaxSize()) {
        // Search bar
        if (showSearchBar) {
            SearchBar(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                onSearch = { },
                active = true,
                onActiveChange = { if (!it) showSearchBar = false },
                placeholder = { Text("Search examples...") },
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Search results
                val results = ExampleRegistry.search(searchQuery)
                LazyColumn {
                    items(results) { example ->
                        ExampleListItem(
                            example = example,
                            onClick = {
                                showSearchBar = false
                                onExampleClick(example)
                            },
                            onLongClick = {
                                selectedExample = example
                                showActionSheet = true
                            }
                        )
                    }
                }
            }
        }

        // Example list by category
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            // Show all categories
            ExampleCategory.all().forEach { category ->
                val examples = ExampleRegistry.getExamples(category)

                if (examples.isNotEmpty()) {
                    // Category header
                    item(key = "header_${category.name}") {
                        CategoryHeader(category = category)
                    }

                    // Examples in this category
                    items(
                        items = examples,
                        key = { it.id }
                    ) { example ->
                        ExampleListItem(
                            example = example,
                            onClick = { onExampleClick(example) },
                            onLongClick = {
                                selectedExample = example
                                showActionSheet = true
                            }
                        )
                    }
                }
            }

            // Show message if no examples yet
            if (ExampleRegistry.allExamplesList.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(32.dp)
                    ) {
                        Text(
                            text = "No examples available yet.\nExamples will be added in Phase 2.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }

    // Action bottom sheet
    if (showActionSheet && selectedExample != null) {
        ActionBottomSheet(
            example = selectedExample!!,
            onDismiss = { showActionSheet = false },
            onViewJson = {
                showActionSheet = false
                showJsonViewer = true
            },
            onCopyJson = { json ->
                showActionSheet = false
                copyToClipboard(context, json)
            }
        )
    }

    // JSON viewer sheet
    if (showJsonViewer && selectedExample != null) {
        JsonViewerSheet(
            example = selectedExample!!,
            onDismiss = { showJsonViewer = false },
            onCopy = { json ->
                copyToClipboard(context, json)
            }
        )
    }
}

@Composable
private fun CategoryHeader(category: ExampleCategory) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
    ) {
        Text(
            text = category.displayName,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = category.description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ExampleListItem(
    example: Example,
    onClick: () -> Unit,
    onLongClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = onClick,
                onLongClick = onLongClick
            ),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 12.dp)
        ) {
            Text(
                text = example.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = example.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
    HorizontalDivider(
        modifier = Modifier.padding(horizontal = 16.dp),
        color = MaterialTheme.colorScheme.outlineVariant
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun ActionBottomSheet(
    example: Example,
    onDismiss: () -> Unit,
    onViewJson: () -> Unit,
    onCopyJson: (String) -> Unit
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
        ) {
            // Title
            Text(
                text = example.title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
            )

            HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

            // View JSON option
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .combinedClickable(
                        onClick = {
                            scope.launch {
                                sheetState.hide()
                                onViewJson()
                            }
                        }
                    )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "View JSON",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            // Copy JSON option
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .combinedClickable(
                        onClick = {
                            scope.launch {
                                sheetState.hide()
                                onCopyJson(example.json)
                            }
                        }
                    )
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "📋",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Copy JSON",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun JsonViewerSheet(
    example: Example,
    onDismiss: () -> Unit,
    onCopy: (String) -> Unit
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val scope = rememberCoroutineScope()

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        modifier = Modifier.fillMaxHeight(0.9f)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Header with title and buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = example.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )

                Row {
                    IconButton(onClick = { onCopy(example.json) }) {
                        Text(
                            text = "📋",
                            fontSize = 20.sp
                        )
                    }
                    IconButton(
                        onClick = {
                            scope.launch {
                                sheetState.hide()
                                onDismiss()
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close"
                        )
                    }
                }
            }

            HorizontalDivider()

            // JSON content with line numbers
            val jsonLines = remember(example.json) {
                formatJson(example.json).split("\n")
            }

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                // Line numbers
                Column(
                    modifier = Modifier
                        .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                ) {
                    jsonLines.forEachIndexed { index, _ ->
                        Text(
                            text = "${index + 1}",
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontFamily = FontFamily.Monospace
                            ),
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.padding(vertical = 2.dp)
                        )
                    }
                }

                // Vertical divider
                VerticalDivider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(horizontal = 8.dp)
                )

                // JSON content
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(top = 16.dp, end = 16.dp, bottom = 16.dp)
                ) {
                    jsonLines.forEach { line ->
                        Text(
                            text = line,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontFamily = FontFamily.Monospace
                            ),
                            modifier = Modifier.padding(vertical = 2.dp)
                        )
                    }
                }
            }
        }
    }
}

private fun formatJson(json: String): String {
    return try {
        // Simple JSON formatting - add proper indentation
        val result = StringBuilder()
        var indentLevel = 0
        var inString = false
        var escapeNext = false

        for (i in json.indices) {
            val char = json[i]

            when {
                escapeNext -> {
                    result.append(char)
                    escapeNext = false
                }
                char == '\\' && inString -> {
                    result.append(char)
                    escapeNext = true
                }
                char == '"' -> {
                    result.append(char)
                    inString = !inString
                }
                inString -> {
                    result.append(char)
                }
                char == '{' || char == '[' -> {
                    result.append(char)
                    indentLevel++
                    result.append('\n')
                    result.append("  ".repeat(indentLevel))
                }
                char == '}' || char == ']' -> {
                    indentLevel--
                    result.append('\n')
                    result.append("  ".repeat(indentLevel))
                    result.append(char)
                }
                char == ',' -> {
                    result.append(char)
                    result.append('\n')
                    result.append("  ".repeat(indentLevel))
                }
                char == ':' -> {
                    result.append(char)
                    result.append(' ')
                }
                !char.isWhitespace() -> {
                    result.append(char)
                }
            }
        }

        result.toString()
    } catch (e: Exception) {
        json
    }
}

private fun copyToClipboard(context: Context, text: String) {
    val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = ClipData.newPlainText("JSON", text)
    clipboard.setPrimaryClip(clip)
}
