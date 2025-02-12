package games.studiohummingbird.cultoftheancestormoth.serialization

import games.studiohummingbird.cultoftheancestormoth.serialization.annotations.isRecord
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.InlineNullTerminatedString
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.nullTerminatedStringEncoder
import kotlinx.io.Buffer
import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.indexOf
import kotlinx.io.readByteArray
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

    private val primitiveBufferEncoder by lazy { PrintlnEncoder(PrimitiveBufferEncoder(sink)) }
    private val stringEncoder by lazy { PrintlnEncoder(sink.encodeWindows1252()) }
    private val nullTerminatedStringEncoder by lazy { PrintlnEncoder(nullTerminatedStringEncoder(sink)) }

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
        return if (descriptor == InlineNullTerminatedString.serializer().descriptor) {
            nullTerminatedStringEncoder
        }
        else {
            this
        }
    }

    override fun encodeByte(value: Byte) = primitiveBufferEncoder.encodeByte(value)

    override fun encodeShort(value: Short) = primitiveBufferEncoder.encodeShort(value)

    override fun encodeInt(value: Int) = primitiveBufferEncoder.encodeInt(value)

    override fun encodeLong(value: Long) = primitiveBufferEncoder.encodeLong(value)

    override fun encodeFloat(value: Float) = primitiveBufferEncoder.encodeFloat(value)

    override fun encodeDouble(value: Double) = primitiveBufferEncoder.encodeDouble(value)

    override fun encodeString(value: String) = stringEncoder.encodeString(value)

    fun encodeBytes(byteArray: ByteArray) {
        println("encodeBytes size=${byteArray.size}")
        sink.write(byteArray)
    }
}

fun Source.readUntil(value: Byte): Source =
    Buffer().also { readTo(it, indexOf(value)) }

fun encodeToByteArray(action: BethesdaBufferEncoder.() -> Unit): ByteArray =
    Buffer()
        .apply { BethesdaBufferEncoder(this).apply(action) }
        .readByteArray()

typealias EncoderAction = BethesdaBufferEncoder.() -> Unit

fun encoderAction(action: EncoderAction): EncoderAction = action

// region structural


fun BethesdaBufferEncoder.encodeRecord(recordTag: String, encodeRecordData: BethesdaBufferEncoder.() -> Unit) {
    val flags = 0
    val formId = 0
    val timeStamp = 0
    val versionControl = 0
    val internalVersion = 0
    val unknown = 0

    val recordData = encodeToByteArray(encodeRecordData)

    encodeString(recordTag)
    encodeInt(recordData.size)
    encodeInt(flags)
    encodeInt(formId)
    encodeShort(timeStamp.toShort())
    encodeShort(versionControl.toShort())
    encodeShort(internalVersion.toShort())
    encodeShort(unknown.toShort())
    encodeBytes(recordData)
}

// endregion
