package creationkotlin.formtypes

data class Potion(
    override val editorId: String = "",
    val value: Int = 0,
    val name: String = "",
) : FormType

class MutablePotion(potion: Potion = Potion()) {

    var potion: Potion = potion
        private set

    var editorId: String
        get() = potion.editorId
        set(value) { potion = potion.copy(editorId = value) }

    var value: Int
        get() = potion.value
        set(value) { potion = potion.copy(value = value) }

    var name: String
        get() = potion.name
        set(value) { potion = potion.copy(name = value) }
}

fun alch(block: MutablePotion.() -> Unit): Potion =
    MutablePotion().apply(block).potion
