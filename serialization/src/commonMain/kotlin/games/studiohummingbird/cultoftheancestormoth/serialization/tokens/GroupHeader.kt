package games.studiohummingbird.cultoftheancestormoth.serialization.tokens

import kotlinx.serialization.Serializable

@Serializable
data class GroupHeader(
    val groupSize: GroupSize,
    val groupProperties: GroupProperties
)
