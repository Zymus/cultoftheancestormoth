package games.studiohummingbird.cultoftheancestormoth

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.elementDescriptors
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class NullTerminatedString : KSerializer<String> {
    override val descriptor: SerialDescriptor = TODO()

    override fun deserialize(decoder: Decoder): String =
        decoder.decodeString()

    override fun serialize(encoder: Encoder, value: String) {
        encoder.encodeString(value)
        encoder.encodeByte(0)
    }
}
