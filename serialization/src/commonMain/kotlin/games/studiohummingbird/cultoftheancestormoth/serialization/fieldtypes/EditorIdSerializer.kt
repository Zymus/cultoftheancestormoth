package games.studiohummingbird.cultoftheancestormoth.serialization.fieldtypes

import games.studiohummingbird.cultoftheancestormoth.fieldtypes.EditorId
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object EditorIdSerializer : KSerializer<EditorId> {
    override val descriptor: SerialDescriptor
        get() = TODO("Not yet implemented")

    override fun deserialize(decoder: Decoder): EditorId {
        TODO("Not yet implemented")
    }

    override fun serialize(encoder: Encoder, value: EditorId) {
        TODO("Not yet implemented")
    }
}