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
import games.studiohummingbird.cultoftheancestormoth.bytestring.serializer.ByteStringSerializer
import games.studiohummingbird.cultoftheancestormoth.bytestring.serializer.decodeFromByteString
import games.studiohummingbird.cultoftheancestormoth.serialization.PluginFormat
import games.studiohummingbird.cultoftheancestormoth.serialization.annotations.fixedLength
import games.studiohummingbird.cultoftheancestormoth.serialization.annotations.isFixedLength
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.NullTerminatedString
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.nullTerminatedStringDecoder
import games.studiohummingbird.cultoftheancestormoth.serialization.decodeWindows1252
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Field
import kotlinx.io.Buffer
import kotlinx.io.Source
import kotlinx.io.bytestring.ByteString
import kotlinx.io.readByteString
import kotlinx.io.readDoubleLe
import kotlinx.io.readFloatLe
import kotlinx.io.readIntLe
import kotlinx.io.readLongLe
import kotlinx.io.readShortLe
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.PolymorphicKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.encoding.AbstractDecoder
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.CompositeDecoder.Companion.DECODE_DONE
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule
import kotlin.time.Duration
import kotlin.time.measureTime

val inlineBuffer = Buffer()
var totalDecodeInlineTime: Duration = Duration.ZERO

@OptIn(ExperimentalStdlibApi::class, ExperimentalSerializationApi::class)
val inlineDecoder = BethesdaBufferDecoder(inlineBuffer, EmptySerializersModule(), ByteStringSerializer.descriptor)

@ExperimentalStdlibApi
@ExperimentalSerializationApi
class BethesdaBufferDecoder(
    private val source: Source,
    override val serializersModule: SerializersModule,
    private val serialDescriptor: SerialDescriptor,
) : AbstractDecoder(), FieldDecoder, ByteStringDecoder {

    private val stringDecoder by lazy { source.decodeWindows1252() }

    private var structureElementIndex = 0

    override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder {
        return BethesdaBufferDecoder(source, serializersModule, descriptor)
    }

    override fun endStructure(descriptor: SerialDescriptor) {
        structureElementIndex++
    }

    override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
        return when (descriptor.kind) {
            StructureKind.LIST -> if (!source.request(1)) { DECODE_DONE } else { structureElementIndex++ }
            StructureKind.CLASS -> if (structureElementIndex >= descriptor.elementsCount) { DECODE_DONE } else { structureElementIndex++ }
            PolymorphicKind.OPEN -> if (structureElementIndex >= descriptor.elementsCount || source.exhausted()) {
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
                source.readTo(inlineBuffer, descriptor.fixedLength.length.toLong())
                inlineDecoder
            }
            descriptor == NullTerminatedString.serializer().descriptor -> nullTerminatedStringDecoder(source)
            else -> this
        }
    }

    override fun decodeByte(): Byte = source.readByte()

    override fun decodeShort(): Short = source.readShortLe()

    override fun decodeInt(): Int = source.readIntLe()

    override fun decodeLong(): Long = source.readLongLe()

    override fun decodeFloat(): Float = source.readFloatLe()

    override fun decodeDouble(): Double = source.readDoubleLe()

    override fun decodeString(): String {
        totalDecodeInlineTime += measureTime {
            if (structureElementIndex == 1 && serialDescriptor.kind == PolymorphicKind.OPEN) {
                return PluginFormat.decodeFromByteString(String.serializer(), decodeByteString(4))
            } else {
                return stringDecoder.decodeString()
            }
        }
    }

    override fun decodeField(): Field {
        TODO()
    }

    override fun decodeByteString(): ByteString = source.readByteString()

    override fun decodeByteString(byteCount: Int): ByteString =
        if (byteCount == 0) {
            ByteString()
        } else {
            source.readByteString(byteCount)
        }
}
