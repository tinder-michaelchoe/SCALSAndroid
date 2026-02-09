package com.example.scalsandroid.scals.ir.resolution

interface DesignSystemProvider {
    fun resolveStyle(reference: String): ResolvedStyle?
}
