package com.example.scalsandroid.examples.layouts

import com.example.scalsandroid.examples.Example
import com.example.scalsandroid.examples.ExampleCategory
import com.example.scalsandroid.examples.PresentationStyle

/**
 * Layout examples demonstrating container layouts for organizing components.
 * Total: 10 examples
 */

private val vstackHstackExample = Example(
    id = "stacks-example",
    title = "VStack & HStack",
    description = "Vertical & horizontal stacks",
    category = ExampleCategory.LAYOUTS,
    presentationStyle = PresentationStyle.BOTTOM_SHEET_MEDIUM,
    json = """
{
  "id": "stacks-example",
  "version": "1.0",
  "styles": {
    "title": { "fontSize": 16, "fontWeight": "semibold", "textColor": "#000000" },
    "box": { "width": 60, "height": 60, "backgroundColor": "#007AFF", "cornerRadius": 8 },
    "boxGreen": { "width": 60, "height": 60, "backgroundColor": "#34C759", "cornerRadius": 8 },
    "boxOrange": { "width": 60, "height": 60, "backgroundColor": "#FF9500", "cornerRadius": 8 },
    "boxLabel": { "fontSize": 12, "fontWeight": "medium", "textColor": "#FFFFFF" }
  },
  "root": {
    "backgroundColor": "#FFFFFF",
    "edgeInsets": { "top": 36, "leading": 28, "trailing": 28 },
    "children": [{
      "type": "vstack",
      "spacing": 24,
      "alignment": "leading",
      "children": [
        { "type": "label", "text": "HStack (horizontal)", "styleId": "title" },
        {
          "type": "hstack", "spacing": 12,
          "children": [
            { "type": "zstack", "children": [{ "type": "gradient", "gradientColors": [{"color": "#007AFF", "location": 0}], "styleId": "box" }, { "type": "label", "text": "1", "styleId": "boxLabel" }] },
            { "type": "zstack", "children": [{ "type": "gradient", "gradientColors": [{"color": "#34C759", "location": 0}], "styleId": "boxGreen" }, { "type": "label", "text": "2", "styleId": "boxLabel" }] },
            { "type": "zstack", "children": [{ "type": "gradient", "gradientColors": [{"color": "#FF9500", "location": 0}], "styleId": "boxOrange" }, { "type": "label", "text": "3", "styleId": "boxLabel" }] }
          ]
        },
        { "type": "label", "text": "VStack (vertical)", "styleId": "title" },
        {
          "type": "vstack", "spacing": 8,
          "children": [
            { "type": "zstack", "children": [{ "type": "gradient", "gradientColors": [{"color": "#007AFF", "location": 0}], "styleId": "box" }, { "type": "label", "text": "A", "styleId": "boxLabel" }] },
            { "type": "zstack", "children": [{ "type": "gradient", "gradientColors": [{"color": "#34C759", "location": 0}], "styleId": "boxGreen" }, { "type": "label", "text": "B", "styleId": "boxLabel" }] }
          ]
        }
      ]
    }]
  }
}
    """.trimIndent()
)

private val zstackExample = Example(
    id = "zstack-example",
    title = "ZStack",
    description = "Layered overlays",
    category = ExampleCategory.LAYOUTS,
    presentationStyle = PresentationStyle.BOTTOM_SHEET_MEDIUM,
    json = """
{
  "id": "zstack-example",
  "version": "1.0",
  "styles": {
    "title": { "fontSize": 16, "fontWeight": "semibold", "textColor": "#000000" },
    "bgGradient": { "width": 200, "height": 120, "cornerRadius": 16 },
    "overlayText": { "fontSize": 20, "fontWeight": "bold", "textColor": "#FFFFFF" },
    "badge": {
      "fontSize": 12, "fontWeight": "bold", "textColor": "#FFFFFF",
      "backgroundColor": "#FF3B30", "cornerRadius": 10,
      "padding": { "horizontal": 8, "vertical": 4 }
    }
  },
  "root": {
    "backgroundColor": "#FFFFFF",
    "edgeInsets": { "top": 36, "leading": 28, "trailing": 28 },
    "children": [{
      "type": "vstack",
      "spacing": 24,
      "alignment": "center",
      "children": [
        { "type": "label", "text": "Layered content with ZStack", "styleId": "title" },
        {
          "type": "zstack",
          "children": [
            {
              "type": "gradient",
              "gradientColors": [
                { "color": "#667eea", "location": 0.0 },
                { "color": "#764ba2", "location": 1.0 }
              ],
              "gradientStart": "topLeading", "gradientEnd": "bottomTrailing",
              "styleId": "bgGradient"
            },
            { "type": "label", "text": "Overlay Text", "styleId": "overlayText" }
          ]
        },
        {
          "type": "zstack",
          "alignment": "topTrailing",
          "children": [
            {
              "type": "gradient",
              "gradientColors": [
                { "color": "#11998e", "location": 0.0 },
                { "color": "#38ef7d", "location": 1.0 }
              ],
              "styleId": "bgGradient"
            },
            { "type": "label", "text": "NEW", "styleId": "badge" }
          ]
        }
      ]
    }]
  }
}
    """.trimIndent()
)

private val nestedExample = Example(
    id = "nested-example",
    title = "Nested Layouts",
    description = "Combined nested layouts",
    category = ExampleCategory.LAYOUTS,
    presentationStyle = PresentationStyle.BOTTOM_SHEET_LARGE,
    json = """
{
  "id": "nested-example",
  "version": "1.0",
  "styles": {
    "title": { "fontSize": 16, "fontWeight": "semibold", "textColor": "#000000" },
    "subtitle": { "fontSize": 13, "fontWeight": "regular", "textColor": "#888888" },
    "box": { "width": 60, "height": 60, "cornerRadius": 8 },
    "boxSmall": { "width": 40, "height": 40, "cornerRadius": 6 },
    "boxWide": { "width": 132, "height": 60, "cornerRadius": 8 },
    "boxTall": { "width": 60, "height": 132, "cornerRadius": 8 },
    "boxLabel": { "fontSize": 12, "fontWeight": "medium", "textColor": "#FFFFFF" },
    "boxLabelSmall": { "fontSize": 10, "fontWeight": "medium", "textColor": "#FFFFFF" },
    "overlayCard": { "width": 150, "height": 100, "cornerRadius": 12 },
    "overlayLabel": { "fontSize": 14, "fontWeight": "semibold", "textColor": "#FFFFFF" },
    "badge": { "fontSize": 10, "fontWeight": "bold", "textColor": "#FFFFFF", "backgroundColor": "#FF3B30", "cornerRadius": 8, "padding": { "horizontal": 6, "vertical": 3 } }
  },
  "root": {
    "backgroundColor": "#FFFFFF",
    "edgeInsets": { "top": 36 },
    "children": [{
      "type": "sectionLayout",
      "sectionSpacing": 32,
      "sections": [{
        "id": "nested-content",
        "layout": {
          "type": "list",
          "showsDividers": false,
          "itemSpacing": 24,
          "contentInsets": { "horizontal": 28, "bottom": 36 }
        },
        "header": {
          "type": "label", "text": "Nested Layout Examples", "styleId": "title",
          "padding": { "bottom": 8 }
        },
        "children": [
          {
            "type": "vstack",
            "spacing": 12,
            "alignment": "leading",
            "children": [
              { "type": "label", "text": "1. VStack with nested HStack", "styleId": "subtitle" },
              {
                "type": "vstack",
                "spacing": 12,
                "children": [
                  { "type": "zstack", "children": [{ "type": "gradient", "gradientColors": [{"color": "#007AFF", "location": 0}], "styleId": "box" }, { "type": "label", "text": "1", "styleId": "boxLabel" }] },
                  {
                    "type": "hstack",
                    "spacing": 12,
                    "children": [
                      { "type": "zstack", "children": [{ "type": "gradient", "gradientColors": [{"color": "#34C759", "location": 0}], "styleId": "box" }, { "type": "label", "text": "2A", "styleId": "boxLabel" }] },
                      { "type": "zstack", "children": [{ "type": "gradient", "gradientColors": [{"color": "#FF9500", "location": 0}], "styleId": "box" }, { "type": "label", "text": "2B", "styleId": "boxLabel" }] }
                    ]
                  }
                ]
              }
            ]
          },

          {
            "type": "vstack",
            "spacing": 12,
            "alignment": "leading",
            "children": [
              { "type": "label", "text": "2. HStack with nested VStacks", "styleId": "subtitle" },
              {
                "type": "hstack",
                "spacing": 12,
                "children": [
                  {
                    "type": "vstack",
                    "spacing": 8,
                    "children": [
                      { "type": "zstack", "children": [{ "type": "gradient", "gradientColors": [{"color": "#AF52DE", "location": 0}], "styleId": "box" }, { "type": "label", "text": "A1", "styleId": "boxLabel" }] },
                      { "type": "zstack", "children": [{ "type": "gradient", "gradientColors": [{"color": "#5856D6", "location": 0}], "styleId": "box" }, { "type": "label", "text": "A2", "styleId": "boxLabel" }] }
                    ]
                  },
                  {
                    "type": "vstack",
                    "spacing": 8,
                    "children": [
                      { "type": "zstack", "children": [{ "type": "gradient", "gradientColors": [{"color": "#FF2D55", "location": 0}], "styleId": "box" }, { "type": "label", "text": "B1", "styleId": "boxLabel" }] },
                      { "type": "zstack", "children": [{ "type": "gradient", "gradientColors": [{"color": "#FF6B6B", "location": 0}], "styleId": "box" }, { "type": "label", "text": "B2", "styleId": "boxLabel" }] }
                    ]
                  },
                  {
                    "type": "vstack",
                    "spacing": 8,
                    "children": [
                      { "type": "zstack", "children": [{ "type": "gradient", "gradientColors": [{"color": "#00C7BE", "location": 0}], "styleId": "box" }, { "type": "label", "text": "C1", "styleId": "boxLabel" }] },
                      { "type": "zstack", "children": [{ "type": "gradient", "gradientColors": [{"color": "#30B0C7", "location": 0}], "styleId": "box" }, { "type": "label", "text": "C2", "styleId": "boxLabel" }] }
                    ]
                  }
                ]
              }
            ]
          },

          {
            "type": "vstack",
            "spacing": 12,
            "alignment": "leading",
            "children": [
              { "type": "label", "text": "3. ZStack with nested HStack & VStack", "styleId": "subtitle" },
              {
                "type": "zstack",
                "alignment": "topTrailing",
                "children": [
                  {
                    "type": "gradient",
                    "gradientColors": [
                      { "color": "#667eea", "location": 0.0 },
                      { "color": "#764ba2", "location": 1.0 }
                    ],
                    "gradientStart": "topLeading", "gradientEnd": "bottomTrailing",
                    "styleId": "overlayCard"
                  },
                  {
                    "type": "vstack",
                    "spacing": 4,
                    "padding": { "all": 12 },
                    "children": [
                      { "type": "label", "text": "Card Title", "styleId": "overlayLabel" },
                      {
                        "type": "hstack",
                        "spacing": 8,
                        "children": [
                          { "type": "zstack", "children": [{ "type": "gradient", "gradientColors": [{"color": "#FFFFFF33", "location": 0}], "styleId": "boxSmall" }, { "type": "label", "text": "1", "styleId": "boxLabelSmall" }] },
                          { "type": "zstack", "children": [{ "type": "gradient", "gradientColors": [{"color": "#FFFFFF33", "location": 0}], "styleId": "boxSmall" }, { "type": "label", "text": "2", "styleId": "boxLabelSmall" }] },
                          { "type": "zstack", "children": [{ "type": "gradient", "gradientColors": [{"color": "#FFFFFF33", "location": 0}], "styleId": "boxSmall" }, { "type": "label", "text": "3", "styleId": "boxLabelSmall" }] }
                        ]
                      }
                    ]
                  },
                  { "type": "label", "text": "NEW", "styleId": "badge" }
                ]
              }
            ]
          },

          {
            "type": "vstack",
            "spacing": 12,
            "alignment": "leading",
            "children": [
              { "type": "label", "text": "4. Complex grid using nested stacks", "styleId": "subtitle" },
              {
                "type": "vstack",
                "spacing": 8,
                "children": [
                  {
                    "type": "hstack",
                    "spacing": 8,
                    "children": [
                      { "type": "zstack", "children": [{ "type": "gradient", "gradientColors": [{"color": "#FF9500", "location": 0}, {"color": "#FF5E3A", "location": 1}], "gradientStart": "top", "gradientEnd": "bottom", "styleId": "boxWide" }, { "type": "label", "text": "Wide", "styleId": "boxLabel" }] },
                      { "type": "zstack", "children": [{ "type": "gradient", "gradientColors": [{"color": "#4CD964", "location": 0}], "styleId": "box" }, { "type": "label", "text": "Sq", "styleId": "boxLabel" }] }
                    ]
                  },
                  {
                    "type": "hstack",
                    "spacing": 8,
                    "children": [
                      { "type": "zstack", "children": [{ "type": "gradient", "gradientColors": [{"color": "#007AFF", "location": 0}], "styleId": "box" }, { "type": "label", "text": "A", "styleId": "boxLabel" }] },
                      { "type": "zstack", "children": [{ "type": "gradient", "gradientColors": [{"color": "#5856D6", "location": 0}], "styleId": "box" }, { "type": "label", "text": "B", "styleId": "boxLabel" }] },
                      { "type": "zstack", "children": [{ "type": "gradient", "gradientColors": [{"color": "#FF2D55", "location": 0}], "styleId": "box" }, { "type": "label", "text": "C", "styleId": "boxLabel" }] }
                    ]
                  }
                ]
              }
            ]
          }
        ]
      }]
    }]
  }
}
    """.trimIndent()
)

private val alignmentExample = Example(
    id = "alignment-example",
    title = "Alignment",
    description = "All alignment options",
    category = ExampleCategory.LAYOUTS,
    presentationStyle = PresentationStyle.BOTTOM_SHEET_LARGE,
    json = """
{
  "id": "alignment-example",
  "version": "1.0",
  "styles": {
    "sectionTitle": { "fontSize": 14, "fontWeight": "semibold", "textColor": "#000000", "padding": { "bottom": 8 } },
    "alignmentLabel": { "fontSize": 11, "fontWeight": "medium", "textColor": "#666666" },
    "box": { "width": 60, "height": 60, "backgroundColor": "#007AFF", "cornerRadius": 8 },
    "boxSmall": { "width": 40, "height": 40, "backgroundColor": "#34C759", "cornerRadius": 6 },
    "boxWide": { "width": 120, "height": 40, "backgroundColor": "#FF9500", "cornerRadius": 8 },
    "boxTall": { "width": 40, "height": 80, "backgroundColor": "#FF2D55", "cornerRadius": 8 },
    "boxBig": { "width": 100, "height": 100, "backgroundColor": "#007AFF", "cornerRadius": 12 },
    "containerBg": { "backgroundColor": "#F2F2F7", "cornerRadius": 12, "padding": { "all": 16 }, "minHeight": 120 }
  },
  "root": {
    "backgroundColor": "#FFFFFF",
    "edgeInsets": { "top": 36 },
    "children": [{
      "type": "sectionLayout",
      "sectionSpacing": 24,
      "sections": [{
        "id": "alignment-examples",
        "layout": {
          "type": "list",
          "showsDividers": false,
          "itemSpacing": 24,
          "contentInsets": { "horizontal": 28, "bottom": 36 }
        },
        "header": {
          "type": "label",
          "text": "Container Alignment Examples",
          "styleId": "sectionTitle"
        },
        "children": [
          {
            "type": "vstack",
            "spacing": 8,
            "alignment": "leading",
            "children": [
              { "type": "label", "text": "VStack - Leading Alignment", "styleId": "alignmentLabel" },
              {
                "type": "vstack",
                "spacing": 8,
                "alignment": "leading",
                "styleId": "containerBg",
                "children": [
                  { "type": "gradient", "gradientColors": [{"color": "#007AFF", "location": 0}], "styleId": "boxWide" },
                  { "type": "gradient", "gradientColors": [{"color": "#34C759", "location": 0}], "styleId": "box" },
                  { "type": "gradient", "gradientColors": [{"color": "#FF9500", "location": 0}], "styleId": "boxSmall" }
                ]
              }
            ]
          },
          {
            "type": "vstack",
            "spacing": 8,
            "alignment": "leading",
            "children": [
              { "type": "label", "text": "VStack - Center Alignment", "styleId": "alignmentLabel" },
              {
                "type": "vstack",
                "spacing": 8,
                "alignment": "center",
                "styleId": "containerBg",
                "children": [
                  { "type": "gradient", "gradientColors": [{"color": "#007AFF", "location": 0}], "styleId": "boxWide" },
                  { "type": "gradient", "gradientColors": [{"color": "#34C759", "location": 0}], "styleId": "box" },
                  { "type": "gradient", "gradientColors": [{"color": "#FF9500", "location": 0}], "styleId": "boxSmall" }
                ]
              }
            ]
          },
          {
            "type": "vstack",
            "spacing": 8,
            "alignment": "leading",
            "children": [
              { "type": "label", "text": "VStack - Trailing Alignment", "styleId": "alignmentLabel" },
              {
                "type": "vstack",
                "spacing": 8,
                "alignment": "trailing",
                "styleId": "containerBg",
                "children": [
                  { "type": "gradient", "gradientColors": [{"color": "#007AFF", "location": 0}], "styleId": "boxWide" },
                  { "type": "gradient", "gradientColors": [{"color": "#34C759", "location": 0}], "styleId": "box" },
                  { "type": "gradient", "gradientColors": [{"color": "#FF9500", "location": 0}], "styleId": "boxSmall" }
                ]
              }
            ]
          },
          {
            "type": "vstack",
            "spacing": 8,
            "alignment": "leading",
            "children": [
              { "type": "label", "text": "HStack - Top Alignment", "styleId": "alignmentLabel" },
              {
                "type": "hstack",
                "spacing": 8,
                "alignment": "top",
                "styleId": "containerBg",
                "children": [
                  { "type": "gradient", "gradientColors": [{"color": "#007AFF", "location": 0}], "styleId": "boxTall" },
                  { "type": "gradient", "gradientColors": [{"color": "#34C759", "location": 0}], "styleId": "box" },
                  { "type": "gradient", "gradientColors": [{"color": "#FF9500", "location": 0}], "styleId": "boxSmall" }
                ]
              }
            ]
          },
          {
            "type": "vstack",
            "spacing": 8,
            "alignment": "leading",
            "children": [
              { "type": "label", "text": "HStack - Center Alignment", "styleId": "alignmentLabel" },
              {
                "type": "hstack",
                "spacing": 8,
                "alignment": "center",
                "styleId": "containerBg",
                "children": [
                  { "type": "gradient", "gradientColors": [{"color": "#007AFF", "location": 0}], "styleId": "boxTall" },
                  { "type": "gradient", "gradientColors": [{"color": "#34C759", "location": 0}], "styleId": "box" },
                  { "type": "gradient", "gradientColors": [{"color": "#FF9500", "location": 0}], "styleId": "boxSmall" }
                ]
              }
            ]
          },
          {
            "type": "vstack",
            "spacing": 8,
            "alignment": "leading",
            "children": [
              { "type": "label", "text": "HStack - Bottom Alignment", "styleId": "alignmentLabel" },
              {
                "type": "hstack",
                "spacing": 8,
                "alignment": "bottom",
                "styleId": "containerBg",
                "children": [
                  { "type": "gradient", "gradientColors": [{"color": "#007AFF", "location": 0}], "styleId": "boxTall" },
                  { "type": "gradient", "gradientColors": [{"color": "#34C759", "location": 0}], "styleId": "box" },
                  { "type": "gradient", "gradientColors": [{"color": "#FF9500", "location": 0}], "styleId": "boxSmall" }
                ]
              }
            ]
          },
          {
            "type": "vstack",
            "spacing": 8,
            "alignment": "leading",
            "children": [
              { "type": "label", "text": "ZStack - Top Leading", "styleId": "alignmentLabel" },
              {
                "type": "zstack",
                "alignment": "topLeading",
                "styleId": "containerBg",
                "children": [
                  { "type": "gradient", "gradientColors": [{"color": "#007AFF", "location": 0}], "styleId": "boxBig" },
                  { "type": "gradient", "gradientColors": [{"color": "#34C759", "location": 0}], "styleId": "boxSmall" }
                ]
              }
            ]
          },
          {
            "type": "vstack",
            "spacing": 8,
            "alignment": "leading",
            "children": [
              { "type": "label", "text": "ZStack - Top", "styleId": "alignmentLabel" },
              {
                "type": "zstack",
                "alignment": "top",
                "styleId": "containerBg",
                "children": [
                  { "type": "gradient", "gradientColors": [{"color": "#007AFF", "location": 0}], "styleId": "boxBig" },
                  { "type": "gradient", "gradientColors": [{"color": "#34C759", "location": 0}], "styleId": "boxSmall" }
                ]
              }
            ]
          },
          {
            "type": "vstack",
            "spacing": 8,
            "alignment": "leading",
            "children": [
              { "type": "label", "text": "ZStack - Top Trailing", "styleId": "alignmentLabel" },
              {
                "type": "zstack",
                "alignment": "topTrailing",
                "styleId": "containerBg",
                "children": [
                  { "type": "gradient", "gradientColors": [{"color": "#007AFF", "location": 0}], "styleId": "boxBig" },
                  { "type": "gradient", "gradientColors": [{"color": "#34C759", "location": 0}], "styleId": "boxSmall" }
                ]
              }
            ]
          },
          {
            "type": "vstack",
            "spacing": 8,
            "alignment": "leading",
            "children": [
              { "type": "label", "text": "ZStack - Leading", "styleId": "alignmentLabel" },
              {
                "type": "zstack",
                "alignment": "leading",
                "styleId": "containerBg",
                "children": [
                  { "type": "gradient", "gradientColors": [{"color": "#007AFF", "location": 0}], "styleId": "boxBig" },
                  { "type": "gradient", "gradientColors": [{"color": "#34C759", "location": 0}], "styleId": "boxSmall" }
                ]
              }
            ]
          },
          {
            "type": "vstack",
            "spacing": 8,
            "alignment": "leading",
            "children": [
              { "type": "label", "text": "ZStack - Center", "styleId": "alignmentLabel" },
              {
                "type": "zstack",
                "alignment": "center",
                "styleId": "containerBg",
                "children": [
                  { "type": "gradient", "gradientColors": [{"color": "#007AFF", "location": 0}], "styleId": "boxBig" },
                  { "type": "gradient", "gradientColors": [{"color": "#34C759", "location": 0}], "styleId": "boxSmall" }
                ]
              }
            ]
          },
          {
            "type": "vstack",
            "spacing": 8,
            "alignment": "leading",
            "children": [
              { "type": "label", "text": "ZStack - Trailing", "styleId": "alignmentLabel" },
              {
                "type": "zstack",
                "alignment": "trailing",
                "styleId": "containerBg",
                "children": [
                  { "type": "gradient", "gradientColors": [{"color": "#007AFF", "location": 0}], "styleId": "boxBig" },
                  { "type": "gradient", "gradientColors": [{"color": "#34C759", "location": 0}], "styleId": "boxSmall" }
                ]
              }
            ]
          },
          {
            "type": "vstack",
            "spacing": 8,
            "alignment": "leading",
            "children": [
              { "type": "label", "text": "ZStack - Bottom Leading", "styleId": "alignmentLabel" },
              {
                "type": "zstack",
                "alignment": "bottomLeading",
                "styleId": "containerBg",
                "children": [
                  { "type": "gradient", "gradientColors": [{"color": "#007AFF", "location": 0}], "styleId": "boxBig" },
                  { "type": "gradient", "gradientColors": [{"color": "#34C759", "location": 0}], "styleId": "boxSmall" }
                ]
              }
            ]
          },
          {
            "type": "vstack",
            "spacing": 8,
            "alignment": "leading",
            "children": [
              { "type": "label", "text": "ZStack - Bottom", "styleId": "alignmentLabel" },
              {
                "type": "zstack",
                "alignment": "bottom",
                "styleId": "containerBg",
                "children": [
                  { "type": "gradient", "gradientColors": [{"color": "#007AFF", "location": 0}], "styleId": "boxBig" },
                  { "type": "gradient", "gradientColors": [{"color": "#34C759", "location": 0}], "styleId": "boxSmall" }
                ]
              }
            ]
          },
          {
            "type": "vstack",
            "spacing": 8,
            "alignment": "leading",
            "children": [
              { "type": "label", "text": "ZStack - Bottom Trailing", "styleId": "alignmentLabel" },
              {
                "type": "zstack",
                "alignment": "bottomTrailing",
                "styleId": "containerBg",
                "children": [
                  { "type": "gradient", "gradientColors": [{"color": "#007AFF", "location": 0}], "styleId": "boxBig" },
                  { "type": "gradient", "gradientColors": [{"color": "#34C759", "location": 0}], "styleId": "boxSmall" }
                ]
              }
            ]
          }
        ]
      }]
    }]
  }
}
    """.trimIndent()
)

private val spacerExample = Example(
    id = "spacer-example",
    title = "Spacer",
    description = "Flexible spacing",
    category = ExampleCategory.LAYOUTS,
    presentationStyle = PresentationStyle.BOTTOM_SHEET_LARGE,
    json = """
{
  "id": "spacer-example",
  "version": "1.0",
  "styles": {
    "title": {
      "fontSize": 24,
      "fontWeight": "bold",
      "textColor": "#000000"
    },
    "caption": {
      "fontSize": 14,
      "fontWeight": "regular",
      "textColor": "#666666"
    },
    "box": {
      "backgroundColor": "#007AFF",
      "cornerRadius": 8,
      "padding": { "all": 12 }
    },
    "boxLabel": {
      "fontSize": 14,
      "fontWeight": "semibold",
      "padding": { "all": 12 },
      "textColor": "#FFFFFF",
      "textAlignment": "center"
    },
    "alignmentContainer": {
      "backgroundColor": "#F2F2F7",
      "cornerRadius": 12,
      "padding": { "all": 16 },
      "minHeight": 120
    },
    "verticalAlignmentContainer": {
      "backgroundColor": "#F2F2F7",
      "cornerRadius": 12,
      "padding": { "all": 16 },
      "minHeight": 200
    },
    "alignmentBox": {
      "backgroundColor": "#34C759",
      "cornerRadius": 8,
      "padding": { "vertical": 8, "horizontal": 12 }
    },
    "alignmentLabel": {
      "fontSize": 12,
      "fontWeight": "semibold",
      "textColor": "#FFFFFF",
      "textAlignment": "center"
    },
    "sectionHeader": {
      "fontSize": 18,
      "fontWeight": "bold",
      "textColor": "#000000",
      "padding": { "top": 16, "bottom": 8 }
    }
  },
  "root": {
    "backgroundColor": "#FFFFFF",
    "edgeInsets": { "top": 36 },
    "children": [{
      "type": "sectionLayout",
      "sectionSpacing": 0,
      "sections": [{
        "layout": {
          "type": "list",
          "showsDividers": false,
          "itemSpacing": 16,
          "contentInsets": { "horizontal": 24, "top": 20, "bottom": 40 }
        },
        "header": {
          "type": "label",
          "text": "Spacer Examples",
          "styleId": "title"
        },
        "children": [
        {
          "type": "label",
          "text": "Default Spacer (flexible)",
          "styleId": "caption"
        },
        {
          "type": "hstack",
          "spacing": 8,
          "children": [
            {
              "type": "vstack",
              "styleId": "box",
              "children": [
                { "type": "label", "text": "Left", "styleId": "boxLabel" }
              ]
            },
            { "type": "spacer" },
            {
              "type": "vstack",
              "styleId": "box",
              "children": [
                { "type": "label", "text": "Right", "styleId": "boxLabel" }
              ]
            }
          ]
        },
        {
          "type": "label",
          "text": "With minLength: 100",
          "styleId": "caption"
        },
        {
          "type": "hstack",
          "spacing": 8,
          "children": [
            {
              "type": "vstack",
              "styleId": "box",
              "children": [
                { "type": "label", "text": "Left", "styleId": "boxLabel" }
              ]
            },
            { "type": "spacer", "minLength": 100 },
            {
              "type": "vstack",
              "styleId": "box",
              "children": [
                { "type": "label", "text": "Right", "styleId": "boxLabel" }
              ]
            }
          ]
        },
        {
          "type": "label",
          "text": "Fixed height: 50",
          "styleId": "caption"
        },
        { "type": "spacer", "height": 50 },
        {
          "type": "vstack",
          "styleId": "box",
          "children": [
            { "type": "label", "text": "After fixed spacer", "styleId": "boxLabel" }
          ]
        },
        {
          "type": "label",
          "text": "Fixed width: 100",
          "styleId": "caption"
        },
        {
          "type": "hstack",
          "spacing": 8,
          "children": [
            {
              "type": "vstack",
              "styleId": "box",
              "children": [
                { "type": "label", "text": "Left", "styleId": "boxLabel" }
              ]
            },
            { "type": "spacer", "width": 100 },
            {
              "type": "vstack",
              "styleId": "box",
              "children": [
                { "type": "label", "text": "Right", "styleId": "boxLabel" }
              ]
            }
          ]
        },
        {
          "type": "label",
          "text": "Fixed vertical spacing: 60",
          "styleId": "caption"
        },
        {
          "type": "vstack",
          "spacing": 0,
          "children": [
            {
              "type": "vstack",
              "styleId": "box",
              "children": [
                { "type": "label", "text": "Top", "styleId": "boxLabel" }
              ]
            },
            { "type": "spacer", "height": 60 },
            {
              "type": "vstack",
              "styleId": "box",
              "children": [
                { "type": "label", "text": "Bottom", "styleId": "boxLabel" }
              ]
            }
          ]
        },
        {
          "type": "label",
          "text": "Horizontal Alignment with Spacers",
          "styleId": "sectionHeader"
        },
        {
          "type": "label",
          "text": "Left Alignment (trailing spacer)",
          "styleId": "caption"
        },
        {
          "type": "hstack",
          "spacing": 0,
          "styleId": "alignmentContainer",
          "children": [
            {
              "type": "vstack",
              "styleId": "alignmentBox",
              "children": [
                { "type": "label", "text": "Left", "styleId": "alignmentLabel" }
              ]
            },
            { "type": "spacer" }
          ]
        },
        {
          "type": "label",
          "text": "Center Alignment (spacers on both sides)",
          "styleId": "caption"
        },
        {
          "type": "hstack",
          "spacing": 0,
          "styleId": "alignmentContainer",
          "children": [
            { "type": "spacer" },
            {
              "type": "vstack",
              "styleId": "alignmentBox",
              "children": [
                { "type": "label", "text": "Center", "styleId": "alignmentLabel" }
              ]
            },
            { "type": "spacer" }
          ]
        },
        {
          "type": "label",
          "text": "Right Alignment (leading spacer)",
          "styleId": "caption"
        },
        {
          "type": "hstack",
          "spacing": 0,
          "styleId": "alignmentContainer",
          "children": [
            { "type": "spacer" },
            {
              "type": "vstack",
              "styleId": "alignmentBox",
              "children": [
                { "type": "label", "text": "Right", "styleId": "alignmentLabel" }
              ]
            }
          ]
        },
        {
          "type": "label",
          "text": "Vertical Alignment with Spacers",
          "styleId": "sectionHeader"
        },
        {
          "type": "label",
          "text": "Top Alignment (bottom spacer)",
          "styleId": "caption"
        },
        {
          "type": "vstack",
          "spacing": 0,
          "styleId": "verticalAlignmentContainer",
          "children": [
            {
              "type": "vstack",
              "styleId": "alignmentBox",
              "children": [
                { "type": "label", "text": "Top", "styleId": "alignmentLabel" }
              ]
            },
            { "type": "spacer" }
          ]
        },
        {
          "type": "label",
          "text": "Center Alignment (spacers on top and bottom)",
          "styleId": "caption"
        },
        {
          "type": "vstack",
          "spacing": 0,
          "styleId": "verticalAlignmentContainer",
          "children": [
            { "type": "spacer" },
            {
              "type": "vstack",
              "styleId": "alignmentBox",
              "children": [
                { "type": "label", "text": "Center", "styleId": "alignmentLabel" }
              ]
            },
            { "type": "spacer" }
          ]
        },
        {
          "type": "label",
          "text": "Bottom Alignment (top spacer)",
          "styleId": "caption"
        },
        {
          "type": "vstack",
          "spacing": 0,
          "styleId": "verticalAlignmentContainer",
          "children": [
            { "type": "spacer" },
            {
              "type": "vstack",
              "styleId": "alignmentBox",
              "children": [
                { "type": "label", "text": "Bottom", "styleId": "alignmentLabel" }
              ]
            }
          ]
        },
        {
          "type": "label",
          "text": "Complex Layout (multiple spacers)",
          "styleId": "sectionHeader"
        },
        {
          "type": "label",
          "text": "Distributed spacing",
          "styleId": "caption"
        },
        {
          "type": "hstack",
          "spacing": 0,
          "styleId": "alignmentContainer",
          "children": [
            {
              "type": "vstack",
              "styleId": "alignmentBox",
              "children": [
                { "type": "label", "text": "1", "styleId": "alignmentLabel" }
              ]
            },
            { "type": "spacer" },
            {
              "type": "vstack",
              "styleId": "alignmentBox",
              "children": [
                { "type": "label", "text": "2", "styleId": "alignmentLabel" }
              ]
            },
            { "type": "spacer" },
            {
              "type": "vstack",
              "styleId": "alignmentBox",
              "children": [
                { "type": "label", "text": "3", "styleId": "alignmentLabel" }
              ]
            }
          ]
        }
      ]}]
    }]
  }
}
    """.trimIndent()
)

private val sectionLayoutExample = Example(
    id = "section-layout-demo",
    title = "Section Layout",
    description = "Combined section layouts",
    category = ExampleCategory.LAYOUTS,
    presentationStyle = PresentationStyle.BOTTOM_SHEET_LARGE,
    json = """
{
  "id": "section-layout-demo",
  "version": "1.0",

  "actions": {
    "dismissView": {
      "type": "dismiss"
    }
  },

  "styles": {
    "screenTitle": {
      "fontSize": 34,
      "fontWeight": "bold",
      "textColor": "#000000"
    },
    "sectionHeader": {
      "fontSize": 22,
      "fontWeight": "bold",
      "textColor": "#1C1C1E"
    },
    "closeButton": {
      "fontSize": 17,
      "fontWeight": "medium",
      "textColor": "#007AFF"
    },
    "horizontalCard": {
      "width": 150,
      "height": 100,
      "backgroundColor": "#007AFF",
      "cornerRadius": 16,
      "padding": { "all": 16 }
    },
    "horizontalCardTitle": {
      "fontSize": 18,
      "fontWeight": "bold",
      "textColor": "#FFFFFF"
    },
    "horizontalCardSubtitle": {
      "fontSize": 13,
      "fontWeight": "medium",
      "textColor": "rgba(255, 255, 255, 0.8)"
    },
    "gridCard": {
      "height": 120,
      "backgroundColor": "#34C759",
      "cornerRadius": 16,
      "padding": { "all": 16 }
    },
    "gridCardTitle": {
      "fontSize": 16,
      "fontWeight": "bold",
      "textColor": "#FFFFFF"
    },
    "gridCardIcon": {
      "width": 32,
      "height": 32,
      "tintColor": "#FFFFFF"
    },
    "listItemContainer": {
      "backgroundColor": "#F2F2F7",
      "cornerRadius": 12,
      "padding": { "all": 16 }
    },
    "listItemTitle": {
      "fontSize": 17,
      "fontWeight": "semibold",
      "textColor": "#000000",
      "textAlignment": "leading"
    },
    "listItemSubtitle": {
      "fontSize": 14,
      "fontWeight": "regular",
      "textColor": "#666666",
      "textAlignment": "leading"
    },
    "listItemIcon": {
      "width": 40,
      "height": 40,
      "tintColor": "#007AFF"
    },
    "listItemTextContainer": {
      "minWidth": 240
    },
    "disclosureChevron": {
      "height": 18,
      "tintColor": "#C7C7CC"
    }
  },

  "root": {
    "backgroundColor": "#FFFFFF",
    "colorScheme": "system",
    "children": [
      {
        "type": "hstack",
        "padding": { "horizontal": 16, "top": 16 },
        "children": [
          { "type": "spacer" },
          {
            "type": "button",
            "text": "Close",
            "styleId": "closeButton",
            "actions": { "onTap": "dismissView" }
          }
        ]
      },
      {
        "type": "hstack",
        "padding": { "horizontal": 16, "bottom": 8 },
        "children": [
          { "type": "label", "text": "Section Layouts", "styleId": "screenTitle" }
        ]
      },
      {
        "type": "sectionLayout",
        "id": "main-sections",
        "sectionSpacing": 24,
        "sections": [
          {
            "id": "horizontal-section",
            "layout": {
              "type": "horizontal",
              "itemSpacing": 12,
              "contentInsets": { "leading": 16, "trailing": 16 },
              "showsIndicators": false
            },
            "header": {
              "type": "vstack",
              "alignment": "leading",
              "padding": { "horizontal": 16, "top": 8, "bottom": 8 },
              "children": [
                { "type": "label", "text": "Horizontal Scroll", "styleId": "sectionHeader" }
              ]
            },
            "children": [
              {
                "type": "vstack",
                "spacing": 8,
                "alignment": "leading",
                "styleId": "horizontalCard",
                "children": [
                  { "type": "image", "image": { "sfsymbol": "star.fill" }, "styleId": "horizontalCardTitle" },
                  { "type": "label", "text": "Featured", "styleId": "horizontalCardTitle" },
                  { "type": "label", "text": "Top picks", "styleId": "horizontalCardSubtitle" }
                ]
              },
              {
                "type": "vstack",
                "spacing": 8,
                "alignment": "leading",
                "styleId": "horizontalCard",
                "children": [
                  { "type": "image", "image": { "sfsymbol": "flame.fill" }, "styleId": "horizontalCardTitle" },
                  { "type": "label", "text": "Trending", "styleId": "horizontalCardTitle" },
                  { "type": "label", "text": "Popular now", "styleId": "horizontalCardSubtitle" }
                ]
              },
              {
                "type": "vstack",
                "spacing": 8,
                "alignment": "leading",
                "styleId": "horizontalCard",
                "children": [
                  { "type": "image", "image": { "sfsymbol": "sparkles" }, "styleId": "horizontalCardTitle" },
                  { "type": "label", "text": "New", "styleId": "horizontalCardTitle" },
                  { "type": "label", "text": "Just added", "styleId": "horizontalCardSubtitle" }
                ]
              },
              {
                "type": "vstack",
                "spacing": 8,
                "alignment": "leading",
                "styleId": "horizontalCard",
                "children": [
                  { "type": "image", "image": { "sfsymbol": "heart.fill" }, "styleId": "horizontalCardTitle" },
                  { "type": "label", "text": "Favorites", "styleId": "horizontalCardTitle" },
                  { "type": "label", "text": "Your likes", "styleId": "horizontalCardSubtitle" }
                ]
              },
              {
                "type": "vstack",
                "spacing": 8,
                "alignment": "leading",
                "styleId": "horizontalCard",
                "children": [
                  { "type": "image", "image": { "sfsymbol": "clock.fill" }, "styleId": "horizontalCardTitle" },
                  { "type": "label", "text": "Recent", "styleId": "horizontalCardTitle" },
                  { "type": "label", "text": "Last viewed", "styleId": "horizontalCardSubtitle" }
                ]
              }
            ]
          },
          {
            "id": "grid-section",
            "layout": {
              "type": "grid",
              "columns": 2,
              "itemSpacing": 12,
              "lineSpacing": 12,
              "contentInsets": { "horizontal": 16 }
            },
            "header": {
              "type": "vstack",
              "alignment": "leading",
              "padding": { "horizontal": 16, "bottom": 8 },
              "children": [
                { "type": "label", "text": "Grid Layout", "styleId": "sectionHeader" }
              ]
            },
            "children": [
              {
                "type": "vstack",
                "spacing": 8,
                "alignment": "center",
                "styleId": "gridCard",
                "children": [
                  { "type": "image", "image": { "sfsymbol": "photo.fill" }, "styleId": "gridCardIcon" },
                  { "type": "label", "text": "Photos", "styleId": "gridCardTitle" }
                ]
              },
              {
                "type": "vstack",
                "spacing": 8,
                "alignment": "center",
                "styleId": "gridCard",
                "children": [
                  { "type": "image", "image": { "sfsymbol": "music.note" }, "styleId": "gridCardIcon" },
                  { "type": "label", "text": "Music", "styleId": "gridCardTitle" }
                ]
              },
              {
                "type": "vstack",
                "spacing": 8,
                "alignment": "center",
                "styleId": "gridCard",
                "children": [
                  { "type": "image", "image": { "sfsymbol": "video.fill" }, "styleId": "gridCardIcon" },
                  { "type": "label", "text": "Videos", "styleId": "gridCardTitle" }
                ]
              },
              {
                "type": "vstack",
                "spacing": 8,
                "alignment": "center",
                "styleId": "gridCard",
                "children": [
                  { "type": "image", "image": { "sfsymbol": "doc.fill" }, "styleId": "gridCardIcon" },
                  { "type": "label", "text": "Documents", "styleId": "gridCardTitle" }
                ]
              }
            ]
          },
          {
            "id": "list-section",
            "layout": {
              "type": "list",
              "itemSpacing": 0,
              "showsDividers": true,
              "contentInsets": { "horizontal": 16 }
            },
            "header": {
              "type": "vstack",
              "alignment": "leading",
              "padding": { "horizontal": 16, "bottom": 8 },
              "children": [
                { "type": "label", "text": "List Layout", "styleId": "sectionHeader" }
              ]
            },
            "children": [
              {
                "type": "hstack",
                "spacing": 12,
                "alignment": "center",
                "styleId": "listItemContainer",
                "children": [
                  { "type": "image", "image": { "sfsymbol": "person.circle.fill" }, "styleId": "listItemIcon" },
                  {
                    "type": "vstack",
                    "spacing": 4,
                    "alignment": "leading",
                    "styleId": "listItemTextContainer",
                    "children": [
                      { "type": "label", "text": "Profile Settings", "styleId": "listItemTitle" },
                      { "type": "label", "text": "Manage your account", "styleId": "listItemSubtitle" }
                    ]
                  },
                  { "type": "spacer" },
                  { "type": "image", "image": { "sfsymbol": "chevron.right" }, "styleId": "disclosureChevron" }
                ]
              },
              {
                "type": "hstack",
                "spacing": 12,
                "alignment": "center",
                "styleId": "listItemContainer",
                "children": [
                  { "type": "image", "image": { "sfsymbol": "bell.circle.fill" }, "styleId": "listItemIcon" },
                  {
                    "type": "vstack",
                    "spacing": 4,
                    "alignment": "leading",
                    "styleId": "listItemTextContainer",
                    "children": [
                      { "type": "label", "text": "Notifications", "styleId": "listItemTitle" },
                      { "type": "label", "text": "Alerts and updates", "styleId": "listItemSubtitle" }
                    ]
                  },
                  { "type": "spacer" },
                  { "type": "image", "image": { "sfsymbol": "chevron.right" }, "styleId": "disclosureChevron" }
                ]
              },
              {
                "type": "hstack",
                "spacing": 12,
                "alignment": "center",
                "styleId": "listItemContainer",
                "children": [
                  { "type": "image", "image": { "sfsymbol": "lock.circle.fill" }, "styleId": "listItemIcon" },
                  {
                    "type": "vstack",
                    "spacing": 4,
                    "alignment": "leading",
                    "styleId": "listItemTextContainer",
                    "children": [
                      { "type": "label", "text": "Privacy & Security", "styleId": "listItemTitle" },
                      { "type": "label", "text": "Control your data", "styleId": "listItemSubtitle" }
                    ]
                  },
                  { "type": "spacer" },
                  { "type": "image", "image": { "sfsymbol": "chevron.right" }, "styleId": "disclosureChevron" }
                ]
              }
            ]
          }
        ]
      }
    ]
  }
}
    """.trimIndent()
)

private val sectionListExample = Example(
    id = "section-list-example",
    title = "Section List",
    description = "Vertical list with dividers",
    category = ExampleCategory.LAYOUTS,
    presentationStyle = PresentationStyle.BOTTOM_SHEET_LARGE,
    json = """
{
  "id": "section-list-example",
  "version": "1.0",
  "styles": {
    "header": { "fontSize": 22, "fontWeight": "bold", "textColor": "#000000" },
    "rowTitle": { "fontSize": 16, "textColor": "#000000" },
    "rowSubtitle": { "fontSize": 14, "textColor": "#888888" },
    "iconBlue": { "width": 24, "height": 24, "tintColor": "#007AFF" },
    "iconOrange": { "width": 24, "height": 24, "tintColor": "#FF9500" },
    "iconGreen": { "width": 24, "height": 24, "tintColor": "#34C759" },
    "iconPurple": { "width": 24, "height": 24, "tintColor": "#AF52DE" },
    "iconRed": { "width": 24, "height": 24, "tintColor": "#FF3B30" },
    "iconTeal": { "width": 24, "height": 24, "tintColor": "#5AC8FA" },
    "iconPink": { "width": 24, "height": 24, "tintColor": "#FF2D55" }
  },
  "root": {
    "backgroundColor": "#FFFFFF",
    "edgeInsets": { "top": 36 },
    "children": [{
      "type": "sectionLayout",
      "sectionSpacing": 24,
      "sections": [{
        "id": "list-section",
        "layout": {
          "type": "list",
          "itemSpacing": 0,
          "showsDividers": true,
          "contentInsets": { "horizontal": 28 }
        },
        "header": {
          "type": "label", "text": "Settings", "styleId": "header",
          "padding": { "bottom": 12 }
        },
        "children": [
          {
            "type": "hstack", "padding": { "vertical": 14 },
            "children": [
              { "type": "image", "image": { "sfsymbol": "person.fill" }, "styleId": "iconBlue" },
              {
                "type": "vstack", "spacing": 2, "alignment": "leading", "padding": { "leading": 12 },
                "children": [
                  { "type": "label", "text": "Account", "styleId": "rowTitle" },
                  { "type": "label", "text": "Manage your profile", "styleId": "rowSubtitle" }
                ]
              },
              { "type": "spacer" }
            ]
          },
          {
            "type": "hstack", "padding": { "vertical": 14 },
            "children": [
              { "type": "image", "image": { "sfsymbol": "bell.fill" }, "styleId": "iconOrange" },
              {
                "type": "vstack", "spacing": 2, "alignment": "leading", "padding": { "leading": 12 },
                "children": [
                  { "type": "label", "text": "Notifications", "styleId": "rowTitle" },
                  { "type": "label", "text": "Alerts and sounds", "styleId": "rowSubtitle" }
                ]
              },
              { "type": "spacer" }
            ]
          },
          {
            "type": "hstack", "padding": { "vertical": 14 },
            "children": [
              { "type": "image", "image": { "sfsymbol": "lock.fill" }, "styleId": "iconGreen" },
              {
                "type": "vstack", "spacing": 2, "alignment": "leading", "padding": { "leading": 12 },
                "children": [
                  { "type": "label", "text": "Privacy", "styleId": "rowTitle" },
                  { "type": "label", "text": "Data and permissions", "styleId": "rowSubtitle" }
                ]
              },
              { "type": "spacer" }
            ]
          },
          {
            "type": "hstack", "padding": { "vertical": 14 },
            "children": [
              { "type": "image", "image": { "sfsymbol": "paintbrush.fill" }, "styleId": "iconPurple" },
              {
                "type": "vstack", "spacing": 2, "alignment": "leading", "padding": { "leading": 12 },
                "children": [
                  { "type": "label", "text": "Appearance", "styleId": "rowTitle" },
                  { "type": "label", "text": "Theme and display", "styleId": "rowSubtitle" }
                ]
              },
              { "type": "spacer" }
            ]
          },
          {
            "type": "hstack", "padding": { "vertical": 14 },
            "children": [
              { "type": "image", "image": { "sfsymbol": "icloud.fill" }, "styleId": "iconTeal" },
              {
                "type": "vstack", "spacing": 2, "alignment": "leading", "padding": { "leading": 12 },
                "children": [
                  { "type": "label", "text": "Cloud Sync", "styleId": "rowTitle" },
                  { "type": "label", "text": "Backup and restore", "styleId": "rowSubtitle" }
                ]
              },
              { "type": "spacer" }
            ]
          },
          {
            "type": "hstack", "padding": { "vertical": 14 },
            "children": [
              { "type": "image", "image": { "sfsymbol": "heart.fill" }, "styleId": "iconPink" },
              {
                "type": "vstack", "spacing": 2, "alignment": "leading", "padding": { "leading": 12 },
                "children": [
                  { "type": "label", "text": "Favorites", "styleId": "rowTitle" },
                  { "type": "label", "text": "Saved items", "styleId": "rowSubtitle" }
                ]
              },
              { "type": "spacer" }
            ]
          },
          {
            "type": "hstack", "padding": { "vertical": 14 },
            "children": [
              { "type": "image", "image": { "sfsymbol": "questionmark.circle.fill" }, "styleId": "iconBlue" },
              {
                "type": "vstack", "spacing": 2, "alignment": "leading", "padding": { "leading": 12 },
                "children": [
                  { "type": "label", "text": "Help & Support", "styleId": "rowTitle" },
                  { "type": "label", "text": "FAQs and contact", "styleId": "rowSubtitle" }
                ]
              },
              { "type": "spacer" }
            ]
          },
          {
            "type": "hstack", "padding": { "vertical": 14 },
            "children": [
              { "type": "image", "image": { "sfsymbol": "arrow.right.square.fill" }, "styleId": "iconRed" },
              {
                "type": "vstack", "spacing": 2, "alignment": "leading", "padding": { "leading": 12 },
                "children": [
                  { "type": "label", "text": "Sign Out", "styleId": "rowTitle" },
                  { "type": "label", "text": "Log out of your account", "styleId": "rowSubtitle" }
                ]
              },
              { "type": "spacer" }
            ]
          }
        ]
      },
      {
        "id": "about-section",
        "layout": {
          "type": "list",
          "itemSpacing": 0,
          "showsDividers": true,
          "contentInsets": { "horizontal": 28 }
        },
        "header": {
          "type": "label", "text": "About", "styleId": "header",
          "padding": { "bottom": 12 }
        },
        "children": [
          {
            "type": "hstack", "padding": { "vertical": 14 },
            "children": [
              { "type": "image", "image": { "sfsymbol": "info.circle.fill" }, "styleId": "iconBlue" },
              {
                "type": "vstack", "spacing": 2, "alignment": "leading", "padding": { "leading": 12 },
                "children": [
                  { "type": "label", "text": "Version", "styleId": "rowTitle" },
                  { "type": "label", "text": "1.0.0 (Build 42)", "styleId": "rowSubtitle" }
                ]
              },
              { "type": "spacer" }
            ]
          },
          {
            "type": "hstack", "padding": { "vertical": 14 },
            "children": [
              { "type": "image", "image": { "sfsymbol": "doc.text.fill" }, "styleId": "iconGreen" },
              {
                "type": "vstack", "spacing": 2, "alignment": "leading", "padding": { "leading": 12 },
                "children": [
                  { "type": "label", "text": "Terms of Service", "styleId": "rowTitle" },
                  { "type": "label", "text": "Legal agreements", "styleId": "rowSubtitle" }
                ]
              },
              { "type": "spacer" }
            ]
          },
          {
            "type": "hstack", "padding": { "vertical": 14 },
            "children": [
              { "type": "image", "image": { "sfsymbol": "hand.raised.fill" }, "styleId": "iconOrange" },
              {
                "type": "vstack", "spacing": 2, "alignment": "leading", "padding": { "leading": 12 },
                "children": [
                  { "type": "label", "text": "Privacy Policy", "styleId": "rowTitle" },
                  { "type": "label", "text": "How we handle your data", "styleId": "rowSubtitle" }
                ]
              },
              { "type": "spacer" }
            ]
          },
          {
            "type": "hstack", "padding": { "vertical": 14 },
            "children": [
              { "type": "image", "image": { "sfsymbol": "star.fill" }, "styleId": "iconPurple" },
              {
                "type": "vstack", "spacing": 2, "alignment": "leading", "padding": { "leading": 12 },
                "children": [
                  { "type": "label", "text": "Rate the App", "styleId": "rowTitle" },
                  { "type": "label", "text": "Leave a review", "styleId": "rowSubtitle" }
                ]
              },
              { "type": "spacer" }
            ]
          }
        ]
      }]
    }]
  }
}
    """.trimIndent()
)

private val sectionGridExample = Example(
    id = "section-grid-example",
    title = "Section Grid",
    description = "Multi-column grid",
    category = ExampleCategory.LAYOUTS,
    presentationStyle = PresentationStyle.BOTTOM_SHEET_MEDIUM,
    json = """
{
  "id": "section-grid-example",
  "version": "1.0",
  "styles": {
    "header": { "fontSize": 22, "fontWeight": "bold", "textColor": "#000000" },
    "gridItem": { "height": 100, "backgroundColor": "#F2F2F7", "cornerRadius": 12, "padding": { "all": 16 } },
    "itemIcon": { "width": 32, "height": 32, "tintColor": "#007AFF" },
    "itemLabel": { "fontSize": 12, "fontWeight": "medium", "textColor": "#333333" }
  },
  "root": {
    "backgroundColor": "#FFFFFF",
    "edgeInsets": { "top": 36, "bottom": 22 },
    "children": [{
      "type": "sectionLayout",
      "sections": [{
        "id": "grid-section",
        "layout": {
          "type": "grid",
          "columns": 3,
          "itemSpacing": 12,
          "lineSpacing": 12,
          "contentInsets": { "horizontal": 28 }
        },
        "header": {
          "type": "label", "text": "Categories", "styleId": "header",
          "padding": { "bottom": 12 }
        },
        "children": [
          { "type": "vstack", "spacing": 8, "styleId": "gridItem", "children": [{ "type": "image", "image": { "sfsymbol": "photo.fill" }, "styleId": "itemIcon" }, { "type": "label", "text": "Photos", "styleId": "itemLabel" }] },
          { "type": "vstack", "spacing": 8, "styleId": "gridItem", "children": [{ "type": "image", "image": { "sfsymbol": "video.fill" }, "styleId": "itemIcon" }, { "type": "label", "text": "Videos", "styleId": "itemLabel" }] },
          { "type": "vstack", "spacing": 8, "styleId": "gridItem", "children": [{ "type": "image", "image": { "sfsymbol": "doc.fill" }, "styleId": "itemIcon" }, { "type": "label", "text": "Files", "styleId": "itemLabel" }] },
          { "type": "vstack", "spacing": 8, "styleId": "gridItem", "children": [{ "type": "image", "image": { "sfsymbol": "music.note" }, "styleId": "itemIcon" }, { "type": "label", "text": "Music", "styleId": "itemLabel" }] },
          { "type": "vstack", "spacing": 8, "styleId": "gridItem", "children": [{ "type": "image", "image": { "sfsymbol": "book.fill" }, "styleId": "itemIcon" }, { "type": "label", "text": "Books", "styleId": "itemLabel" }] },
          { "type": "vstack", "spacing": 8, "styleId": "gridItem", "children": [{ "type": "image", "image": { "sfsymbol": "gamecontroller.fill" }, "styleId": "itemIcon" }, { "type": "label", "text": "Games", "styleId": "itemLabel" }] }
        ]
      }]
    }]
  }
}
    """.trimIndent()
)

private val sectionFlowExample = Example(
    id = "section-flow-example",
    title = "Section Flow",
    description = "Wrapping flow layout",
    category = ExampleCategory.LAYOUTS,
    presentationStyle = PresentationStyle.BOTTOM_SHEET_MEDIUM,
    json = """
{
  "id": "section-flow-example",
  "version": "1.0",
  "state": { "selected": [] },
  "styles": {
    "header": { "fontSize": 22, "fontWeight": "bold", "textColor": "#000000" },
    "tag": {
      "fontSize": 14, "fontWeight": "medium",
      "backgroundColor": "#F2F2F7", "textColor": "#333333",
      "cornerRadius": 16, "height": 32, "padding": { "horizontal": 14 }
    },
    "tagSelected": {
      "fontSize": 14, "fontWeight": "semibold",
      "backgroundColor": "#007AFF", "textColor": "#FFFFFF",
      "cornerRadius": 16, "height": 32, "padding": { "horizontal": 14 }
    }
  },
  "root": {
    "backgroundColor": "#FFFFFF",
    "edgeInsets": { "top": 36 },
    "children": [{
      "type": "sectionLayout",
      "sections": [{
        "id": "flow-section",
        "layout": {
          "type": "flow",
          "itemSpacing": 8,
          "lineSpacing": 10,
          "contentInsets": { "horizontal": 28 }
        },
        "header": {
          "type": "label", "text": "Select Tags", "styleId": "header",
          "padding": { "bottom": 12 }
        },
        "children": [
          { "type": "button", "text": "Swift", "styles": { "normal": "tag", "selected": "tagSelected" }, "isSelectedBinding": "${'$'}{selected.contains('Swift')}", "actions": { "onTap": { "type": "toggleInArray", "path": "selected", "value": "Swift" } } },
          { "type": "button", "text": "iOS", "styles": { "normal": "tag", "selected": "tagSelected" }, "isSelectedBinding": "${'$'}{selected.contains('iOS')}", "actions": { "onTap": { "type": "toggleInArray", "path": "selected", "value": "iOS" } } },
          { "type": "button", "text": "SwiftUI", "styles": { "normal": "tag", "selected": "tagSelected" }, "isSelectedBinding": "${'$'}{selected.contains('SwiftUI')}", "actions": { "onTap": { "type": "toggleInArray", "path": "selected", "value": "SwiftUI" } } },
          { "type": "button", "text": "UIKit", "styles": { "normal": "tag", "selected": "tagSelected" }, "isSelectedBinding": "${'$'}{selected.contains('UIKit')}", "actions": { "onTap": { "type": "toggleInArray", "path": "selected", "value": "UIKit" } } },
          { "type": "button", "text": "Combine", "styles": { "normal": "tag", "selected": "tagSelected" }, "isSelectedBinding": "${'$'}{selected.contains('Combine')}", "actions": { "onTap": { "type": "toggleInArray", "path": "selected", "value": "Combine" } } },
          { "type": "button", "text": "Async/Await", "styles": { "normal": "tag", "selected": "tagSelected" }, "isSelectedBinding": "${'$'}{selected.contains('Async/Await')}", "actions": { "onTap": { "type": "toggleInArray", "path": "selected", "value": "Async/Await" } } },
          { "type": "button", "text": "Core Data", "styles": { "normal": "tag", "selected": "tagSelected" }, "isSelectedBinding": "${'$'}{selected.contains('Core Data')}", "actions": { "onTap": { "type": "toggleInArray", "path": "selected", "value": "Core Data" } } },
          { "type": "button", "text": "CloudKit", "styles": { "normal": "tag", "selected": "tagSelected" }, "isSelectedBinding": "${'$'}{selected.contains('CloudKit')}", "actions": { "onTap": { "type": "toggleInArray", "path": "selected", "value": "CloudKit" } } },
          { "type": "button", "text": "Networking", "styles": { "normal": "tag", "selected": "tagSelected" }, "isSelectedBinding": "${'$'}{selected.contains('Networking')}", "actions": { "onTap": { "type": "toggleInArray", "path": "selected", "value": "Networking" } } },
          { "type": "button", "text": "Testing", "styles": { "normal": "tag", "selected": "tagSelected" }, "isSelectedBinding": "${'$'}{selected.contains('Testing')}", "actions": { "onTap": { "type": "toggleInArray", "path": "selected", "value": "Testing" } } },
          { "type": "button", "text": "Animations", "styles": { "normal": "tag", "selected": "tagSelected" }, "isSelectedBinding": "${'$'}{selected.contains('Animations')}", "actions": { "onTap": { "type": "toggleInArray", "path": "selected", "value": "Animations" } } },
          { "type": "button", "text": "ARKit", "styles": { "normal": "tag", "selected": "tagSelected" }, "isSelectedBinding": "${'$'}{selected.contains('ARKit')}", "actions": { "onTap": { "type": "toggleInArray", "path": "selected", "value": "ARKit" } } },
          { "type": "button", "text": "Metal", "styles": { "normal": "tag", "selected": "tagSelected" }, "isSelectedBinding": "${'$'}{selected.contains('Metal')}", "actions": { "onTap": { "type": "toggleInArray", "path": "selected", "value": "Metal" } } },
          { "type": "button", "text": "MapKit", "styles": { "normal": "tag", "selected": "tagSelected" }, "isSelectedBinding": "${'$'}{selected.contains('MapKit')}", "actions": { "onTap": { "type": "toggleInArray", "path": "selected", "value": "MapKit" } } },
          { "type": "button", "text": "WidgetKit", "styles": { "normal": "tag", "selected": "tagSelected" }, "isSelectedBinding": "${'$'}{selected.contains('WidgetKit')}", "actions": { "onTap": { "type": "toggleInArray", "path": "selected", "value": "WidgetKit" } } },
          { "type": "button", "text": "App Clips", "styles": { "normal": "tag", "selected": "tagSelected" }, "isSelectedBinding": "${'$'}{selected.contains('App Clips')}", "actions": { "onTap": { "type": "toggleInArray", "path": "selected", "value": "App Clips" } } }
        ]
      }]
    }]
  }
}
    """.trimIndent()
)

private val sectionHorizontalExample = Example(
    id = "section-horizontal-example",
    title = "Section Horizontal",
    description = "Carousel & paging",
    category = ExampleCategory.LAYOUTS,
    presentationStyle = PresentationStyle.BOTTOM_SHEET_LARGE,
    json = """
{
  "id": "section-horizontal-example",
  "version": "1.0",
  "state": { "currentPage": 0 },
  "styles": {
    "header": { "fontSize": 22, "fontWeight": "bold", "textColor": "#000000", "padding": { "horizontal": 20 } },
    "card": { "width": 140, "height": 180, "backgroundColor": "#F2F2F7", "cornerRadius": 12 },
    "cardImage": { "width": 140, "height": 100, "cornerRadius": 12 },
    "cardTitle": { "fontSize": 14, "fontWeight": "semibold", "textColor": "#000000" },
    "cardSubtitle": { "fontSize": 12, "textColor": "#888888" },
    "pageCard": {
      "minHeight": 400
    },
    "cardBgGreen": {
      "backgroundColor": "#A5D6A7",
      "cornerRadius": 20,
      "padding": { "all": 40 }
    },
    "cardBgBlue": {
      "backgroundColor": "#90CAF9",
      "cornerRadius": 20,
      "padding": { "all": 40 }
    },
    "cardBgPurple": {
      "backgroundColor": "#CE93D8",
      "cornerRadius": 20,
      "padding": { "all": 40 }
    },
    "pageEmoji": { "fontSize": 64, "textAlignment": "center" },
    "pageTitle": {
      "fontSize": 28,
      "fontWeight": "bold",
      "textColor": "#1C1C1E",
      "textAlignment": "center"
    },
    "pageBody": {
      "fontSize": 16,
      "textColor": "#3C3C43",
      "textAlignment": "center",
      "padding": { "top": 8 }
    }
  },
  "root": {
    "backgroundColor": "#F2F2F7",
    "scrollable": true,
    "edgeInsets": { "top": 52 },
    "children": [{
      "type": "sectionLayout",
      "sectionSpacing": 32,
      "sections": [
        {
          "id": "horizontal-section",
          "layout": {
            "type": "horizontal",
            "itemSpacing": 12,
            "contentInsets": { "leading": 20, "trailing": 20 },
            "showsIndicators": false
          },
          "header": {
            "type": "label", "text": "Featured", "styleId": "header",
            "padding": { "bottom": 12 }
          },
          "children": [
            { "type": "vstack", "spacing": 8, "alignment": "leading", "styleId": "card", "children": [{ "type": "gradient", "gradientColors": [{"color": "#FF6B6B", "location": 0}, {"color": "#4ECDC4", "location": 1}], "styleId": "cardImage" }, { "type": "label", "text": "Card One", "styleId": "cardTitle", "padding": { "horizontal": 8 } }, { "type": "label", "text": "Description", "styleId": "cardSubtitle", "padding": { "horizontal": 8 } }] },
            { "type": "vstack", "spacing": 8, "alignment": "leading", "styleId": "card", "children": [{ "type": "gradient", "gradientColors": [{"color": "#667eea", "location": 0}, {"color": "#764ba2", "location": 1}], "styleId": "cardImage" }, { "type": "label", "text": "Card Two", "styleId": "cardTitle", "padding": { "horizontal": 8 } }, { "type": "label", "text": "Description", "styleId": "cardSubtitle", "padding": { "horizontal": 8 } }] },
            { "type": "vstack", "spacing": 8, "alignment": "leading", "styleId": "card", "children": [{ "type": "gradient", "gradientColors": [{"color": "#f093fb", "location": 0}, {"color": "#f5576c", "location": 1}], "styleId": "cardImage" }, { "type": "label", "text": "Card Three", "styleId": "cardTitle", "padding": { "horizontal": 8 } }, { "type": "label", "text": "Description", "styleId": "cardSubtitle", "padding": { "horizontal": 8 } }] },
            { "type": "vstack", "spacing": 8, "alignment": "leading", "styleId": "card", "children": [{ "type": "gradient", "gradientColors": [{"color": "#11998e", "location": 0}, {"color": "#38ef7d", "location": 1}], "styleId": "cardImage" }, { "type": "label", "text": "Card Four", "styleId": "cardTitle", "padding": { "horizontal": 8 } }, { "type": "label", "text": "Description", "styleId": "cardSubtitle", "padding": { "horizontal": 8 } }] }
          ]
        },
        {
          "id": "card-paging-section",
          "layout": {
            "type": "horizontal",
            "isPagingEnabled": true,
            "cardWidth": 0.85,
            "cardSpacing": 16,
            "currentPageBinding": "currentPage"
          },
          "header": {
            "type": "label", "text": "Card Paging", "styleId": "header",
            "padding": { "bottom": 12 }
          },
          "children": [
            {
              "type": "zstack",
              "styleId": "pageCard",
              "children": [
                {
                  "type": "gradient",
                  "gradientColors": [
                    { "color": "#A5D6A7", "location": 0.0 },
                    { "color": "#A5D6A7", "location": 1.0 }
                  ],
                  "cornerRadius": 20
                },
                {
                  "type": "vstack",
                  "padding": { "all": 40 },
                  "spacing": 16,
                  "alignment": "center",
                  "children": [
                    { "type": "label", "text": "Welcome", "styleId": "pageTitle" },
                    { "type": "label", "text": "Track your plants and keep them healthy", "styleId": "pageBody" }
                  ]
                }
              ]
            },
            {
              "type": "zstack",
              "styleId": "pageCard",
              "children": [
                {
                  "type": "gradient",
                  "gradientColors": [
                    { "color": "#90CAF9", "location": 0.0 },
                    { "color": "#90CAF9", "location": 1.0 }
                  ],
                  "cornerRadius": 20
                },
                {
                  "type": "vstack",
                  "padding": { "all": 40 },
                  "spacing": 16,
                  "alignment": "center",
                  "children": [
                    { "type": "label", "text": "Watering", "styleId": "pageTitle" },
                    { "type": "label", "text": "Get reminders when your plants need water", "styleId": "pageBody" }
                  ]
                }
              ]
            },
            {
              "type": "zstack",
              "styleId": "pageCard",
              "children": [
                {
                  "type": "gradient",
                  "gradientColors": [
                    { "color": "#CE93D8", "location": 0.0 },
                    { "color": "#CE93D8", "location": 1.0 }
                  ],
                  "cornerRadius": 20
                },
                {
                  "type": "vstack",
                  "padding": { "all": 40 },
                  "spacing": 16,
                  "alignment": "center",
                  "children": [
                    { "type": "label", "text": "Statistics", "styleId": "pageTitle" },
                    { "type": "label", "text": "See your plant care history and trends", "styleId": "pageBody" }
                  ]
                }
              ]
            }
          ]
        }
      ]
    },
    {
      "type": "pageIndicator",
      "currentPage": "currentPage",
      "pageCount": 3,
      "dotSize": 8,
      "dotSpacing": 8,
      "padding": { "vertical": 20, "horizontal": 20 }
    }]
  }
}
    """.trimIndent()
)

/**
 * All layout examples
 */
val layoutExamples: List<Example> = listOf(
    vstackHstackExample,
    zstackExample,
    nestedExample,
    alignmentExample,
    spacerExample,
    sectionLayoutExample,
    sectionListExample,
    sectionGridExample,
    sectionFlowExample,
    sectionHorizontalExample
)
