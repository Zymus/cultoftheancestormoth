@file:PluginAnnotation
package games.studiohummingbird.cultoftheancestormoth.serialization

import games.studiohummingbird.cultoftheancestormoth.bytestring.serializer.encodeToByteString
import games.studiohummingbird.cultoftheancestormoth.serialization.annotations.PluginAnnotation
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.NullTerminatedString
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.TypeTag
import games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes.ALCH
import games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes.BooleanGameSetting
import games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes.Effect
import games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes.EffectParams
import games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes.EnchantedItem
import games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes.FloatGameSetting
import games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes.GRUP
import games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes.IntGameSetting
import games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes.StringGameSetting
import games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes.TES4
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Field
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.FieldSize
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.FieldType
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.FieldValue
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Fields
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Group
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.GroupHeader
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.GroupProperties
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.GroupSize
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.GroupTag
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Plugin
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.PluginHeader
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Record
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RecordHeader
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RecordProperties
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RecordSize
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RecordType
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Records
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.builtins.serializer

const val RESTORE_HEALTH_MAGIC_EFFECT_FORM_ID = 0x0003eb15

@ExperimentalStdlibApi
@ExperimentalSerializationApi
fun experimentalPlugin(): Plugin {
    var gameSettingGroup = GRUP(
        label = "GMST",
        records = setOf(
            BooleanGameSetting("FirstBoolean", true),
            IntGameSetting("FirstInt", 26),
            FloatGameSetting("FirstFloat", 13.0f),
            StringGameSetting("FirstString", "first string")
        )
    )

    val groupToken = Group(
        GroupHeader(GroupTag, GroupSize(0.toUInt()), GroupProperties(TypeTag("GMST"), 0, 0.toShort(), 0.toShort(), 0)),
        Records(
            listOf(
                Record(
                    RecordHeader(
                        RecordType(TypeTag("GMST")),
                        RecordSize(-1),
                        RecordProperties(0, 0, 0.toShort(), 0.toShort(), 0.toShort(), 0.toShort())
                    ),
                    Fields(
                        listOf(
                            Field(
                                FieldType(TypeTag("EDID")),
                                FieldSize(0),
                                FieldValue(PluginFormat.encodeToByteString(String.serializer(), "FirstBoolean"))
                            ),
                            Field(
                                FieldType(TypeTag("DATA")),
                                FieldSize(0),
                                FieldValue(PluginFormat.encodeToByteString(Boolean.serializer(), true))
                            ),
                        )
                    )
                )
            )
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
        PluginHeader(
            Record(
                RecordHeader(
                    RecordType(TypeTag("TES4")),
                    RecordSize(-1),
                    RecordProperties(0, 0, 0.toShort(), 0.toShort(), 0.toShort(), 0.toShort())
                ),
                Fields(listOf(
                    Field(FieldType(TypeTag("HEDR")), FieldSize(0), FieldValue(PluginFormat.encodeToByteString(TES4.Header(1.7f, 0, 0)))),
                    Field(FieldType(TypeTag("CNAM")), FieldSize(0), FieldValue(PluginFormat.encodeToByteString("Zymus"))),
                    Field(FieldType(TypeTag("SNAM")), FieldSize(0), FieldValue(PluginFormat.encodeToByteString("TES4 Token Example"))),
                    Field(FieldType(TypeTag("MAST")), FieldSize(0), FieldValue(PluginFormat.encodeToByteString("Skyrim.esm"))),
                    Field(FieldType(TypeTag("DATA")), FieldSize(0), FieldValue(PluginFormat.encodeToByteString(0L))),
                    Field(FieldType(TypeTag("MAST")), FieldSize(0), FieldValue(PluginFormat.encodeToByteString("Update.esm"))),
                    Field(FieldType(TypeTag("DATA")), FieldSize(0), FieldValue(PluginFormat.encodeToByteString(0L))),
                    Field(FieldType(TypeTag("MAST")), FieldSize(0), FieldValue(PluginFormat.encodeToByteString("Hearthfires.esm"))),
                    Field(FieldType(TypeTag("DATA")), FieldSize(0), FieldValue(PluginFormat.encodeToByteString(0L))),
                    Field(FieldType(TypeTag("ONAM")), FieldSize(0), FieldValue(PluginFormat.encodeToByteString(listOf(0L)))),
                    Field(FieldType(TypeTag("INTV")), FieldSize(0), FieldValue(PluginFormat.encodeToByteString(0))),
                    Field(FieldType(TypeTag("INCC")), FieldSize(0), FieldValue(PluginFormat.encodeToByteString(0))),
                ))
            )
        ),
        listOf(groupToken)
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
