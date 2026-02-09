package com.example.scalsandroid.examples

import com.example.scalsandroid.examples.components.componentExamples
import com.example.scalsandroid.examples.layouts.layoutExamples
import com.example.scalsandroid.examples.actions.actionExamples
import com.example.scalsandroid.examples.data.dataExamples
import com.example.scalsandroid.examples.styles.styleExamples
import com.example.scalsandroid.examples.complex.complexExamples
import com.example.scalsandroid.examples.custom.customExamples

/**
 * Central registry for all SCALS examples.
 * Organizes examples by category and provides search/filter capabilities.
 */
object ExampleRegistry {

    /**
     * All examples organized by category.
     */
    val allExamples: Map<ExampleCategory, List<Example>> by lazy {
        mapOf(
            ExampleCategory.COMPONENTS to componentExamples,
            ExampleCategory.LAYOUTS to layoutExamples,
            ExampleCategory.ACTIONS to actionExamples,
            ExampleCategory.DATA to dataExamples,
            ExampleCategory.STYLES to styleExamples,
            ExampleCategory.COMPLEX to complexExamples,
            ExampleCategory.CUSTOM to customExamples
        )
    }

    /**
     * Flattened list of all examples for search.
     */
    val allExamplesList: List<Example> by lazy {
        allExamples.values.flatten()
    }

    /**
     * Get examples for a specific category.
     */
    fun getExamples(category: ExampleCategory): List<Example> {
        return allExamples[category] ?: emptyList()
    }

    /**
     * Find an example by ID.
     */
    fun findById(id: String): Example? {
        return allExamplesList.find { it.id == id }
    }

    /**
     * Search examples by title or description.
     */
    fun search(query: String): List<Example> {
        if (query.isBlank()) return allExamplesList

        val lowerQuery = query.lowercase()
        return allExamplesList.filter { example ->
            example.title.lowercase().contains(lowerQuery) ||
            example.description.lowercase().contains(lowerQuery) ||
            example.category.displayName.lowercase().contains(lowerQuery)
        }
    }

    // Note: Category-specific example lists are imported from their respective packages
}
