package com.example.scalsandroid.scals.ir

import org.junit.Test
import org.junit.Assert.*

class IRAlignmentTest {

    @Test
    fun testPresets() {
        val center = IR.Alignment.center
        assertEquals(IR.HorizontalAlignment.CENTER, center.horizontal)
        assertEquals(IR.VerticalAlignment.CENTER, center.vertical)

        val leading = IR.Alignment.leading
        assertEquals(IR.HorizontalAlignment.LEADING, leading.horizontal)
        assertEquals(IR.VerticalAlignment.CENTER, leading.vertical)

        val trailing = IR.Alignment.trailing
        assertEquals(IR.HorizontalAlignment.TRAILING, trailing.horizontal)
        assertEquals(IR.VerticalAlignment.CENTER, trailing.vertical)

        val top = IR.Alignment.top
        assertEquals(IR.HorizontalAlignment.CENTER, top.horizontal)
        assertEquals(IR.VerticalAlignment.TOP, top.vertical)

        val bottom = IR.Alignment.bottom
        assertEquals(IR.HorizontalAlignment.CENTER, bottom.horizontal)
        assertEquals(IR.VerticalAlignment.BOTTOM, bottom.vertical)

        val topLeading = IR.Alignment.topLeading
        assertEquals(IR.HorizontalAlignment.LEADING, topLeading.horizontal)
        assertEquals(IR.VerticalAlignment.TOP, topLeading.vertical)

        val topTrailing = IR.Alignment.topTrailing
        assertEquals(IR.HorizontalAlignment.TRAILING, topTrailing.horizontal)
        assertEquals(IR.VerticalAlignment.TOP, topTrailing.vertical)

        val bottomLeading = IR.Alignment.bottomLeading
        assertEquals(IR.HorizontalAlignment.LEADING, bottomLeading.horizontal)
        assertEquals(IR.VerticalAlignment.BOTTOM, bottomLeading.vertical)

        val bottomTrailing = IR.Alignment.bottomTrailing
        assertEquals(IR.HorizontalAlignment.TRAILING, bottomTrailing.horizontal)
        assertEquals(IR.VerticalAlignment.BOTTOM, bottomTrailing.vertical)
    }
}
