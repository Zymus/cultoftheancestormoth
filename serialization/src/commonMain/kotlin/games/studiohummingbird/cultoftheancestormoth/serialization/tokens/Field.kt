package games.studiohummingbird.cultoftheancestormoth.serialization.tokens

import games.studiohummingbird.cultoftheancestormoth.serialization.FieldSerializer
import kotlinx.serialization.Serializable

@Serializable(with = FieldSerializer::class)
data class Field(
    val fieldType: FieldType,
    val fieldSize: FieldSize,
    val fieldValue: FieldValue
) : RecordToken
