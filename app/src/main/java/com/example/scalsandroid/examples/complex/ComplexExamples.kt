package com.example.scalsandroid.examples.complex

import com.example.scalsandroid.examples.Example
import com.example.scalsandroid.examples.ExampleCategory
import com.example.scalsandroid.examples.PresentationStyle

/**
 * Complex examples demonstrating full-featured applications and advanced interactions.
 * Total: 10 examples
 */

private val componentShowcaseExample = Example(
    id = "component-showcase",
    title = "Component Showcase",
    description = "Comprehensive demonstration of all available component types",
    category = ExampleCategory.COMPLEX,
    presentationStyle = PresentationStyle.FULL_SCREEN,
    json = """
{
  "id": "component-showcase",
  "version": "1.0",
  "state": {
    "textFieldValue": "",
    "buttonTapCount": 0,
    "isToggled": false,
    "toggle1": false,
    "toggle2": true,
    "toggle3": false,
    "slider1": 0.5,
    "slider2": 0.75,
    "slider3": 25
  },
  "styles": {
    "screenTitle": { "fontSize": 28, "fontWeight": "bold", "textColor": "#000000", "textAlignment": "leading" },
    "sectionTitle": { "fontSize": 18, "fontWeight": "semibold", "textColor": "#000000" },
    "bodyText": { "fontSize": 15, "fontWeight": "regular", "textColor": "#333333" },
    "captionText": { "fontSize": 13, "fontWeight": "regular", "textColor": "#888888" },
    "primaryButton": { "fontSize": 16, "fontWeight": "semibold", "backgroundColor": "#007AFF", "textColor": "#FFFFFF", "cornerRadius": 10, "height": 44, "padding": { "horizontal": 20 } },
    "secondaryButton": { "fontSize": 16, "fontWeight": "medium", "backgroundColor": "#E5E5EA", "textColor": "#000000", "cornerRadius": 10, "height": 44, "padding": { "horizontal": 20 } },
    "toggleButton": { "fontSize": 14, "fontWeight": "medium", "backgroundColor": "#E5E5EA", "textColor": "#000000", "cornerRadius": 8, "height": 36, "padding": { "horizontal": 16 } },
    "toggleButtonSelected": { "fontSize": 14, "fontWeight": "semibold", "backgroundColor": "#34C759", "textColor": "#FFFFFF", "cornerRadius": 8, "height": 36, "padding": { "horizontal": 16 } },
    "textFieldStyle": { "fontSize": 16, "fontWeight": "regular", "textColor": "#000000", "backgroundColor": "#F2F2F7", "cornerRadius": 8, "padding": { "horizontal": 12, "vertical": 12 } },
    "iconStyle": { "width": 48, "height": 48 },
    "redIconStyle": { "inherits": "iconStyle", "tintColor": "#FF3B30" },
    "orangeIconStyle": { "inherits": "iconStyle", "tintColor": "#FF9500" },
    "blueIconStyle": { "inherits": "iconStyle", "tintColor": "#007AFF" },
    "urlImageStyle": { "cornerRadius": 12 },
    "spinnerStyle": { "width": 40, "height": 40 },
    "loadingButton": { "fontSize": 16, "fontWeight": "medium", "backgroundColor": "#E5E5EA", "textColor": "#666666", "cornerRadius": 10, "height": 44, "padding": { "horizontal": 20 } },
    "greenToggleStyle": { "tintColor": "#34C759" },
    "purpleToggleStyle": { "tintColor": "#AF52DE" },
    "orangeSliderStyle": { "tintColor": "#FF9500" },
    "redSliderStyle": { "tintColor": "#FF3B30" },
    "gradientStyle": { "width": 320, "height": 80, "cornerRadius": 12 },
    "gradientLabel": { "fontSize": 16, "fontWeight": "semibold", "textColor": "#FFFFFF" },
    "closeButton": { "fontSize": 15, "fontWeight": "regular", "textColor": "#007AFF" }
  },
  "actions": {
    "incrementCount": { "type": "setState", "path": "buttonTapCount", "value": { "${'$'}expr": "${'$'}{buttonTapCount} + 1" } },
    "close": { "type": "dismiss" }
  },
  "root": {
    "backgroundColor": "#FFFFFF",
    "edgeInsets": { "top": 16 },
    "children": [{
      "type": "sectionLayout",
      "sectionSpacing": 52,
      "sections": [
        {
          "id": "header",
          "layout": { "type": "list", "showsDividers": false, "contentInsets": { "horizontal": 20 } },
          "children": [
            { "type": "hstack", "children": [{ "type": "spacer" }, { "type": "button", "text": "Close", "styleId": "closeButton", "actions": { "onTap": "close" } }] },
            { "type": "label", "text": "Component Showcase", "styleId": "screenTitle" },
            { "type": "label", "text": "This example demonstrates all available component types in ScalsRenderer.", "styleId": "bodyText" }
          ]
        },
        {
          "id": "labels",
          "layout": { "type": "list", "showsDividers": false, "itemSpacing": 8, "contentInsets": { "horizontal": 20 } },
          "header": { "type": "label", "text": "Labels", "styleId": "sectionTitle", "padding": { "bottom": 12 } },
          "children": [
            { "type": "label", "text": "This is body text with regular weight.", "styleId": "bodyText" },
            { "type": "label", "text": "This is caption text, smaller and lighter.", "styleId": "captionText" }
          ]
        },
        {
          "id": "buttons",
          "layout": { "type": "list", "showsDividers": false, "itemSpacing": 12, "contentInsets": { "horizontal": 20 } },
          "header": { "type": "label", "text": "Buttons", "styleId": "sectionTitle", "padding": { "bottom": 12 } },
          "children": [
            { "type": "hstack", "spacing": 12, "children": [
              { "type": "button", "text": "Primary", "styleId": "primaryButton", "actions": { "onTap": "incrementCount" } },
              { "type": "button", "text": "Secondary", "styleId": "secondaryButton", "actions": { "onTap": "incrementCount" } }
            ]},
            { "type": "hstack", "spacing": 8, "children": [
              { "type": "label", "text": "Tap count:", "styleId": "captionText" },
              { "type": "label", "dataSourceId": "tapCountText", "styleId": "captionText" }
            ]}
          ]
        },
        {
          "id": "textfield",
          "layout": { "type": "list", "showsDividers": false, "itemSpacing": 8, "contentInsets": { "horizontal": 20 } },
          "header": { "type": "label", "text": "Text Field", "styleId": "sectionTitle", "padding": { "bottom": 12 } },
          "children": [
            { "type": "textfield", "placeholder": "Enter some text...", "styleId": "textFieldStyle", "bind": "textFieldValue" },
            { "type": "hstack", "spacing": 8, "children": [
              { "type": "label", "text": "You typed:", "styleId": "captionText" },
              { "type": "label", "dataSourceId": "textFieldDisplay", "styleId": "captionText" }
            ]}
          ]
        },
        {
          "id": "toggles",
          "layout": { "type": "list", "showsDividers": false, "itemSpacing": 16, "contentInsets": { "horizontal": 20 } },
          "header": { "type": "label", "text": "Toggles", "styleId": "sectionTitle", "padding": { "bottom": 12 } },
          "children": [
            { "type": "hstack", "spacing": 12, "children": [{ "type": "label", "text": "Default toggle:", "styleId": "bodyText" }, { "type": "toggle", "bind": "toggle1" }] },
            { "type": "hstack", "spacing": 12, "children": [{ "type": "label", "text": "Green toggle:", "styleId": "bodyText" }, { "type": "toggle", "bind": "toggle2", "styleId": "greenToggleStyle" }] },
            { "type": "hstack", "spacing": 12, "children": [{ "type": "label", "text": "Purple toggle:", "styleId": "bodyText" }, { "type": "toggle", "bind": "toggle3", "styleId": "purpleToggleStyle" }] }
          ]
        },
        {
          "id": "sliders",
          "layout": { "type": "list", "showsDividers": false, "itemSpacing": 16, "contentInsets": { "horizontal": 20 } },
          "header": { "type": "label", "text": "Sliders", "styleId": "sectionTitle", "padding": { "bottom": 12 } },
          "children": [
            { "type": "vstack", "spacing": 8, "alignment": "leading", "children": [{ "type": "label", "text": "Default slider (0-1):", "styleId": "bodyText" }, { "type": "slider", "bind": "slider1" }] },
            { "type": "vstack", "spacing": 8, "alignment": "leading", "children": [{ "type": "label", "text": "Orange slider (0-1):", "styleId": "bodyText" }, { "type": "slider", "bind": "slider2", "styleId": "orangeSliderStyle" }] },
            { "type": "vstack", "spacing": 8, "alignment": "leading", "children": [{ "type": "label", "text": "Red slider (0-100):", "styleId": "bodyText" }, { "type": "slider", "bind": "slider3", "minValue": 0, "maxValue": 100, "styleId": "redSliderStyle" }] }
          ]
        },
        {
          "id": "images",
          "layout": { "type": "list", "showsDividers": false, "contentInsets": { "horizontal": 20 } },
          "header": { "type": "label", "text": "Images", "styleId": "sectionTitle", "padding": { "bottom": 12 } },
          "children": [
            { "type": "hstack", "spacing": 16, "children": [
              { "type": "vstack", "spacing": 4, "children": [{ "type": "image", "image": { "icon": "StarFilled" }, "styleId": "iconStyle" }, { "type": "label", "text": "Default", "styleId": "captionText" }] },
              { "type": "vstack", "spacing": 4, "children": [{ "type": "image", "image": { "icon": "FavoriteFilled" }, "styleId": "redIconStyle" }, { "type": "label", "text": "Red", "styleId": "captionText" }] },
              { "type": "vstack", "spacing": 4, "children": [{ "type": "image", "image": { "icon": "Bolt" }, "styleId": "orangeIconStyle" }, { "type": "label", "text": "Orange", "styleId": "captionText" }] },
              { "type": "vstack", "spacing": 4, "children": [{ "type": "image", "image": { "icon": "Globe" }, "styleId": "blueIconStyle" }, { "type": "label", "text": "Blue", "styleId": "captionText" }] }
            ]}
          ]
        },
        {
          "id": "gradient",
          "layout": { "type": "list", "showsDividers": false, "contentInsets": { "horizontal": 20, "bottom": 40 } },
          "header": { "type": "label", "text": "Gradient", "styleId": "sectionTitle", "padding": { "bottom": 12 } },
          "children": [
            { "type": "zstack", "children": [
              { "type": "gradient", "gradientColors": [{ "color": "#FF6B6B", "location": 0.0 }, { "color": "#4ECDC4", "location": 0.5 }, { "color": "#45B7D1", "location": 1.0 }], "gradientStart": "leading", "gradientEnd": "trailing", "styleId": "gradientStyle" },
              { "type": "label", "text": "Gradient Overlay", "styleId": "gradientLabel" }
            ]}
          ]
        }
      ]
    }]
  },
  "dataSources": {
    "tapCountText": { "type": "binding", "template": "${'$'}{buttonTapCount}" },
    "textFieldDisplay": { "type": "binding", "template": "${'$'}{textFieldValue}" }
  }
}
"""
)

private val dadJokesExample = Example(
    id = "dad-jokes",
    title = "Dad Jokes",
    description = "REST API integration fetching jokes from icanhazdadjoke.com",
    category = ExampleCategory.COMPLEX,
    presentationStyle = PresentationStyle.FULL_SCREEN,
    json = """
{
  "id": "dad-jokes",
  "version": "1.0",

  "state": {
    "setup": "",
    "punchline": "",
    "hiddenPunchline": "",
    "hasJoke": false,
    "isLoading": false
  },

  "styles": {
    "closeButton": {
      "fontSize": 16,
      "fontWeight": "semibold",
      "textColor": "#007AFF",
      "backgroundColor": "rgba(0, 122, 255, 0.1)",
      "width": 40,
      "height": 40
    },
    "screenTitle": {
      "fontSize": 28,
      "fontWeight": "bold",
      "textColor": "#1a1a1a"
    },
    "jokeSetup": {
      "fontSize": 20,
      "fontWeight": "medium",
      "textColor": "#333333",
      "textAlignment": "center"
    },
    "jokePunchline": {
      "fontSize": 22,
      "fontWeight": "bold",
      "textColor": "#E85D04",
      "textAlignment": "center"
    },
    "placeholderText": {
      "fontSize": 17,
      "fontWeight": "regular",
      "textColor": "#888888",
      "textAlignment": "center"
    },
    "fetchButton": {
      "fontSize": 17,
      "fontWeight": "semibold",
      "backgroundColor": "#007AFF",
      "textColor": "#FFFFFF",
      "cornerRadius": 12,
      "height": 50
    },
    "revealButton": {
      "fontSize": 16,
      "fontWeight": "medium",
      "backgroundColor": "#F2F2F7",
      "textColor": "#007AFF",
      "cornerRadius": 10,
      "height": 44
    },
    "cardStyle": {
      "backgroundColor": "#F9F9F9",
      "cornerRadius": 16
    }
  },

  "dataSources": {
    "setupText": {
      "type": "binding",
      "path": "setup"
    },
    "punchlineText": {
      "type": "binding",
      "path": "punchline"
    }
  },

  "actions": {
    "dismiss": { "type": "dismiss" }
  },

  "root": {
    "backgroundColor": "#FFFFFF",
    "actions": {
      "onAppear": { "type": "fetchJoke" }
    },
    "children": [
      {
        "type": "vstack",
        "spacing": 0,
        "children": [
          {
            "type": "hstack",
            "padding": { "leading": 20, "trailing": 20, "top": 16 },
            "children": [
              {
                "type": "button",
                "image": { "icon": "Close" },
                "buttonShape": "circle",
                "styleId": "closeButton",
                "actions": { "onTap": { "type": "dismiss" } }
              },
              { "type": "spacer" }
            ]
          },
          {
            "type": "vstack",
            "spacing": 24,
            "padding": { "horizontal": 20, "top": 8 },
            "children": [
              {
                "type": "vstack",
                "alignment": "center",
                "spacing": 24,
                "padding": { "all": 24 },
                "styleId": "cardStyle",
                "children": [
                  {
                    "type": "label",
                    "dataSourceId": "setupText",
                    "styleId": "jokeSetup"
                  },
                  {
                    "type": "label",
                    "dataSourceId": "punchlineText",
                    "styleId": "jokePunchline"
                  }
                ]
              }
            ]
          },
          { "type": "spacer" },
          {
            "type": "hstack",
            "spacing": 12,
            "padding": { "horizontal": 20, "bottom": 20 },
            "children": [
              {
                "type": "button",
                "text": "Reveal",
                "styleId": "revealButton",
                "fillWidth": true,
                "actions": { "onTap": { "type": "revealPunchline" } }
              },
              {
                "type": "button",
                "text": "New Joke",
                "styleId": "fetchButton",
                "fillWidth": true,
                "actions": {
                  "onTap": {
                    "type": "sequence",
                    "steps": [
                      { "type": "fetchJoke" }
                    ]
                  }
                }
              }
            ]
          }
        ]
      }
    ]
  }
}
"""
)

private val taskManagerExample = Example(
    id = "task-manager",
    title = "Task Manager",
    description = "Dynamic task list with add, toggle, and delete functionality",
    category = ExampleCategory.COMPLEX,
    presentationStyle = PresentationStyle.FULL_SCREEN,
    json = """
{
  "id": "task-manager",
  "version": "1.0",
  "state": {
    "newTaskTitle": ""
  },
  "styles": {
    "screenTitle": { "fontSize": 32, "fontWeight": "bold", "textColor": "#000000" },
    "subtitle": { "fontSize": 14, "textColor": "#8E8E93" },
    "sectionHeader": { "fontSize": 18, "fontWeight": "semibold", "textColor": "#000000" },
    "inputField": {
      "fontSize": 16, "textColor": "#000000",
      "backgroundColor": "#F2F2F7", "cornerRadius": 12
    },
    "addButton": {
      "fontSize": 16, "fontWeight": "semibold",
      "backgroundColor": "#007AFF", "textColor": "#FFFFFF",
      "cornerRadius": 12, "height": 48
    },
    "taskRow": { "padding": { "vertical": 12 } },
    "taskTitle": { "fontSize": 16, "textColor": "#000000" },
    "taskTitleCompleted": { "fontSize": 16, "textColor": "#8E8E93", "strikethrough": true },
    "checkboxEmpty": { "width": 24, "height": 24, "tintColor": "#C7C7CC" },
    "checkboxFilled": { "width": 24, "height": 24, "tintColor": "#34C759" },
    "deleteButton": { "width": 20, "height": 20, "tintColor": "#FF3B30" },
    "closeButton": { "width": 28, "height": 28, "tintColor": "#8E8E93" },
    "emptyIcon": { "width": 48, "height": 48, "tintColor": "#C7C7CC" },
    "emptyTitle": { "fontSize": 18, "fontWeight": "semibold", "textColor": "#8E8E93" },
    "emptySubtitle": { "fontSize": 14, "textColor": "#C7C7CC" }
  },
  "actions": {
    "close": { "type": "dismiss" },
    "addTask": { "type": "addTask" }
  },
  "root": {
    "backgroundColor": "#FFFFFF",
    "edgeInsets": { "top": 20 },
    "children": [{
      "type": "sectionLayout",
      "sectionSpacing": 24,
      "sections": [
        {
          "id": "header",
          "layout": { "type": "list", "showsDividers": false, "contentInsets": { "horizontal": 20 } },
          "children": [
            {
              "type": "hstack",
              "children": [
                {
                  "type": "vstack", "alignment": "leading", "spacing": 4,
                  "children": [
                    { "type": "label", "text": "Tasks", "styleId": "screenTitle" },
                    { "type": "label", "text": "0 tasks", "styleId": "subtitle" }
                  ]
                },
                { "type": "spacer" },
                {
                  "type": "button",
                  "actions": { "onTap": "close" },
                  "children": [{ "type": "image", "image": { "icon": "CancelFilled" }, "styleId": "closeButton" }]
                }
              ]
            }
          ]
        },
        {
          "id": "add-task",
          "layout": { "type": "list", "showsDividers": false, "contentInsets": { "horizontal": 20 } },
          "header": { "type": "label", "text": "Add New Task", "styleId": "sectionHeader", "padding": { "bottom": 12 } },
          "children": [
            {
              "type": "hstack", "spacing": 12,
              "children": [
                { "type": "textfield", "placeholder": "What needs to be done?", "styleId": "inputField", "bind": "newTaskTitle" },
                { "type": "button", "text": "Add", "styleId": "addButton", "actions": { "onTap": "addTask" } }
              ]
            }
          ]
        },
        {
          "id": "task-list",
          "layout": { "type": "list", "showsDividers": false, "contentInsets": { "horizontal": 20 } },
          "header": { "type": "label", "text": "Your Tasks", "styleId": "sectionHeader", "padding": { "bottom": 8 } },
          "children": [
            {
              "type": "vstack",
              "alignment": "center",
              "spacing": 12,
              "padding": { "vertical": 40 },
              "children": [
                { "type": "image", "image": { "icon": "Checklist" }, "styleId": "emptyIcon" },
                { "type": "label", "text": "No tasks yet", "styleId": "emptyTitle" },
                { "type": "label", "text": "Add a task above to get started", "styleId": "emptySubtitle" }
              ]
            }
          ]
        }
      ]
    }]
  }
}
"""
)

private val shoppingCartExample = Example(
    id = "shopping-cart",
    title = "Shopping Cart",
    description = "E-commerce cart with product management and promo codes",
    category = ExampleCategory.COMPLEX,
    presentationStyle = PresentationStyle.FULL_SCREEN,
    json = """
{
  "id": "shopping-cart",
  "version": "1.0",
  "state": {
    "cartItems": [
      { "name": "Wireless Headphones", "price": 199.99, "quantity": 1, "image": "headphones" },
      { "name": "Smart Watch", "price": 299.99, "quantity": 1, "image": "applewatch" },
      { "name": "Phone Case", "price": 29.99, "quantity": 2, "image": "iphone" }
    ],
    "promoCode": "",
    "promoApplied": false
  },
  "styles": {
    "screenTitle": { "fontSize": 28, "fontWeight": "bold", "textColor": "#000000" },
    "itemCount": { "fontSize": 14, "textColor": "#8E8E93" },
    "sectionHeader": { "fontSize": 16, "fontWeight": "semibold", "textColor": "#000000" },
    "productImage": { "width": 80, "height": 80, "backgroundColor": "#F2F2F7", "cornerRadius": 12 },
    "productIcon": { "width": 40, "height": 40, "tintColor": "#007AFF" },
    "productName": { "fontSize": 16, "fontWeight": "medium", "textColor": "#000000" },
    "productPrice": { "fontSize": 14, "fontWeight": "semibold", "textColor": "#007AFF" },
    "quantityLabel": { "fontSize": 14, "textColor": "#8E8E93" },
    "quantityValue": { "fontSize": 16, "fontWeight": "semibold", "textColor": "#000000" },
    "quantityButton": {
      "fontSize": 18, "fontWeight": "bold",
      "backgroundColor": "#F2F2F7", "textColor": "#007AFF",
      "cornerRadius": 8, "width": 32, "height": 32
    },
    "removeButton": { "fontSize": 14, "textColor": "#FF3B30" },
    "promoField": {
      "fontSize": 16, "textColor": "#000000",
      "backgroundColor": "#F2F2F7", "cornerRadius": 10,
      "padding": { "horizontal": 14, "vertical": 12 }
    },
    "applyButton": {
      "fontSize": 14, "fontWeight": "semibold",
      "backgroundColor": "#34C759", "textColor": "#FFFFFF",
      "cornerRadius": 10, "height": 44, "padding": { "horizontal": 16 }
    },
    "summaryRow": { "fontSize": 16, "textColor": "#000000" },
    "summaryLabel": { "fontSize": 16, "textColor": "#8E8E93" },
    "totalLabel": { "fontSize": 18, "fontWeight": "bold", "textColor": "#000000" },
    "totalValue": { "fontSize": 24, "fontWeight": "bold", "textColor": "#007AFF" },
    "checkoutButton": {
      "fontSize": 18, "fontWeight": "semibold",
      "backgroundColor": "#007AFF", "textColor": "#FFFFFF",
      "cornerRadius": 14, "height": 56
    },
    "emptyCartIcon": { "width": 80, "height": 80, "tintColor": "#C7C7CC" },
    "emptyCartText": { "fontSize": 18, "fontWeight": "medium", "textColor": "#8E8E93" },
    "continueButton": {
      "fontSize": 16, "fontWeight": "medium",
      "backgroundColor": "#E5E5EA", "textColor": "#007AFF",
      "cornerRadius": 10, "height": 44, "padding": { "horizontal": 20 }
    },
    "divider": { "height": 1, "backgroundColor": "#E5E5EA" },
    "cardBackground": {
      "backgroundColor": "#FFFFFF", "cornerRadius": 16,
      "padding": { "all": 16 }
    },
    "summaryCard": {
      "backgroundColor": "#F8F8F8", "cornerRadius": 16,
      "padding": { "all": 20 }
    },
    "closeButton": { "width": 28, "height": 28, "tintColor": "#8E8E93" }
  },
  "actions": {
    "close": { "type": "dismiss" },
    "applyPromo": {
      "type": "sequence",
      "steps": [
        { "type": "setState", "path": "promoApplied", "value": true },
        {
          "type": "showAlert",
          "title": "Promo Applied!",
          "message": "You saved 10% on your order",
          "buttons": [{ "label": "Awesome!", "style": "default" }]
        }
      ]
    },
    "checkout": {
      "type": "showAlert",
      "title": "Proceed to Checkout?",
      "message": "You will be redirected to payment",
      "buttons": [
        { "label": "Cancel", "style": "cancel" },
        { "label": "Continue", "style": "default" }
      ]
    },
    "removeItem": {
      "type": "showAlert",
      "title": "Remove Item?",
      "message": "Are you sure you want to remove this item?",
      "buttons": [
        { "label": "Cancel", "style": "cancel" },
        { "label": "Remove", "style": "destructive" }
      ]
    }
  },
  "dataSources": {
    "itemCountText": { "type": "binding", "template": "${'$'}{cartItems.count} items in cart" },
    "subtotal": { "type": "static", "value": "${'$'}559.96" },
    "shipping": { "type": "static", "value": "FREE" },
    "tax": { "type": "static", "value": "${'$'}44.80" },
    "total": { "type": "static", "value": "${'$'}604.76" }
  },
  "root": {
    "backgroundColor": "#F2F2F7",
    "edgeInsets": { "top": 20 },
    "children": [{
      "type": "sectionLayout",
      "sectionSpacing": 16,
      "sections": [
        {
          "id": "header",
          "layout": { "type": "list", "showsDividers": false, "contentInsets": { "horizontal": 20 } },
          "children": [
            {
              "type": "hstack",
              "children": [
                {
                  "type": "vstack", "alignment": "leading", "spacing": 4,
                  "children": [
                    { "type": "label", "text": "Shopping Cart", "styleId": "screenTitle" },
                    { "type": "label", "dataSourceId": "itemCountText", "styleId": "itemCount" }
                  ]
                },
                { "type": "spacer" },
                {
                  "type": "button",
                  "actions": { "onTap": "close" },
                  "children": [{ "type": "image", "image": { "icon": "CancelFilled" }, "styleId": "closeButton" }]
                }
              ]
            }
          ]
        },
        {
          "id": "items",
          "layout": { "type": "list", "showsDividers": false, "itemSpacing": 12, "contentInsets": { "horizontal": 20 } },
          "children": [
            {
              "type": "hstack", "spacing": 16, "styleId": "cardBackground",
              "children": [
                {
                  "type": "zstack", "styleId": "productImage",
                  "children": [
                    { "type": "image", "image": { "icon": "Headset" }, "styleId": "productIcon" }
                  ]
                },
                {
                  "type": "vstack", "spacing": 6, "alignment": "leading",
                  "children": [
                    { "type": "label", "text": "Wireless Headphones", "styleId": "productName" },
                    { "type": "label", "text": "${'$'}199.99", "styleId": "productPrice" },
                    {
                      "type": "hstack", "spacing": 12,
                      "children": [
                        { "type": "button", "text": "-", "styleId": "quantityButton" },
                        { "type": "label", "text": "1", "styleId": "quantityValue" },
                        { "type": "button", "text": "+", "styleId": "quantityButton" }
                      ]
                    }
                  ]
                },
                { "type": "spacer" },
                { "type": "button", "text": "Remove", "styleId": "removeButton", "actions": { "onTap": "removeItem" } }
              ]
            },
            {
              "type": "hstack", "spacing": 16, "styleId": "cardBackground",
              "children": [
                {
                  "type": "zstack", "styleId": "productImage",
                  "children": [
                    { "type": "image", "image": { "icon": "Watch" }, "styleId": "productIcon" }
                  ]
                },
                {
                  "type": "vstack", "spacing": 6, "alignment": "leading",
                  "children": [
                    { "type": "label", "text": "Smart Watch", "styleId": "productName" },
                    { "type": "label", "text": "${'$'}299.99", "styleId": "productPrice" },
                    {
                      "type": "hstack", "spacing": 12,
                      "children": [
                        { "type": "button", "text": "-", "styleId": "quantityButton" },
                        { "type": "label", "text": "1", "styleId": "quantityValue" },
                        { "type": "button", "text": "+", "styleId": "quantityButton" }
                      ]
                    }
                  ]
                },
                { "type": "spacer" },
                { "type": "button", "text": "Remove", "styleId": "removeButton", "actions": { "onTap": "removeItem" } }
              ]
            },
            {
              "type": "hstack", "spacing": 16, "styleId": "cardBackground",
              "children": [
                {
                  "type": "zstack", "styleId": "productImage",
                  "children": [
                    { "type": "image", "image": { "icon": "Phone" }, "styleId": "productIcon" }
                  ]
                },
                {
                  "type": "vstack", "spacing": 6, "alignment": "leading",
                  "children": [
                    { "type": "label", "text": "Phone Case", "styleId": "productName" },
                    { "type": "label", "text": "${'$'}29.99", "styleId": "productPrice" },
                    {
                      "type": "hstack", "spacing": 12,
                      "children": [
                        { "type": "button", "text": "-", "styleId": "quantityButton" },
                        { "type": "label", "text": "2", "styleId": "quantityValue" },
                        { "type": "button", "text": "+", "styleId": "quantityButton" }
                      ]
                    }
                  ]
                },
                { "type": "spacer" },
                { "type": "button", "text": "Remove", "styleId": "removeButton", "actions": { "onTap": "removeItem" } }
              ]
            }
          ]
        },
        {
          "id": "promo",
          "layout": { "type": "list", "showsDividers": false, "contentInsets": { "horizontal": 20 } },
          "children": [
            {
              "type": "vstack", "spacing": 12, "styleId": "cardBackground",
              "children": [
                { "type": "label", "text": "Promo Code", "styleId": "sectionHeader" },
                {
                  "type": "hstack", "spacing": 12,
                  "children": [
                    { "type": "textfield", "placeholder": "Enter code", "styleId": "promoField", "bind": "promoCode" },
                    { "type": "button", "text": "Apply", "styleId": "applyButton", "actions": { "onTap": "applyPromo" } }
                  ]
                }
              ]
            }
          ]
        },
        {
          "id": "summary",
          "layout": { "type": "list", "showsDividers": false, "contentInsets": { "horizontal": 20, "bottom": 20 } },
          "children": [
            {
              "type": "vstack", "spacing": 16, "styleId": "summaryCard",
              "children": [
                { "type": "label", "text": "Order Summary", "styleId": "sectionHeader" },
                {
                  "type": "hstack",
                  "children": [
                    { "type": "label", "text": "Subtotal", "styleId": "summaryLabel" },
                    { "type": "spacer" },
                    { "type": "label", "dataSourceId": "subtotal", "styleId": "summaryRow" }
                  ]
                },
                {
                  "type": "hstack",
                  "children": [
                    { "type": "label", "text": "Shipping", "styleId": "summaryLabel" },
                    { "type": "spacer" },
                    { "type": "label", "dataSourceId": "shipping", "styleId": "summaryRow" }
                  ]
                },
                {
                  "type": "hstack",
                  "children": [
                    { "type": "label", "text": "Tax", "styleId": "summaryLabel" },
                    { "type": "spacer" },
                    { "type": "label", "dataSourceId": "tax", "styleId": "summaryRow" }
                  ]
                },
                { "type": "gradient", "gradientColors": [{"color": "#E5E5EA", "location": 0}], "styleId": "divider" },
                {
                  "type": "hstack",
                  "children": [
                    { "type": "label", "text": "Total", "styleId": "totalLabel" },
                    { "type": "spacer" },
                    { "type": "label", "dataSourceId": "total", "styleId": "totalValue" }
                  ]
                },
                { "type": "button", "text": "Proceed to Checkout", "styleId": "checkoutButton", "fillWidth": true, "actions": { "onTap": "checkout" } }
              ]
            }
          ]
        }
      ]
    }]
  }
}
"""
)

/**
 * All complex examples in a single list for easy access.
 *
 * Additional complex examples are in separate files:
 * - MusicPlayerExample.kt
 * - MetMuseumExample.kt
 * - WeatherDashboardExample.kt
 * - PlantCareTrackerExample.kt
 * - AstrologyModeExample.kt
 * - DesignSystemExample.kt
 */
val complexExamples = listOf(
    componentShowcaseExample,
    dadJokesExample,
    taskManagerExample,
    shoppingCartExample,
    musicPlayerExample,
    metMuseumExample,
    weatherDashboardExample,
    plantCareTrackerExample,
    astrologyModeExample,
    designSystemExample
)
