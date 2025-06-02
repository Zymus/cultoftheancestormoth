package games.studiohummingbird.cultoftheancestormoth.serialization.tokens

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("RecordHeader")
data class RecordHeader(
    val recordSize: RecordSize,
    val recordProperties: RecordProperties
)
