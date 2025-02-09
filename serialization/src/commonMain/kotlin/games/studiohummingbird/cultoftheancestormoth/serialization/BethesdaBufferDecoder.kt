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
package games.studiohummingbird.cultoftheancestormoth.serialization

import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.InlineNullTerminatedString
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.nullTerminatedStringDecoder
import kotlinx.io.Buffer
import kotlinx.io.Source
import kotlinx.io.readByteArray
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.AbstractDecoder
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.CompositeDecoder.Companion.DECODE_DONE
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule

@ExperimentalStdlibApi
@ExperimentalSerializationApi
class BethesdaBufferDecoder(private val source: Source) : AbstractDecoder() {

    override val serializersModule: SerializersModule = EmptySerializersModule()

    private val primitiveBufferDecoder by lazy { PrintlnDecoder(PrimitiveBufferDecoder(source)) }

    private var structureElements = 0
    private var structureElementIndex = 0

    override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder {
        return BethesdaBufferDecoder(source).also {
            it.structureElements = descriptor.elementsCount
            it.structureElementIndex = 0
        }
    }

    override fun endStructure(descriptor: SerialDescriptor) {
        structureElements = 0
        structureElementIndex = 0
    }

    override fun decodeInline(descriptor: SerialDescriptor): Decoder {
        println("decodeInline $descriptor")
        return when (descriptor) {
            InlineNullTerminatedString.serializer().descriptor -> nullTerminatedStringDecoder(source)
            else -> this
        }
    }

    override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
        println("decodeElementIndex ${descriptor.serialName} $structureElements $structureElementIndex")
        return if (structureElementIndex == structureElements) { DECODE_DONE } else { structureElementIndex++ }
    }

    override fun decodeByte(): Byte = primitiveBufferDecoder.decodeByte()

    override fun decodeShort(): Short = primitiveBufferDecoder.decodeShort()

    override fun decodeInt(): Int = primitiveBufferDecoder.decodeInt()

    override fun decodeLong(): Long = primitiveBufferDecoder.decodeLong()

    override fun decodeFloat(): Float = primitiveBufferDecoder.decodeFloat()

    override fun decodeDouble(): Double = primitiveBufferDecoder.decodeDouble()

    override fun decodeString(): String {
        print("decodeString ")
        val bytes = source.readByteArray()
        val result = bytes.decodeWindows1252String()
        println(result)
        return result
    }
}

@OptIn(ExperimentalStdlibApi::class, ExperimentalSerializationApi::class)
fun bethesdaBufferDecoder(action: BethesdaBufferDecoder.() -> Unit): ByteArray =
    Buffer()
        .apply { BethesdaBufferDecoder(this).apply(action) }
        .readByteArray()
