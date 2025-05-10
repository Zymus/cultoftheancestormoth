/**
Cult of the Ancestor Moth (RecordFieldSerializer.kt)
Copyright (C) 2025  Zymus (moore.zyle@gmail.com)

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

import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.FieldSize
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.FieldType
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.FieldValue
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RecordField
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure

class RecordFieldSerializer<T : Any>(
    private val fieldValueSerializer: KSerializer<FieldValue<T>>
) : KSerializer<RecordField<T>> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor(SERIAL_NAME) {
        element<FieldType>("type")
        element<FieldSize>("size")
        element("value", fieldValueSerializer.descriptor)
    }

    override fun serialize(
        encoder: Encoder,
        value: RecordField<T>
    ) {
        TODO("Not yet implemented")
    }

    override fun deserialize(decoder: Decoder): RecordField<T> {
        return decoder.decodeStructure(descriptor) {
            val fieldType = decodeSerializableElement(descriptor, 0, FieldType.serializer())
            val fieldSize = decodeSerializableElement(descriptor, 1, FieldSize.serializer())
            val fieldValue = decodeSerializableElement(descriptor, 2, fieldValueSerializer)

            RecordField(fieldType, fieldSize, fieldValue)
        }
    }

    companion object {
        const val SERIAL_NAME = "games.studiohummingbird.cultoftheancestormoth.serialization.RecordField"
    }
}