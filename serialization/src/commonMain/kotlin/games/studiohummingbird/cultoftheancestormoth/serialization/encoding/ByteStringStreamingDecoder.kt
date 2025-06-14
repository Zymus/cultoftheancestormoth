/**
Cult of the Ancestor Moth (ByteStringStreamingDecoder.kt)
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
package games.studiohummingbird.cultoftheancestormoth.serialization.encoding

import games.studiohummingbird.cultoftheancestormoth.bytestring.serializer.ByteStringDecoder
import games.studiohummingbird.cultoftheancestormoth.serialization.annotations.fixedLength
import games.studiohummingbird.cultoftheancestormoth.serialization.annotations.isFixedLength
import kotlinx.io.bytestring.ByteString
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.CompositeDecoder.Companion.DECODE_DONE
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.modules.SerializersModule

@OptIn(ExperimentalSerializationApi::class)
class ByteStringStreamingDecoder(
    override val serializersModule: SerializersModule,
    private val byteString: ByteString,
    private val startIndex: Int = 0,
    private val endIndex: Int = byteString.size,
) : Decoder, CompositeDecoder, ByteStringDecoder {

    private var offset: Int = 0
    private var structureElementIndex: Int = 0

    private val absoluteOffset: Int
        get() = startIndex + offset

    private val remaining: Int
        get() = endIndex - absoluteOffset

    private var serialDescriptorStack: ArrayDeque<SerialDescriptor> = ArrayDeque(10)

    override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder {
        serialDescriptorStack.addFirst(descriptor)
        return ByteStringStreamingDecoder(serializersModule, byteString, absoluteOffset).apply {
            serialDescriptorStack.addFirst(descriptor)
        }
    }

    override fun decodeBoolean(): Boolean {
        TODO("Not yet implemented")
    }

    override fun endStructure(descriptor: SerialDescriptor) {
        structureElementIndex++
        serialDescriptorStack.removeFirst()
    }

    override fun decodeBooleanElement(
        descriptor: SerialDescriptor,
        index: Int
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun decodeByteElement(
        descriptor: SerialDescriptor,
        index: Int
    ): Byte =
        decodeByte()

    override fun decodeCharElement(
        descriptor: SerialDescriptor,
        index: Int
    ): Char {
        TODO("Not yet implemented")
    }

    override fun decodeDoubleElement(
        descriptor: SerialDescriptor,
        index: Int
    ): Double =
        decodeDouble()

    override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
        return when (descriptor.kind) {
            StructureKind.LIST -> if (!request(1)) {
                DECODE_DONE
            } else {
                structureElementIndex++
            }

            StructureKind.CLASS -> if (structureElementIndex >= descriptor.elementsCount) {
                DECODE_DONE
            } else {
                structureElementIndex++
            }

            PolymorphicKind.OPEN -> if (structureElementIndex >= descriptor.elementsCount || !request(1)) {
                DECODE_DONE
            } else {
                structureElementIndex++
            }

            else -> structureElementIndex++
        }
    }

    override fun decodeFloatElement(
        descriptor: SerialDescriptor,
        index: Int
    ): Float =
        decodeFloat()

    override fun decodeInlineElement(
        descriptor: SerialDescriptor,
        index: Int
    ): Decoder =
        decodeInline(descriptor)

    override fun decodeIntElement(
        descriptor: SerialDescriptor,
        index: Int
    ): Int =
        decodeInt()

    override fun decodeLongElement(
        descriptor: SerialDescriptor,
        index: Int
    ): Long =
        decodeLong()

    @ExperimentalSerializationApi
    override fun <T : Any> decodeNullableSerializableElement(
        descriptor: SerialDescriptor,
        index: Int,
        deserializer: DeserializationStrategy<T?>,
        previousValue: T?
    ): T? {
        TODO("Not yet implemented")
    }

    override fun <T> decodeSerializableElement(
        descriptor: SerialDescriptor,
        index: Int,
        deserializer: DeserializationStrategy<T>,
        previousValue: T?
    ): T =
        deserializer.deserialize(this)

    override fun decodeShortElement(
        descriptor: SerialDescriptor,
        index: Int
    ): Short =
        decodeShort()

    override fun decodeStringElement(
        descriptor: SerialDescriptor,
        index: Int
    ): String {
        val stringLength: Int =
            if (descriptor.kind == PolymorphicKind.OPEN) { 4 }
            else { remaining }

        return decodeString(stringLength)
    }

    override fun decodeInline(descriptor: SerialDescriptor): Decoder {
        return when {
            descriptor.isFixedLength -> {
                val fixedLength = descriptor.fixedLength.length
                require(fixedLength)
                val decoder = ByteStringStreamingDecoder(
                    serializersModule,
                    byteString,
                    absoluteOffset,
                    absoluteOffset + fixedLength,
                )
                offset += fixedLength
                decoder
            }
            descriptor.kind == PolymorphicKind.OPEN -> {
                val fixedLength = 4
                require(fixedLength)
                val decoder = ByteStringStreamingDecoder(
                    serializersModule,
                    byteString,
                    absoluteOffset,
                    absoluteOffset + fixedLength
                )
                offset += fixedLength
                decoder
            }
//            descriptor == NullTerminatedString.serializer().descriptor -> nullTerminatedStringDecoder(source)
            else -> this
        }
    }

    override fun decodeByte(): Byte =
        decodeMaskedByte().toByte()

    override fun decodeChar(): Char {
        TODO("Not yet implemented")
    }

    override fun decodeShort(): Short =
        decodeMaskedShort().toShort()

    override fun decodeInt(): Int =
        decodeMaskedInt().toInt()

    override fun decodeLong(): Long =
        decodeMaskedLong().toLong()

    @ExperimentalSerializationApi
    override fun decodeNotNullMark(): Boolean {
        TODO("Not yet implemented")
    }

    @ExperimentalSerializationApi
    override fun decodeNull(): Nothing? {
        TODO("Not yet implemented")
    }

    override fun decodeFloat(): Float =
        Float.fromBits(decodeInt())

    override fun decodeDouble(): Double =
        Double.fromBits(decodeLong())

    override fun decodeEnum(enumDescriptor: SerialDescriptor): Int {
        TODO("Not yet implemented")
    }

    override fun decodeString(): String =
        decodeString(remaining)

    private fun decodeString(stringLength: Int): String {
        val builder = StringBuilder(stringLength)
        repeat(stringLength) {
            builder.append(decodeByte().toInt().toChar())
        }
        return builder.toString()
    }

    override fun decodeByteString(): ByteString =
        decodeByteString(remaining)

    override fun decodeByteString(byteCount: Int): ByteString {
        require(byteCount)
        val result = byteString.substring(absoluteOffset, absoluteOffset + byteCount)
        offset += byteCount
        return result
    }

    private fun skip(byteCount: Int) {
        require(byteCount)
        offset += byteCount
    }

    private fun request(byteCount: Int): Boolean =
        byteCount <= remaining

    private fun require(byteCount: Int): Unit =
        require(request(byteCount)) { "EOF: required $byteCount, remaining $remaining" }

    private fun decodeMaskedByte(): Int =
        byteString[startIndex + offset++].toInt() and BYTE_MASK

    private fun decodeMaskedShort(): Int {
        require(2)
        val low = decodeMaskedByte()
        val high = decodeMaskedByte() shl 8
        val result = (high or low) and SHORT_MASK
        return result
    }

    private fun decodeMaskedInt(): Long {
        require(4)
        val low = decodeMaskedShort()
        val high = decodeMaskedShort() shl 16
        val result = (high or low).toLong() and INT_MASK
        return result
    }

    private fun decodeMaskedLong(): ULong {
        require(8)
        val low = decodeMaskedInt()
        val high = decodeMaskedInt() shl 32
        val result = (high or low).toULong() and LONG_MASK
        return result
    }

    companion object {
        private const val BYTE_MASK = 0x000000FF
        private const val SHORT_MASK = 0x0000FFFF
        private const val INT_MASK = 0x00000000_FFFFFFFF
        private const val LONG_MASK = 0xFFFFFFFF_FFFFFFFFu
    }
}
