package games.studiohummingbird.cultoftheancestormoth.serialization.encoding

import games.studiohummingbird.cultoftheancestormoth.serialization.annotations.isRecord
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.NullTerminatedString
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.nullTerminatedStringEncoder
import games.studiohummingbird.cultoftheancestormoth.serialization.encodeWindows1252
import kotlinx.io.Buffer
import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.indexOf
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.AbstractEncoder
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule

@OptIn(ExperimentalSerializationApi::class)
class BethesdaBufferEncoder(private val sink: Sink = Buffer()) : AbstractEncoder() {

    override val serializersModule: SerializersModule = EmptySerializersModule()

    private val byteSinkEncoder by lazy { ByteSinkEncoder(sink) }
    private val littleEndianSinkEncoder by lazy { LittleEndianSinkEncoder(sink) }
    private val stringEncoder by lazy { sink.encodeWindows1252() }
    private val nullTerminatedStringEncoder by lazy { nullTerminatedStringEncoder(sink) }

    override fun beginCollection(descriptor: SerialDescriptor, collectionSize: Int): CompositeEncoder {
        println("beginCollection ${descriptor.serialName} $collectionSize")
        return beginStructure(descriptor)
    }

    override fun beginStructure(descriptor: SerialDescriptor): CompositeEncoder {
        println("beginStructure ${descriptor.serialName}")
        println("- kind=${descriptor.kind}")
        println("- annotations=${descriptor.annotations}")
        println("- elementsCount=${descriptor.elementsCount}")
        println(". isRecord=${descriptor.isRecord()}")

        return this
    }

    override fun endStructure(descriptor: SerialDescriptor) {
        println("endStructure kind=${descriptor.kind} ${descriptor.serialName}")
    }

    override fun encodeInline(descriptor: SerialDescriptor): Encoder {
        println("encodeInline $descriptor")
        return if (descriptor == NullTerminatedString.serializer().descriptor) {
            nullTerminatedStringEncoder
        }
        else {
            this
        }
    }

    override fun encodeByte(value: Byte) = byteSinkEncoder.encodeByte(value)

    override fun encodeShort(value: Short) = littleEndianSinkEncoder.encodeShort(value)

    override fun encodeInt(value: Int) = littleEndianSinkEncoder.encodeInt(value)

    override fun encodeLong(value: Long) = littleEndianSinkEncoder.encodeLong(value)

    override fun encodeFloat(value: Float) = littleEndianSinkEncoder.encodeFloat(value)

    override fun encodeDouble(value: Double) = littleEndianSinkEncoder.encodeDouble(value)

    override fun encodeString(value: String) = stringEncoder.encodeString(value)

    fun encodeBytes(byteArray: ByteArray) {
        println("encodeBytes size=${byteArray.size}")
        sink.write(byteArray)
    }
}

/**
 * Returns a portion of this Source, with bytes read until the specified value was reached.
 */
fun Source.readUntil(value: Byte): Source =
    Buffer().also {
        val indexOfValue = indexOf(value)
        readTo(it, indexOfValue)
        // read the value byte
        readByte()
    }
