@file:PluginAnnotation
package games.studiohummingbird.cultoftheancestormoth.serialization

import games.studiohummingbird.cultoftheancestormoth.serialization.annotations.PluginAnnotation
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.NullTerminatedString
import games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes.ALCH
import games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes.BooleanGameSetting
import games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes.Effect
import games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes.EffectParams
import games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes.EnchantedItem
import games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes.FloatGameSetting
import games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes.GRUP
import games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes.IntGameSetting
import games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes.StringGameSetting
import kotlinx.serialization.ExperimentalSerializationApi

const val RESTORE_HEALTH_MAGIC_EFFECT_FORM_ID = 0x0003eb15

@ExperimentalStdlibApi
@ExperimentalSerializationApi
fun experimentalPlugin(): Plugin {
    val gameSettingGroup = GRUP(
        label = "GMST",
        records = setOf(
            BooleanGameSetting("FirstBoolean", true),
            IntGameSetting("FirstInt", 26),
            FloatGameSetting("FirstFloat", 13.0f),
            StringGameSetting("FirstString", "first string")
        )
    )

    val effects = listOf(
        Effect(RESTORE_HEALTH_MAGIC_EFFECT_FORM_ID.toUInt(), EffectParams(2.6f, 0.toUInt(), 0.toUInt()))
    )

    val potionGroup = GRUP(
        label = "ALCH",
        records = setOf(
            ALCH(NullTerminatedString("TestPotionEditorId1"), weight = 1.5f, enchantedItem = EnchantedItem(1.toUInt(), 1.toUInt())),
            ALCH(NullTerminatedString("TestPotionEditorId2"), weight = 2.5f, enchantedItem = EnchantedItem(2.toUInt(), 2.toUInt())),
            ALCH(NullTerminatedString("TestPotionEditorId3"), weight = 3.5f, enchantedItem = EnchantedItem(3.toUInt(), 3.toUInt()), effects = effects),
        )
    )

    val recordCount = potionGroup.records.count() + 1 + gameSettingGroup.records.count() + 1

    val plugin = Plugin(
        "Zymus",
        "Cult of the Ancestor Moth Example",
        setOf("Skyrim.esm")
    )

    return plugin
}

// PLUGIN: PLUGIN_HEADER, GROUP*
// PLUGIN_HEADER: TES4

// GROUP: GROUP_HEADER, RECORD*
// GROUP_HEADER: GROUP_LABEL, GROSS_GROUP_LENGTH, GROUP_PROPERTIES

// RECORD: RECORD_HEADER, FIELD*
// RECORD_HEADER: RECORD_TYPE, NET_RECORD_LENGTH, RECORD_PROPERTIES

// FIELD:  FIELD_NAME, NET_FIELD_LENGTH, FIELD_VALUE
