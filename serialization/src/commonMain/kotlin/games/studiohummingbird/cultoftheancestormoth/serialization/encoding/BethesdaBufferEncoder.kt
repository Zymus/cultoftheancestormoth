package games.studiohummingbird.cultoftheancestormoth.serialization.encoding

import games.studiohummingbird.cultoftheancestormoth.bytestring.serializer.ByteStringEncoder
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.NullTerminatedString
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.nullTerminatedStringEncoder
import games.studiohummingbird.cultoftheancestormoth.serialization.encodeWindows1252
import kotlinx.io.Buffer
import kotlinx.io.Source
import kotlinx.io.bytestring.ByteString
import kotlinx.io.indexOf
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.AbstractEncoder
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.modules.SerializersModule

@OptIn(ExperimentalSerializationApi::class)
class BethesdaBufferEncoder(
    private val buffer: Buffer = Buffer(),
    override val serializersModule: SerializersModule
) : AbstractEncoder(), ByteStringEncoder {

    private val byteSinkEncoder by lazy { ByteSinkEncoder(buffer) }
    private val littleEndianSinkEncoder by lazy { LittleEndianSinkEncoder(buffer) }
    private val stringEncoder by lazy { buffer.encodeWindows1252() }
    private val nullTerminatedStringEncoder by lazy { nullTerminatedStringEncoder(buffer) }

    override fun beginCollection(descriptor: SerialDescriptor, collectionSize: Int): CompositeEncoder {
        when (descriptor.serialName) {
            "Record with byte string" -> {
            }
        }
        return beginStructure(descriptor)
    }

    override fun beginStructure(descriptor: SerialDescriptor): CompositeEncoder {
        return when (descriptor.serialName) {
            else -> this
        }
    }

    override fun endStructure(descriptor: SerialDescriptor) {
    }

    override fun encodeInline(descriptor: SerialDescriptor): Encoder {
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

    override fun encodeByteString(byteString: ByteString) = buffer.write(byteString.toByteArray())
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
