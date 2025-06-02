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

import games.studiohummingbird.cultoftheancestormoth.serialization.encoding.BethesdaBufferDecoder
import games.studiohummingbird.cultoftheancestormoth.serialization.encoding.BethesdaBufferEncoder
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.recordValueModule
import kotlinx.io.Buffer
import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.readByteArray
import kotlinx.serialization.BinaryFormat
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.modules.SerializersModule

@OptIn(ExperimentalSerializationApi::class, ExperimentalStdlibApi::class)
object PluginFormat : BinaryFormat, BufferFormat {
    override val serializersModule: SerializersModule = SerializersModule {
        include(polymorphicPrimitiveModule)
        include(recordValueModule)
        include(polymorphicPluginToken)
    }

    override fun <T> decodeFromByteArray(deserializer: DeserializationStrategy<T>, bytes: ByteArray): T {
        val buffer = Buffer().apply { write(bytes) }
        val decoder = BethesdaBufferDecoder(buffer, serializersModule, deserializer.descriptor)
        val deserializedFromBytes = deserializer.deserialize(decoder)
        return deserializedFromBytes
//            .also { println("${deserializer.descriptor} $it") }
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
