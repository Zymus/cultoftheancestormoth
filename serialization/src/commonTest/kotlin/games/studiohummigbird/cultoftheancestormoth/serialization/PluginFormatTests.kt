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

import games.studiohummingbird.cultoftheancestormoth.serialization.PluginFormat
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.NullTerminatedString
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.TypeTag
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.CellRecord
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Field
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.FieldSize
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.FieldType
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.FieldValue
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Group
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.GroupHeader
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.GroupProperties
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.GroupSize
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.GroupTag
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Record
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RecordHeader
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RecordProperties
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RecordSize
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RecordType
import kotlinx.io.Buffer
import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
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
    fun `format record type`() {
        val encoded = PluginFormat.encodeToByteArray(TEST_RECORD_TYPE)
        val decoded: RecordType = PluginFormat.decodeFromByteArray(encoded)

        assertEquals(4, encoded.size)
        assertEquals(TEST_RECORD_TYPE, decoded)
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
        assertEquals(TEST_RECORD_TYPE, decoded.header.recordType)
        assertEquals(10, decoded.header.recordSize.int)
        assertEquals(TEST_RECORD_PROPERTIES, decoded.header.recordProperties)
    }

    @Test
    fun `format record value token`() {
        val encoded = PluginFormat.encodeToByteArray(TEST_RECORD_WITH_FIELDS)
        val decoded: Record = PluginFormat.decodeFromByteArray(encoded)

        assertEquals(34, encoded.size)
        assertEquals(TEST_RECORD_TYPE, decoded.header.recordType)
        assertEquals(10, decoded.header.recordSize.int)
        assertEquals(TEST_RECORD_PROPERTIES, decoded.header.recordProperties)
    }

    @Test
    fun `format record of subgroups`() {
        val encoded = PluginFormat.encodeToByteArray(TEST_RECORD_GROUP)
        println(encoded.toHexString())
        val decoded: Record = PluginFormat.decodeFromByteArray(encoded)

        assertEquals(24, encoded.size)

//        assertEquals(1, decoded.value.list.size)
    }

    @Test
    fun `format group tag`() {
        val encoded = PluginFormat.encodeToByteArray(GroupTag)
        val decoded: GroupTag = PluginFormat.decodeFromByteArray(encoded)

        assertEquals(4, encoded.size)
        assertEquals(GroupTag, decoded)
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
    fun `format group`() {
        val encoded = PluginFormat.encodeToByteArray(TEST_GROUP).run { Buffer().apply { write(this@run) } }
//        PluginFormat.decodeFromSource(GroupHeader.serializer(), encoded)
//        PluginFormat.decodeFromSource(RecordHeader.serializer(), encoded)
//        PluginFormat.decodeFromSource(Field.serializer(), encoded)
//        PluginFormat.decodeFromSource(Record.serializer(Fields.serializer()), encoded)
//        PluginFormat.decodeFromSource(Records.serializer(), encoded)
        val decoded = PluginFormat.decodeFromSource(Group.serializer(), encoded)

//        assertEquals(58, encoded.size)
//        assertEquals(TEST_GROUP, decoded)
    }

    @Test
    fun `read TES4 from Skyrim esm`() {
        val encoded = SystemFileSystem
            // /media/zymus/5516E98402BDA1A5/SteamLibrary/steamapps/common/Skyrim Special Edition/Data
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

        val decoded: Record = PluginFormat.decodeFromSource(Record.serializer(), encoded)

        val groups = listOf(
            "GMST",
            "KYWD",
            "LCRT",
            "AACT",
            "TXST",
            "GLOB",
            "CLAS",
            "FACT",
            "HDPT",// buffer becoming exhausted here now?
            "HAIR",
            "EYES",
            "RACE",
            "SOUN",
            "ASPC",
            "MGEF",
            "SCPT",
            "LTEX",
            "ENCH",
            "SPEL",
            "SCRL",
            "ACTI",
            "TACT",
            "ARMO",
            "BOOK",
            "CONT",
            "DOOR",
            "INGR",
            "LIGH",
            "MISC",
            "APPA",
            "STAT",
            "SCOL",
            "MSTT",
            "PWAT",
            "GRAS",
            "TREE",
            "CLDC",
            "FLOR",
            "FURN",
            "WEAP",
            "AMMO",
            "NPC_",// NPC_ is the first group with compressed records
            "LVLN",
            "KEYM",
            "ALCH",
            "IDLM",
            "COBJ",
            "PROJ",
            "HAZD",
            "SLGM",
            "LVLI",
            "WTHR",
            "CLMT",
            "SPGD",
            "RFCT",
            "REGN",
            "NAVI",
            "CELL",// has sub groups in the groups, under first record.
//            "WRLD",
//            "DIAL",
//            "QUST",
//            "IDLE",
//            "PACK",
//            "CSTY",
//            "LSCR",
//            "LVSP",
//            "ANIO",
//            "WATR",
//            "EFSH",
//            "EXPL",
//            "DEBR",
//            "IMGS",
//            "IMAD",
//            "FLST",
//            "PERK",
//            "BPTD",
//            "ADDN",
//            "AVIF",
//            "CAMS",
//            "CPTH",
//            "VTYP",
//            "MATT",
//            "IPCT",
//            "IPDS",
//            "ARMA",
//            "ECZN",
//            "LCTN",
//            "MESG",
//            "RGDL",
//            "DOBJ",
//            "LGTM",
//            "MUSC",
//            "FSTP",
//            "FSTS",
//            "SMBN",
//            "SMQN",
//            "SMEN",
//            "DLBR",
//            "MUST",
//            "DLVW",
//            "WOOP",
//            "SHOU",
//            "EQUP",
//            "RELA",
//            "SCEN",
//            "ASTP",
//            "OTFT",
//            "ARTO",
//            "MATO",
//            "MOVT",
//            "HAZD",
//            "SNDR",
//            "DUAL",
//            "SNCT",
//            "SOPM",
//            "COLL",
//            "CLFM",
//            "REVB"
        ).forEach { groupName ->
            val group = PluginFormat.decodeFromSource(Group.serializer(), encoded)
            assertEquals(groupName, group.header.groupProperties.label.string)
            group.records.list.forEach { record ->
                assertEquals(groupName, record.header.recordType.typeTag.string)
            }
            println("verified $groupName")
        }
//            .single { it.header.groupProperties.label.string == "AMMO" }
//            .also { println(it.header.groupSize) }
//            .also { println(it.records.list.size) }
//            .run {
//                records.list
//                    .apply { assertEquals(35, size) }
//                    .flatMap { it.fields }
//                    .filter { it.fieldType.typeTag.string == "EDID" }
//                    .map { PluginFormat.decodeFromByteString<NullTerminatedString>(it.fieldValue.value) }
////                    .forEach(::println)
//            }

        encoded
            .run {
                buildList {
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

                    // WLRD stuff

                    add(GroupHeader.serializer())// GRUP (WRLD) 0
//
//                    add(Record.serializer())// Main WRLD
//                    add(GroupHeader.serializer())// GRUP (CELL) 1
//                    add(CellRecord.serializer())
////                    // the above chunk reads until GRUP 4
////                    // the below chunk reads until the next WRLD record
//////                    add(Record.serializer())// Main CELL
//////                    add(Group.serializer())// GRUP 6
//                    repeat(168) {
//                        // group 4s
//                        add(Group.serializer())
//                    }

                    // second WRLD
//                    add(Record.serializer())// Main WRLD
//                    add(GroupHeader.serializer())// GRUP (CELL) 1
//                    add(CellRecord.serializer())
//                    repeat(6) {
//                        // group 4s
//                        add(Group.serializer())
//                    }

                    // repeatable WRLD
                    listOf(
                        168,
                        6,
                        1
                    ).forEach { group4Count ->
                        add(Record.serializer())// Main WRLD
                        add(GroupHeader.serializer())// GRUP (CELL) 1
                        add(CellRecord.serializer())
                        repeat(group4Count) {
                            // group 4s
                            add(Group.serializer())
                        }
                    }

//                    repeat(2) {
//                        add(Group.serializer())// GRUP 5 (groupHeader cellRecords)
//                    }
//                    add(GroupHeader.serializer())// HEADER 5
//                    repeat(1) {
//                        add(CellRecord.serializer())
//                    }
                    // / Top level (0)
                    //   / Records
                    //   / WRLD Children (1)
                    //     / CELL Record
                    //       / Cell Children (6)
                    //         / Cell Persistent Children (8)
                    //           / Record
                    //         / Cell Temporary Children (9)
                    //           / Record
                    //   / (Interior | Exterior)
                    //     / Cell Block (2 | 4)
                    //       / Cell Sub Block (3 | 5)
                    //         / Record
                    //         / Cell Children (6)
                    //           / Cell Persistent Children (8)
                    //             / Record
                    //           / Cell Temporary Children (9)
                    //             / Record
//                    listOf(
//                        listOf(16),
//                        listOf(16),
//                        listOf(16),
//                        listOf(4),
//                        listOf(1),
//                        listOf(4),
//                        listOf(16),
//                        listOf(16),
//                        listOf(16),
//                        listOf(16),
//                        listOf(16),
//                        listOf(10),
//                    ).forEach { subgroup ->
//                        add(GroupHeader.serializer())// GRUP 4 => GRUP 5
//                        subgroup.forEach { records ->
//                            repeat(records) {
//                                add(Group.serializer())// GRUP 5 => CELL
//                            }
//                        }
//                    }

//                    add(GroupHeader.serializer())
//                    add(Record.serializer())

                    // next GRUP 1
                    repeat(5) {
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
                }.mapIndexed { index, it ->
                    print("$index ")
                    val deserialized = PluginFormat.decodeFromSource(it, this)
                    println(debugString(deserialized))
                }
            }
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
                    it.groupProperties.label.hashCode().toHexString(),
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
                    debugString(it.recordType),
                    debugString(it.recordSize),
                    "compressed",
                    it.recordProperties.isDataCompressed
                ).joinToString(" ")

            is Record -> {
                listOf(
                    "record",
                    debugString(it.header.recordType.typeTag),
                    debugString(it.header.recordSize),
                    "compressed",
                    it.header.recordProperties.isDataCompressed,
                ).joinToString(" ")
            }

            is Group -> {
                listOf(
                    "group",
                    debugString(it.header),
                    "records",
                    it.records.list.size,
                    "subgroups",
                    "subGroupSizeSum",
                    it.subGroups.list.sumOf { it.header.groupSize.uint },
                    it.subGroups.list.joinToString(", ") { sub -> "${sub.header.groupProperties.groupType} ${sub.header.groupSize.uint}" },
                    "cellRecords",
                    it.cellRecords?.list?.size ?: "null"
                ).joinToString(" ")
            }

            is UInt -> "UInt ${it.toHexString()}"

            is CellRecord -> listOf(
                "cellRecord",
                debugString(it.cell.header.recordType),
                debugString(it.children.header.groupProperties.label),
                it.children.header.groupProperties.groupType,
                debugString(it.children.header.groupSize)
            ).joinToString(" ")

            is TypeTag -> listOf(
                "TypeTag",
                it.hashCode().toHexString(),
                it
            ).joinToString(" ")

            else -> "unknown ${it::class}"
        }
}
