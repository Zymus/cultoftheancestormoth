package games.studiohummingbird.cultoftheancestormoth.serialization.tokens

import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.TypeTag
import kotlinx.serialization.Serializable

@Serializable
data class GroupProperties(
    val label: TypeTag,// or int later, TODO
    val groupType: Int,
    val timestamp: Short,
    val versionControl: Short,
    val unknown: Int
)
