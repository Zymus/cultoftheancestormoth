package games.studiohummingbird.cultoftheancestormoth

import kotlinx.serialization.*
import kotlinx.serialization.encoding.AbstractEncoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.modules.SerializersModule
import java.nio.ByteBuffer

class PluginFormat : BinaryFormat {
    override val serializersModule: SerializersModule
        get() = TODO("Not yet implemented")

    override fun <T> decodeFromByteArray(deserializer: DeserializationStrategy<T>, bytes: ByteArray): T {
        TODO("Not yet implemented")
    }

    override fun <T> encodeToByteArray(serializer: SerializationStrategy<T>, value: T): ByteArray {
        TODO("Not yet implemented")
    }

    @ExperimentalSerializationApi
    private object PluginEncoder : AbstractEncoder() {
        override val serializersModule: SerializersModule
            get() = TODO("Not yet implemented")

        override fun encodeChar(value: Char) {
            super.encodeChar(value)
        }
    }
}

interface ByteBufferFormat : SerialFormat {
    fun <T> decodeFromByteBuffer(deserializer: DeserializationStrategy<T>, buffer: ByteBuffer) : T
    fun <T> encodeToByteBuffer(serializer: SerializationStrategy<T>, value: T) : ByteBuffer
}

fun Encoder.encodeUByte(ubyte: UByte) = encodeByte(ubyte.toByte())
fun Encoder.encodeUShort(ushort: UShort) = encodeShort(ushort.toShort())
fun Encoder.encodeUInt(uint: UInt) = encodeInt(uint.toInt())
fun Encoder.encodeULong(ulong: ULong) = encodeLong(ulong.toLong())
