package com.example.scalsandroid.scals.document

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*

@Serializable
@JvmInline
value class ComponentKind(val rawValue: String)

@Serializable
data class ComponentActions(
    val onTap: ActionBinding? = null,
    val onValueChanged: ActionBinding? = null,
)

@Serializable
data class ComponentStyles(
    val normal: Style? = null,
    val selected: Style? = null,
    val disabled: Style? = null,
)

@Serializable(with = ComponentSerializer::class)
data class Component(
    val type: ComponentKind,
    val id: String? = null,
    val styleId: String? = null,
    val style: Style? = null,
    val styles: ComponentStyles? = null,
    val actions: ComponentActions? = null,
    val text: String? = null,
    val value: StateValue? = null,
    val binding: String? = null,
    val placeholder: String? = null,
    val image: ImageSource? = null,
    val label: String? = null,
    val children: List<LayoutNode>? = null,
    val isEnabled: Boolean? = null,
    val isHidden: Boolean? = null,
    val isSelected: Boolean? = null,
    val width: DimensionValue? = null,
    val height: DimensionValue? = null,
    val minWidth: DimensionValue? = null,
    val minHeight: DimensionValue? = null,
    val maxWidth: DimensionValue? = null,
    val maxHeight: DimensionValue? = null,
    val cornerRadius: Double? = null,
    val padding: Padding? = null,
    val backgroundColor: String? = null,
    val foregroundColor: String? = null,
    val opacity: Double? = null,
    val navigation: NavigationConfig? = null,
    val dataSource: DataReference? = null,
    val gradient: List<GradientColorConfig>? = null,
    val additionalProperties: Map<String, StateValue> = emptyMap(),
) : LayoutNode

@Serializable
data class NavigationConfig(
    val presentation: NavigationPresentation? = null,
    val destination: LayoutNode? = null,
    val destinationId: String? = null,
)

internal object ComponentSerializer : KSerializer<Component> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Component")

    private val KNOWN_KEYS = setOf(
        "type", "id", "styleId", "style", "styles", "actions",
        "text", "value", "binding", "placeholder", "image", "label",
        "children", "isEnabled", "isHidden", "isSelected",
        "width", "height", "minWidth", "minHeight", "maxWidth", "maxHeight",
        "cornerRadius", "padding", "backgroundColor", "foregroundColor",
        "opacity", "navigation", "dataSource", "gradient",
    )

    override fun serialize(encoder: Encoder, value: Component) {
        val jsonEncoder = encoder as? JsonEncoder
            ?: throw SerializationException("Component can only be serialized to JSON")
        val knownJson = ScalsJson.encodeToJsonElement(ComponentData.serializer(), value.toComponentData())
        val obj = knownJson.jsonObject.toMutableMap()
        for ((k, v) in value.additionalProperties) {
            obj[k] = v.toJsonElement()
        }
        jsonEncoder.encodeJsonElement(JsonObject(obj))
    }

    override fun deserialize(decoder: Decoder): Component {
        val jsonDecoder = decoder as? JsonDecoder
            ?: throw SerializationException("Component can only be deserialized from JSON")
        val obj = jsonDecoder.decodeJsonElement().jsonObject
        val knownObj = JsonObject(obj.filterKeys { it in KNOWN_KEYS })
        val data = ScalsJson.decodeFromJsonElement(ComponentData.serializer(), knownObj)
        val additionalProperties = mutableMapOf<String, StateValue>()
        for ((key, value) in obj) {
            if (key !in KNOWN_KEYS) {
                additionalProperties[key] = StateValueSerializer.fromJsonElement(value)
            }
        }
        return data.toComponent(additionalProperties)
    }
}

@Serializable
internal data class ComponentData(
    val type: ComponentKind,
    val id: String? = null,
    val styleId: String? = null,
    val style: Style? = null,
    val styles: ComponentStyles? = null,
    val actions: ComponentActions? = null,
    val text: String? = null,
    val value: StateValue? = null,
    val binding: String? = null,
    val placeholder: String? = null,
    val image: ImageSource? = null,
    val label: String? = null,
    val children: List<LayoutNode>? = null,
    val isEnabled: Boolean? = null,
    val isHidden: Boolean? = null,
    val isSelected: Boolean? = null,
    val width: DimensionValue? = null,
    val height: DimensionValue? = null,
    val minWidth: DimensionValue? = null,
    val minHeight: DimensionValue? = null,
    val maxWidth: DimensionValue? = null,
    val maxHeight: DimensionValue? = null,
    val cornerRadius: Double? = null,
    val padding: Padding? = null,
    val backgroundColor: String? = null,
    val foregroundColor: String? = null,
    val opacity: Double? = null,
    val navigation: NavigationConfig? = null,
    val dataSource: DataReference? = null,
    val gradient: List<GradientColorConfig>? = null,
)

internal fun Component.toComponentData() = ComponentData(
    type = type, id = id, styleId = styleId, style = style, styles = styles,
    actions = actions, text = text, value = value, binding = binding,
    placeholder = placeholder, image = image, label = label, children = children,
    isEnabled = isEnabled, isHidden = isHidden, isSelected = isSelected,
    width = width, height = height, minWidth = minWidth, minHeight = minHeight,
    maxWidth = maxWidth, maxHeight = maxHeight, cornerRadius = cornerRadius,
    padding = padding, backgroundColor = backgroundColor, foregroundColor = foregroundColor,
    opacity = opacity, navigation = navigation, dataSource = dataSource, gradient = gradient,
)

internal fun ComponentData.toComponent(additionalProperties: Map<String, StateValue> = emptyMap()) = Component(
    type = type, id = id, styleId = styleId, style = style, styles = styles,
    actions = actions, text = text, value = value, binding = binding,
    placeholder = placeholder, image = image, label = label, children = children,
    isEnabled = isEnabled, isHidden = isHidden, isSelected = isSelected,
    width = width, height = height, minWidth = minWidth, minHeight = minHeight,
    maxWidth = maxWidth, maxHeight = maxHeight, cornerRadius = cornerRadius,
    padding = padding, backgroundColor = backgroundColor, foregroundColor = foregroundColor,
    opacity = opacity, navigation = navigation, dataSource = dataSource, gradient = gradient,
    additionalProperties = additionalProperties,
)
