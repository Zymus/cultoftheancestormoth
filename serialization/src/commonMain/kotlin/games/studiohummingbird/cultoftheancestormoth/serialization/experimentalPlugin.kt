@file:PluginAnnotation

package games.studiohummingbird.cultoftheancestormoth.serialization

import games.studiohummingbird.cultoftheancestormoth.bytestring.serializer.encodeToByteString
import games.studiohummingbird.cultoftheancestormoth.serialization.annotations.PluginAnnotation
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.TypeTag
import games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes.TES4
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Field
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.FieldType
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.FieldValue
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Fields
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.GMST
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.GRUP
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.GroupHeader
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.GroupProperties
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.GroupSize
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Plugin
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.PluginHeader
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Record
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RecordHeader
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RecordProperties
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RecordSize
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.builtins.serializer

const val RESTORE_HEALTH_MAGIC_EFFECT_FORM_ID = 0x0003eb15

@ExperimentalStdlibApi
@ExperimentalSerializationApi
fun experimentalPlugin(): Plugin {
    val groupToken = GRUP(
        GroupHeader(GroupSize(0.toUInt()), GroupProperties(TypeTag("GMST"), 0, 0.toShort(), 0.toShort(), 0)),
        listOf(
            GMST(
                RecordHeader(
                    RecordSize(-1),
                    RecordProperties(0, 0, 0.toShort(), 0.toShort(), 0.toShort(), 0.toShort())
                ),
                Fields(
                    listOf(
                        Field(
                            FieldType(TypeTag("EDID")),
                            0.toUShort(),
                            FieldValue(PluginFormat.encodeToByteString(String.serializer(), "FirstBoolean"))
                        ),
                        Field(
                            FieldType(TypeTag("DATA")),
                            0.toUShort(),
                            FieldValue(PluginFormat.encodeToByteString(Boolean.serializer(), true))
                        ),
                    )
                )
            )
        )
    )

    val plugin = Plugin(
        PluginHeader(
            Record(
                TypeTag("TES4"),
                RecordHeader(
                    RecordSize(-1),
                    RecordProperties(0, 0, 0.toShort(), 0.toShort(), 0.toShort(), 0.toShort())
                ),
                Fields(
                    listOf(
                        Field(
                            FieldType(TypeTag("HEDR")),
                            0.toUShort(),
                            FieldValue(PluginFormat.encodeToByteString(TES4.Header(1.7f, 0, 0)))
                        ),
                        Field(
                            FieldType(TypeTag("CNAM")),
                            0.toUShort(),
                            FieldValue(PluginFormat.encodeToByteString("Zymus"))
                        ),
                        Field(
                            FieldType(TypeTag("SNAM")),
                            0.toUShort(),
                            FieldValue(PluginFormat.encodeToByteString("TES4 Token Example"))
                        ),
                        Field(
                            FieldType(TypeTag("MAST")),
                            0.toUShort(),
                            FieldValue(PluginFormat.encodeToByteString("Skyrim.esm"))
                        ),
                        Field(
                            FieldType(TypeTag("DATA")),
                            0.toUShort(),
                            FieldValue(PluginFormat.encodeToByteString(0L))
                        ),
                        Field(
                            FieldType(TypeTag("MAST")),
                            0.toUShort(),
                            FieldValue(PluginFormat.encodeToByteString("Update.esm"))
                        ),
                        Field(
                            FieldType(TypeTag("DATA")),
                            0.toUShort(),
                            FieldValue(PluginFormat.encodeToByteString(0L))
                        ),
                        Field(
                            FieldType(TypeTag("MAST")),
                            0.toUShort(),
                            FieldValue(PluginFormat.encodeToByteString("Hearthfires.esm"))
                        ),
                        Field(
                            FieldType(TypeTag("DATA")),
                            0.toUShort(),
                            FieldValue(PluginFormat.encodeToByteString(0L))
                        ),
                        Field(
                            FieldType(TypeTag("ONAM")),
                            0.toUShort(),
                            FieldValue(PluginFormat.encodeToByteString(listOf(0L)))
                        ),
                        Field(FieldType(TypeTag("INTV")), 0.toUShort(), FieldValue(PluginFormat.encodeToByteString(0))),
                        Field(FieldType(TypeTag("INCC")), 0.toUShort(), FieldValue(PluginFormat.encodeToByteString(0))),
                    )
                )
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
