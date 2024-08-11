package games.studiohummingbird.cultoftheancestormoth.serialization

import kotlinx.serialization.Serializable

@Serializable
@JvmInline
value class RecordType(val string: String) {
    init {
        require(string.length == 4) { "length must be 4" }
    }
}
