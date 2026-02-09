package com.example.scalsandroid.examples.styles

import com.example.scalsandroid.examples.Example
import com.example.scalsandroid.examples.ExampleCategory
import com.example.scalsandroid.examples.PresentationStyle

/**
 * Style examples demonstrating visual styling and theming.
 * Total: 5 examples
 */

private val basicStylesExample = Example(
    id = "basicstyles-example",
    title = "Basic Styles",
    description = "Font, color, spacing, backgrounds, and corner radius",
    category = ExampleCategory.STYLES,
    presentationStyle = PresentationStyle.BOTTOM_SHEET_MEDIUM,
    json = """
{
  "id": "basicstyles-example",
  "version": "1.0",
  "styles": {
    "title": { "fontSize": 18, "fontWeight": "bold", "textColor": "#000000" },
    "large": { "fontSize": 24, "fontWeight": "bold", "textColor": "#000000" },
    "medium": { "fontSize": 16, "fontWeight": "regular", "textColor": "#333333" },
    "small": { "fontSize": 12, "fontWeight": "light", "textColor": "#888888" },
    "colored": { "fontSize": 16, "textColor": "#007AFF" },
    "background": {
      "fontSize": 16, "textColor": "#FFFFFF",
      "backgroundColor": "#FF3B30", "cornerRadius": 8,
      "padding": { "horizontal": 16, "vertical": 8 }
    },
    "rounded": {
      "fontSize": 16, "textColor": "#000000",
      "backgroundColor": "#FEF7FF", "cornerRadius": 20,
      "padding": { "horizontal": 20, "vertical": 10 }
    }
  },
  "root": {
    "backgroundColor": "#FEF7FF",
    "edgeInsets": { "top": 36, "leading": 28, "trailing": 28 },
    "children": [{
      "type": "vstack",
      "spacing": 16,
      "alignment": "leading",
      "children": [
        { "type": "label", "text": "Basic Style Properties", "styleId": "title" },
        { "type": "label", "text": "Large Bold Text", "styleId": "large" },
        { "type": "label", "text": "Medium Regular Text", "styleId": "medium" },
        { "type": "label", "text": "Small Light Text", "styleId": "small" },
        { "type": "label", "text": "Colored Text", "styleId": "colored" },
        { "type": "label", "text": "Background + Corner Radius", "styleId": "background" },
        { "type": "label", "text": "Pill Shape", "styleId": "rounded" }
      ]
    }]
  }
}
    """.trimIndent()
)

private val styleInheritanceExample = Example(
    id = "styleinheritance-example",
    title = "Style Inheritance",
    description = "Multi-level inheritance chains, overrides, and style reuse",
    category = ExampleCategory.STYLES,
    presentationStyle = PresentationStyle.BOTTOM_SHEET_LARGE,
    json = """
{
  "id": "styleinheritance-example",
  "version": "1.0",
  "styles": {
    "sectionTitle": { "fontSize": 16, "fontWeight": "semibold", "textColor": "#000000", "padding": { "top": 20, "bottom": 8 } },
    "description": { "fontSize": 13, "textColor": "#666666", "padding": { "bottom": 8 } },
    "codeLabel": { "fontSize": 12, "fontFamily": "Menlo", "textColor": "#D32F2F", "backgroundColor": "#F8F8F8", "cornerRadius": 4, "padding": { "horizontal": 6, "vertical": 3 } },

    "baseButton": {
      "fontSize": 16, "fontWeight": "semibold",
      "cornerRadius": 10, "height": 44,
      "padding": { "horizontal": 20 }
    },
    "primaryButton": {
      "inherits": "baseButton",
      "backgroundColor": "#007AFF", "textColor": "#FFFFFF"
    },
    "secondaryButton": {
      "inherits": "baseButton",
      "backgroundColor": "#E5E5EA", "textColor": "#000000"
    },
    "dangerButton": {
      "inherits": "baseButton",
      "backgroundColor": "#FF3B30", "textColor": "#FFFFFF"
    },

    "baseCard": {
      "backgroundColor": "#FEF7FF",
      "cornerRadius": 12,
      "padding": { "horizontal": 16, "vertical": 20 }
    },
    "accentCard": {
      "inherits": "baseCard",
      "backgroundColor": "#E3F2FD"
    },
    "accentCardBold": {
      "inherits": "accentCard",
      "backgroundColor": "#007AFF",
      "padding": { "horizontal": 20, "vertical": 20 }
    },

    "baseText": {
      "fontSize": 16,
      "textColor": "#333333"
    },
    "mediumText": {
      "inherits": "baseText",
      "fontWeight": "medium"
    },
    "largeBoldText": {
      "inherits": "mediumText",
      "fontSize": 20,
      "fontWeight": "bold",
      "textColor": "#000000"
    },

    "roundedButton": {
      "fontSize": 14,
      "fontWeight": "medium",
      "cornerRadius": 20,
      "height": 40,
      "padding": { "horizontal": 16 }
    },
    "blueRoundedButton": {
      "inherits": "roundedButton",
      "backgroundColor": "#007AFF",
      "textColor": "#FFFFFF"
    },
    "largeBlueRoundedButton": {
      "inherits": "blueRoundedButton",
      "fontSize": 16,
      "height": 50,
      "cornerRadius": 25
    },

    "compactButton": {
      "inherits": "baseButton",
      "fontSize": 14,
      "height": 36,
      "padding": { "horizontal": 12 }
    },
    "compactPrimaryButton": {
      "inherits": "compactButton",
      "backgroundColor": "#007AFF",
      "textColor": "#FFFFFF"
    },
    "overriddenCompactButton": {
      "inherits": "compactPrimaryButton",
      "fontSize": 18,
      "height": 50,
      "backgroundColor": "#34C759",
      "cornerRadius": 25
    }
  },
  "root": {
    "backgroundColor": "#FEF7FF",
    "edgeInsets": { "top": 44 },
    "children": [{
      "type": "sectionLayout",
      "sectionSpacing": 16,
      "sections": [
        {
          "id": "basic",
          "layout": { "type": "list", "showsDividers": false, "itemSpacing": 12, "contentInsets": { "horizontal": 20 } },
          "header": { "type": "label", "text": "1. Basic Inheritance", "styleId": "sectionTitle" },
          "children": [
            { "type": "label", "text": "All buttons inherit common properties from baseButton", "styleId": "description" },
            { "type": "label", "text": "inherits: baseButton", "styleId": "codeLabel" },
            { "type": "button", "text": "Primary", "styleId": "primaryButton" },
            { "type": "button", "text": "Secondary", "styleId": "secondaryButton" },
            { "type": "button", "text": "Danger", "styleId": "dangerButton" }
          ]
        },
        {
          "id": "multilevel",
          "layout": { "type": "list", "showsDividers": false, "itemSpacing": 12, "alignment": "center", "contentInsets": { "horizontal": 20 } },
          "header": { "type": "label", "text": "2. Multi-Level Inheritance (Grandparent → Parent → Child)", "styleId": "sectionTitle" },
          "children": [
            { "type": "label", "text": "Child inherits from parent, parent inherits from grandparent", "styleId": "description" },
            {
              "type": "vstack",
              "spacing": 8,
              "alignment": "leading",
              "styleId": "baseCard",
              "children": [
                { "type": "label", "text": "Level 1: baseCard", "styleId": "codeLabel" },
                { "type": "label", "text": "Gray bg (#F2F2F7), 12px radius, 16px padding", "fontSize": 12, "textColor": "#666666" }
              ]
            },
            {
              "type": "vstack",
              "spacing": 8,
              "alignment": "leading",
              "styleId": "accentCard",
              "children": [
                { "type": "label", "text": "Level 2: accentCard (inherits: baseCard)", "styleId": "codeLabel" },
                { "type": "label", "text": "Light blue bg (#E3F2FD) overrides gray", "fontSize": 12, "textColor": "#1976D2" }
              ]
            },
            {
              "type": "vstack",
              "spacing": 8,
              "alignment": "leading",
              "styleId": "accentCardBold",
              "children": [
                { "type": "label", "text": "Level 3: accentCardBold (inherits: accentCard)", "styleId": "codeLabel" },
                { "type": "label", "text": "Dark blue bg (#007AFF), 20px padding (overridden)", "fontSize": 12, "textColor": "#FFFFFF" }
              ]
            }
          ]
        },
        {
          "id": "textchain",
          "layout": { "type": "list", "showsDividers": false, "itemSpacing": 12, "contentInsets": { "horizontal": 20 } },
          "header": { "type": "label", "text": "3. Text Style Chain", "styleId": "sectionTitle" },
          "children": [
            { "type": "label", "text": "Each level adds or overrides properties", "styleId": "description" },
            { "type": "label", "text": "Base: 16px, gray", "styleId": "baseText" },
            { "type": "label", "text": "Medium: 16px, gray, medium weight", "styleId": "mediumText" },
            { "type": "label", "text": "Large Bold: 20px, black, bold", "styleId": "largeBoldText" }
          ]
        },
        {
          "id": "buttonchain",
          "layout": { "type": "list", "showsDividers": false, "itemSpacing": 12, "contentInsets": { "horizontal": 20 } },
          "header": { "type": "label", "text": "4. Button Chain (3 Levels)", "styleId": "sectionTitle" },
          "children": [
            { "type": "label", "text": "roundedButton → blueRoundedButton → largeBlueRoundedButton", "styleId": "description" },
            { "type": "button", "text": "Small Rounded", "styleId": "blueRoundedButton" },
            { "type": "button", "text": "Large Rounded", "styleId": "largeBlueRoundedButton" }
          ]
        },
        {
          "id": "override",
          "layout": { "type": "list", "showsDividers": false, "itemSpacing": 12, "contentInsets": { "horizontal": 20, "bottom": 36 } },
          "header": { "type": "label", "text": "5. Child Overriding Parent", "styleId": "sectionTitle" },
          "children": [
            { "type": "label", "text": "Child can override any inherited property", "styleId": "description" },
            { "type": "label", "text": "baseButton → compactButton → compactPrimaryButton → overriddenCompactButton", "styleId": "codeLabel" },
            { "type": "button", "text": "Compact Primary (blue, small)", "styleId": "compactPrimaryButton" },
            { "type": "button", "text": "Overridden (green, large, more rounded)", "styleId": "overriddenCompactButton" },
            { "type": "label", "text": "Overrides: fontSize (18), height (50), backgroundColor (green), cornerRadius (25)", "styleId": "description" }
          ]
        }
      ]
    }]
  }
}
    """.trimIndent()
)

private val conditionalStylesExample = Example(
    id = "conditionalstyles-example",
    title = "Conditional Styles",
    description = "State-based styling with dynamic style switching",
    category = ExampleCategory.STYLES,
    presentationStyle = PresentationStyle.BOTTOM_SHEET_MEDIUM,
    json = """
{
  "id": "conditionalstyles-example",
  "version": "1.0",
  "state": { "isActive": false },
  "styles": {
    "title": { "fontSize": 18, "fontWeight": "bold", "textColor": "#000000" },
    "normalCard": {
      "backgroundColor": "#FEF7FF", "cornerRadius": 12,
      "padding": { "all": 20 }
    },
    "activeCard": {
      "backgroundColor": "#007AFF", "cornerRadius": 12,
      "padding": { "all": 20 }
    },
    "normalText": { "fontSize": 16, "textColor": "#333333" },
    "activeText": { "fontSize": 16, "fontWeight": "semibold", "textColor": "#FFFFFF" },
    "toggleButton": {
      "fontSize": 16, "fontWeight": "medium",
      "backgroundColor": "#E5E5EA", "textColor": "#000000",
      "cornerRadius": 8, "height": 40, "padding": { "horizontal": 16 }
    },
    "toggleButtonSelected": {
      "fontSize": 16, "fontWeight": "semibold",
      "backgroundColor": "#34C759", "textColor": "#FFFFFF",
      "cornerRadius": 8, "height": 40, "padding": { "horizontal": 16 }
    }
  },
  "actions": {
    "toggle": { "type": "toggleState", "path": "isActive" }
  },
  "dataSources": {
    "statusText": { "type": "binding", "template": "${'$'}{isActive ? 'Card is ACTIVE' : 'Card is inactive'}" }
  },
  "root": {
    "backgroundColor": "#FEF7FF",
    "edgeInsets": { "top": 36, "leading": 28, "trailing": 28 },
    "children": [{
      "type": "vstack",
      "spacing": 20,
      "alignment": "center",
      "children": [
        { "type": "label", "text": "Conditional Styles", "styleId": "title" },
        {
          "type": "button",
          "text": "Toggle State",
          "styles": { "normal": "toggleButton", "selected": "toggleButtonSelected" },
          "isSelectedBinding": "isActive",
          "actions": { "onTap": "toggle" }
        },
        { "type": "label", "dataSourceId": "statusText", "styleId": "normalText" }
      ]
    }]
  }
}
    """.trimIndent()
)

private val shadowsExample = Example(
    id = "shadows-example",
    title = "Shadows",
    description = "Box shadows with color, radius, offset, and inheritance",
    category = ExampleCategory.STYLES,
    presentationStyle = PresentationStyle.BOTTOM_SHEET_LARGE,
    json = """
{
  "id": "shadows-example",
  "version": "1.0",
  "styles": {
    "title": { "fontSize": 18, "fontWeight": "bold", "textColor": "#000000" },
    "card": {
      "backgroundColor": "#FEF7FF",
      "cornerRadius": 12,
      "width": { "fractional": 0.8 },
      "padding": { "horizontal": 16, "vertical": 24 }
    },
    "subtle": {
      "inherits": "card",
      "shadow": {
        "color": "rgba(0, 0, 0, 0.08)",
        "radius": 4,
        "x": 0,
        "y": 2
      }
    },
    "elevated": {
      "inherits": "card",
      "shadow": {
        "color": "rgba(0, 0, 0, 0.12)",
        "radius": 8,
        "x": 0,
        "y": 4
      }
    },
    "dramatic": {
      "inherits": "card",
      "shadow": {
        "color": "rgba(0, 0, 0, 0.2)",
        "radius": 16,
        "x": 0,
        "y": 8
      }
    },
    "offset": {
      "inherits": "card",
      "shadow": {
        "color": "rgba(0, 0, 0, 0.15)",
        "radius": 8,
        "x": 4,
        "y": 4
      }
    },
    "negative-offset": {
      "inherits": "card",
      "shadow": {
        "color": "rgba(0, 0, 0, 0.15)",
        "radius": 8,
        "x": -4,
        "y": -4
      }
    },
    "colored-shadow": {
      "inherits": "card",
      "backgroundColor": "#007AFF",
      "shadow": {
        "color": "rgba(0, 122, 255, 0.5)",
        "radius": 12,
        "x": 0,
        "y": 6
      }
    },
    "no-shadow": {
      "inherits": "elevated",
      "shadow": {}
    }
  },
  "root": {
    "backgroundColor": "#FEF7FF",
    "children": [{
      "type": "sectionLayout",
      "sections": [
        {
          "layout": {
            "type": "list",
            "itemSpacing": 20,
            "contentInsets": { "horizontal": 48, "vertical": 20 }
          },
          "header": {
            "type": "label",
            "text": "Shadow Examples",
            "styleId": "title",
            "padding": { "horizontal": 28, "top": 36, "bottom": 16 }
          },
          "children": [
            {
              "type": "vstack",
              "styleId": "subtle",
              "children": [
                { "type": "label", "text": "Subtle Shadow", "style": { "fontWeight": "semibold" } },
                { "type": "label", "text": "Light shadow for subtle depth", "style": { "fontSize": 12, "textColor": "#666666" } }
              ]
            },
            {
              "type": "vstack",
              "styleId": "elevated",
              "children": [
                { "type": "label", "text": "Elevated Shadow", "style": { "fontWeight": "semibold" } },
                { "type": "label", "text": "Medium shadow for raised elements", "style": { "fontSize": 12, "textColor": "#666666" } }
              ]
            },
            {
              "type": "vstack",
              "styleId": "dramatic",
              "children": [
                { "type": "label", "text": "Dramatic Shadow", "style": { "fontWeight": "semibold" } },
                { "type": "label", "text": "Deep shadow for prominent cards", "style": { "fontSize": 12, "textColor": "#666666" } }
              ]
            },
            {
              "type": "vstack",
              "styleId": "offset",
              "children": [
                { "type": "label", "text": "Offset Shadow", "style": { "fontWeight": "semibold" } },
                { "type": "label", "text": "Shadow with horizontal and vertical offset", "style": { "fontSize": 12, "textColor": "#666666" } }
              ]
            },
            {
              "type": "vstack",
              "styleId": "negative-offset",
              "children": [
                { "type": "label", "text": "Negative Offset Shadow", "style": { "fontWeight": "semibold" } },
                { "type": "label", "text": "Shadow offset to top-left", "style": { "fontSize": 12, "textColor": "#666666" } }
              ]
            },
            {
              "type": "vstack",
              "styleId": "colored-shadow",
              "children": [
                { "type": "label", "text": "Colored Shadow", "style": { "fontWeight": "semibold", "textColor": "#FFFFFF" } },
                { "type": "label", "text": "Blue shadow matching the card color", "style": { "fontSize": 12, "textColor": "rgba(255, 255, 255, 0.8)" } }
              ]
            },
            {
              "type": "vstack",
              "styleId": "no-shadow",
              "children": [
                { "type": "label", "text": "No Shadow", "style": { "fontWeight": "semibold" } },
                { "type": "label", "text": "Inherits from elevated but removes shadow", "style": { "fontSize": 12, "textColor": "#666666" } }
              ]
            }
          ]
        }
      ]
    }]
  }
}
    """.trimIndent()
)

private val fractionalSizingExample = Example(
    id = "fractional-sizing-example",
    title = "Fractional Sizing",
    description = "Responsive widths and heights relative to container",
    category = ExampleCategory.STYLES,
    presentationStyle = PresentationStyle.BOTTOM_SHEET_MEDIUM,
    json = """
{
  "id": "fractional-sizing-example",
  "version": "1.0",
  "styles": {
    "title": { "fontSize": 18, "fontWeight": "bold", "textColor": "#000000" },
    "subtitle": { "fontSize": 14, "textColor": "#666666" },
    "fullWidth": {
      "width": {"fractional": 1.0},
      "backgroundColor": "#007AFF",
      "cornerRadius": 8,
      "padding": {"vertical": 16}
    },
    "halfWidth": {
      "width": {"fractional": 0.5},
      "backgroundColor": "#34C759",
      "cornerRadius": 8,
      "padding": {"vertical": 16}
    },
    "thirdWidth": {
      "width": {"fractional": 0.33},
      "backgroundColor": "#FF9500",
      "cornerRadius": 8,
      "padding": {"vertical": 16}
    },
    "responsive": {
      "width": {"fractional": 0.8},
      "minWidth": {"absolute": 200},
      "maxWidth": {"absolute": 600},
      "backgroundColor": "#AF52DE",
      "cornerRadius": 8,
      "padding": {"vertical": 16}
    },
    "mixed": {
      "width": {"fractional": 0.9},
      "height": {"absolute": 100},
      "backgroundColor": "#FF2D55",
      "cornerRadius": 8
    },
    "labelWhite": {
      "textColor": "#FFFFFF",
      "fontWeight": "semibold"
    },
    "labelSmall": {
      "textColor": "#FFFFFF",
      "fontSize": 12
    }
  },
  "root": {
    "backgroundColor": "#FEF7FF",
    "edgeInsets": { "top": 36, "leading": 28, "trailing": 28, "bottom": 36 },
    "children": [{
      "type": "vstack",
      "spacing": 24,
      "alignment": "leading",
      "children": [
        { "type": "label", "text": "Fractional Sizing", "styleId": "title" },
        { "type": "label", "text": "Widths relative to container", "styleId": "subtitle" },

        {
          "type": "vstack",
          "spacing": 12,
          "children": [
            {
              "type": "vstack",
              "styleId": "fullWidth",
              "children": [
                { "type": "label", "text": "100% Width", "styleId": "labelWhite" }
              ]
            },
            {
              "type": "vstack",
              "styleId": "halfWidth",
              "children": [
                { "type": "label", "text": "50% Width", "styleId": "labelWhite" }
              ]
            },
            {
              "type": "hstack",
              "spacing": 8,
              "children": [
                {
                  "type": "vstack",
                  "styleId": "thirdWidth",
                  "children": [
                    { "type": "label", "text": "33%", "styleId": "labelWhite" }
                  ]
                },
                {
                  "type": "vstack",
                  "styleId": "thirdWidth",
                  "children": [
                    { "type": "label", "text": "33%", "styleId": "labelWhite" }
                  ]
                },
                {
                  "type": "vstack",
                  "styleId": "thirdWidth",
                  "children": [
                    { "type": "label", "text": "33%", "styleId": "labelWhite" }
                  ]
                }
              ]
            },
            {
              "type": "vstack",
              "styleId": "responsive",
              "spacing": 4,
              "children": [
                { "type": "label", "text": "Responsive: 80% with min/max", "styleId": "labelWhite" },
                { "type": "label", "text": "Min 200pt, Max 600pt", "styleId": "labelSmall" }
              ]
            },
            {
              "type": "vstack",
              "styleId": "mixed",
              "children": [
                { "type": "label", "text": "Mixed: 90% width, 100pt height", "styleId": "labelWhite" }
              ]
            }
          ]
        }
      ]
    }]
  }
}
    """.trimIndent()
)

/**
 * All style examples
 */
val styleExamples: List<Example> = listOf(
    basicStylesExample,
    styleInheritanceExample,
    conditionalStylesExample,
    shadowsExample,
    fractionalSizingExample
)
