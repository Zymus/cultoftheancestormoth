package creationkotlin.recordtypes

import creationkotlin.annotations.Field
import creationkotlin.annotations.Record

data class EnchantedItem
(
    val potionValue: UInt,
    val flags: UInt,
)

data class Effect
(
    val effectId: String,//formid; Could this be targeted to Id/REFR<MGEF>?
    val effectParams: EffectParams,
    val conditions: Conditions? = null,
)

typealias Effects = Iterable<Effect>

data class EffectParams
(
    val magnitude: Float = 1.0f,
    val areaOfEffect: UInt,
    val duration: UInt = 10.toUInt(),
)

class Condition

typealias Conditions = Iterable<Condition>

@Record
data class Potion
(
    @Field("EDID")
    val editorId: String = "",

    @Field("FULL")
    val name: String = "",
    val keywords: Keywords = emptySet(),
    val model: String/*MODL*/ = "",
    val useSound: String = "",
    val pickupSound: String = "",
    val dropSound: String = "",
    val weight: Float,
    val enchantedItem: EnchantedItem,
    val effects: Effects? = null
)
: RecordType
