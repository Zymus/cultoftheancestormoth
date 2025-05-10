package games.studiohummingbird.cultoftheancestormoth.serialization.tokens

import kotlinx.serialization.Serializable

@Serializable
sealed class GroupHeader(
    val groupTag: GroupTag,
    val groupSize: GroupSize,
    val groupProperties: GroupProperties
) : PluginToken
