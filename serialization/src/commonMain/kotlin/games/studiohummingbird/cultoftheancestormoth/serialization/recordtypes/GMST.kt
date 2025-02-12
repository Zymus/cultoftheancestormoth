package games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes

import games.studiohummingbird.cultoftheancestormoth.serialization.BethesdaBufferEncoder
import games.studiohummingbird.cultoftheancestormoth.serialization.EncoderAction
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.InlineNullTerminatedString
import games.studiohummingbird.cultoftheancestormoth.serialization.encodeField
import games.studiohummingbird.cultoftheancestormoth.serialization.encoderAction
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
sealed class GMST(open val name: String)

data class BooleanGameSetting
(override val name: String, val value: Boolean )
: GMST(name)

data class IntGameSetting
( override val name: String, val value: Int )
: GMST(name)

data class FloatGameSetting
( override val name: String, val value: Float )
: GMST(name)

data class StringGameSetting
( override val name: String, val value: String )
: GMST(name)

@OptIn(ExperimentalSerializationApi::class)
fun BethesdaBufferEncoder.encodeGameSetting(gameSetting: GMST) {
    val (namePrefix: String, encodeSetting: EncoderAction) = when (gameSetting) {
        is BooleanGameSetting -> "b" to encoderAction { encodeInt(if (gameSetting.value) 1 else 0) }
        is FloatGameSetting -> "f" to encoderAction { encodeFloat(gameSetting.value) }
        is IntGameSetting -> "i" to encoderAction { encodeInt(gameSetting.value) }
        is StringGameSetting -> "s" to encoderAction { encodeString(gameSetting.value) }
    }

    encodeField("EDID") { encodeInline(InlineNullTerminatedString.serializer().descriptor).encodeString("$namePrefix${gameSetting.name}") }
    encodeField("DATA") { encodeSetting() }
}
