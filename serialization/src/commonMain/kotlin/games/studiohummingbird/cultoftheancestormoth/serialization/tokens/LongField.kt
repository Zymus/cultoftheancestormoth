package games.studiohummingbird.cultoftheancestormoth.serialization.tokens

import games.studiohummingbird.cultoftheancestormoth.serialization.LongFieldSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable(with = LongFieldSerializer::class)
@SerialName(LongField.SERIAL_NAME)
data class LongField(
    val fieldSize: UShort,
    val actualSize: Int,
    val longField: Field
) : RecordValue {
    companion object {
        const val SERIAL_NAME = "XXXX"
    }
}
