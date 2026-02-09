package com.example.scalsandroid.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.scalsandroid.examples.Example
import com.example.scalsandroid.examples.ExampleCategory
import com.example.scalsandroid.examples.ExampleRegistry
import com.example.scalsandroid.examples.PresentationStyle

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
                            onClick = { onExampleClick(example) }
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

@Composable
private fun ExampleListItem(
    example: Example,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
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
