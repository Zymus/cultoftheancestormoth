package games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes

import games.studiohummingbird.cultoftheancestormoth.recordtypes.Potion
import games.studiohummingbird.cultoftheancestormoth.serialization.RESTORE_HEALTH_MAGIC_EFFECT_FORM_ID
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ArraySerializer
import kotlinx.serialization.builtins.ByteArraySerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.encodeCollection

object PotionSerializer : KSerializer<Potion> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Potion") {
        element("edid", String.serializer().descriptor)
        element("edidValue", String.serializer().descriptor)
        // this gets awkward...
        // I think I might have to serialize the smaller things, somehow
        // and use the field serializer, somehow
    }

    override fun deserialize(decoder: Decoder): Potion {
        TODO("Not yet implemented")
    }

    override fun serialize(encoder: Encoder, value: Potion) {
        // doesn't properly encode field sizes
        encoder.encodeString("EDID")
        encoder.encodeString(value.editorId)
        encoder.encodeByte(0)
        encoder.encodeString("OBND")
        encoder.encodeShort(0)
        encoder.encodeShort(0)
        encoder.encodeShort(0)
        encoder.encodeShort(0)
        encoder.encodeShort(0)
        encoder.encodeShort(0)
        encoder.encodeString("DATA")
        encoder.encodeFloat(value.weight)// weight
        encoder.encodeString("ENIT")
        encoder.encodeInt(26)// potion value
        encoder.encodeInt(0x10001)// flags, manual calc == 1
        encoder.encodeInt(0)// addiction
        encoder.encodeInt(0)// addiction change
        encoder.encodeInt(0)// use sound sndr formid
        value.effects?.forEach { effect ->
            encoder.encodeString("EFID")
            encoder.encodeInt(effect.effectId.toInt())// effect form id
            encoder.encodeString("EFIT")
            encoder.encodeFloat(effect.effectParams.magnitude)
            encoder.encodeInt(effect.effectParams.areaOfEffect.toInt())
            encoder.encodeInt(effect.effectParams.duration.toInt())
        }

    }
}
