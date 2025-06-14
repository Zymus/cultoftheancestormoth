/**
Cult of the Ancestor Moth (ObjectBoundsSerializer.kt)
Copyright (C) 2024  Zymus (moore.zyle@gmail.com)

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
package games.studiohummingbird.cultoftheancestormoth.serialization

import games.studiohummingbird.cultoftheancestormoth.bytestring.serializer.decodeFromByteString
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.TypeTag
import games.studiohummingbird.cultoftheancestormoth.serialization.encoding.BethesdaBufferDecoder
import games.studiohummingbird.cultoftheancestormoth.serialization.encoding.BethesdaBufferEncoder
import games.studiohummingbird.cultoftheancestormoth.serialization.encoding.ByteStringStreamingDecoder
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.GRUP
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.GroupSize
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RecordSize
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.PluginElementMarker
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.recordValueModule
import kotlinx.io.Buffer
import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.bytestring.ByteString
import kotlinx.io.readByteArray
import kotlinx.serialization.BinaryFormat
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.modules.SerializersModule

@OptIn(ExperimentalSerializationApi::class, ExperimentalStdlibApi::class)
object PluginFormat : BinaryFormat, BufferFormat {
    override val serializersModule: SerializersModule = SerializersModule {
        include(polymorphicPrimitiveModule)
        include(recordValueModule)
        include(polymorphicPluginToken)
    }

    override fun <T> decodeFromByteArray(deserializer: DeserializationStrategy<T>, bytes: ByteArray): T {
        val decoder = ByteStringStreamingDecoder(serializersModule, ByteString(bytes))
        val deserializedFromBytes = deserializer.deserialize(decoder)
        return deserializedFromBytes
    }

    override fun <T> encodeToByteArray(serializer: SerializationStrategy<T>, value: T): ByteArray {
        val buffer = Buffer()
        val encoder = BethesdaBufferEncoder(buffer, serializersModule)
        val serializedToBytes = run {
            serializer.serialize(encoder, value)
            buffer.readByteArray()
        }
        return serializedToBytes
//            .also { println("${serializer.descriptor} ${it.toHexString()}") }
    }

    override fun <T : Any> decodeFromSource(deserializer: DeserializationStrategy<T>, source: Source): T {
        val decoder = BethesdaBufferDecoder(source, serializersModule, deserializer.descriptor)
        val deserializedFromBytes = deserializer.deserialize(decoder)
        return deserializedFromBytes
//            .also(::println)
    }

    override fun <T : Any> encodeToSink(serializer: SerializationStrategy<T>, value: T): Sink {
        val sink = Buffer()
        val encoder = BethesdaBufferEncoder(sink, serializersModule)
        val serializedToBytes = run {
            serializer.serialize(encoder, value)
            sink
        }
        return serializedToBytes
    }

    private val recordTypes = setOf(
        "TES4",
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
        "REFR",
        "WRLD",
        "DIAL",
        "QUST",
        "IDLE",
        "PACK",
        "CSTY",
        "LSCR",
        "LVSP",
        "ANIO",
        "WATR",
        "EFSH",
        "EXPL",
        "DEBR",
        "IMGS",
        "IMAD",
        "FLST",
        "PERK",
        "BPTD",
        "ADDN",
        "AVIF",
        "CAMS",
        "CPTH",
        "VTYP",
        "MATT",
        "IPCT",
        "IPDS",
        "ARMA",
        "ECZN",
        "LCTN",
        "MESG",
        "RGDL",
        "DOBJ",
        "LGTM",
        "MUSC",
        "FSTP",
        "FSTS",
        "SMBN",
        "SMQN",
        "SMEN",
        "DLBR",
        "MUST",
        "DLVW",
        "WOOP",
        "SHOU",
        "EQUP",
        "RELA",
        "SCEN",
        "ASTP",
        "OTFT",
        "ARTO",
        "MATO",
        "MOVT",
        "HAZD",// second empty HAZD group
        "SNDR",
        "DUAL",
        "SNCT",
        "SOPM",
        "COLL",
        "CLFM",
        "REVB",
        "ACHR",
        "NAVM",
        "PGRE",
        "PHZD",
        "LAND",
        "INFO",
        "VOLI"
    )

    fun decodeMarkerSequenceFromByteString(byteString: ByteString): Sequence<PluginElementMarker> {
        var sourcePosition = 0L
        return sequence {
            while (sourcePosition < byteString.size) {
                val startPosition = sourcePosition
                val type = decodeFromByteString(TypeTag.serializer(), byteString, sourcePosition.toInt(), 4)

                sourcePosition += 4

                val isGroup = type.string == GRUP.SERIAL_NAME
                val isRecord = type.string in recordTypes
                val isField = !isGroup && !isRecord

                var elementSize: Long

                if (isGroup) {
                    elementSize = decodeFromByteString(
                        GroupSize.serializer(),
                        byteString,
                        sourcePosition.toInt(),
                        4
                    ).uint.toLong()

                    sourcePosition += 4

                    sourcePosition += 16

                    yield(
                        PluginElementMarker(
                            type,
                            startPosition,
                            elementSize,
                            PluginElementMarker.Type.GROUP,
                            false
                        )
                    )
                } else if (isRecord) {
                    val recordSize = decodeFromByteString(
                        Int.serializer(),
                        byteString,
                        sourcePosition.toInt(),
                        4
                    ).toUInt().toLong()

                    if (recordSize > 4004967224) {
                        TODO("wrong size $recordSize")
                    }
                    elementSize = recordSize + 24
                    sourcePosition += 4

                    val isDataCompressed: Boolean = decodeFromByteString(
                        Int.serializer(),
                        byteString,
                        sourcePosition.toInt(),
                        4
                    ) and 0x40000 == 0x40000

                    sourcePosition += 4

                    sourcePosition += 12

                    if (isDataCompressed) {// if compressed, position to next record, not fields
                        sourcePosition += recordSize
                    }

                    // position now ready to read field
                    yield(
                        PluginElementMarker(
                            type,
                            startPosition,
                            elementSize,
                            PluginElementMarker.Type.RECORD,
                            isDataCompressed
                        )
                    )
                } else if (isField) {
                    val fieldSize: Long = decodeFromByteString(
                        Short.serializer(),
                        byteString,
                        sourcePosition.toInt(),
                        2
                    ).toUShort().toLong()

                    elementSize = fieldSize + 6
                    sourcePosition += 2

                    yield(
                        PluginElementMarker(
                            type,
                            startPosition,
                            elementSize,
                            PluginElementMarker.Type.FIELD,
                            false
                        )
                    )

                    if (type.string == "XXXX") {
                        val followingFieldSize = decodeFromByteString(UInt.serializer(), byteString, sourcePosition.toInt(), 4).toLong()

                        sourcePosition += 4

                        val followingFieldPosition = sourcePosition
                        val followingFieldType = decodeFromByteString(TypeTag.serializer(), byteString, sourcePosition.toInt(), 4)
                        sourcePosition += 4

                        elementSize = followingFieldSize + 6

                        // skip next field size, since it's 0/from xxxxFieldvalue
                        sourcePosition += 2

                        // skip value
                        sourcePosition += followingFieldSize

                        yield(
                            PluginElementMarker(
                                followingFieldType,
                                skip = followingFieldPosition,
                                size = elementSize,
                                PluginElementMarker.Type.FIELD,
                                false
                            )
                        )
                        // source position should be ready to read the next thing
                    } else {
                        sourcePosition += fieldSize
                    }
                }
            }

            // fieldsize inline
            // js
            // 1m 23.096s, 466ms, 6.768s, 5.514s, 27.522s, 0s, 0s
            // 1m 23.039s, 450ms, 6.73s, 5.576s, 26.978s, 0s, 0s
            // 1m 24.661s, 482ms, 6.769s, 5.636s, 27.452s, 0s, 0s
            // jvm
            // 1.696471530s, 11.727634ms, 215.929210ms, 226.309469ms, 589.155905ms, 32.297us, 2.2us
            // 1.807724077s, 11.385516ms, 157.970618ms, 149.373956ms, 602.136577ms, 31.608us, 2.877us
            // 1.621915260s, 12.156239ms, 177.644043ms, 292.651642ms, 846.020982ms, 52.201us, 2.114us
            //
            // fielsize ushort
            // js
            // 1m 21.355s, 465ms, 6.681s, 5.397s, 26.082s, 0s, 0s
            // 1m 25.292s, 430ms, 6.89s, 5.607s, 26.98s, 0s, 0s
            // 1m 23.742s, 436ms, 6.701s, 5.644s, 23.565s, 0s, 0s
            // 1m 22.948s, 470ms, 6.684s, 5.514s, 22.708s, 0s, 0s
            // jvm
            // 1.649838320s, 12.630822ms, 182.123703ms, 163.819700ms, 797.389269ms, 36.982us, 2.073us
            // 1.398357988s, 11.671792ms, 157.024320ms, 144.928280ms, 768.156098ms, 34.642us, 2.364us
            // 1.690950099s, 10.988019ms, 154.176983ms, 146.981722ms, 705.293220ms, 29.137us, 1.959us

            // dedicated inline buffer
            // jvm
            // 4387994, 50494, 869688, 3467813, 1.965857275s, 14.173881ms, 185.916601ms, 207.069730ms, 679.574765ms, 30.648us, 3.18us
            // 4387994, 50494, 869688, 3467813, 1.250448790s, 14.536106ms, 180.673293ms, 150.030008ms, 705.009939ms, 52.755us, 3.453us
        }
    }

    fun decodeMarkerSequenceFromSource(source: Source): Sequence<PluginElementMarker> {
        // [TES4][54][54 bytes]
        // [24 Bytes][54 bytes]
        // 0: TES4, skip = 0, size = 78 (54 + 24)
        // [HEDR][2 bytes][12 bytes]
        // [4   + 2 bytes][12 bytes]
        // 1: HEDR, skip = 24, size = 18
        // [CNAM][2 bytes][x bytes]
        // 2: CNAM, skip = 24 + 18
        var sourcePosition = 0L
        return sequence {
            while (!source.exhausted()) {
                val startPosition = sourcePosition
                val type = decodeFromSource(TypeTag.serializer(), source)
                sourcePosition += 4

                val isGroup = type.string == GRUP.SERIAL_NAME
                val isRecord = type.string in recordTypes
                val isField = !isGroup && !isRecord

                var elementSize: Long

                if (isGroup) {
                    elementSize = decodeFromSource(GroupSize.serializer(), source).uint.toLong()
                    sourcePosition += 4

                    source.skip(16)// skip the rest of the GRUP header
                    sourcePosition += 16

                    yield(
                        PluginElementMarker(
                            type,
                            startPosition,
                            elementSize,
                            PluginElementMarker.Type.GROUP,
                            false
                        )
                    )
                } else if (isRecord) {
                    val recordSize = decodeFromSource(RecordSize.serializer(), source).int.toUInt().toLong()
                    elementSize = recordSize + 24
                    sourcePosition += 4

                    val isDataCompressed = decodeFromSource(Int.serializer(), source) and 0x40000 == 0x40000
                    sourcePosition += 4

                    source.skip(12)// remaining bytes of record header, after <tag> <size> <flags>
                    sourcePosition += 12

                    if (isDataCompressed) {// if compressed, position to next record, not fields
                        source.skip(recordSize)
                        sourcePosition += recordSize
                    }

                    // position now ready to read field
                    yield(
                        PluginElementMarker(
                            type,
                            startPosition,
                            elementSize,
                            PluginElementMarker.Type.RECORD,
                            isDataCompressed
                        )
                    )
                } else if (isField) {
                    val fieldSize = decodeFromSource(UShort.serializer(), source).toLong()
                    elementSize = fieldSize + 6
                    sourcePosition += 2

                    yield(
                        PluginElementMarker(
                            type,
                            startPosition,
                            elementSize,
                            PluginElementMarker.Type.FIELD,
                            false
                        )
                    )

                    if (type.string == "XXXX") {
                        val followingFieldSize = decodeFromSource(UInt.serializer(), source).toLong()
                        sourcePosition += 4

                        val followingFieldPosition = sourcePosition
                        val followingFieldType = decodeFromSource(TypeTag.serializer(), source)
                        sourcePosition += 4

                        elementSize = followingFieldSize + 6

                        // skip next field size, since it's 0/from xxxxFieldvalue
                        source.skip(2)
                        sourcePosition += 2

                        // skip value
                        source.skip(followingFieldSize)
                        sourcePosition += followingFieldSize

                        yield(
                            PluginElementMarker(
                                followingFieldType,
                                skip = followingFieldPosition,
                                size = elementSize,
                                PluginElementMarker.Type.FIELD,
                                false
                            )
                        )
                        // source position should be ready to read the next thing
                    } else {
                        source.skip(fieldSize)
                        sourcePosition += fieldSize
                    }
                }
            }
        }
    }
}

/**
 * The <serializer> communicates <codec elements> with the <codec>.
 * There are many <codec element types> that can be communicated.
 * Complex <codec elements> are made of simpler <codec elements>.
 * Some examples of <codec elements> are
 * - fixed width integers (byte, short, int, long)
 * - floating point numbers (float, double)
 * - strings
 * - chars
 * - collections
 * - other serializable values
 *
 * Some <formats> have <format-specific> <codec elements>.
 * These <format-specific> <codec elements> may not be generally useful across multiple <formats> or multiple <codecs>.
 *
 * Since all <codec elements> must have an associated <serializer>, all <codec elements> are inherently <serializable>.
 *
 * While <serializable> <codec elements> are supported with a <compiler-generated> serializer, it may be simpler to have a different <codec element communication channel> to support communication of that <codec element> between the <serializer> and the <codec>.
 *
 * For example, <codec element type>:<codec element communication channel>
 * - Int:encodeInt
 * - Boolean:encodeBoolean
 * - String:encodeString
 *
 * With a different <codec element communication channel>, we also get
 * - Point2D:encodePoint2D
 *
 * Without a different <codec element communication channel>, we end up with
 * - Point2D:encodeSerializableValue
 *
 * even though it's effectively the same call, it keeps the code more symmetrical.
 *
 * # Split
 *
 */
