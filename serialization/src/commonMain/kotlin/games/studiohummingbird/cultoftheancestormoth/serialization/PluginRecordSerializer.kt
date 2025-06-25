package games.studiohummingbird.cultoftheancestormoth.serialization

import games.studiohummingbird.cultoftheancestormoth.bytestring.serializer.ByteStringDecoder
import games.studiohummingbird.cultoftheancestormoth.bytestring.serializer.decodeFromByteString
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.TypeTag
import games.studiohummingbird.cultoftheancestormoth.serialization.encoding.CompressedFieldsDecoder
import games.studiohummingbird.cultoftheancestormoth.serialization.encoding.RecordFieldsDecoder
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.LongField
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.PluginRecord
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RecordHeader
import kotlinx.io.Buffer
import kotlinx.io.write
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure

@OptIn(ExperimentalSerializationApi::class)
sealed class PluginRecordSerializer<TRecord : PluginRecord>(
    val recordSerializer: KSerializer<TRecord>
) : KSerializer<TRecord> {
    override val descriptor: SerialDescriptor = recordSerializer.descriptor

    @OptIn(ExperimentalStdlibApi::class)
    override fun deserialize(decoder: Decoder): TRecord {
        require(decoder is ByteStringDecoder)
        debug("deserialize PluginRecord")
        return decoder.decodeStructure(descriptor) {
            val buffer = Buffer()

            val headerByteString = decoder.decodeByteString(20)
            val header = PluginFormat.decodeFromByteString(RecordHeader.serializer(), headerByteString)
            buffer.write(headerByteString)
            if (header.recordSize.int ==  0) {
                println("0 record size")
            }

            debug("reading ${header.recordSize} ${header.recordProperties.isDataCompressed} bytes for record")
            val recordValueByteString = decoder.decodeByteString(header.recordSize.int)
            buffer.write(recordValueByteString)

            debug("deserializing record from buffer")
            val recordDecoder = if (header.recordProperties.isDataCompressed || header.recordSize.int == 0) {
                CompressedFieldsDecoder(serializersModule, buffer)
            } else if (PluginFormat.decodeFromByteString(TypeTag.serializer(), recordValueByteString).string == LongField.SERIAL_NAME) {
                RecordFieldsDecoder(serializersModule, buffer)
            } else {
                RecordFieldsDecoder(serializersModule, buffer)
            }
            val deserializedRecord = recordSerializer.deserialize(recordDecoder)

            // the issue here is how to decode te fields correctly
            // each record could be compressed
            // assume: there's no case where a record has some compressed fields and some uncompressed
            // Knowing if the fields are compressed is determined after reading the header
            // I need to use the provided serializer to deserialize the correct record Type
            // all types serializable by this serializer are PluginRecord
            // No deviation, they have a header and a value
            // both will call beginStructure, decodeSerializableElement by default
            // could provide a different encoder
            //

            deserializedRecord
        }
    }

    override fun serialize(encoder: Encoder, value: TRecord) {
        TODO("Not yet implemented")
    }

    private fun debug(message: String, override: Boolean = false) {
        val debug = false || override
        if (debug) {
            println(message)
        }
    }
}

