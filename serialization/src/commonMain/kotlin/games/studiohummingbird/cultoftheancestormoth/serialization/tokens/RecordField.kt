package games.studiohummingbird.cultoftheancestormoth.serialization.tokens

import kotlinx.serialization.Serializable

@Serializable
data class RecordField<T : Any>(
    val fieldType: FieldType,
    val fieldSize: FieldSize,
    val fieldValue: FieldValue<T>
) : RecordToken
