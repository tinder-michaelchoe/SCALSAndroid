package com.example.scalsandroid.examples

/**
 * Represents a single SCALS example with metadata.
 */
data class Example(
    val id: String,
    val title: String,
    val description: String,
    val category: ExampleCategory,
    val json: String,
    val presentationStyle: PresentationStyle = PresentationStyle.BOTTOM_SHEET_MEDIUM,
    val supportsCustomActions: Boolean = false
)

/**
 * Defines how an example should be presented to the user.
 */
enum class PresentationStyle {
    /** Bottom sheet taking ~50% of screen height */
    BOTTOM_SHEET_MEDIUM,

    /** Bottom sheet taking ~90% of screen height */
    BOTTOM_SHEET_LARGE,

    /** Bottom sheet that wraps content height */
    BOTTOM_SHEET_DYNAMIC,

    /** Full screen presentation */
    FULL_SCREEN,

    /** Fixed height bottom sheet */
    BOTTOM_SHEET_FIXED
}
