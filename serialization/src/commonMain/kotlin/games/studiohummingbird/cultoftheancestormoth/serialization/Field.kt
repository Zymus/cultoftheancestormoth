/**
Cult of the Ancestor Moth (ObjectBoundsSerializer.kt)
Copyright (C) 2024  Zymus (moore.zyle@gmail.com)

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published
by the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package games.studiohummingbird.cultoftheancestormoth.serialization

import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.FieldType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ByteArraySerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.encodeStructure
import kotlinx.serialization.serializer

@Serializable(with = FieldSerializer::class)
data class Field<T : Any>(
    val name: FieldType,
    val value: T,
)

/**
 * Encodes a field using the given actions. Basically a serializer-and-encoder, just directly making encoder calls.
 */
fun BethesdaBufferEncoder.encodeField(name: String, fieldBufferAction: BethesdaBufferEncoder.() -> Unit) {
    encodeString(name)
    val fieldData = encodeToByteArray { fieldBufferAction() }
    encodeShort(fieldData.size.toShort())
    encodeBytes(fieldData)
}

/**
 * Does the same as encodeField, but with a value, and calling encodeSerializableValue
 * This should now be equivalent to a serialization strategy, so let me tweak it
 */
inline fun <reified T : Any> BethesdaBufferEncoder.encodeSerializableField(name: String, value: T) {
    encodeString(name)
    val fieldData = encodeToByteArray { encodeSerializableValue(serializer<T>(), value)}
    encodeShort(fieldData.size.toShort())
    encodeBytes(fieldData)
}

class FieldSerializer<T : Any>(private val valueSerializer: KSerializer<T>) : KSerializer<Field<T>> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("games.studiohummingbird.cultoftheancestormoth.serialization.Field") {
        element<String>("type")
        element<Short>("length")
        element("value", valueSerializer.descriptor)
    }

    override fun deserialize(decoder: Decoder): Field<T> {
        TODO()
    }

    override fun serialize(
        encoder: Encoder,
        value: Field<T>
    ) {
        encoder.encodeStructure(descriptor) {
            encodeStringElement(descriptor, 0, value.name.string)
            val fieldData = encodeToByteArray { encodeSerializableValue(valueSerializer, value.value) }
            encodeShortElement(descriptor, 1, fieldData.size.toShort())
            encodeSerializableElement(descriptor, 2, ByteArraySerializer(), fieldData)
        }
    }
}