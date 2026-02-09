package com.example.scalsandroid.examples.actions

import com.example.scalsandroid.examples.Example
import com.example.scalsandroid.examples.ExampleCategory
import com.example.scalsandroid.examples.PresentationStyle

/**
 * Action examples demonstrating user interactions and state modifications.
 * Total: 7 examples (HTTPRequestExample excluded - requires network support)
 */

private val setStateExample = Example(
    id = "setstate-example",
    title = "Set State",
    description = "Update state values using arithmetic and templates",
    category = ExampleCategory.ACTIONS,
    presentationStyle = PresentationStyle.BOTTOM_SHEET_MEDIUM,
    json = """
{
  "id": "setstate-example",
  "version": "1.0",
  "state": { "count": 0, "message": "Hello" },
  "styles": {
    "title": { "fontSize": 18, "fontWeight": "bold", "textColor": "#000000" },
    "value": { "fontSize": 48, "fontWeight": "bold", "textColor": "#007AFF" },
    "button": {
      "fontSize": 16, "fontWeight": "semibold",
      "backgroundColor": "#007AFF", "textColor": "#FFFFFF",
      "cornerRadius": 10, "height": 44, "padding": { "horizontal": 20 }
    }
  },
  "actions": {
    "increment": { "type": "setState", "path": "count", "value": { "${'$'}expr": "${'$'}{count} + 1" } },
    "decrement": { "type": "setState", "path": "count", "value": { "${'$'}expr": "${'$'}{count} - 1" } },
    "reset": { "type": "setState", "path": "count", "value": 0 }
  },
  "dataSources": {
    "countDisplay": { "type": "binding", "template": "${'$'}{count}" }
  },
  "root": {
    "backgroundColor": "#FFFFFF",
    "edgeInsets": { "top": 36, "leading": 28, "trailing": 28 },
    "children": [{
      "type": "vstack",
      "spacing": 24,
      "alignment": "center",
      "children": [
        { "type": "label", "text": "Counter with setState", "styleId": "title" },
        { "type": "label", "dataSourceId": "countDisplay", "styleId": "value" },
        {
          "type": "hstack", "spacing": 12,
          "children": [
            { "type": "button", "text": "-", "styleId": "button", "actions": { "onTap": "decrement" } },
            { "type": "button", "text": "Reset", "styleId": "button", "actions": { "onTap": "reset" } },
            { "type": "button", "text": "+", "styleId": "button", "actions": { "onTap": "increment" } }
          ]
        },
        { "type": "spacer" }
      ]
    }]
  }
}
    """.trimIndent()
)

private val toggleStateExample = Example(
    id = "togglestate-example",
    title = "Toggle State",
    description = "Toggle boolean state with visual feedback",
    category = ExampleCategory.ACTIONS,
    presentationStyle = PresentationStyle.BOTTOM_SHEET_MEDIUM,
    json = """
{
  "id": "togglestate-example",
  "version": "1.0",
  "state": { "isOn": false },
  "styles": {
    "title": { "fontSize": 18, "fontWeight": "bold", "textColor": "#000000" },
    "status": { "fontSize": 24, "fontWeight": "semibold" },
    "buttonOff": {
      "fontSize": 16, "fontWeight": "semibold",
      "backgroundColor": "#8E8E93", "textColor": "#FFFFFF",
      "cornerRadius": 10, "height": 44, "padding": { "horizontal": 24 }
    },
    "buttonOn": {
      "fontSize": 16, "fontWeight": "semibold",
      "backgroundColor": "#34C759", "textColor": "#FFFFFF",
      "cornerRadius": 10, "height": 44, "padding": { "horizontal": 24 }
    }
  },
  "actions": {
    "toggle": { "type": "toggleState", "path": "isOn" }
  },
  "dataSources": {
    "statusText": { "type": "binding", "template": "${'$'}{isOn ? 'ON' : 'OFF'}" }
  },
  "root": {
    "backgroundColor": "#FFFFFF",
    "edgeInsets": { "top": 36, "leading": 28, "trailing": 28 },
    "children": [{
      "type": "vstack",
      "spacing": 24,
      "alignment": "center",
      "children": [
        { "type": "label", "text": "Toggle State Action", "styleId": "title" },
        { "type": "label", "dataSourceId": "statusText", "styleId": "status" },
        {
          "type": "button",
          "text": "Toggle",
          "styles": { "normal": "buttonOff", "selected": "buttonOn" },
          "isSelectedBinding": "isOn",
          "actions": { "onTap": "toggle" }
        }
      ]
    }]
  }
}
    """.trimIndent()
)

private val showAlertExample = Example(
    id = "showalert-example",
    title = "Show Alert",
    description = "Display alert dialogs with various button styles",
    category = ExampleCategory.ACTIONS,
    presentationStyle = PresentationStyle.BOTTOM_SHEET_MEDIUM,
    json = """
{
  "id": "showalert-example",
  "version": "1.0",
  "styles": {
    "title": { "fontSize": 18, "fontWeight": "bold", "textColor": "#000000" },
    "subtitle": { "fontSize": 14, "textColor": "#666666" },
    "button": {
      "fontSize": 16, "fontWeight": "semibold",
      "backgroundColor": "#007AFF", "textColor": "#FFFFFF",
      "cornerRadius": 10, "height": 44, "padding": { "horizontal": 24 }
    },
    "destructiveButton": {
      "fontSize": 16, "fontWeight": "semibold",
      "backgroundColor": "#FF3B30", "textColor": "#FFFFFF",
      "cornerRadius": 10, "height": 44, "padding": { "horizontal": 24 }
    }
  },
  "actions": {
    "simpleAlert": {
      "type": "showAlert",
      "title": "Hello!",
      "message": "This is a simple alert.",
      "buttons": [{ "label": "OK", "style": "default" }]
    },
    "confirmAlert": {
      "type": "showAlert",
      "title": "Confirm Action",
      "message": "Are you sure you want to proceed?",
      "buttons": [
        { "label": "Cancel", "style": "cancel" },
        { "label": "Confirm", "style": "default" }
      ]
    },
    "destructiveAlert": {
      "type": "showAlert",
      "title": "Delete Item?",
      "message": "This action cannot be undone.",
      "buttons": [
        { "label": "Cancel", "style": "cancel" },
        { "label": "Delete", "style": "destructive" }
      ]
    }
  },
  "root": {
    "backgroundColor": "#FFFFFF",
    "edgeInsets": { "top": 36, "leading": 28, "trailing": 28 },
    "children": [{
      "type": "vstack",
      "spacing": 16,
      "alignment": "center",
      "children": [
        { "type": "label", "text": "Alert Examples", "styleId": "title" },
        { "type": "label", "text": "Tap buttons to show different alerts", "styleId": "subtitle" },
        { "type": "button", "text": "Simple Alert", "styleId": "button", "actions": { "onTap": "simpleAlert" } },
        { "type": "button", "text": "Confirmation Alert", "styleId": "button", "actions": { "onTap": "confirmAlert" } },
        { "type": "button", "text": "Destructive Alert", "styleId": "destructiveButton", "actions": { "onTap": "destructiveAlert" } },
        { "type": "spacer" }
      ]
    }]
  }
}
    """.trimIndent()
)

private val dismissExample = Example(
    id = "dismiss-example",
    title = "Dismiss",
    description = "Close the current view with dismiss action",
    category = ExampleCategory.ACTIONS,
    presentationStyle = PresentationStyle.BOTTOM_SHEET_MEDIUM,
    json = """
{
  "id": "dismiss-example",
  "version": "1.0",
  "styles": {
    "title": { "fontSize": 24, "fontWeight": "bold", "textColor": "#000000" },
    "subtitle": { "fontSize": 16, "textColor": "#666666" },
    "button": {
      "fontSize": 16, "fontWeight": "semibold",
      "backgroundColor": "#007AFF", "textColor": "#FFFFFF",
      "cornerRadius": 10, "height": 50, "padding": { "horizontal": 32 }
    },
    "successIcon": { "width": 80, "height": 80, "tintColor": "#34C759" }
  },
  "actions": {
    "close": { "type": "dismiss" }
  },
  "root": {
    "backgroundColor": "#FFFFFF",
    "edgeInsets": { "top": 36, "leading": 28, "trailing": 28 },
    "children": [{
      "type": "vstack",
      "spacing": 24,
      "alignment": "center",
      "children": [
        { "type": "spacer" },
        { "type": "image", "image": { "sfsymbol": "checkmark.circle.fill" }, "styleId": "successIcon" },
        { "type": "label", "text": "Success!", "styleId": "title" },
        { "type": "label", "text": "Tap the button to dismiss this view", "styleId": "subtitle" },
        { "type": "spacer" },
        { "type": "button", "text": "Done", "styleId": "button", "fillWidth": true, "actions": { "onTap": "close" } }
      ]
    }]
  }
}
    """.trimIndent()
)

private val navigateExample = Example(
    id = "navigate-example",
    title = "Navigate",
    description = "Push new screens with navigate action",
    category = ExampleCategory.ACTIONS,
    presentationStyle = PresentationStyle.BOTTOM_SHEET_MEDIUM,
    json = """
{
  "id": "navigate-example",
  "version": "1.0",
  "styles": {
    "title": { "fontSize": 18, "fontWeight": "bold", "textColor": "#000000" },
    "subtitle": { "fontSize": 14, "textColor": "#666666" },
    "row": { "padding": { "vertical": 16 } },
    "rowTitle": { "fontSize": 16, "textColor": "#000000" },
    "rowIcon": { "width": 24, "height": 24, "tintColor": "#007AFF" },
    "chevron": { "width": 16, "height": 16, "tintColor": "#C7C7CC" }
  },
  "actions": {
    "goToProfile": { "type": "navigate", "destination": "profile" },
    "goToSettings": { "type": "navigate", "destination": "settings" }
  },
  "root": {
    "backgroundColor": "#FFFFFF",
    "edgeInsets": { "top": 36, "leading": 28, "trailing": 28 },
    "children": [{
      "type": "vstack",
      "spacing": 16,
      "alignment": "leading",
      "children": [
        { "type": "label", "text": "Navigation Actions", "styleId": "title" },
        { "type": "label", "text": "Navigate action pushes a new destination", "styleId": "subtitle" },
        {
          "type": "button", "styleId": "row",
          "actions": { "onTap": "goToProfile" },
          "children": [{
            "type": "hstack",
            "children": [
              { "type": "image", "image": { "sfsymbol": "person.circle" }, "styleId": "rowIcon" },
              { "type": "label", "text": "Go to Profile", "styleId": "rowTitle", "padding": { "leading": 12 } },
              { "type": "spacer" },
              { "type": "image", "image": { "sfsymbol": "chevron.right" }, "styleId": "chevron" }
            ]
          }]
        },
        {
          "type": "button", "styleId": "row",
          "actions": { "onTap": "goToSettings" },
          "children": [{
            "type": "hstack",
            "children": [
              { "type": "image", "image": { "sfsymbol": "gear" }, "styleId": "rowIcon" },
              { "type": "label", "text": "Go to Settings", "styleId": "rowTitle", "padding": { "leading": 12 } },
              { "type": "spacer" },
              { "type": "image", "image": { "sfsymbol": "chevron.right" }, "styleId": "chevron" }
            ]
          }]
        }
      ]
    }]
  }
}
    """.trimIndent()
)

private val sequenceExample = Example(
    id = "sequence-example",
    title = "Sequence Actions",
    description = "Chain multiple actions together in order",
    category = ExampleCategory.ACTIONS,
    presentationStyle = PresentationStyle.BOTTOM_SHEET_MEDIUM,
    json = """
{
  "id": "sequence-example",
  "version": "1.0",
  "state": { "step": 0 },
  "styles": {
    "title": { "fontSize": 18, "fontWeight": "bold", "textColor": "#000000" },
    "stepLabel": { "fontSize": 48, "fontWeight": "bold", "textColor": "#007AFF" },
    "button": {
      "fontSize": 16, "fontWeight": "semibold",
      "backgroundColor": "#007AFF", "textColor": "#FFFFFF",
      "cornerRadius": 10, "height": 44, "padding": { "horizontal": 24 }
    }
  },
  "actions": {
    "multiStep": {
      "type": "sequence",
      "steps": [
        { "type": "setState", "path": "step", "value": { "${'$'}expr": "${'$'}{step} + 1" } },
        {
          "type": "showAlert",
          "title": "Step Complete",
          "message": { "type": "binding", "template": "You are now on step ${'$'}{step}" },
          "buttons": [{ "label": "OK", "style": "default" }]
        }
      ]
    }
  },
  "dataSources": {
    "stepText": { "type": "binding", "template": "Step ${'$'}{step}" }
  },
  "root": {
    "backgroundColor": "#FFFFFF",
    "edgeInsets": { "top": 36, "leading": 28, "trailing": 28 },
    "children": [{
      "type": "vstack",
      "spacing": 24,
      "alignment": "center",
      "children": [
        { "type": "label", "text": "Sequence Actions", "styleId": "title" },
        { "type": "label", "dataSourceId": "stepText", "styleId": "stepLabel" },
        { "type": "button", "text": "Next Step", "styleId": "button", "actions": { "onTap": "multiStep" } }
      ]
    }]
  }
}
    """.trimIndent()
)

private val arrayActionsExample = Example(
    id = "arrayactions-example",
    title = "Array Actions",
    description = "Add and remove items from arrays",
    category = ExampleCategory.ACTIONS,
    presentationStyle = PresentationStyle.BOTTOM_SHEET_MEDIUM,
    json = """
{
  "id": "arrayactions-example",
  "version": "1.0",
  "state": { "items": ["Apple", "Banana"], "newItem": "" },
  "styles": {
    "title": { "fontSize": 18, "fontWeight": "bold", "textColor": "#000000" },
    "count": { "fontSize": 14, "textColor": "#666666" },
    "field": {
      "fontSize": 16, "textColor": "#000000",
      "backgroundColor": "#F2F2F7", "cornerRadius": 8,
      "padding": { "horizontal": 12, "vertical": 12 }
    },
    "addButton": {
      "fontSize": 16, "fontWeight": "semibold",
      "backgroundColor": "#34C759", "textColor": "#FFFFFF",
      "cornerRadius": 8, "height": 44, "padding": { "horizontal": 16 }
    },
    "removeButton": {
      "fontSize": 14, "fontWeight": "medium",
      "backgroundColor": "#FF3B30", "textColor": "#FFFFFF",
      "cornerRadius": 6, "padding": { "horizontal": 10, "vertical": 6 }
    },
    "itemLabel": { "fontSize": 16, "textColor": "#000000" }
  },
  "actions": {
    "addItem": {
      "type": "sequence",
      "steps": [
        { "type": "appendToArray", "path": "items", "value": { "${'$'}expr": "${'$'}{newItem}" } },
        { "type": "setState", "path": "newItem", "value": "" }
      ]
    }
  },
  "dataSources": {
    "countText": { "type": "binding", "template": "${'$'}{items.count} items" }
  },
  "root": {
    "backgroundColor": "#FFFFFF",
    "edgeInsets": { "top": 36, "leading": 28, "trailing": 28 },
    "children": [{
      "type": "vstack",
      "spacing": 16,
      "alignment": "leading",
      "children": [
        { "type": "label", "text": "Array Actions", "styleId": "title" },
        { "type": "label", "dataSourceId": "countText", "styleId": "count" },
        {
          "type": "hstack", "spacing": 8,
          "children": [
            { "type": "textfield", "placeholder": "New item", "styleId": "field", "bind": "newItem" },
            { "type": "button", "text": "Add", "styleId": "addButton", "actions": { "onTap": "addItem" } }
          ]
        },
        { "type": "label", "text": "Add items above and watch the count update!", "styleId": "count" }
      ]
    }]
  }
}
    """.trimIndent()
)

/**
 * All action examples
 */
val actionExamples: List<Example> = listOf(
    setStateExample,
    toggleStateExample,
    showAlertExample,
    dismissExample,
    navigateExample,
    sequenceExample,
    arrayActionsExample
)
