package com.example.scalsandroid.examples.custom

import com.example.scalsandroid.examples.Example
import com.example.scalsandroid.examples.ExampleCategory
import com.example.scalsandroid.examples.PresentationStyle

/**
 * Custom examples demonstrating examples with custom UI components.
 * Total: 3 examples
 *
 * Ported from iOS ScalsRenderer/Examples/CustomComponents/
 */

private val doubleDateExample = Example(
    id = "double-date-onboarding",
    title = "Double Date Onboarding",
    description = "Get early access to Double Date - onboarding sheet with gradient and hero image",
    category = ExampleCategory.CUSTOM,
    presentationStyle = PresentationStyle.FULL_SCREEN,
    json = """
    {
      "id": "double-date-onboarding",
      "version": "1.0",
      "root": {
        "backgroundColor": "#000000",
        "edgeInsets": {
          "top": { "positioning": "absolute", "value": 0 },
          "bottom": { "positioning": "absolute", "value": 0 }
        },
        "children": [
          {
            "type": "zstack",
            "alignment": {
              "horizontal": "center",
              "vertical": "top"
            },
            "children": [
              {
                "type": "gradient",
                "gradientColors": [
                  { "color": "#FF6B6B", "location": 0.0 },
                  { "color": "#FF5555", "location": 0.3 },
                  { "color": "#2D0A0A", "location": 0.7 },
                  { "color": "#000000", "location": 1.0 }
                ],
                "gradientStart": "top",
                "gradientEnd": "bottom",
                "styleId": "gradientBackground"
              },
              {
                "type": "vstack",
                "spacing": 0,
                "children": [
                  {
                    "type": "hstack",
                    "padding": { "leading": 24, "trailing": 24, "top": 60 },
                    "children": [
                      {
                        "type": "button",
                        "image": { "icon": "Close" },
                        "buttonShape": "circle",
                        "styleId": "closeButton",
                        "actions": { "onTap": "dismiss" }
                      },
                      { "type": "spacer" }
                    ]
                  },
                  { "type": "spacer" },
                  {
                    "type": "image",
                    "image": { "asset": "DoubleDateHero" },
                    "styleId": "cardsImage"
                  },
                  {
                    "type": "vstack",
                    "spacing": 16,
                    "alignment": "center",
                    "padding": { "horizontal": 32, "top": 40 },
                    "children": [
                      {
                        "type": "label",
                        "text": "Get early access to Double Date!",
                        "styleId": "titleStyle"
                      },
                      {
                        "type": "label",
                        "text": "Invite friends to pair up on Double Date, and be the first to try it when it launches.",
                        "styleId": "subtitleStyle"
                      }
                    ]
                  },
                  { "type": "spacer" },
                  {
                    "type": "vstack",
                    "spacing": 16,
                    "alignment": "center",
                    "padding": { "horizontal": 32, "bottom": 60 },
                    "children": [
                      {
                        "type": "hstack",
                        "spacing": 8,
                        "alignment": "center",
                        "children": [
                          {
                            "type": "image",
                            "image": { "icon": "Schedule" },
                            "styleId": "clockIcon"
                          },
                          {
                            "type": "label",
                            "text": "Double Date launches soon.",
                            "styleId": "launchTextStyle"
                          }
                        ]
                      },
                      {
                        "type": "button",
                        "text": "Invite friends",
                        "image": { "icon": "People" },
                        "imagePlacement": "leading",
                        "imageSpacing": 8,
                        "styleId": "primaryButton",
                        "fillWidth": true,
                        "actions": { "onTap": "inviteFriends" }
                      },
                      {
                        "type": "button",
                        "text": "Maybe later",
                        "image": { "icon": "ArrowForward" },
                        "imagePlacement": "trailing",
                        "imageSpacing": 8,
                        "styleId": "secondaryButton",
                        "fillWidth": true,
                        "actions": { "onTap": "dismiss" }
                      }
                    ]
                  }
                ]
              }
            ]
          }
        ]
      },
      "styles": {
        "gradientBackground": {
          "height": 400
        },
        "closeButton": {
          "fontSize": 18,
          "fontWeight": "semibold",
          "textColor": "#FFFFFF",
          "backgroundColor": "rgba(255, 255, 255, 0.15)",
          "width": 44,
          "height": 44
        },
        "cardsImage": {
          "width": 350,
          "height": 350,
          "cornerRadius": 24
        },
        "titleStyle": {
          "fontSize": 32,
          "fontWeight": "bold",
          "textColor": "#FFFFFF",
          "textAlignment": "center"
        },
        "subtitleStyle": {
          "fontSize": 17,
          "fontWeight": "regular",
          "textColor": "#FFFFFF",
          "textAlignment": "center"
        },
        "clockIcon": {
          "width": 20,
          "height": 20,
          "tintColor": "#FFFFFF"
        },
        "launchTextStyle": {
          "fontSize": 15,
          "fontWeight": "medium",
          "textColor": "#FFFFFF"
        },
        "primaryButton": {
          "fontSize": 17,
          "fontWeight": "semibold",
          "textColor": "#000000",
          "backgroundColor": "#FEF7FF",
          "cornerRadius": 28,
          "height": 56
        },
        "secondaryButton": {
          "fontSize": 17,
          "fontWeight": "medium",
          "textColor": "#FFFFFF",
          "backgroundColor": "transparent",
          "height": 56
        }
      },
      "actions": {
        "dismiss": { "type": "dismiss" },
        "inviteFriends": { "type": "inviteFriends" }
      }
    }
    """
)

private val feedbackSurveyExample = Example(
    id = "feedbackSurvey",
    title = "Feedback Survey",
    description = "Survey asking why users don't want their photo enhanced - uses custom CloseButtonComponent",
    category = ExampleCategory.CUSTOM,
    presentationStyle = PresentationStyle.BOTTOM_SHEET_LARGE,
    json = """
    {
      "id": "feedbackSurvey",
      "version": "1.0",
      "state": {
        "selectedOption": ""
      },
      "root": {
        "backgroundColor": "#FEF7FF",
        "cornerRadius": 24,
        "children": [
          {
            "type": "vstack",
            "spacing": 0,
            "children": [
              {
                "type": "hstack",
                "padding": { "horizontal": 16, "top": 16, "bottom": 0 },
                "children": [
                  {
                    "type": "closeButton",
                    "actions": { "onTap": "dismiss" }
                  },
                  { "type": "spacer" },
                  {
                    "type": "label",
                    "text": "Help us improve",
                    "styleId": "headerTitle"
                  },
                  { "type": "spacer" },
                  {
                    "type": "vstack",
                    "styleId": "placeholderSpacer",
                    "children": []
                  }
                ]
              },
              {
                "type": "vstack",
                "spacing": 24,
                "padding": { "horizontal": 24, "top": 24, "bottom": 32 },
                "alignment": "leading",
                "children": [
                  {
                    "type": "label",
                    "text": "Why don't you want your photo enhanced?",
                    "styleId": "questionTitle"
                  },
                  {
                    "type": "vstack",
                    "spacing": 0,
                    "styleId": "optionsContainer",
                    "children": [
                      {
                        "type": "hstack",
                        "padding": { "vertical": 16, "horizontal": 16 },
                        "children": [
                          { "type": "label", "text": "It didn't look like me", "styleId": "optionText" },
                          { "type": "spacer" },
                          {
                            "type": "button",
                            "text": "",
                            "styles": { "normal": "radioButtonNormal", "selected": "radioButtonSelected" },
                            "isSelectedBinding": "${'$'}{selectedOption == 'didnt_look_like_me'}",
                            "actions": { "onTap": { "type": "setState", "path": "selectedOption", "value": "didnt_look_like_me" } }
                          }
                        ]
                      },
                      { "type": "divider", "styleId": "rowDivider" },
                      {
                        "type": "hstack",
                        "padding": { "vertical": 16, "horizontal": 16 },
                        "children": [
                          { "type": "label", "text": "It looked overly edited", "styleId": "optionText" },
                          { "type": "spacer" },
                          {
                            "type": "button",
                            "text": "",
                            "styles": { "normal": "radioButtonNormal", "selected": "radioButtonSelected" },
                            "isSelectedBinding": "${'$'}{selectedOption == 'overly_edited'}",
                            "actions": { "onTap": { "type": "setState", "path": "selectedOption", "value": "overly_edited" } }
                          }
                        ]
                      },
                      { "type": "divider", "styleId": "rowDivider" },
                      {
                        "type": "hstack",
                        "padding": { "vertical": 16, "horizontal": 16 },
                        "children": [
                          { "type": "label", "text": "I didn't notice a difference", "styleId": "optionText" },
                          { "type": "spacer" },
                          {
                            "type": "button",
                            "text": "",
                            "styles": { "normal": "radioButtonNormal", "selected": "radioButtonSelected" },
                            "isSelectedBinding": "${'$'}{selectedOption == 'no_difference'}",
                            "actions": { "onTap": { "type": "setState", "path": "selectedOption", "value": "no_difference" } }
                          }
                        ]
                      },
                      { "type": "divider", "styleId": "rowDivider" },
                      {
                        "type": "hstack",
                        "padding": { "vertical": 16, "horizontal": 16 },
                        "children": [
                          { "type": "label", "text": "I prefer my original photo", "styleId": "optionText" },
                          { "type": "spacer" },
                          {
                            "type": "button",
                            "text": "",
                            "styles": { "normal": "radioButtonNormal", "selected": "radioButtonSelected" },
                            "isSelectedBinding": "${'$'}{selectedOption == 'prefer_original'}",
                            "actions": { "onTap": { "type": "setState", "path": "selectedOption", "value": "prefer_original" } }
                          }
                        ]
                      },
                      { "type": "divider", "styleId": "rowDivider" },
                      {
                        "type": "hstack",
                        "padding": { "vertical": 16, "horizontal": 16 },
                        "children": [
                          { "type": "label", "text": "I don't like editing my photos", "styleId": "optionText" },
                          { "type": "spacer" },
                          {
                            "type": "button",
                            "text": "",
                            "styles": { "normal": "radioButtonNormal", "selected": "radioButtonSelected" },
                            "isSelectedBinding": "${'$'}{selectedOption == 'dont_like_editing'}",
                            "actions": { "onTap": { "type": "setState", "path": "selectedOption", "value": "dont_like_editing" } }
                          }
                        ]
                      },
                      { "type": "divider", "styleId": "rowDivider" },
                      {
                        "type": "hstack",
                        "padding": { "vertical": 16, "horizontal": 16 },
                        "children": [
                          { "type": "label", "text": "Other", "styleId": "optionText" },
                          { "type": "spacer" },
                          {
                            "type": "button",
                            "text": "",
                            "styles": { "normal": "radioButtonNormal", "selected": "radioButtonSelected" },
                            "isSelectedBinding": "${'$'}{selectedOption == 'other'}",
                            "actions": { "onTap": { "type": "setState", "path": "selectedOption", "value": "other" } }
                          }
                        ]
                      }
                    ]
                  },
                  {
                    "type": "button",
                    "text": "Submit",
                    "styleId": "submitButton",
                    "fillWidth": true,
                    "actions": { "onTap": "submitFeedback" }
                  }
                ]
              }
            ]
          }
        ]
      },
      "styles": {
        "headerTitle": {
          "fontSize": 16,
          "fontWeight": "semibold",
          "textColor": "#1A1A1A",
          "textAlignment": "center"
        },
        "placeholderSpacer": {
          "width": 32,
          "height": 32
        },
        "questionTitle": {
          "fontSize": 28,
          "fontWeight": "bold",
          "textColor": "#1A1A1A",
          "textAlignment": "leading"
        },
        "optionsContainer": {
          "backgroundColor": "#FEF7FF",
          "cornerRadius": 16
        },
        "optionText": {
          "fontSize": 17,
          "fontWeight": "regular",
          "textColor": "#1A1A1A"
        },
        "radioButtonNormal": {
          "width": 24,
          "height": 24,
          "cornerRadius": 12,
          "borderWidth": 2,
          "borderColor": "#CCCCCC",
          "backgroundColor": "transparent"
        },
        "radioButtonSelected": {
          "width": 24,
          "height": 24,
          "cornerRadius": 12,
          "backgroundColor": "#1A1A1A"
        },
        "rowDivider": {
          "height": 1,
          "backgroundColor": "#E0E0E0"
        },
        "submitButton": {
          "fontSize": 16,
          "fontWeight": "semibold",
          "textColor": "#FFFFFF",
          "backgroundColor": "#1A1A1A",
          "cornerRadius": 28,
          "height": 56
        }
      },
      "actions": {
        "dismiss": { "type": "dismiss" },
        "submitFeedback": {
          "type": "sequence",
          "steps": [
            { "type": "dismiss" },
            {
              "type": "showAlert",
              "title": "Feedback Submitted",
              "message": { "type": "binding", "template": "You selected: ${'$'}{selectedOption}" },
              "buttons": [
                { "label": "OK", "style": "default" }
              ]
            }
          ]
        }
      }
    }
    """
)

private val photoTouchUpExample = Example(
    id = "photoTouchUp",
    title = "Photo Touch Up",
    description = "Before/after photo comparison with touch up announcement - uses custom PhotoComparisonComponent and CloseButtonComponent",
    category = ExampleCategory.CUSTOM,
    presentationStyle = PresentationStyle.BOTTOM_SHEET_LARGE,
    json = """
    {
      "id": "photoTouchUp",
      "root": {
        "backgroundColor": "#FEF7FF",
        "children": [
          {
            "type": "vstack",
            "spacing": 0,
            "padding": { "horizontal": 0, "top": 0, "bottom": 0 },
            "children": [
              {
                "type": "hstack",
                "padding": { "horizontal": 16, "top": 16, "bottom": 0 },
                "children": [
                  {
                    "type": "closeButton",
                    "actions": { "onTap": "dismiss" }
                  },
                  { "type": "spacer" }
                ]
              },
              {
                "type": "vstack",
                "spacing": 24,
                "padding": { "horizontal": 24, "top": 0, "bottom": 32 },
                "alignment": "center",
                "children": [
                  {
                    "type": "photoComparison",
                    "styleId": "photoComparison",
                    "data": {
                      "beforeImage": { "type": "static", "value": "touchUpBefore" },
                      "afterImage": { "type": "static", "value": "touchUpAfter" }
                    }
                  },
                  {
                    "type": "vstack",
                    "spacing": 8,
                    "alignment": "center",
                    "children": [
                      {
                        "type": "label",
                        "text": "Your photo is now",
                        "styleId": "titleStyle"
                      },
                      {
                        "type": "label",
                        "text": "touched up",
                        "styleId": "titleStyle"
                      }
                    ]
                  },
                  {
                    "type": "vstack",
                    "spacing": 4,
                    "alignment": "center",
                    "children": [
                      {
                        "type": "label",
                        "text": "We improved clarity on one of your photos to help it look its best. You're always in control—review, adjust, or turn it off at anytime.",
                        "styleId": "bodyStyle"
                      },
                      {
                        "type": "button",
                        "text": "Learn more about touch ups",
                        "styleId": "linkButton",
                        "actions": { "onTap": "learnMore" }
                      }
                    ]
                  },
                  {
                    "type": "button",
                    "text": "Review",
                    "styleId": "primaryButton",
                    "fillWidth": true,
                    "actions": { "onTap": "review" }
                  }
                ]
              }
            ]
          }
        ]
      },
      "styles": {
        "photoComparison": {
          "width": 200,
          "height": 267
        },
        "titleStyle": {
          "fontSize": 28,
          "fontWeight": "bold",
          "textColor": "#1A1A1A",
          "textAlignment": "center"
        },
        "bodyStyle": {
          "fontSize": 14,
          "fontWeight": "regular",
          "textColor": "#666666",
          "textAlignment": "center"
        },
        "linkButton": {
          "fontSize": 14,
          "fontWeight": "regular",
          "textColor": "#1A1A1A"
        },
        "primaryButton": {
          "fontSize": 16,
          "fontWeight": "semibold",
          "textColor": "#FFFFFF",
          "backgroundColor": "#1A1A1A",
          "cornerRadius": 28,
          "height": 56
        }
      },
      "actions": {
        "dismiss": { "type": "dismiss" },
        "learnMore": {
          "type": "showAlert",
          "title": "Learn More",
          "message": "The user pressed Learn More",
          "buttons": [
            { "text": "OK", "role": "cancel" }
          ]
        },
        "review": {
          "type": "openURL",
          "url": "http://www.yahoo.com"
        }
      }
    }
    """
)

/**
 * All custom examples
 */
val customExamples: List<Example> = listOf(
    doubleDateExample,
    feedbackSurveyExample,
    photoTouchUpExample
)
