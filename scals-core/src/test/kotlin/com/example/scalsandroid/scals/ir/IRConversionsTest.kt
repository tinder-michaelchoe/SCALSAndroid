package com.example.scalsandroid.scals.ir

import com.example.scalsandroid.scals.document.*
import org.junit.Test
import org.junit.Assert.*

class IRConversionsTest {

    @Test
    fun testPaddingToEdgeInsets() {
        val padding = Padding(top = 10.0, leading = 20.0, bottom = 30.0, trailing = 40.0)
        val edgeInsets = padding.toEdgeInsets()
        assertEquals(10.0, edgeInsets.top, 0.001)
        assertEquals(20.0, edgeInsets.leading, 0.001)
        assertEquals(30.0, edgeInsets.bottom, 0.001)
        assertEquals(40.0, edgeInsets.trailing, 0.001)
    }

    @Test
    fun testHorizontalAlignmentConversion() {
        assertEquals(IR.HorizontalAlignment.LEADING, HorizontalAlignment.LEADING.toIR())
        assertEquals(IR.HorizontalAlignment.CENTER, HorizontalAlignment.CENTER.toIR())
        assertEquals(IR.HorizontalAlignment.TRAILING, HorizontalAlignment.TRAILING.toIR())
    }

    @Test
    fun testVerticalAlignmentConversion() {
        assertEquals(IR.VerticalAlignment.TOP, VerticalAlignment.TOP.toIR())
        assertEquals(IR.VerticalAlignment.CENTER, VerticalAlignment.CENTER.toIR())
        assertEquals(IR.VerticalAlignment.BOTTOM, VerticalAlignment.BOTTOM.toIR())
    }

    @Test
    fun testDimensionValueConversion() {
        val absolute = DimensionValue.Absolute(100.0).toIR()
        assertTrue(absolute is IR.DimensionValue.Absolute)
        assertEquals(100.0, (absolute as IR.DimensionValue.Absolute).value, 0.001)

        val fractional = DimensionValue.Fractional(0.5).toIR()
        assertTrue(fractional is IR.DimensionValue.Fractional)
        assertEquals(0.5, (fractional as IR.DimensionValue.Fractional).value, 0.001)
    }

    @Test
    fun testSnapBehaviorConversion() {
        assertEquals(IR.SnapBehavior.NONE, SnapBehavior.NONE.toIR())
        assertEquals(IR.SnapBehavior.VIEW_ALIGNED, SnapBehavior.VIEW_ALIGNED.toIR())
        assertEquals(IR.SnapBehavior.PAGING, SnapBehavior.PAGING.toIR())
    }
}
