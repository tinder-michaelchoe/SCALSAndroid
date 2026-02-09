package com.example.scalsandroid.scals.ir

import org.junit.Test
import org.junit.Assert.*

class IRColorTest {

    @Test
    fun testFromHexRGB() {
        val color = IR.Color.fromHex("#F00")
        assertNotNull(color)
        assertEquals(1.0, color!!.red, 0.001)
        assertEquals(0.0, color.green, 0.001)
        assertEquals(0.0, color.blue, 0.001)
        assertEquals(1.0, color.alpha, 0.001)
    }

    @Test
    fun testFromHexRRGGBB() {
        val red = IR.Color.fromHex("#FF0000")
        assertNotNull(red)
        assertEquals(1.0, red!!.red, 0.001)
        assertEquals(0.0, red.green, 0.001)
        assertEquals(0.0, red.blue, 0.001)

        val green = IR.Color.fromHex("#00FF00")
        assertNotNull(green)
        assertEquals(0.0, green!!.red, 0.001)
        assertEquals(1.0, green.green, 0.001)
        assertEquals(0.0, green.blue, 0.001)

        val blue = IR.Color.fromHex("#0000FF")
        assertNotNull(blue)
        assertEquals(0.0, blue!!.red, 0.001)
        assertEquals(0.0, blue.green, 0.001)
        assertEquals(1.0, blue.blue, 0.001)
    }

    @Test
    fun testFromHexAARRGGBB() {
        val color = IR.Color.fromHex("#80FF0000")
        assertNotNull(color)
        assertEquals(1.0, color!!.red, 0.001)
        assertEquals(0.0, color.green, 0.001)
        assertEquals(0.0, color.blue, 0.001)
        assertEquals(0.502, color.alpha, 0.01)
    }

    @Test
    fun testFromRgba() {
        val color = IR.Color.fromHex("rgba(255,0,0,1.0)")
        assertNotNull(color)
        assertEquals(1.0, color!!.red, 0.001)
        assertEquals(0.0, color.green, 0.001)
        assertEquals(0.0, color.blue, 0.001)
        assertEquals(1.0, color.alpha, 0.001)
    }

    @Test
    fun testConstants() {
        val black = IR.Color.black
        assertEquals(0.0, black.red, 0.001)
        assertEquals(0.0, black.green, 0.001)
        assertEquals(0.0, black.blue, 0.001)
        assertEquals(1.0, black.alpha, 0.001)

        val white = IR.Color.white
        assertEquals(1.0, white.red, 0.001)
        assertEquals(1.0, white.green, 0.001)
        assertEquals(1.0, white.blue, 0.001)
        assertEquals(1.0, white.alpha, 0.001)

        val clear = IR.Color.clear
        assertEquals(0.0, clear.red, 0.001)
        assertEquals(0.0, clear.green, 0.001)
        assertEquals(0.0, clear.blue, 0.001)
        assertEquals(0.0, clear.alpha, 0.001)

        val red = IR.Color.red
        assertEquals(1.0, red.red, 0.001)
        assertEquals(0.0, red.green, 0.001)
        assertEquals(0.0, red.blue, 0.001)
        assertEquals(1.0, red.alpha, 0.001)

        val green = IR.Color.green
        assertEquals(0.0, green.red, 0.001)
        assertEquals(1.0, green.green, 0.001)
        assertEquals(0.0, green.blue, 0.001)
        assertEquals(1.0, green.alpha, 0.001)

        val blue = IR.Color.blue
        assertEquals(0.0, blue.red, 0.001)
        assertEquals(0.0, blue.green, 0.001)
        assertEquals(1.0, blue.blue, 0.001)
        assertEquals(1.0, blue.alpha, 0.001)
    }

    @Test
    fun testInvalidHex() {
        assertNull(IR.Color.fromHex("invalid"))
        assertNull(IR.Color.fromHex("#GGG"))
        assertNull(IR.Color.fromHex("notahex"))
        assertNull(IR.Color.fromHex("#"))
    }
}
