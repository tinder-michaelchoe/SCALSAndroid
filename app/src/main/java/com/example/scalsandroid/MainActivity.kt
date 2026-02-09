package com.example.scalsandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.scalsandroid.examples.Example
import com.example.scalsandroid.examples.PresentationStyle
import com.example.scalsandroid.scals.components.compose.ScalsView
import com.example.scalsandroid.ui.ExampleCatalogScreen
import com.example.scalsandroid.ui.JsonPlaygroundScreen
import com.example.scalsandroid.ui.theme.SCALSAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SCALSAndroidTheme {
                ScalsApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScalsApp() {
    var selectedTab by remember { mutableIntStateOf(0) }
    var selectedExample by remember { mutableStateOf<Example?>(null) }
    val tabs = listOf("Examples", "Playground")

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column(modifier = Modifier.windowInsetsPadding(WindowInsets.statusBars)) {
                TabRow(selectedTabIndex = selectedTab) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            text = { Text(title) },
                        )
                    }
                }
            }
        },
        contentWindowInsets = WindowInsets(0, 0, 0, 0)
    ) { innerPadding ->
        when (selectedTab) {
            0 -> ExampleCatalogScreen(
                modifier = Modifier.padding(innerPadding),
                onExampleClick = { example ->
                    selectedExample = example
                }
            )
            1 -> JsonPlaygroundScreen(
                modifier = Modifier.padding(innerPadding)
            )
        }
    }

    // Show example in bottom sheet when selected
    selectedExample?.let { example ->
        ExampleBottomSheet(
            example = example,
            onDismiss = { selectedExample = null }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExampleBottomSheet(
    example: Example,
    onDismiss: () -> Unit
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    var alertConfig by remember { mutableStateOf<com.example.scalsandroid.scals.actions.AlertConfig?>(null) }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = when (example.presentationStyle) {
            PresentationStyle.FULL_SCREEN -> true
            PresentationStyle.BOTTOM_SHEET_LARGE -> true
            PresentationStyle.BOTTOM_SHEET_MEDIUM -> true
            PresentationStyle.BOTTOM_SHEET_DYNAMIC -> false
            PresentationStyle.BOTTOM_SHEET_FIXED -> true
        }
    )

    // Determine height based on presentation style
    val heightModifier = when (example.presentationStyle) {
        PresentationStyle.FULL_SCREEN -> Modifier.fillMaxHeight(0.95f)
        PresentationStyle.BOTTOM_SHEET_LARGE -> Modifier.fillMaxHeight(0.85f)
        PresentationStyle.BOTTOM_SHEET_MEDIUM -> Modifier.fillMaxHeight(0.6f)
        PresentationStyle.BOTTOM_SHEET_DYNAMIC -> Modifier.heightIn(min = screenHeight * 0.5f)
        PresentationStyle.BOTTOM_SHEET_FIXED -> Modifier.fillMaxHeight(0.7f)
    }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
        ) {
            // Example title
            Text(
                text = example.title,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )

            // Render the SCALS example
            ScalsView(
                json = example.json,
                modifier = Modifier.fillMaxWidth(),
                onDismiss = onDismiss,
                onShowAlert = { config -> alertConfig = config }
            )
        }
    }

    // Show alert dialog if config is set
    alertConfig?.let { config ->
        AlertDialog(
            onDismissRequest = { alertConfig = null },
            title = { Text(config.title) },
            text = { Text(config.message) },
            confirmButton = {
                config.buttons.firstOrNull { it.style != "cancel" }?.let { button ->
                    TextButton(
                        onClick = {
                            button.action?.invoke()
                            alertConfig = null
                        },
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = if (button.style == "destructive") {
                                MaterialTheme.colorScheme.error
                            } else {
                                MaterialTheme.colorScheme.primary
                            }
                        )
                    ) {
                        Text(button.label)
                    }
                }
            },
            dismissButton = {
                config.buttons.firstOrNull { it.style == "cancel" }?.let { button ->
                    TextButton(
                        onClick = {
                            button.action?.invoke()
                            alertConfig = null
                        }
                    ) {
                        Text(button.label)
                    }
                }
            }
        )
    }
}
