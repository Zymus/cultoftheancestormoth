/**
Cult of the Ancestor Moth (PluginFormatTests.kt)
Copyright (C) 2025  Zymus (moore.zyle@gmail.com)

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published
by the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package games.studiohummigbird.cultoftheancestormoth.serialization

import games.studiohummingbird.cultoftheancestormoth.bytestring.serializer.decodeFromByteString
import games.studiohummingbird.cultoftheancestormoth.serialization.PluginFormat
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.NullTerminatedString
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.TypeTag
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Field
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.FieldType
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.FieldValue
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Fields
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.GRUP
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.GroupHeader
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.GroupProperties
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.GroupSize
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.PluginToken
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Record
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RecordHeader
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RecordProperties
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RecordSize
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RecordType
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.VOLI
import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertEquals

@InternalSerializationApi
@ExperimentalSerializationApi
@ExperimentalStdlibApi
class PluginFormatTests {

    @Test
    fun `format byte`() {
        val encoded = PluginFormat.encodeToByteArray(TEST_VALUE_BYTE)
        val decoded: Byte = PluginFormat.decodeFromByteArray(encoded)

        assertEquals(1, encoded.size)
        assertEquals(TEST_VALUE_BYTE, decoded)
    }

    @Test
    fun `format short`() {
        val encoded = PluginFormat.encodeToByteArray(TEST_VALUE_SHORT)
        val decoded: Short = PluginFormat.decodeFromByteArray(encoded)

        assertEquals(2, encoded.size)
        assertEquals(TEST_VALUE_SHORT, decoded)
    }

    @Test
    fun `format int`() {
        val encoded = PluginFormat.encodeToByteArray(TEST_VALUE_INT)
        val decoded: Int = PluginFormat.decodeFromByteArray(encoded)

        assertEquals(4, encoded.size)
        assertEquals(TEST_VALUE_INT, decoded)
    }

    @Test
    fun `format long`() {
        val encoded = PluginFormat.encodeToByteArray(TEST_VALUE_LONG)
        val decoded: Long = PluginFormat.decodeFromByteArray(encoded)

        assertEquals(8, encoded.size)
        assertEquals(TEST_VALUE_LONG, decoded)
    }

    @Test
    fun `format float`() {
        val encoded = PluginFormat.encodeToByteArray(TEST_VALUE_FLOAT)
        val decoded: Float = PluginFormat.decodeFromByteArray(encoded)

        assertEquals(4, encoded.size)
        assertEquals(TEST_VALUE_FLOAT, decoded)
    }

    @Test
    fun `format double`() {
        val encoded = PluginFormat.encodeToByteArray(TEST_VALUE_DOUBLE)
        val decoded: Double = PluginFormat.decodeFromByteArray(encoded)

        assertEquals(8, encoded.size)
        assertEquals(TEST_VALUE_DOUBLE, decoded)
    }

    @Test
    fun `format string`() {
        val encoded = PluginFormat.encodeToByteArray(TEST_STRING)
        val decoded: String = PluginFormat.decodeFromByteArray(encoded)

        assertEquals(TEST_STRING.length, encoded.size)
        assertEquals(TEST_STRING, decoded)
    }

    @Test
    fun `format null terminated string`() {
        val encoded = PluginFormat.encodeToByteArray(TEST_NULL_TERMINATED_STRING)
        val decoded: NullTerminatedString = PluginFormat.decodeFromByteArray(encoded)

        assertEquals(TEST_STRING.length + 1, encoded.size)
        assertEquals(TEST_NULL_TERMINATED_STRING, decoded)
    }

    @Test
    fun `format type tag`() {
        val encoded = PluginFormat.encodeToByteArray(TEST_TYPE_TAG)
        val decoded: TypeTag = PluginFormat.decodeFromByteArray(encoded)

        assertEquals(TEST_TYPE_TAG.string.length, encoded.size)
        assertEquals(TEST_TYPE_TAG, decoded)
    }

    @Test
    fun `format field type`() {
        val encoded = PluginFormat.encodeToByteArray(TEST_FIELD_TYPE)
        val decoded: FieldType = PluginFormat.decodeFromByteArray(encoded)

        assertEquals(TEST_FIELD_TYPE.typeTag.string.length, encoded.size)
        assertEquals(TEST_FIELD_TYPE, decoded)
    }

    @Test
    fun `format field size`() {
        val encoded = PluginFormat.encodeToByteArray(TEST_FIELD_SIZE)
        val decoded: FieldSize = PluginFormat.decodeFromByteArray(encoded)

        assertEquals(2, encoded.size)
        assertEquals(TEST_FIELD_SIZE, decoded)
    }

    @Test
    fun `format field value int`() {
        val encoded = PluginFormat.encodeToByteArray(TEST_FIELD_VALUE_INT)
        val decoded: FieldValue = PluginFormat.decodeFromByteArray(encoded)

        assertEquals(4, encoded.size)
        assertEquals(TEST_FIELD_VALUE_INT, decoded)
    }

    @Test
    fun `format field int`() {
        val encoded = PluginFormat.encodeToByteArray(TEST_FIELD_INT)
        val decoded: Field = PluginFormat.decodeFromByteArray(encoded)

        assertEquals(10, encoded.size)
        assertEquals(TEST_FIELD_TYPE, decoded.fieldType)
        assertEquals(4, decoded.fieldSize.ushort.toInt())
        assertEquals(TEST_FIELD_VALUE_INT, decoded.fieldValue)
    }

    @Test
    fun `format record size`() {
        val encoded = PluginFormat.encodeToByteArray(TEST_RECORD_SIZE)
        val decoded: RecordSize = PluginFormat.decodeFromByteArray(encoded)

        assertEquals(4, encoded.size)
        assertEquals(TEST_RECORD_SIZE, decoded)
    }

    @Test
    fun `format record properties`() {
        val encoded = PluginFormat.encodeToByteArray(TEST_RECORD_PROPERTIES)
        val decoded: RecordProperties = PluginFormat.decodeFromByteArray(encoded)

        assertEquals(16, encoded.size)
        assertEquals(TEST_RECORD_PROPERTIES, decoded)
    }

    @Test
    fun `format record of fields`() {
        val encoded = PluginFormat.encodeToByteArray(TEST_RECORD_WITH_FIELDS)
        val decoded: Record = PluginFormat.decodeFromByteArray(encoded)

        assertEquals(34, encoded.size)
        assertEquals(TEST_TYPE_TAG, decoded.tag)
        assertEquals(10, decoded.header.recordSize.int)
        assertEquals(TEST_RECORD_PROPERTIES, decoded.header.recordProperties)
    }

    @Test
    fun `format record value token`() {
        val encoded = PluginFormat.encodeToByteArray(TEST_RECORD_WITH_FIELDS)
        val decoded: Record = PluginFormat.decodeFromByteArray(encoded)

        assertEquals(34, encoded.size)
        assertEquals(TEST_TYPE_TAG, decoded.tag)
        assertEquals(10, decoded.header.recordSize.int)
        assertEquals(TEST_RECORD_PROPERTIES, decoded.header.recordProperties)
    }

    @Test
    fun `format group size`() {
        val encoded = PluginFormat.encodeToByteArray(TEST_GROUP_SIZE)
        val decoded: GroupSize = PluginFormat.decodeFromByteArray(encoded)

        assertEquals(4, encoded.size)
        assertEquals(TEST_GROUP_SIZE, decoded)
    }

    @Test
    fun `format group properties`() {
        val encoded = PluginFormat.encodeToByteArray(TEST_GROUP_PROPERTIES)
        val decoded: GroupProperties = PluginFormat.decodeFromByteArray(encoded)

        assertEquals(16, encoded.size)
        assertEquals(TEST_GROUP_PROPERTIES, decoded)
    }

    @Test
    fun `format group header`() {
        val encoded = PluginFormat.encodeToByteArray(TEST_GROUP_HEADER)
        val decoded: GroupHeader = PluginFormat.decodeFromByteArray(encoded)

        assertEquals(24, encoded.size)
        assertEquals(TEST_GROUP_HEADER, decoded)
    }

    @Test
    @Ignore
    fun `read TES4 from Skyrim esm`() {
        val groups = mapOf<String, List<String>>(
//            "Skyrim" to emptyList(),
//            listOf(
//                "GMST",
//                "KYWD",
//                "LCRT",
//                "AACT",
//                "TXST",
//                "GLOB",
//                "CLAS",
//                "FACT",
//                "HDPT",// buffer becoming exhausted here now?
//                "HAIR",
//                "EYES",
//                "RACE",
//                "SOUN",
//                "ASPC",
//                "MGEF",
//                "SCPT",
//                "LTEX",
//                "ENCH",
//                "SPEL",
//                "SCRL",
//                "ACTI",
//                "TACT",
//                "ARMO",
//                "BOOK",
//                "CONT",
//                "DOOR",
//                "INGR",
//                "LIGH",
//                "MISC",
//                "APPA",
//                "STAT",
//                "SCOL",
//                "MSTT",
//                "PWAT",
//                "GRAS",
//                "TREE",
//                "CLDC",
//                "FLOR",
//                "FURN",
//                "WEAP",
//                "AMMO",
//                "NPC_",// NPC_ is the first group with compressed records
//                "LVLN",
//                "KEYM",
//                "ALCH",
//                "IDLM",
//                "COBJ",
//                "PROJ",
//                "HAZD",
//                "SLGM",
//                "LVLI",
//                "WTHR",
//                "CLMT",
//                "SPGD",
//                "RFCT",
//                "REGN",
//                "NAVI",
//                "CELL",// has sub groups in the groups, under first record.
//                "WRLD",
//                "DIAL",
//                "QUST",
//                "IDLE",
//                "PACK",
//                "CSTY",
//                "LSCR",
//                "LVSP",
//                "ANIO",
//                "WATR",
//                "EFSH",
//                "EXPL",
//                "DEBR",
//                "IMGS",
//                "IMAD",
//                "FLST",
//                "PERK",
//                "BPTD",
//                "ADDN",
//                "AVIF",
//                "CAMS",
//                "CPTH",
//                "VTYP",
//                "MATT",
//                "IPCT",
//                "IPDS",
//                "ARMA",
//                "ECZN",
//                "LCTN",
//                "MESG",
//                "RGDL",
//                "DOBJ",
//                "LGTM",
//                "MUSC",
//                "FSTP",
//                "FSTS",
//                "SMBN",
//                "SMQN",
//                "SMEN",
//                "DLBR",
//                "MUST",
//                "DLVW",
//                "WOOP",
//                "SHOU",
//                "EQUP",
//                "RELA",
//                "SCEN",
//                "ASTP",
//                "OTFT",
//                "ARTO",
//                "MATO",
//                "MOVT",
//                "HAZD",// second empty HAZD group
//                "SNDR",
//                "DUAL",
//                "SNCT",
//                "SOPM",
//                "COLL",
//                "CLFM",
//                "REVB"
//            ),
            "Update" to emptyList(),
//                    listOf(
//                        "GMST",
//                        "KYWD",
//                        "TXST",
//                        "GLOB",
//                        "FACT",
//                        "RACE",
//                        "MGEF",
//                        "LTEX",
//                        "ENCH",
//                        "SPEL",
//                        "SCRL",
//                        "ACTI",
//                        "ARMO",
//                        "BOOK",
//                        "CONT",
//                        "DOOR",
//                        "INGR",
//                        "MISC",
//                        "STAT",
//                        "GRAS",
//                        "FLOR",
//                        "FURN",
//                        "WEAP",
//                        "AMMO",
//                        "NPC_",// NPC_ is the first group with compressed records
//                        "LVLN",
//                        "ALCH",
//                        "COBJ",
//                        "PROJ",
//                        "LVLI",
//                        "WTHR",
//                        "REGN",
//                        "NAVI",
//                        "CELL",// has sub groups in the groups, under first record.
//                        "WRLD",// behaves weird in Update.esm, had inner empty NAVM records
//                        "DIAL",
//                        "QUST",
//                        "IDLE",
//                        "PACK",
//                        "LSCR",
//                        "WATR",
//                        "EXPL",
//                        "IMGS",
//                        "IMAD",
//                        "FLST",
//                        "PERK",
//                        "BPTD",
//                        "AVIF",
//                        "CAMS",
//                        "CPTH",
//                        "IPCT",
//                        "IPDS",
//                        "ARMA",
//                        "ECZN",
//                        "LCTN",
//                        "MESG",
//                        "DOBJ",
//                        "MUSC",
//                        "FSTP",
//                        "FSTS",
//                        "DLBR",
//                        "MUST",
//                        "DLVW",
//                        "SCEN",
//                        "MATO",
//                        "SNDR",
//                        "VOLI"
//                    ),
//            "HearthFires" to emptyList<String>()
        ).map { file ->
            val encoded = SystemFileSystem
                .source(
                    Path(
                        "/",
                        "media",
                        "zymus",
                        "5516E98402BDA1A5",
                        "SteamLibrary",
                        "steamapps",
                        "common",
                        "Skyrim Special Edition",
                        "Data",
                        "${file.key}.esm"
                    )
                )
                .buffered()

            val tes4 = PluginFormat.decodeFromSource(PolymorphicSerializer(PluginToken::class), encoded)

            PluginFormat.decodeFromSource(ListSerializer(PolymorphicSerializer(PluginToken::class)), encoded)
                .filterIsInstance<GRUP>()
                .filter { it.header.groupProperties.label.string == "VOLI" }
                .flatMap { it.children }
                .filterIsInstance<VOLI>()
                .map { it.fields as Fields }
                .flatMap { it.list }
                .map {
                    listOf(
                        it.fieldType.typeTag.string,
                        it.fieldSize.ushort,
                        PluginFormat.decodeFromByteString(String.serializer(), it.fieldValue.value)
                    ).joinToString()
                }
                .run(::println)
//                .forEach { token -> println(debugString(token)) }
//
//            file.value.forEach { groupName ->
//                val group = PluginFormat.decodeFromSource(PolymorphicSerializer(PluginToken::class), encoded) as GRUP
//                assertEquals(groupName, group.header.groupProperties.label.string)
//                println(
//                    "verified ${debugString(group.header)} ${
//                    group.children
//                        .mapNotNull { it as? PluginRecord }
//                        .mapNotNull { it.fields as? Fields }
//                        .flatMap { it.list }
//                        .filter { it.fieldType.typeTag.string == "EDID" }
//                        .joinToString {
//                            PluginFormat.decodeFromByteString(
//                                NullTerminatedString.serializer(),
//                                it.fieldValue.value
//                            ).string
//                        }
//                }")
//            }
//
//            buildList {
//                add(TypeTag.serializer())
////                add(GroupHeader.serializer())
////                add(PolymorphicSerializer(PluginToken::class))// WRLD
//////                add(PolymorphicSerializer(PluginToken::class))// GRUP
////                add(TypeTag.serializer())
////                add(GroupHeader.serializer())
////                add(PolymorphicSerializer(PluginToken::class))// CELL
////                add(PolymorphicSerializer(PluginToken::class))// GRUP 6
////                add(PolymorphicSerializer(PluginToken::class))// GRUP 4
////                add(PolymorphicSerializer(PluginToken::class))// GRUP 4
////                add(PolymorphicSerializer(PluginToken::class))// GRUP 4
////                add(PolymorphicSerializer(PluginToken::class))// GRUP 4
////                add(PolymorphicSerializer(PluginToken::class))// GRUP 4
////                add(TypeTag.serializer())
////                add(GroupHeader.serializer())// GRUP 4
////                add(TypeTag.serializer())
////                add(GroupHeader.serializer())// GRUP 5
////                repeat(36) {
////                    add(PolymorphicSerializer(PluginToken::class))// CELL
////                    add(PolymorphicSerializer(PluginToken::class))// GRUP 6
////                }
////                repeat(1) {
////                    add(PolymorphicSerializer(PluginToken::class))// GRUP 5
////                }
////                add(TypeTag.serializer())
////                add(GroupHeader.serializer())// GRUP 5
////                repeat(30) {
////                    add(PolymorphicSerializer(PluginToken::class))// CELL
////                    add(PolymorphicSerializer(PluginToken::class))// GRUP 6
////                }
////                add(PolymorphicSerializer(PluginToken::class))// CELL
////                add(TypeTag.serializer())
////                add(GroupHeader.serializer())// GRUP 6
////                add(TypeTag.serializer())
////                add(GroupHeader.serializer())// GRUP 9
////                add(PolymorphicSerializer(PluginToken::class))// LAND
////                add(PolymorphicSerializer(PluginToken::class))// NAVM
////                add(PolymorphicSerializer(PluginToken::class))// NAVM
////                add(PolymorphicSerializer(PluginToken::class))// GRUP 8
////                add(TypeTag.serializer())// GRUP 8
////                add(GroupHeader.serializer())
////                add(PolymorphicSerializer(PluginToken::class))// CELL
////                add(PolymorphicSerializer(PluginToken::class))// GRUP
////                add(PolymorphicSerializer(PluginToken::class))// CELL
////                add(PolymorphicSerializer(PluginToken::class))// GRUP 6
////                add(PolymorphicSerializer(PluginToken::class))// CELL
////                add(PolymorphicSerializer(PluginToken::class))// GRUP 6
////                add(PolymorphicSerializer(PluginToken::class))// CELL
////                add(PolymorphicSerializer(PluginToken::class))// GRUP 6
////                add(PolymorphicSerializer(PluginToken::class))// CELL
////                add(PolymorphicSerializer(PluginToken::class))// GRUP 6
////                add(PolymorphicSerializer(PluginToken::class))// CELL
////                add(PolymorphicSerializer(PluginToken::class))// GRUP 6
////                add(PolymorphicSerializer(PluginToken::class))// CELL
////                add(PolymorphicSerializer(PluginToken::class))// GRUP 6
////                add(PolymorphicSerializer(PluginToken::class))// CELL
////                add(PolymorphicSerializer(PluginToken::class))// GRUP 6
////                repeatPeek(1)
//            }.forEachIndexed { index, serializer ->
//                PluginFormat.decodeFromSource(serializer, encoded)
//                    .also { println("$index, ${debugString(it)}") }
//            }
        }
    }

    @Test
    fun `faster tokens`() {
        val bufferedMasterFile = SystemFileSystem
            .source(
                Path(
                    "/",
                    "media",
                    "zymus",
                    "5516E98402BDA1A5",
                    "SteamLibrary",
                    "steamapps",
                    "common",
                    "Skyrim Special Edition",
                    "Data",
                    "Skyrim.esm"
                )
            )
            .buffered()

        // first read TES4
        val peekSource = bufferedMasterFile.peek()
        val typeTag = PluginFormat.decodeFromSource(TypeTag.serializer(), peekSource)
        val recordSize = PluginFormat.decodeFromSource(RecordSize.serializer(), peekSource)
        val totalSize = recordSize.int.toUInt().toLong() + 24
        println("${debugString(typeTag)} ${debugString(recordSize)} $totalSize")
        bufferedMasterFile.skip(totalSize)

        // GRUPs
        repeat(118) {
            val grupPeek = bufferedMasterFile.peek()
            val grupTag = PluginFormat.decodeFromSource(TypeTag.serializer(), grupPeek)
            val grupSize = PluginFormat.decodeFromSource(GroupSize.serializer(), grupPeek)
            println("$it ${debugString(grupTag)} ${debugString(grupSize)}")
            bufferedMasterFile.skip(grupSize.uint.toLong())
        }
    }

    @Test
    fun `faster tokens with sequence`() {
        val bufferedMasterFile = SystemFileSystem
            .source(
                Path(
                    "/",
                    "media",
                    "zymus",
                    "5516E98402BDA1A5",
                    "SteamLibrary",
                    "steamapps",
                    "common",
                    "Skyrim Special Edition",
                    "Data",
//                    "Update.esm"     //  153724
                    "Skyrim.esm"     // 4387995
//                    "HearthFires.esm"//  127665
                )
            )
            .buffered()

        val sequence = PluginFormat.decodeMarkerSequenceFromSource(bufferedMasterFile.peek())
//        sequence.filter { it.tag.string == "EDID" }.forEach(::println)
//        sequence.last().run(::println)
//        val npcEDIDs = sequence.dropWhile { it.tag.string != NPC.SERIAL_NAME }.takeWhile { it.tag.string != GRUP.SERIAL_NAME }.forEach(::println)
        sequence
//            .toList()
            .count()
            .run(::println)
//            .filter(StreamingToken::isDataCompressed)
//            .filter { it.tag.string == "RELA" }
//            .first()
//            .let { firstMarker ->
//                println("reading from $this")
//                val intermediateSource = bufferedMasterFile.peek()
//                intermediateSource.skip(firstMarker.skip)
//
//                while (!intermediateSource.exhausted()) {
//                    val pluginToken =
//                        PluginFormat.decodeFromSource(PolymorphicSerializer(PluginToken::class), intermediateSource)
//
//                    when (pluginToken) {
//                        is RELA ->
//                            let { pluginToken.fields as Fields }
//                                .list
//                                .single { it.fieldType.typeTag.string == "EDID" }
//                                .run {
//                                    PluginFormat.decodeFromByteString(
//                                        NullTerminatedString.serializer(),
//                                        fieldValue.value
//                                    )
//                                }
//                                .also(::println)
//
//                        else -> TODO()
//                    }
//                }
//            }


    }

    private fun debugString(it: Any): String =
        when (it) {
            is GroupSize -> listOf(
                "groupSize",
                it.uint.toString()
            ).joinToString(" ")

            is GroupHeader -> {
                listOf(
                    "groupHeader",
                    debugString(it.groupProperties.label),
                    "groupType",
                    it.groupProperties.groupType,
                    debugString(it.groupSize)
                ).joinToString(" ")
            }

            is RecordSize -> listOf(
                "recordSize",
                it.int.toString()
            ).joinToString(" ")

            is RecordType -> listOf(
                "recordType",
                debugString(it.typeTag)
            ).joinToString(" ")

            is RecordHeader ->
                listOf(
                    "recordHeader",
                    debugString(it.recordSize),
                    "compressed",
                    it.recordProperties.isDataCompressed
                ).joinToString(" ")

            is Record -> {
                listOf(
                    "record",
                    debugString(it.tag),
                    it.header.recordProperties.recordId.toHexString(),
                    debugString(it.header.recordSize),
                    "compressed",
                    it.header.recordProperties.isDataCompressed,
                ).joinToString(" ")
            }

            is UInt -> "UInt ${it.toHexString()}"

            is TypeTag -> listOf(
                "TypeTag",
                if (it.string.all { it.isLetterOrDigit() || it == '_' }) {
                    it.string
                } else {
                    it.string.hashCode().toHexString()
                }
            ).joinToString(" ")

            is GRUP -> listOf(
                "GRUP",
                debugString(it.header)
            ).joinToString(" ")

            else -> "unknown ${it::class}"
        }

    private fun MutableList<KSerializer<out Any>>.repeatPeek(times: Int) {
        // preview peek
        repeat(times) {
            addAll(
                listOf(
                    TypeTag.serializer(),
                    UInt.serializer(),
                    UInt.serializer(),
                    UInt.serializer(),
                    UInt.serializer(),
                    UInt.serializer(),
                )
            )
        }
    }

    private fun MutableList<KSerializer<out Any>>.cellRecord() {
    }

    private fun MutableList<KSerializer<out Any>>.topLevelCellGroup() {
//                    repeat(1) {// outermost group
//                        add(GroupHeader.serializer())// 0
//                    }
//                    add(Record.serializer())// first CELL record in top-level group
//                    add(GroupHeader.serializer())// header for cell children 6
//                    add(Group.serializer())
//                    add(Group.serializer())
//
//                    add(Record.serializer())// first CELL record in top-level group
//                    add(GroupHeader.serializer())// header for cell children 6
//                    add(Group.serializer())
//                    add(Group.serializer())
//                    listOf(
//                        listOf(2, 5, 6, 2, 6, 9, 5, 9, 5, 8),
//                        listOf(3, 7, 8, 4, 5, 5, 4, 5, 5, 7),
//                        listOf(7, 7, 6, 4, 3, 5, 4, 7, 5, 7),
//                        listOf(4, 7, 7, 6, 4, 6, 3, 4, 6, 6),
//                        listOf(1)
//                    ).forEach { subgroup ->
////                        add(GroupHeader.serializer())// 2
////                        subgroup.forEach { records ->
//////                            add(GroupHeader.serializer())// 3
//////                            repeat(records) {
//////                                add(RecordAndGroup.serializer())
//////                            }
////                            add(Group.serializer())
////                        }
//                        add(Group.serializer())
//                    }
//
//
//                    add(Group.serializer())
    }

    private fun MutableList<KSerializer<out Any>>.world(size: Int) {
        add(Record.serializer())// Main WRLD
//        add(Group.serializer())
        add(GroupHeader.serializer())// GRUP (CELL) 1
        cellRecord()
        repeat(size) {
            // group 4s
        }
    }

    private fun MutableList<KSerializer<out Any>>.cellBlock() {
        add(GroupHeader.serializer())
        add(GroupHeader.serializer())
    }
}
