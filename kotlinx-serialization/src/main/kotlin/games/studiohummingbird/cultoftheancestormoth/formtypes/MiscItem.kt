package creationkotlin.formtypes

data class MiscItem(
    override val editorId: String = "",
    val value: Int = 0,
    val name: String = "",
    val model: String = "",
    val weight: Float = 0.0f,
) : FormType

class MutableMiscItem(miscItem: MiscItem = MiscItem()) {

    var miscItem: MiscItem = miscItem
        private set

    var editorId: String
        get() = miscItem.editorId
        set(value) { miscItem = miscItem.copy(editorId = value) }

    var value: Int
        get() = miscItem.value
        set(value) { miscItem = miscItem.copy(value = value) }

    var name: String
        get() = miscItem.name
        set(value) { miscItem = miscItem.copy(name = value) }

    var model: String
        get() = miscItem.model
        set(value) { miscItem = miscItem.copy(model = value) }

    var weight: Float
        get() = miscItem.weight
        set(value) { miscItem = miscItem.copy(weight = value) }
}

fun misc(block: MutableMiscItem.() -> Unit): MiscItem =
    MutableMiscItem().apply(block).miscItem
