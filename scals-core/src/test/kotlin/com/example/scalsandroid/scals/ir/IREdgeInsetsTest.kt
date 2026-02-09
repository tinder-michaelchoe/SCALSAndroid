package com.example.scalsandroid.scals.ir

import org.junit.Test
import org.junit.Assert.*

class IREdgeInsetsTest {

    @Test
    fun testZero() {
        val zero = IR.EdgeInsets.ZERO
        assertEquals(0.0, zero.top, 0.001)
        assertEquals(0.0, zero.leading, 0.001)
        assertEquals(0.0, zero.bottom, 0.001)
        assertEquals(0.0, zero.trailing, 0.001)
    }

    @Test
    fun testIsEmpty() {
        assertTrue(IR.EdgeInsets.ZERO.isEmpty)
        assertFalse(IR.EdgeInsets(top = 1.0).isEmpty)
        assertFalse(IR.EdgeInsets(leading = 1.0).isEmpty)
        assertFalse(IR.EdgeInsets(bottom = 1.0).isEmpty)
        assertFalse(IR.EdgeInsets(trailing = 1.0).isEmpty)
    }

    @Test
    fun testValues() {
        val insets = IR.EdgeInsets(top = 10.0, leading = 20.0, bottom = 30.0, trailing = 40.0)
        assertEquals(10.0, insets.top, 0.001)
        assertEquals(20.0, insets.leading, 0.001)
        assertEquals(30.0, insets.bottom, 0.001)
        assertEquals(40.0, insets.trailing, 0.001)
        assertFalse(insets.isEmpty)
    }
}
