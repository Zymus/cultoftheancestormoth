
package games.studiohummingbird.cultoftheancestormoth.serialization.fieldtypes

import games.studiohummingbird.cultoftheancestormoth.Field
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class FieldSerializer<T> : KSerializer<Field<T>> {
    override val descriptor: SerialDescriptor
        get() = TODO("Not yet implemented")

    override fun deserialize(decoder: Decoder): Field<T> {
        val name = decoder.decodeString()
        val size = decoder.decodeShort().toUShort()
        // field data is structured depending on the field type...
        // I think that means I'm going to need to serialize the different fields differently
        // like, effect item is a struct of 12 bytes, but if I try to serialize it as a "Field"
        // as writen, it would just be a byte array
        // that means that CTDA would need to be serialized differently than EFIT...
        // this might have to be polymorphic or something
        TODO("Not yet implemented")
    }

    override fun serialize(encoder: Encoder, value: Field<T>) {
        TODO("Not yet implemented")
    }
}
