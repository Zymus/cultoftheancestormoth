package games.studiohummingbird.cultoftheancestormoth.serialization.tokens

import kotlinx.serialization.Serializable

@Serializable
data class GroupHeader(
    val groupTag: GroupTag,
    val groupSize: GroupSize,
    val groupProperties: GroupProperties
) : PluginToken
