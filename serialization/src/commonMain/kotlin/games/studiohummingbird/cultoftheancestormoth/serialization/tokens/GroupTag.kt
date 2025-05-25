package games.studiohummingbird.cultoftheancestormoth.serialization.tokens

import games.studiohummingbird.cultoftheancestormoth.serialization.GroupTagSerializer
import kotlinx.serialization.Serializable

@Serializable(with = GroupTagSerializer::class)
object GroupTag : GroupToken
