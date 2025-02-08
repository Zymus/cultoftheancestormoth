package games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes

import games.studiohummingbird.cultoftheancestormoth.serialization.BethesdaBufferEncoder
import games.studiohummingbird.cultoftheancestormoth.serialization.annotations.FieldAnnotation
import games.studiohummingbird.cultoftheancestormoth.serialization.annotations.RecordAnnotation
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.InlineNullTerminatedString
import games.studiohummingbird.cultoftheancestormoth.serialization.encodeField
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

@Serializable
data class EnchantedItem
(
    val potionValue: UInt,
    val flags: UInt,
)

@Serializable
data class Effect
(
    val effectId: UInt,//formid; Could this be targeted to Id/REFR<MGEF>?
    val effectParams: EffectParams,
    val conditions: List<Condition>? = null,
)

@Serializable
data class EffectParams
(
    val magnitude: Float = 1.0f,
    val areaOfEffect: UInt,
    val duration: UInt = 10.toUInt(),
)

@Serializable
class Condition

@ExperimentalSerializationApi
@Serializable
@RecordAnnotation("ALCH")
data class ALCH
(
    @FieldAnnotation("EDID")
    val editorId: InlineNullTerminatedString = InlineNullTerminatedString(""),

    @FieldAnnotation("FULL")
    val name: InlineNullTerminatedString = InlineNullTerminatedString(""),
    val keywords: Set<KYWD> = emptySet(),
    val model: String/*MODL*/ = "",
    val useSound: String = "",
    val pickupSound: String = "",
    val dropSound: String = "",
    val weight: Float,
    val enchantedItem: EnchantedItem,
    val effects: List<Effect>? = null
)

@OptIn(ExperimentalSerializationApi::class)
fun BethesdaBufferEncoder.encodePotion(potion: ALCH) {
    encodeField("EDID") { encodeSerializableValue(InlineNullTerminatedString.serializer(), potion.editorId) }
    encodeField("OBND") { repeat(6) { encodeShort(0) } }
    encodeField("FULL") { encodeSerializableValue(InlineNullTerminatedString.serializer(), potion.name) }
    encodeField("DATA") { encodeFloat(potion.weight) }
    encodeField("ENIT") {
        val potionValue = 0
        val flags = 0
        val addiction = 0
        val addictionChance = 0
        val useSoundFormId = 0

        encodeInt(potionValue)
        encodeInt(flags)
        encodeInt(addiction)
        encodeInt(addictionChance)
        encodeInt(useSoundFormId)
    }

    potion.effects?.forEach {
        encodeField("EFID") { encodeInt(it.effectId.toInt()) }
        encodeField("EFIT") {
            encodeFloat(it.effectParams.magnitude)
            encodeInt(it.effectParams.areaOfEffect.toInt())
            encodeInt(it.effectParams.duration.toInt())
        }
    }
}
