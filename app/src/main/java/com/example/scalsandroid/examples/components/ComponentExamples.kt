package com.example.scalsandroid.examples.components

import com.example.scalsandroid.examples.Example
import com.example.scalsandroid.examples.ExampleCategory
import com.example.scalsandroid.examples.PresentationStyle

/**
 * Component examples demonstrating UI components like buttons, text, images, and controls.
 * Total: 8 examples
 */

private val buttonsExample = Example(
    id = "buttons-example",
    title = "Buttons",
    description = "Text, image, and icon buttons with various placements and styles",
    category = ExampleCategory.COMPONENTS,
    presentationStyle = PresentationStyle.BOTTOM_SHEET_LARGE,
    json = """
{
  "id": "buttons-example",
  "version": "1.0",
  "state": { "tapCount": 0 },
  "styles": {
    "sectionTitle": { "fontSize": 18, "fontWeight": "bold", "textColor": "#000000", "padding": { "top": 8, "bottom": 8 } },
    "primary": {
      "fontSize": 16, "fontWeight": "semibold",
      "backgroundColor": "#007AFF", "textColor": "#FFFFFF",
      "cornerRadius": 10, "height": 44, "padding": { "horizontal": 20 }
    },
    "secondary": {
      "fontSize": 16, "fontWeight": "medium",
      "backgroundColor": "#E5E5EA", "textColor": "#000000",
      "cornerRadius": 10, "height": 44, "padding": { "horizontal": 20 }
    },
    "destructive": {
      "fontSize": 16, "fontWeight": "semibold",
      "backgroundColor": "#FF3B30", "textColor": "#FFFFFF",
      "cornerRadius": 10, "height": 44, "padding": { "horizontal": 20 }
    },
    "pill": {
      "fontSize": 14, "fontWeight": "medium",
      "backgroundColor": "#F2F2F7", "textColor": "#000000",
      "cornerRadius": 20, "height": 36, "padding": { "horizontal": 16 }
    },
    "pillSelected": {
      "fontSize": 14, "fontWeight": "semibold",
      "backgroundColor": "#34C759", "textColor": "#FFFFFF",
      "cornerRadius": 20, "height": 36, "padding": { "horizontal": 16 }
    },
    "countLabel": { "fontSize": 14, "textColor": "#666666" },
    "iconButton": {
      "fontSize": 16, "fontWeight": "medium",
      "backgroundColor": "#F2F2F7", "textColor": "#000000",
      "cornerRadius": 10, "width": 44, "height": 44
    },
    "iconButtonPrimary": {
      "fontSize": 16, "fontWeight": "medium",
      "backgroundColor": "#007AFF", "textColor": "#FFFFFF",
      "cornerRadius": 10, "width": 44, "height": 44
    },
    "verticalButton": {
      "fontSize": 14, "fontWeight": "medium",
      "backgroundColor": "#F2F2F7", "textColor": "#000000",
      "cornerRadius": 12, "padding": { "vertical": 12, "horizontal": 16 }
    },
    "compactButton": {
      "fontSize": 13, "fontWeight": "medium",
      "backgroundColor": "#E5E5EA", "textColor": "#000000",
      "cornerRadius": 8, "height": 32, "padding": { "horizontal": 12 }
    }
  },
  "actions": {
    "increment": { "type": "setState", "path": "tapCount", "value": { "${'$'}expr": "${'$'}{tapCount} + 1" } }
  },
  "dataSources": {
    "countText": { "type": "binding", "template": "Tapped ${'$'}{tapCount} times" }
  },
  "root": {
    "backgroundColor": "#FFFFFF",
    "children": [{
      "type": "sectionLayout",
      "sectionSpacing": 20,
      "sections": [{
        "layout": {
          "type": "list",
          "showsDividers": false,
          "itemSpacing": 20,
          "contentInsets": { "top": 36, "horizontal": 28, "bottom": 20 }
        },
        "children": [
        { "type": "label", "text": "Text Only", "styleId": "sectionTitle" },
        { "type": "button", "text": "Primary Button", "styleId": "primary", "fillWidth": true, "actions": { "onTap": "increment" } },
        { "type": "button", "text": "Secondary Button", "styleId": "secondary", "fillWidth": true, "actions": { "onTap": "increment" } },
        { "type": "button", "text": "Destructive", "styleId": "destructive", "fillWidth": true, "actions": { "onTap": "increment" } },

        { "type": "label", "text": "Image Only", "styleId": "sectionTitle" },
        {
          "type": "hstack", "spacing": 12,
          "children": [
            { "type": "button", "image": { "icon": "Add" }, "styleId": "iconButtonPrimary", "actions": { "onTap": "increment" } },
            { "type": "button", "image": { "icon": "Favorite" }, "styleId": "iconButton", "actions": { "onTap": "increment" } },
            { "type": "button", "image": { "icon": "Star" }, "styleId": "iconButton", "actions": { "onTap": "increment" } },
            { "type": "button", "image": { "icon": "Delete" }, "styleId": "iconButton", "actions": { "onTap": "increment" } },
            { "type": "button", "image": { "icon": "Share" }, "styleId": "iconButton", "actions": { "onTap": "increment" } }
          ]
        },

        { "type": "label", "text": "Image Leading", "styleId": "sectionTitle" },
        {
          "type": "vstack", "spacing": 8,
          "children": [
            { "type": "button", "text": "Add Item", "image": { "icon": "AddCircleFilled" }, "imagePlacement": "leading", "styleId": "primary", "fillWidth": true, "actions": { "onTap": "increment" } },
            { "type": "button", "text": "Edit Document", "image": { "icon": "Edit" }, "imagePlacement": "leading", "styleId": "secondary", "fillWidth": true, "actions": { "onTap": "increment" } },
            { "type": "button", "text": "Download", "image": { "icon": "Download" }, "imagePlacement": "leading", "styleId": "secondary", "fillWidth": true, "actions": { "onTap": "increment" } }
          ]
        },

        { "type": "label", "text": "Image Trailing", "styleId": "sectionTitle" },
        {
          "type": "vstack", "spacing": 8,
          "children": [
            { "type": "button", "text": "Open Settings", "image": { "icon": "Settings" }, "imagePlacement": "trailing", "styleId": "secondary", "fillWidth": true, "actions": { "onTap": "increment" } },
            { "type": "button", "text": "Continue", "image": { "icon": "ChevronRight" }, "imagePlacement": "trailing", "styleId": "primary", "fillWidth": true, "actions": { "onTap": "increment" } },
            { "type": "button", "text": "External Link", "image": { "icon": "OpenExternal" }, "imagePlacement": "trailing", "styleId": "secondary", "fillWidth": true, "actions": { "onTap": "increment" } }
          ]
        },

        { "type": "label", "text": "Image Top", "styleId": "sectionTitle" },
        {
          "type": "hstack", "spacing": 12,
          "children": [
            { "type": "button", "text": "Home", "image": { "icon": "HomeFilled" }, "imagePlacement": "top", "styleId": "verticalButton", "actions": { "onTap": "increment" } },
            { "type": "button", "text": "Search", "image": { "icon": "Search" }, "imagePlacement": "top", "styleId": "verticalButton", "actions": { "onTap": "increment" } },
            { "type": "button", "text": "Profile", "image": { "icon": "PersonFilled" }, "imagePlacement": "top", "styleId": "verticalButton", "actions": { "onTap": "increment" } }
          ]
        },

        { "type": "label", "text": "Image Bottom", "styleId": "sectionTitle" },
        {
          "type": "hstack", "spacing": 12,
          "children": [
            { "type": "button", "text": "Upload", "image": { "icon": "Upload" }, "imagePlacement": "bottom", "styleId": "verticalButton", "actions": { "onTap": "increment" } },
            { "type": "button", "text": "Share", "image": { "icon": "Share" }, "imagePlacement": "bottom", "styleId": "verticalButton", "actions": { "onTap": "increment" } },
            { "type": "button", "text": "Archive", "image": { "icon": "Archive" }, "imagePlacement": "bottom", "styleId": "verticalButton", "actions": { "onTap": "increment" } }
          ]
        },

        { "type": "label", "text": "Compact with Images", "styleId": "sectionTitle" },
        {
          "type": "hstack", "spacing": 8,
          "children": [
            { "type": "button", "text": "Like", "image": { "icon": "ThumbUp" }, "imagePlacement": "leading", "styleId": "compactButton", "actions": { "onTap": "increment" } },
            { "type": "button", "text": "Save", "image": { "icon": "Bookmark" }, "imagePlacement": "leading", "styleId": "compactButton", "actions": { "onTap": "increment" } },
            { "type": "button", "text": "More", "image": { "icon": "MoreVert" }, "imagePlacement": "trailing", "styleId": "compactButton", "actions": { "onTap": "increment" } }
          ]
        },

        { "type": "label", "text": "Mixed Styles", "styleId": "sectionTitle" },
        {
          "type": "vstack", "spacing": 8,
          "children": [
            { "type": "button", "text": "Delete All", "image": { "icon": "DeleteFilled" }, "imagePlacement": "leading", "styleId": "destructive", "fillWidth": true, "actions": { "onTap": "increment" } },
            { "type": "button", "text": "Refresh", "image": { "icon": "Refresh" }, "imagePlacement": "trailing", "styleId": "pill", "actions": { "onTap": "increment" } },
            { "type": "button", "text": "Selected", "image": { "icon": "Check" }, "imagePlacement": "leading", "styleId": "pillSelected", "actions": { "onTap": "increment" } }
          ]
        },

        { "type": "label", "dataSourceId": "countText", "styleId": "countLabel" }
      ]}]
    }]
  }
}
    """.trimIndent()
)

private val labelsExample = Example(
    id = "labels-example",
    title = "Labels",
    description = "Text display with various sizes, weights, and alignment options",
    category = ExampleCategory.COMPONENTS,
    presentationStyle = PresentationStyle.BOTTOM_SHEET_MEDIUM,
    json = """
{
  "id": "labels-example",
  "version": "1.0",
  "styles": {
    "title": { "fontSize": 24, "fontWeight": "bold", "textColor": "#000000" },
    "body": { "fontSize": 16, "fontWeight": "regular", "textColor": "#333333" },
    "caption": { "fontSize": 12, "fontWeight": "light", "textColor": "#888888" },
    "centered": { "fontSize": 16, "textAlignment": "center", "textColor": "#007AFF" },
    "multiline": { "fontSize": 14, "textColor": "#333333", "numberOfLines": 3 }
  },
  "root": {
    "backgroundColor": "#FFFFFF",
    "edgeInsets": { "top": 36, "leading": 28, "trailing": 28 },
    "children": [{
      "type": "vstack",
      "spacing": 16,
      "alignment": "leading",
      "children": [
        { "type": "label", "text": "Title Label", "styleId": "title" },
        { "type": "label", "text": "Body text with regular weight and dark gray color.", "styleId": "body" },
        { "type": "label", "text": "Caption - smaller and lighter", "styleId": "caption" },
        { "type": "hstack", "children": [{ "type": "spacer" }, { "type": "label", "text": "Centered Text", "styleId": "centered" }, { "type": "spacer" }] },
        { "type": "label", "text": "This is a multiline label that can wrap to multiple lines when the text is too long to fit on a single line.", "styleId": "multiline" },
        { "type": "spacer" }
      ]
    }]
  }
}
    """.trimIndent()
)

private val textFieldsExample = Example(
    id = "textfields-example",
    title = "Text Fields",
    description = "User input fields with placeholders and state binding",
    category = ExampleCategory.COMPONENTS,
    presentationStyle = PresentationStyle.BOTTOM_SHEET_MEDIUM,
    json = """
{
  "id": "textfields-example",
  "version": "1.0",
  "state": { "name": "", "email": "", "bio": "" },
  "styles": {
    "label": { "fontSize": 14, "fontWeight": "medium", "textColor": "#333333" },
    "field": {
      "fontSize": 16, "textColor": "#000000",
      "backgroundColor": "#F2F2F7", "cornerRadius": 8,
      "padding": { "horizontal": 12, "vertical": 12 }
    },
    "preview": { "fontSize": 13, "textColor": "#888888" }
  },
  "dataSources": {
    "namePreview": { "type": "binding", "template": "Name: ${'$'}{name}" },
    "emailPreview": { "type": "binding", "template": "Email: ${'$'}{email}" }
  },
  "root": {
    "backgroundColor": "#FFFFFF",
    "edgeInsets": { "top": 36, "leading": 28, "trailing": 28 },
    "children": [{
      "type": "vstack",
      "spacing": 16,
      "alignment": "leading",
      "children": [
        { "type": "label", "text": "Name", "styleId": "label" },
        { "type": "textfield", "placeholder": "Enter your name", "styleId": "field", "bind": "name" },
        { "type": "label", "text": "Email", "styleId": "label" },
        { "type": "textfield", "placeholder": "Enter your email", "styleId": "field", "bind": "email" },
        { "type": "label", "dataSourceId": "namePreview", "styleId": "preview" },
        { "type": "label", "dataSourceId": "emailPreview", "styleId": "preview" },
        { "type": "spacer" }
      ]
    }]
  }
}
    """.trimIndent()
)

private val togglesExample = Example(
    id = "toggles-example",
    title = "Toggles",
    description = "Boolean switches with custom tint colors",
    category = ExampleCategory.COMPONENTS,
    presentationStyle = PresentationStyle.BOTTOM_SHEET_MEDIUM,
    json = """
{
  "id": "toggles-example",
  "version": "1.0",
  "state": { "notifications": true, "darkMode": false, "autoSave": true },
  "styles": {
    "rowLabel": { "fontSize": 16, "textColor": "#000000" },
    "greenTint": { "tintColor": "#34C759" },
    "purpleTint": { "tintColor": "#AF52DE" },
    "orangeTint": { "tintColor": "#FF9500" }
  },
  "root": {
    "backgroundColor": "#FFFFFF",
    "edgeInsets": { "top": 36, "leading": 28, "trailing": 28 },
    "children": [{
      "type": "vstack",
      "spacing": 24,
      "children": [
        {
          "type": "hstack",
          "children": [
            { "type": "label", "text": "Notifications", "styleId": "rowLabel" },
            { "type": "spacer" },
            { "type": "toggle", "bind": "notifications", "styleId": "greenTint" }
          ]
        },
        {
          "type": "hstack",
          "children": [
            { "type": "label", "text": "Dark Mode", "styleId": "rowLabel" },
            { "type": "spacer" },
            { "type": "toggle", "bind": "darkMode", "styleId": "purpleTint" }
          ]
        },
        {
          "type": "hstack",
          "children": [
            { "type": "label", "text": "Auto Save", "styleId": "rowLabel" },
            { "type": "spacer" },
            { "type": "toggle", "bind": "autoSave", "styleId": "orangeTint" }
          ]
        },
        { "type": "spacer" }
      ]
    }]
  }
}
    """.trimIndent()
)

private val slidersExample = Example(
    id = "sliders-example",
    title = "Sliders",
    description = "Range selection controls with min/max values and tint colors",
    category = ExampleCategory.COMPONENTS,
    presentationStyle = PresentationStyle.BOTTOM_SHEET_MEDIUM,
    json = """
{
  "id": "sliders-example",
  "version": "1.0",
  "state": { "volume": 0.5, "brightness": 0.75, "temperature": 72 },
  "styles": {
    "label": { "fontSize": 16, "textColor": "#000000" },
    "value": { "fontSize": 14, "fontWeight": "medium", "textColor": "#007AFF" },
    "blueTint": { "tintColor": "#007AFF" },
    "orangeTint": { "tintColor": "#FF9500" },
    "redTint": { "tintColor": "#FF3B30" }
  },
  "dataSources": {
    "volumeText": { "type": "binding", "template": "${'$'}{volume}" },
    "brightnessText": { "type": "binding", "template": "${'$'}{brightness}" },
    "tempText": { "type": "binding", "template": "${'$'}{temperature}F" }
  },
  "root": {
    "backgroundColor": "#FFFFFF",
    "edgeInsets": { "top": 36, "leading": 28, "trailing": 28 },
    "children": [{
      "type": "vstack",
      "spacing": 24,
      "children": [
        {
          "type": "vstack", "spacing": 8, "alignment": "leading",
          "children": [
            {
              "type": "hstack",
              "children": [
                { "type": "label", "text": "Volume", "styleId": "label" },
                { "type": "spacer" },
                { "type": "label", "dataSourceId": "volumeText", "styleId": "value" }
              ]
            },
            { "type": "slider", "bind": "volume", "styleId": "blueTint" }
          ]
        },
        {
          "type": "vstack", "spacing": 8, "alignment": "leading",
          "children": [
            {
              "type": "hstack",
              "children": [
                { "type": "label", "text": "Brightness", "styleId": "label" },
                { "type": "spacer" },
                { "type": "label", "dataSourceId": "brightnessText", "styleId": "value" }
              ]
            },
            { "type": "slider", "bind": "brightness", "styleId": "orangeTint" }
          ]
        },
        {
          "type": "vstack", "spacing": 8, "alignment": "leading",
          "children": [
            {
              "type": "hstack",
              "children": [
                { "type": "label", "text": "Temperature", "styleId": "label" },
                { "type": "spacer" },
                { "type": "label", "dataSourceId": "tempText", "styleId": "value" }
              ]
            },
            { "type": "slider", "bind": "temperature", "minValue": 60, "maxValue": 90, "styleId": "redTint" }
          ]
        },
        { "type": "spacer" }
      ]
    }]
  }
}
    """.trimIndent()
)

private val imagesExample = Example(
    id = "images-example",
    title = "Images",
    description = "SF Symbols, asset catalog, remote URLs, and activity indicators",
    category = ExampleCategory.COMPONENTS,
    presentationStyle = PresentationStyle.BOTTOM_SHEET_LARGE,
    json = """
{
  "id": "images-example",
  "version": "1.0",
  "state": {
    "dynamicImageUrl": "https://images.unsplash.com/photo-1745826092440-0d6542010bcc?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
    "currentImageIndex": 0,
    "imageUrls": [
      "https://images.unsplash.com/photo-1745826092440-0d6542010bcc?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
      "https://plus.unsplash.com/premium_photo-1717972598410-6a47fc079a16?q=80&w=2670&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
      "https://images.unsplash.com/photo-1519638617638-c589a8ba5b76?q=80&w=2662&auto=format&fit=crop&ixlib=rb-4.1.0&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
    ]
  },
  "actions": {
    "cycleImage": {
      "type": "sequence",
      "steps": [
        {
          "type": "setState",
          "path": "currentImageIndex",
          "value": { "${'$'}expr": "(currentImageIndex + 1) % 3" }
        },
        {
          "type": "setState",
          "path": "dynamicImageUrl",
          "value": { "${'$'}expr": "imageUrls[currentImageIndex]" }
        }
      ]
    }
  },
  "dataSources": {
    "imageCounter": {
      "type": "binding",
      "template": "Image ${'$'}{(currentImageIndex + 1)}/3 (tap to cycle)"
    }
  },
  "styles": {
    "sectionTitle": {
      "fontSize": 16,
      "fontWeight": "semibold",
      "textColor": "#000000",
      "textAlignment": "center"
    },
    "iconSmall": { "width": 40, "height": 40 },
    "iconRed": { "width": 40, "height": 40, "tintColor": "#FF3B30" },
    "iconBlue": { "width": 40, "height": 40, "tintColor": "#007AFF" },
    "iconGreen": { "width": 40, "height": 40, "tintColor": "#34C759" },
    "assetImage": { "width": 160, "height": 200, "cornerRadius": 12 },
    "urlImage": { "width": 200, "height": 150, "cornerRadius": 12 },
    "dynamicImage": { "width": 240, "height": 180, "cornerRadius": 12 },
    "spinner": { "width": 40, "height": 40 },
    "caption": { "fontSize": 11, "textColor": "#888888", "textAlignment": "center" },
    "changeImageButton": {
      "fontSize": 14,
      "fontWeight": "semibold",
      "backgroundColor": "#007AFF",
      "textColor": "#FFFFFF",
      "height": 40,
      "cornerRadius": 8
    }
  },
  "root": {
    "backgroundColor": "#F2F2F7",
    "edgeInsets": { "top": 52 },
    "children": [{
      "type": "sectionLayout",
      "sectionSpacing": 32,
      "sections": [
        {
          "id": "sf-symbols",
          "layout": { "type": "list", "showsDividers": false, "contentInsets": { "horizontal": 20 } },
          "header": { "type": "label", "text": "SF Symbols", "styleId": "sectionTitle", "padding": { "bottom": 12 } },
          "children": [
            {
              "type": "hstack",
              "spacing": 20,
              "alignment": "center",
              "children": [
                {
                  "type": "vstack", "spacing": 4, "alignment": "center",
                  "children": [
                    { "type": "image", "image": { "icon": "StarFilled" }, "styleId": "iconSmall" },
                    { "type": "label", "text": "Default", "styleId": "caption" }
                  ]
                },
                {
                  "type": "vstack", "spacing": 4, "alignment": "center",
                  "children": [
                    { "type": "image", "image": { "icon": "FavoriteFilled" }, "styleId": "iconRed" },
                    { "type": "label", "text": "Red", "styleId": "caption" }
                  ]
                },
                {
                  "type": "vstack", "spacing": 4, "alignment": "center",
                  "children": [
                    { "type": "image", "image": { "icon": "NotificationsFilled" }, "styleId": "iconBlue" },
                    { "type": "label", "text": "Blue", "styleId": "caption" }
                  ]
                },
                {
                  "type": "vstack", "spacing": 4, "alignment": "center",
                  "children": [
                    { "type": "image", "image": { "icon": "CheckCircleFilled" }, "styleId": "iconGreen" },
                    { "type": "label", "text": "Green", "styleId": "caption" }
                  ]
                }
              ]
            }
          ]
        },
        {
          "id": "asset-catalog",
          "layout": { "type": "list", "showsDividers": false, "contentInsets": { "horizontal": 20 } },
          "header": { "type": "label", "text": "Asset Catalog", "styleId": "sectionTitle", "padding": { "bottom": 12 } },
          "children": [
            {
              "type": "vstack",
              "spacing": 8,
              "alignment": "center",
              "children": [
                { "type": "image", "image": { "asset": "womanAligator" }, "styleId": "assetImage" },
                { "type": "label", "text": "Local asset image", "styleId": "caption" }
              ]
            }
          ]
        },
        {
          "id": "remote-url",
          "layout": { "type": "list", "showsDividers": false, "contentInsets": { "horizontal": 20 } },
          "header": { "type": "label", "text": "Remote URL", "styleId": "sectionTitle", "padding": { "bottom": 12 } },
          "children": [
            {
              "type": "vstack",
              "spacing": 8,
              "alignment": "center",
              "children": [
                { "type": "image", "image": { "url": "https://images.pexels.com/photos/1658967/pexels-photo-1658967.jpeg?w=400" }, "styleId": "urlImage" },
                { "type": "label", "text": "URL-loaded image", "styleId": "caption" }
              ]
            }
          ]
        },
        {
          "id": "dynamic-url",
          "layout": { "type": "list", "showsDividers": false, "contentInsets": { "horizontal": 20 } },
          "header": { "type": "label", "text": "Dynamic URL (State Binding)", "styleId": "sectionTitle", "padding": { "bottom": 12 } },
          "children": [
            {
              "type": "vstack",
              "spacing": 12,
              "alignment": "center",
              "children": [
                {
                  "type": "image",
                  "image": {
                    "url": "${'$'}{dynamicImageUrl}",
                    "loading": { "activityIndicator": true }
                  },
                  "styleId": "dynamicImage"
                },
                {
                  "type": "label",
                  "dataSourceId": "imageCounter",
                  "styleId": "caption"
                },
                {
                  "type": "button",
                  "text": "Next Image (Cycle)",
                  "styleId": "changeImageButton",
                  "fillWidth": true,
                  "actions": { "onTap": "cycleImage" }
                }
              ]
            }
          ]
        },
        {
          "id": "activity-indicator",
          "layout": { "type": "list", "showsDividers": false, "contentInsets": { "horizontal": 20 } },
          "header": { "type": "label", "text": "Activity Indicator", "styleId": "sectionTitle", "padding": { "bottom": 12 } },
          "children": [
            {
              "type": "vstack",
              "spacing": 8,
              "alignment": "center",
              "children": [
                { "type": "image", "image": { "activityIndicator": true }, "styleId": "spinner" },
                { "type": "label", "text": "Loading spinner", "styleId": "caption" }
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

private val gradientsExample = Example(
    id = "gradients-example",
    title = "Gradients",
    description = "Linear gradients with multiple color stops and directions",
    category = ExampleCategory.COMPONENTS,
    presentationStyle = PresentationStyle.BOTTOM_SHEET_MEDIUM,
    json = """
{
  "id": "gradients-example",
  "version": "1.0",
  "styles": {
    "gradientBox": { "width": 280, "height": 80, "cornerRadius": 12 },
    "gradientLabel": { "fontSize": 14, "fontWeight": "semibold", "textColor": "#FFFFFF" },
    "caption": { "fontSize": 12, "textColor": "#888888" }
  },
  "root": {
    "backgroundColor": "#FFFFFF",
    "edgeInsets": { "top": 36, "leading": 28, "trailing": 28 },
    "children": [{
      "type": "vstack",
      "spacing": 20,
      "alignment": "center",
      "children": [
        {
          "type": "zstack",
          "children": [
            {
              "type": "gradient",
              "gradientColors": [
                { "color": "#FF6B6B", "location": 0.0 },
                { "color": "#4ECDC4", "location": 1.0 }
              ],
              "gradientStart": "leading", "gradientEnd": "trailing",
              "styleId": "gradientBox"
            },
            { "type": "label", "text": "Horizontal Gradient", "styleId": "gradientLabel" }
          ]
        },
        {
          "type": "zstack",
          "children": [
            {
              "type": "gradient",
              "gradientColors": [
                { "color": "#667eea", "location": 0.0 },
                { "color": "#764ba2", "location": 1.0 }
              ],
              "gradientStart": "top", "gradientEnd": "bottom",
              "styleId": "gradientBox"
            },
            { "type": "label", "text": "Vertical Gradient", "styleId": "gradientLabel" }
          ]
        },
        {
          "type": "zstack",
          "children": [
            {
              "type": "gradient",
              "gradientColors": [
                { "color": "#f093fb", "location": 0.0 },
                { "color": "#f5576c", "location": 0.5 },
                { "color": "#4facfe", "location": 1.0 }
              ],
              "gradientStart": "topLeading", "gradientEnd": "bottomTrailing",
              "styleId": "gradientBox"
            },
            { "type": "label", "text": "Multi-stop Diagonal", "styleId": "gradientLabel" }
          ]
        }
      ]
    }]
  }
}
    """.trimIndent()
)

private val shapesExample = Example(
    id = "shapes-example",
    title = "Shapes",
    description = "Rectangle, circle, capsule, ellipse with fills and strokes",
    category = ExampleCategory.COMPONENTS,
    presentationStyle = PresentationStyle.BOTTOM_SHEET_LARGE,
    json = """
{
  "id": "shapes-example",
  "version": "1.0",
  "styles": {
    "showcaseTitle": { "fontSize": 28, "fontWeight": "bold", "textColor": "#000000", "padding": { "bottom": 4 } },
    "sectionTitle": { "fontSize": 16, "fontWeight": "semibold", "textColor": "#000000", "padding": { "bottom": 12 } },
    "shapeLabel": { "fontSize": 12, "textColor": "#666666" },
    "filledRed": { "width": 60, "height": 60, "backgroundColor": "#FF6B6B" },
    "filledBlue": { "width": 60, "height": 60, "backgroundColor": "#4ECDC4" },
    "filledGreen": { "width": 60, "height": 60, "backgroundColor": "#95E1D3" },
    "filledOrange": { "width": 80, "height": 40, "backgroundColor": "#FFA07A" },
    "filledPurple": { "width": 70, "height": 50, "backgroundColor": "#DDA0DD" },
    "strokedRed": { "width": 60, "height": 60, "borderColor": "#FF6B6B", "borderWidth": 3 },
    "strokedBlue": { "width": 60, "height": 60, "borderColor": "#4ECDC4", "borderWidth": 3 },
    "strokedGreen": { "width": 60, "height": 60, "borderColor": "#95E1D3", "borderWidth": 3 },
    "strokedOrange": { "width": 80, "height": 40, "borderColor": "#FFA07A", "borderWidth": 3 },
    "strokedPurple": { "width": 70, "height": 50, "borderColor": "#DDA0DD", "borderWidth": 3 },
    "cardBackground": { "backgroundColor": "#F2F2F7", "cornerRadius": 16, "padding": { "all": 20 } },
    "cardTitle": { "fontSize": 18, "fontWeight": "bold", "textColor": "#000000" },
    "cardBody": { "fontSize": 14, "textColor": "#666666" },
    "layer1": { "width": 120, "height": 120, "backgroundColor": "#C8C8FF80" },
    "layer2": { "width": 80, "height": 80, "backgroundColor": "#FFC8C8B3" },
    "overlayText": { "fontSize": 16, "fontWeight": "bold", "textColor": "#FFFFFF" }
  },
  "root": {
    "backgroundColor": "#FFFFFF",
    "edgeInsets": { "top": 56 },
    "children": [{
      "type": "sectionLayout",
      "sections": [{
        "layout": {
          "type": "list",
          "showsDividers": false,
          "itemSpacing": 20,
          "contentInsets": { "horizontal": 20, "top": 12, "bottom": 36 }
        },
        "header": { "type": "label", "text": "Shapes Showcase", "styleId": "showcaseTitle" },
        "children": [
          { "type": "label", "text": "Filled Shapes", "fontSize": 14, "fontWeight": "medium", "textColor": "#333333" },
        {
          "type": "hstack",
          "spacing": 12,
          "children": [
            {
              "type": "vstack",
              "spacing": 4,
              "children": [
                { "type": "shape", "shapeType": "rectangle", "styleId": "filledRed" },
                { "type": "label", "text": "Rectangle", "styleId": "shapeLabel" }
              ]
            },
            {
              "type": "vstack",
              "spacing": 4,
              "children": [
                { "type": "shape", "shapeType": "circle", "styleId": "filledBlue" },
                { "type": "label", "text": "Circle", "styleId": "shapeLabel" }
              ]
            },
            {
              "type": "vstack",
              "spacing": 4,
              "children": [
                { "type": "shape", "shapeType": "roundedRectangle", "cornerRadius": 12, "styleId": "filledGreen" },
                { "type": "label", "text": "Rounded", "styleId": "shapeLabel" }
              ]
            }
          ]
        },
        {
          "type": "hstack",
          "spacing": 12,
          "children": [
            {
              "type": "vstack",
              "spacing": 4,
              "children": [
                { "type": "shape", "shapeType": "capsule", "styleId": "filledOrange" },
                { "type": "label", "text": "Capsule", "styleId": "shapeLabel" }
              ]
            },
            {
              "type": "vstack",
              "spacing": 4,
              "children": [
                { "type": "shape", "shapeType": "ellipse", "styleId": "filledPurple" },
                { "type": "label", "text": "Ellipse", "styleId": "shapeLabel" }
              ]
            }
          ]
        },

        { "type": "divider", "padding": { "vertical": 8 } },

        { "type": "label", "text": "Stroked Shapes", "fontSize": 14, "fontWeight": "medium", "textColor": "#333333" },
        {
          "type": "hstack",
          "spacing": 12,
          "children": [
            {
              "type": "vstack",
              "spacing": 4,
              "children": [
                { "type": "shape", "shapeType": "rectangle", "styleId": "strokedRed" },
                { "type": "label", "text": "Rectangle", "styleId": "shapeLabel" }
              ]
            },
            {
              "type": "vstack",
              "spacing": 4,
              "children": [
                { "type": "shape", "shapeType": "circle", "styleId": "strokedBlue" },
                { "type": "label", "text": "Circle", "styleId": "shapeLabel" }
              ]
            },
            {
              "type": "vstack",
              "spacing": 4,
              "children": [
                { "type": "shape", "shapeType": "roundedRectangle", "cornerRadius": 12, "styleId": "strokedGreen" },
                { "type": "label", "text": "Rounded", "styleId": "shapeLabel" }
              ]
            }
          ]
        },

        { "type": "divider", "padding": { "vertical": 8 } },

        { "type": "label", "text": "Container Backgrounds", "fontSize": 14, "fontWeight": "medium", "textColor": "#333333" },
        {
          "type": "vstack",
          "styleId": "cardBackground",
          "padding": { "all": 16 },
          "spacing": 8,
          "children": [
            { "type": "label", "text": "Card with Background", "styleId": "cardTitle" },
            { "type": "label", "text": "VStack now supports backgroundColor, cornerRadius, and borders!", "styleId": "cardBody" }
          ]
        },

        { "type": "divider", "padding": { "vertical": 8 } },

        { "type": "label", "text": "Layered Design", "fontSize": 14, "fontWeight": "medium", "textColor": "#333333" },
        {
          "type": "zstack",
          "children": [
            { "type": "shape", "shapeType": "roundedRectangle", "cornerRadius": 20, "styleId": "layer1" },
            { "type": "shape", "shapeType": "circle", "styleId": "layer2" },
            { "type": "label", "text": "Layered", "styleId": "overlayText" }
          ]
        }
        ]
      }]
    }]
  }
}
    """.trimIndent()
)

/**
 * All component examples
 */
val componentExamples: List<Example> = listOf(
    buttonsExample,
    labelsExample,
    textFieldsExample,
    togglesExample,
    slidersExample,
    imagesExample,
    gradientsExample,
    shapesExample
)
