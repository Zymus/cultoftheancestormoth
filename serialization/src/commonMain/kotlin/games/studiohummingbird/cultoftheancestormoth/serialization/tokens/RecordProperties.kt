package games.studiohummingbird.cultoftheancestormoth.serialization.tokens

import kotlinx.serialization.Serializable

@Serializable
sealed class RecordProperties(
    val flags: Int,
    val recordId: Int,
    val timestamp: Short,
    val versionControl: Short,
    val recordVersion: Short,
    val unknown: Short
) : RecordToken
