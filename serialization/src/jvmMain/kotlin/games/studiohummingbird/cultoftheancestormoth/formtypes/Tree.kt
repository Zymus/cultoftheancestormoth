package creationkotlin.formtypes

const val initialTreeValue: Float = 1.0000f
const val initialSeasonalValue: Int = 100

data class Tree (
    override val editorId: String = "",
    val trunkFlexibility: Float = initialTreeValue,
    val branchFlexibility: Float = initialTreeValue,
    val leafAmplitude: Float = initialTreeValue,
    val leafFrequency: Float = initialTreeValue,
    val model: String = "",
    val name: String = "",
    val ingredient: MiscItem = MiscItem(),
    val harvestSound: SoundDescriptor = SoundDescriptor(),
    val spring: Int = initialSeasonalValue,
    val summer: Int = initialSeasonalValue,
    val fall: Int = initialSeasonalValue,
    val winter: Int = initialSeasonalValue,
) : FormType

class MutableTree(tree: Tree = Tree()) {

    var tree: Tree = tree
        private set

    var editorId: String
        get() = tree.editorId
        set(value) {
            tree = tree.copy(editorId = value)
        }

    var trunkFlexibility: Float
        get() = tree.trunkFlexibility
        set(value) {
            tree = tree.copy(trunkFlexibility = value)
        }

    var branchFlexibility: Float
        get() = tree.branchFlexibility
        set(value) {
            tree = tree.copy(branchFlexibility = value)
        }

    var leafAmplitude: Float
        get() = tree.leafAmplitude
        set(value) {
            tree = tree.copy(leafAmplitude = value)
        }

    var leafFrequency: Float
        get() = tree.leafFrequency
        set(value) {
            tree = tree.copy(leafFrequency = value)
        }

    var model: String
        get() = tree.model
        set(value) {
            tree = tree.copy(model = value)
        }

    var name: String
        get() = tree.name
        set(value) {
            tree = tree.copy(name = value)
        }

    var ingredient: MiscItem
        get() = tree.ingredient
        set(value) {
            tree = tree.copy(ingredient = value)
        }

    var harvestSound: SoundDescriptor
        get() = tree.harvestSound
        set(value) {
            tree = tree.copy(harvestSound = value)
        }

    var spring: Int
        get() = tree.spring
        set(value) {
            tree = tree.copy(spring = value)
        }

    var summer: Int
        get() = tree.summer
        set(value) {
            tree = tree.copy(summer = value)
        }

    var fall: Int
        get() = tree.fall
        set(value) {
            tree = tree.copy(fall = value)
        }

    var winter: Int
        get() = tree.winter
        set(value) {
            tree = tree.copy(winter = value)
        }
}

fun tree(block: MutableTree.() -> Unit): Tree =
    MutableTree().apply(block).tree
