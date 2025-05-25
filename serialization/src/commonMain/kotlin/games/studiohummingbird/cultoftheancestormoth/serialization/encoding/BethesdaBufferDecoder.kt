/**
Cult of the Ancestor Moth (BethesdaBufferDecoder.kt)
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
package games.studiohummingbird.cultoftheancestormoth.serialization.encoding

import games.studiohummingbird.cultoftheancestormoth.bytestring.serializer.ByteStringDecoder
import games.studiohummingbird.cultoftheancestormoth.serialization.GroupTagSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.annotations.fixedLength
import games.studiohummingbird.cultoftheancestormoth.serialization.annotations.isFixedLength
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.NullTerminatedString
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.TypeTag
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.nullTerminatedStringDecoder
import games.studiohummingbird.cultoftheancestormoth.serialization.decodeWindows1252
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Field
import kotlinx.io.Buffer
import kotlinx.io.Source
import kotlinx.io.bytestring.ByteString
import kotlinx.io.readByteString
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.encoding.AbstractDecoder
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.CompositeDecoder.Companion.DECODE_DONE
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.serializer

@ExperimentalStdlibApi
@ExperimentalSerializationApi
class BethesdaBufferDecoder(
    private val source: Source,
    override val serializersModule: SerializersModule
) : AbstractDecoder(), FieldDecoder, TypeTagDecoder, ByteStringDecoder {

    private val primitiveBufferDecoder by lazy { LittleEndianSourceDecoder(source) }
    private val stringDecoder by lazy { source.decodeWindows1252() }

    private var structureElements = 0
    private var structureElementIndex = 0

    override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder {
        return when(descriptor.serialName) {
            GroupTagSerializer.SERIAL_NAME -> {
                val decoder = BethesdaBufferDecoder(source, serializersModule)
                val groupTag: TypeTag = decoder.decodeSerializableValue(serializersModule.serializer())
                if (groupTag.string != "GRUP") {
                    throw SerializationException("expected GRUP: $groupTag")
                }
                decoder
            }
            else -> BethesdaBufferDecoder(source, serializersModule)// basically a subscope, same source, different elements and indexes
        }.also {
            it.structureElements = descriptor.elementsCount
            it.structureElementIndex = 0
        }
    }

    override fun endStructure(descriptor: SerialDescriptor) {
        structureElements = 0
        structureElementIndex = 0
    }

    override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
        return when (descriptor.kind) {
            StructureKind.LIST -> if (!source.request(1)) { DECODE_DONE } else { structureElementIndex++ }
            StructureKind.CLASS -> if (structureElementIndex == structureElements) { DECODE_DONE } else { structureElementIndex++ }
            else -> CompositeDecoder.UNKNOWN_NAME
        }
    }

    override fun decodeInline(descriptor: SerialDescriptor): Decoder {
        return when {
            descriptor.isFixedLength -> BethesdaBufferDecoder(Buffer().apply { source.readTo(this, descriptor.fixedLength.length.toLong()) }, serializersModule)
            descriptor == NullTerminatedString.serializer().descriptor -> nullTerminatedStringDecoder(source)
            else -> this
        }
    }

    override fun decodeByte(): Byte = source.readByte()

    override fun decodeShort(): Short = primitiveBufferDecoder.decodeShort()

    override fun decodeInt(): Int = primitiveBufferDecoder.decodeInt()

    override fun decodeLong(): Long = primitiveBufferDecoder.decodeLong()

    override fun decodeFloat(): Float = primitiveBufferDecoder.decodeFloat()

    override fun decodeDouble(): Double = primitiveBufferDecoder.decodeDouble()

    override fun decodeString(): String = stringDecoder.decodeString()

    override fun decodeField(): Field {
        TODO()
    }

    override fun decodeTypeTag(): TypeTag = decodeSerializableValue(serializersModule.serializer<TypeTag>())

    override fun decodeByteString(): ByteString = source.readByteString()

    override fun decodeByteString(byteCount: Int): ByteString = source.readByteString(byteCount)
}
