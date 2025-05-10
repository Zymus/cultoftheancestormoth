package games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes

import games.studiohummingbird.cultoftheancestormoth.serialization.annotations.FieldAnnotation
import games.studiohummingbird.cultoftheancestormoth.serialization.annotations.RecordAnnotation
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.NullTerminatedString
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
    val editorId: NullTerminatedString = NullTerminatedString(""),

    @FieldAnnotation("FULL")
    val name: NullTerminatedString = NullTerminatedString(""),
    val keywords: Set<KYWD> = emptySet(),
    val model: String/*MODL*/ = "",
    val useSound: String = "",
    val pickupSound: String = "",
    val dropSound: String = "",
    val weight: Float,
    val enchantedItem: EnchantedItem,
    val effects: List<Effect>? = null
)
