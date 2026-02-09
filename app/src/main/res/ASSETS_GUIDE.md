# Asset Migration Guide

This guide describes how to migrate assets from the iOS ScalsRenderer app to the Android ScalsAndroid app.

## Fonts

The iOS app uses the Merriweather font family. Fonts need to be copied from:
**Source:** `/Users/michael.choe/Desktop/PROGRAMMING/ScalsRenderer/ScalsRenderer/Fonts/`

**Destination:** `/Users/michael.choe/Desktop/PROGRAMMING/ScalsAndroid/app/src/main/res/font/`

### Required Font Files:
- `merriweather_light.ttf`
- `merriweather_lightitalic.ttf`
- `merriweather_regular.ttf`
- `merriweather_italic.ttf`
- `merriweather_bold.ttf`
- `merriweather_bolditalic.ttf`
- `merriweather_black.ttf`
- `merriweather_blackitalic.ttf`

### Android Font Naming Convention:
Font files must use lowercase with underscores. After copying, rename if necessary:
- `Merriweather-Light.ttf` → `merriweather_light.ttf`
- `Merriweather-Regular.ttf` → `merriweather_regular.ttf`
- etc.

### Font Family XML:
Create `app/src/main/res/font/merriweather.xml` to define the font family:

```xml
<?xml version="1.0" encoding="utf-8"?>
<font-family xmlns:android="http://schemas.android.com/apk/res/android">
    <font
        android:fontStyle="normal"
        android:fontWeight="300"
        android:font="@font/merriweather_light" />
    <font
        android:fontStyle="italic"
        android:fontWeight="300"
        android:font="@font/merriweather_lightitalic" />
    <font
        android:fontStyle="normal"
        android:fontWeight="400"
        android:font="@font/merriweather_regular" />
    <font
        android:fontStyle="italic"
        android:fontWeight="400"
        android:font="@font/merriweather_italic" />
    <font
        android:fontStyle="normal"
        android:fontWeight="700"
        android:font="@font/merriweather_bold" />
    <font
        android:fontStyle="italic"
        android:fontWeight="700"
        android:font="@font/merriweather_bolditalic" />
    <font
        android:fontStyle="normal"
        android:fontWeight="900"
        android:font="@font/merriweather_black" />
    <font
        android:fontStyle="italic"
        android:fontWeight="900"
        android:font="@font/merriweather_blackitalic" />
</font-family>
```

## Images

The iOS app uses several image assets that need to be migrated.

**Source:** `/Users/michael.choe/Desktop/PROGRAMMING/ScalsRenderer/ScalsRenderer/Assets.xcassets/`

**Destination:** `/Users/michael.choe/Desktop/PROGRAMMING/ScalsAndroid/app/src/main/res/drawable-nodpi/`

### Required Images:

1. **touchUpBefore** - Blurry photo for PhotoTouchUpExample
   - Extract from `touchUpBefore.imageset/`
   - Rename to `touch_up_before.png` or `.jpg`

2. **touchUpAfter** - Sharp photo for PhotoTouchUpExample
   - Extract from `touchUpAfter.imageset/`
   - Rename to `touch_up_after.png` or `.jpg`

3. **astrology** - Hero image for AstrologyModeExample
   - Extract from `astrology.imageset/`
   - Rename to `astrology.png` or `.jpg`

4. **womanAligator** - Sample image for ImageExample
   - Extract from `womanAligator.imageset/`
   - Rename to `woman_aligator.png` or `.jpg`

5. **DoubleDateHero** - Onboarding hero for DoubleDateExample
   - Extract from `DoubleDateHero.imageset/`
   - Rename to `double_date_hero.png` or `.jpg`

### Android Image Naming Convention:
- Use lowercase with underscores
- No hyphens or capital letters
- Example: `touchUpBefore` → `touch_up_before.png`

### Image Formats:
- PNG for images with transparency
- JPEG for photos without transparency
- Place in `drawable-nodpi` to prevent density scaling

## Manual Migration Steps

### Step 1: Copy Fonts
```bash
# From iOS project
cd /Users/michael.choe/Desktop/PROGRAMMING/ScalsRenderer/ScalsRenderer/Fonts/

# Find TTF files (Merriweather)
ls -la | grep -i merriweather

# Copy to Android (with renaming to lowercase)
# Example:
cp Merriweather-Light.ttf /Users/michael.choe/Desktop/PROGRAMMING/ScalsAndroid/app/src/main/res/font/merriweather_light.ttf
```

### Step 2: Copy Images
```bash
# From iOS project
cd /Users/michael.choe/Desktop/PROGRAMMING/ScalsRenderer/ScalsRenderer/Assets.xcassets/

# List image sets
ls -la | grep imageset

# Copy images (extract from .imageset folders)
# Each .imageset folder contains multiple resolutions (@1x, @2x, @3x)
# For Android, use the highest resolution (@3x) or @2x
```

### Step 3: Update SCALS JSON
After copying assets, update the example JSON files to use Android resource names:
- iOS: `"image": "touchUpBefore"`
- Android: `"image": "touch_up_before"`

Or update the image loading logic to handle resource name transformation.

## Icon System

The iOS app uses SF Symbols (Apple's icon system). Android uses Material Icons.

### Icon Name Mapping:
Create a mapping from SF Symbols to Material Icons names:
- `"heart.fill"` → `"favorite"`
- `"star.fill"` → `"star"`
- `"plus"` → `"add"`
- `"xmark"` → `"close"`
- etc.

This mapping should be implemented in the image resolver or as part of the component rendering logic.

## Next Steps

After completing asset migration:
1. ✅ Verify fonts load correctly in Compose
2. ✅ Verify images display correctly
3. ✅ Test Lightspeed Design System with Merriweather fonts
4. ✅ Implement icon name mapping for Material Icons
