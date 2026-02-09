package com.example.scalsandroid.examples

object ExampleJsons {

    val counter = """
    {
      "id": "counter-example",
      "state": {
        "count": 0
      },
      "actions": {
        "increment": {
          "type": "setState",
          "parameters": {
            "path": "count",
            "value": { "${'$'}expr": "count + 1" }
          }
        },
        "decrement": {
          "type": "setState",
          "parameters": {
            "path": "count",
            "value": { "${'$'}expr": "count - 1" }
          }
        },
        "reset": {
          "type": "setState",
          "parameters": {
            "path": "count",
            "value": 0
          }
        }
      },
      "root": {
        "children": [
          {
            "layout": "vstack",
            "spacing": 24,
            "alignment": { "horizontal": "center" },
            "padding": { "top": 40, "leading": 20, "bottom": 20, "trailing": 20 },
            "children": [
              {
                "type": "label",
                "text": "Counter Example",
                "style": {
                  "fontSize": 28,
                  "fontWeight": "bold",
                  "foregroundColor": "#333333"
                }
              },
              {
                "type": "label",
                "text": "${'$'}{count}",
                "style": {
                  "fontSize": 64,
                  "fontWeight": "bold",
                  "foregroundColor": "#007AFF"
                }
              },
              {
                "layout": "hstack",
                "spacing": 16,
                "children": [
                  {
                    "type": "button",
                    "label": "  -  ",
                    "actions": { "onTap": "decrement" },
                    "style": {
                      "backgroundColor": "#FF3B30",
                      "foregroundColor": "#FFFFFF",
                      "cornerRadius": 12,
                      "fontSize": 20,
                      "fontWeight": "bold",
                      "padding": { "top": 8, "leading": 16, "bottom": 8, "trailing": 16 }
                    }
                  },
                  {
                    "type": "button",
                    "label": "Reset",
                    "actions": { "onTap": "reset" },
                    "style": {
                      "backgroundColor": "#8E8E93",
                      "foregroundColor": "#FFFFFF",
                      "cornerRadius": 12,
                      "fontSize": 20,
                      "fontWeight": "semibold",
                      "padding": { "top": 8, "leading": 16, "bottom": 8, "trailing": 16 }
                    }
                  },
                  {
                    "type": "button",
                    "label": "  +  ",
                    "actions": { "onTap": "increment" },
                    "style": {
                      "backgroundColor": "#34C759",
                      "foregroundColor": "#FFFFFF",
                      "cornerRadius": 12,
                      "fontSize": 20,
                      "fontWeight": "bold",
                      "padding": { "top": 8, "leading": 16, "bottom": 8, "trailing": 16 }
                    }
                  }
                ]
              }
            ]
          }
        ]
      }
    }
    """.trimIndent()

    val form = """
    {
      "id": "form-example",
      "state": {
        "name": "",
        "agreed": false
      },
      "root": {
        "children": [
          {
            "layout": "vstack",
            "spacing": 20,
            "alignment": { "horizontal": "leading" },
            "padding": { "top": 40, "leading": 20, "bottom": 20, "trailing": 20 },
            "children": [
              {
                "type": "label",
                "text": "Form Example",
                "style": {
                  "fontSize": 28,
                  "fontWeight": "bold",
                  "foregroundColor": "#333333"
                }
              },
              {
                "type": "textField",
                "placeholder": "Enter your name",
                "binding": "name"
              },
              {
                "layout": "hstack",
                "spacing": 12,
                "children": [
                  {
                    "type": "label",
                    "text": "I agree to terms"
                  },
                  {
                    "type": "toggle",
                    "binding": "agreed"
                  }
                ]
              },
              {
                "type": "label",
                "text": "Hello ${'$'}{name}!",
                "style": {
                  "fontSize": 22,
                  "fontWeight": "medium",
                  "foregroundColor": "#007AFF"
                }
              }
            ]
          }
        ]
      }
    }
    """.trimIndent()
}
