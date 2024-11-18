package games.studiohummingbird.cultoftheancestormoth.serialization.datatypes

data class FieldTag(
    val string: String
) {
    init {
        require(string.length == TAG_LENGTH)
    }

    companion object {
        const val TAG_LENGTH = 4
    }
}
