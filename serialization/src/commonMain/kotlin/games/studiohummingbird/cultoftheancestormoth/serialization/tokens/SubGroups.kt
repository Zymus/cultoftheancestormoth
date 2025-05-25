package games.studiohummingbird.cultoftheancestormoth.serialization.tokens

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@JvmInline
@Serializable
value class SubGroups(
    val list: List<Group>
) : GroupValueToken, RecordValueToken
