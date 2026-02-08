package com.example.scalsandroid.scals.document

import org.junit.Assert.*
import org.junit.Test

class LayoutNodeSerializerTest {

    @Test
    fun `dispatch to Layout for vstack`() {
        val json = """{"type": "vstack", "spacing": 8}"""
        val result = ScalsJson.decodeFromString(LayoutNode.serializer(), json)
        assertTrue(result is Layout)
        val layout = result as Layout
        assertEquals(LayoutType.VSTACK, layout.type)
        assertEquals(8.0, layout.spacing!!, 0.001)
    }

    @Test
    fun `dispatch to Layout for hstack`() {
        val json = """{"type": "hstack", "alignment": "center"}"""
        val result = ScalsJson.decodeFromString(LayoutNode.serializer(), json)
        assertTrue(result is Layout)
        assertEquals(LayoutType.HSTACK, (result as Layout).type)
    }

    @Test
    fun `dispatch to Layout for zstack`() {
        val json = """{"type": "zstack"}"""
        val result = ScalsJson.decodeFromString(LayoutNode.serializer(), json)
        assertTrue(result is Layout)
        assertEquals(LayoutType.ZSTACK, (result as Layout).type)
    }

    @Test
    fun `dispatch to Spacer`() {
        val json = """{"type": "spacer", "minLength": 10}"""
        val result = ScalsJson.decodeFromString(LayoutNode.serializer(), json)
        assertTrue(result is Spacer)
        assertEquals(10.0, (result as Spacer).minLength!!, 0.001)
    }

    @Test
    fun `dispatch to ForEach`() {
        val json = """{
            "type": "forEach",
            "items": [1, 2, 3],
            "template": {"type": "text", "text": "item"}
        }"""
        val result = ScalsJson.decodeFromString(LayoutNode.serializer(), json)
        assertTrue(result is ForEach)
        val forEach = result as ForEach
        assertEquals(3, forEach.items?.arrayValue?.size)
        assertTrue(forEach.template is Component)
    }

    @Test
    fun `dispatch to SectionLayout`() {
        val json = """{
            "type": "sectionLayout",
            "sections": [
                {
                    "layout": {"type": "list"},
                    "children": [{"type": "text", "text": "Row"}]
                }
            ]
        }"""
        val result = ScalsJson.decodeFromString(LayoutNode.serializer(), json)
        assertTrue(result is SectionLayout)
        val section = (result as SectionLayout).sections.first()
        assertEquals(SectionType.LIST, section.layout.type)
    }

    @Test
    fun `dispatch to Component for unknown type`() {
        val json = """{"type": "myWidget", "text": "hi"}"""
        val result = ScalsJson.decodeFromString(LayoutNode.serializer(), json)
        assertTrue(result is Component)
        assertEquals(ComponentKind("myWidget"), (result as Component).type)
    }

    @Test
    fun `nested tree`() {
        val json = """{
            "type": "vstack",
            "children": [
                {"type": "text", "text": "Title"},
                {"type": "hstack", "children": [
                    {"type": "button", "text": "OK"},
                    {"type": "spacer"}
                ]}
            ]
        }"""
        val result = ScalsJson.decodeFromString(LayoutNode.serializer(), json)
        assertTrue(result is Layout)
        val vstack = result as Layout
        assertEquals(2, vstack.children?.size)
        assertTrue(vstack.children!![0] is Component)
        assertTrue(vstack.children!![1] is Layout)
        val hstack = vstack.children!![1] as Layout
        assertEquals(2, hstack.children?.size)
        assertTrue(hstack.children!![0] is Component)
        assertTrue(hstack.children!![1] is Spacer)
    }
}
