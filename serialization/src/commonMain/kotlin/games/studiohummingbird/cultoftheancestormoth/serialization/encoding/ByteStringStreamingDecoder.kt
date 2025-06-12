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
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.encoding.AbstractDecoder
import kotlinx.serialization.encoding.CompositeDecoder.Companion.DECODE_DONE
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.modules.SerializersModule

@OptIn(ExperimentalSerializationApi::class)
class ByteStringStreamingDecoder(
    override val serializersModule: SerializersModule,
    private val byteString: ByteString,
    private val startIndex: Int = 0,
    private val endIndex: Int = byteString.size
) : AbstractDecoder(), ByteStringDecoder {

    private var offset: Int = 0
    private var structureElementIndex: Int = 0

    private val absoluteOffset: Int
        get() = startIndex + offset

    private val remaining: Int
        get() = endIndex - absoluteOffset

    override fun endStructure(descriptor: SerialDescriptor) {
        structureElementIndex++
    }

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

    override fun decodeInline(descriptor: SerialDescriptor): Decoder {
        return when {
            descriptor.isFixedLength -> {
                val fixedLength = descriptor.fixedLength.length
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
        byteString[startIndex + offset++]

    override fun decodeShort(): Short {
        require(2)
        val low = decodeByte().toInt()
        val high = decodeByte().toInt() shl 8
        val result = (high or low).toShort()
        return result
    }

    override fun decodeInt(): Int {
        require(4)
        val low = decodeShort().toInt()
        val high = decodeShort().toInt() shl 16
        val result = (high or low)
        return result
    }

    override fun decodeLong(): Long {
        require(8)
        val low = decodeInt().toLong()
        val high = decodeInt().toLong() shl 32
        val result = (high or low)
        return result
    }

    override fun decodeFloat(): Float {
        require(4)
        return Float.fromBits(decodeInt())
    }

    override fun decodeDouble(): Double {
        require(8)
        return Double.fromBits(decodeLong())
    }

    override fun decodeString(): String {
        val builder = StringBuilder(remaining)
        repeat(remaining) {
            builder.append(decodeByte())
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
        byteCount < remaining

    private fun require(byteCount: Int): Unit =
        require(request(byteCount)) { "EOF: required $byteCount, remaining $remaining" }
}
