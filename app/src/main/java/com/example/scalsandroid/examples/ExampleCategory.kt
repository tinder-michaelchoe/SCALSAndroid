package com.example.scalsandroid.examples

/**
 * Categories for organizing SCALS examples, following the SCALS naming convention.
 * Based on the iOS ScalsRenderer app structure.
 */
enum class ExampleCategory(val displayName: String, val description: String) {
    COMPONENTS(
        displayName = "Components",
        description = "UI components like buttons, text, images, and controls"
    ),
    LAYOUTS(
        displayName = "Layouts",
        description = "Container layouts for organizing components"
    ),
    ACTIONS(
        displayName = "Actions",
        description = "User interactions and state modifications"
    ),
    DATA(
        displayName = "Data",
        description = "State management and data binding"
    ),
    STYLES(
        displayName = "Styles",
        description = "Visual styling and theming"
    ),
    COMPLEX(
        displayName = "Complex Examples",
        description = "Complete mini-apps demonstrating multiple features"
    ),
    CUSTOM(
        displayName = "Custom Components",
        description = "Examples with custom UI components"
    );

    companion object {
        /**
         * Returns all categories in display order
         */
        fun all(): List<ExampleCategory> = entries.toList()
    }
}
