package com.example.scalsandroid.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.example.scalsandroid.scals.components.compose.ScalsView

/**
 * JSON Playground for editing and previewing custom SCALS documents.
 * Similar to the iOS app's JSON Playground feature.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JsonPlaygroundScreen(
    modifier: Modifier = Modifier
) {
    var jsonText by remember {
        mutableStateOf("""
{
  "id": "playground",
  "version": "1.0",
  "state": {
    "message": "Hello, SCALS!"
  },
  "root": {
    "type": "vstack",
    "spacing": 16,
    "children": [
      {
        "type": "text",
        "content": "${'$'}{message}",
        "fontSize": 24,
        "fontWeight": "bold"
      }
    ]
  }
}
        """.trimIndent())
    }
    var showPreview by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Column(modifier = modifier.fillMaxSize()) {
        // Editor section
        OutlinedCard(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "JSON Editor",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = jsonText,
                    onValueChange = {
                        jsonText = it
                        errorMessage = null
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    textStyle = MaterialTheme.typography.bodySmall.copy(
                        fontFamily = FontFamily.Monospace
                    ),
                    placeholder = { Text("Enter SCALS JSON here...") }
                )
            }
        }

        // Error display
        if (errorMessage != null) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Text(
                    text = "Error: $errorMessage",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onErrorContainer
                )
            }
        }

        // Render button
        Button(
            onClick = {
                try {
                    errorMessage = null
                    showPreview = true
                } catch (e: Exception) {
                    errorMessage = e.message
                    showPreview = false
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Icon(Icons.Default.PlayArrow, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Render")
        }

        // Preview section
        if (showPreview) {
            OutlinedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Text(
                        text = "Preview",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Render the SCALS document
                    ScalsView(json = jsonText)
                }
            }
        }
    }
}
