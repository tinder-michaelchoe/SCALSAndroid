package com.example.scalsandroid.scals.document

import org.junit.Assert.*
import org.junit.Test

class DefinitionTest {

    @Test
    fun `parse minimal definition`() {
        val json = """{
            "id": "test-doc",
            "root": {
                "children": []
            }
        }"""
        val definition = Definition.fromJson(json)
        assertEquals("test-doc", definition.id)
        assertTrue(definition.root.children.isEmpty())
        assertNull(definition.version)
        assertNull(definition.state)
    }

    @Test
    fun `parse definition with version`() {
        val json = """{
            "id": "versioned",
            "version": {"major": 0, "minor": 1, "patch": 0},
            "root": {"children": []}
        }"""
        val definition = Definition.fromJson(json)
        assertEquals(DocumentVersion(0, 1, 0), definition.version)
    }

    @Test
    fun `parse definition with state`() {
        val json = """{
            "id": "stateful",
            "state": {
                "count": 0,
                "name": "default",
                "active": true
            },
            "root": {"children": []}
        }"""
        val definition = Definition.fromJson(json)
        val state = definition.state!!
        assertEquals(StateValue.IntValue(0L), state["count"])
        assertEquals(StateValue.StringValue("default"), state["name"])
        assertEquals(StateValue.BoolValue(true), state["active"])
    }

    @Test
    fun `parse definition with styles`() {
        val json = """{
            "id": "styled",
            "styles": {
                "heading": {
                    "fontSize": 24,
                    "fontWeight": "bold",
                    "textColor": "#000000"
                }
            },
            "root": {"children": []}
        }"""
        val definition = Definition.fromJson(json)
        val heading = definition.styles!!["heading"]!!
        assertEquals(24.0, heading.fontSize!!, 0.001)
        assertEquals(FontWeight.BOLD, heading.fontWeight)
        assertEquals("#000000", heading.textColor)
    }

    @Test
    fun `parse definition with actions`() {
        val json = """{
            "id": "actionable",
            "actions": {
                "increment": {"type": "setState", "key": "count", "value": 1}
            },
            "root": {"children": []}
        }"""
        val definition = Definition.fromJson(json)
        val increment = definition.actions!!["increment"]!!
        assertEquals(ActionKind("setState"), increment.type)
        assertEquals(StateValue.StringValue("count"), increment.parameters["key"])
    }

    @Test
    fun `parse definition with data sources`() {
        val json = """{
            "id": "data-driven",
            "dataSources": {
                "items": {"type": "static", "value": [1, 2, 3]}
            },
            "root": {"children": []}
        }"""
        val definition = Definition.fromJson(json)
        val items = definition.dataSources!!["items"]!!
        assertEquals(DataSourceKind.STATIC, items.type)
        assertEquals(3, items.value?.arrayValue?.size)
    }

    @Test
    fun `parse definition with root component`() {
        val json = """{
            "id": "full",
            "root": {
                "backgroundColor": "#FFFFFF",
                "children": [
                    {"type": "vstack", "spacing": 16, "children": [
                        {"type": "text", "text": "Hello World"},
                        {"type": "button", "text": "Click Me", "actions": {"onTap": "handleTap"}}
                    ]}
                ]
            }
        }"""
        val definition = Definition.fromJson(json)
        assertEquals("#FFFFFF", definition.root.backgroundColor)
        assertEquals(1, definition.root.children.size)
        assertTrue(definition.root.children[0] is Layout)
        val vstack = definition.root.children[0] as Layout
        assertEquals(2, vstack.children?.size)
    }

    @Test
    fun `round trip full document`() {
        val definition = Definition(
            id = "round-trip-test",
            version = DocumentVersion(0, 1, 0),
            state = mapOf(
                "counter" to StateValue.IntValue(0L),
                "title" to StateValue.StringValue("Test"),
            ),
            styles = mapOf(
                "body" to Style(fontSize = 16.0, fontWeight = FontWeight.REGULAR),
            ),
            actions = mapOf(
                "inc" to Action(ActionKind("setState"), mapOf("key" to StateValue.StringValue("counter"))),
            ),
            root = RootComponent(
                backgroundColor = "#FFFFFF",
                children = listOf(
                    Layout(
                        type = LayoutType.VSTACK,
                        spacing = 8.0,
                        children = listOf(
                            Component(type = ComponentKind("text"), text = "Hello"),
                            Spacer(minLength = 10.0),
                            Component(
                                type = ComponentKind("button"),
                                text = "Tap",
                                actions = ComponentActions(onTap = ActionBinding.Reference("inc")),
                            ),
                        ),
                    ),
                ),
            ),
        )
        val json = definition.toJson()
        val decoded = Definition.fromJson(json)
        assertEquals(definition, decoded)
    }

    @Test
    fun `parse definition with edge insets`() {
        val json = """{
            "id": "insets",
            "root": {
                "edgeInsets": {
                    "top": 10,
                    "bottom": {"positioning": "absolute", "value": 20}
                },
                "children": []
            }
        }"""
        val definition = Definition.fromJson(json)
        val insets = definition.root.edgeInsets!!
        assertEquals(Positioning.SAFE_AREA, insets.top?.positioning)
        assertEquals(10.0, insets.top?.value!!, 0.001)
        assertEquals(Positioning.ABSOLUTE, insets.bottom?.positioning)
        assertEquals(20.0, insets.bottom?.value!!, 0.001)
    }

    @Test
    fun `parse definition with section layout`() {
        val json = """{
            "id": "sections",
            "root": {
                "children": [{
                    "type": "sectionLayout",
                    "sectionSpacing": 12,
                    "sections": [{
                        "layout": {"type": "grid", "columns": {"adaptive": {"minWidth": 100}}, "itemSpacing": 8},
                        "children": [{"type": "text", "text": "Cell"}]
                    }]
                }]
            }
        }"""
        val definition = Definition.fromJson(json)
        val sectionLayout = definition.root.children[0] as SectionLayout
        assertEquals(12.0, sectionLayout.sectionSpacing!!, 0.001)
        val section = sectionLayout.sections[0]
        assertEquals(SectionType.GRID, section.layout.type)
        assertEquals(ColumnConfig.Adaptive(100.0), section.layout.columns)
        assertEquals(8.0, section.layout.itemSpacing!!, 0.001)
    }

    @Test
    fun `unknown keys are ignored`() {
        val json = """{
            "id": "forward-compat",
            "unknownField": "should be ignored",
            "root": {"children": [], "futureFeature": true}
        }"""
        val definition = Definition.fromJson(json)
        assertEquals("forward-compat", definition.id)
    }
}
