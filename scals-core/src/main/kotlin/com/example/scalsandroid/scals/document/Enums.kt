package com.example.scalsandroid.scals.document

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class LayoutType {
    @SerialName("vstack") VSTACK,
    @SerialName("hstack") HSTACK,
    @SerialName("zstack") ZSTACK,
}

@Serializable
enum class HorizontalAlignment {
    @SerialName("leading") LEADING,
    @SerialName("center") CENTER,
    @SerialName("trailing") TRAILING,
}

@Serializable
enum class VerticalAlignment {
    @SerialName("top") TOP,
    @SerialName("center") CENTER,
    @SerialName("bottom") BOTTOM,
}

@Serializable
enum class FontWeight {
    @SerialName("ultraLight") ULTRA_LIGHT,
    @SerialName("thin") THIN,
    @SerialName("light") LIGHT,
    @SerialName("regular") REGULAR,
    @SerialName("medium") MEDIUM,
    @SerialName("semibold") SEMIBOLD,
    @SerialName("bold") BOLD,
    @SerialName("heavy") HEAVY,
    @SerialName("black") BLACK,
}

@Serializable
enum class TextAlignment {
    @SerialName("leading") LEADING,
    @SerialName("center") CENTER,
    @SerialName("trailing") TRAILING,
}

@Serializable
enum class SectionType {
    @SerialName("horizontal") HORIZONTAL,
    @SerialName("list") LIST,
    @SerialName("grid") GRID,
    @SerialName("flow") FLOW,
}

@Serializable
enum class SectionAlignment {
    @SerialName("leading") LEADING,
    @SerialName("center") CENTER,
    @SerialName("trailing") TRAILING,
}

@Serializable
enum class SnapBehavior {
    @SerialName("none") NONE,
    @SerialName("viewAligned") VIEW_ALIGNED,
    @SerialName("paging") PAGING,
}

@Serializable
enum class DataSourceKind {
    @SerialName("static") STATIC,
    @SerialName("binding") BINDING,
}

@Serializable
enum class DataReferenceType {
    @SerialName("static") STATIC,
    @SerialName("binding") BINDING,
    @SerialName("localBinding") LOCAL_BINDING,
}

@Serializable
enum class Positioning {
    @SerialName("safeArea") SAFE_AREA,
    @SerialName("absolute") ABSOLUTE,
}

@Serializable
enum class NavigationPresentation {
    @SerialName("push") PUSH,
    @SerialName("present") PRESENT,
    @SerialName("fullScreen") FULL_SCREEN,
}

@Serializable
enum class AlertButtonStyle {
    @SerialName("default") DEFAULT,
    @SerialName("cancel") CANCEL,
    @SerialName("destructive") DESTRUCTIVE,
}
