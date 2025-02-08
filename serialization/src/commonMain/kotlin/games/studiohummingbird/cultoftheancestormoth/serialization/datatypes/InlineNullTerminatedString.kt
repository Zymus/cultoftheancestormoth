/**
Cult of the Ancestor Moth (InlineNullTerminatedString.kt)
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
package games.studiohummingbird.cultoftheancestormoth.serialization.datatypes

import games.studiohummingbird.cultoftheancestormoth.serialization.annotations.NullTerminatedString
import games.studiohummingbird.cultoftheancestormoth.serialization.decodeWindows1252String
import games.studiohummingbird.cultoftheancestormoth.serialization.encodeWindows1252
import games.studiohummingbird.cultoftheancestormoth.serialization.readUntil
import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.readByteArray
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.AbstractDecoder
import kotlinx.serialization.encoding.AbstractEncoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule
import kotlin.jvm.JvmInline

@JvmInline
@Serializable
value class InlineNullTerminatedString(val value: String) {
    val length: Int
        get() = value.length
}

/**
 * @return an [Encoder] capable of encoding a null-terminated string.
 */
@ExperimentalSerializationApi
fun nullTerminatedStringEncoder(sink: Sink): Encoder =
    object : AbstractEncoder() {
        override val serializersModule: SerializersModule = EmptySerializersModule()

        override fun encodeString(value: String) {
            sink.encodeWindows1252().encodeString(value)
            sink.writeByte(0)
        }
    }

@ExperimentalSerializationApi
fun nullTerminatedStringDecoder(source: Source): Decoder =
    object : AbstractDecoder() {
        override val serializersModule: SerializersModule = EmptySerializersModule()

        override fun decodeElementIndex(descriptor: SerialDescriptor): Int = TODO()

        override fun decodeSequentially(): Boolean = true

        override fun decodeString(): String {
            val stringBuffer = source.readUntil(0)
            val stringBytes = stringBuffer.readByteArray()
            val decodedString = stringBytes.decodeWindows1252String()
            return decodedString
        }
    }

@ExperimentalSerializationApi
fun SerialDescriptor.isNullTerminatedString(index: Int): Boolean =
    getElementAnnotations(index).any { it is NullTerminatedString }
