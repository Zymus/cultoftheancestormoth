package games.studiohummingbird.cultoftheancestormoth.serialization.tokens

import kotlinx.serialization.Serializable

@Serializable
data class RecordHeader(
    val recordType: RecordType,
    val recordSize: RecordSize,
    val recordProperties: RecordProperties
) : GroupToken
