package games.studiohummingbird.cultoftheancestormoth.serialization

import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.readByteArray
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.AbstractDecoder
import kotlinx.serialization.encoding.AbstractEncoder
import kotlinx.serialization.encoding.CompositeDecoder.Companion.UNKNOWN_NAME
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule

const val WINDOWS_1252_CHARSET_NAME = "windows-1252"

expect fun ByteArray.decodeWindows1252String(): String

expect fun String.toWindows1252ByteArray(): ByteArray

@ExperimentalSerializationApi
fun Sink.encodeWindows1252(): Encoder =
    object : AbstractEncoder() {
        override val serializersModule: SerializersModule = EmptySerializersModule()

        override fun encodeString(value: String) {
            val windows1252ByteArray = value.toWindows1252ByteArray()
            write(windows1252ByteArray)
        }
    }

@ExperimentalSerializationApi
fun Source.decodeWindows1252(): Decoder =
    object : AbstractDecoder() {
        override val serializersModule: SerializersModule = EmptySerializersModule()

        override fun decodeElementIndex(descriptor: SerialDescriptor): Int = UNKNOWN_NAME

        override fun decodeString(): String = readByteArray().decodeWindows1252String()
    }