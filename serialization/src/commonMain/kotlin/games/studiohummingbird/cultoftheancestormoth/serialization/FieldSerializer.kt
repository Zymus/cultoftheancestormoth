/**
Cult of the Ancestor Moth (FieldSerializer.kt)
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

import games.studiohummingbird.cultoftheancestormoth.bytestring.serializer.ByteStringDecoder
import games.studiohummingbird.cultoftheancestormoth.bytestring.serializer.encodeToByteString
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Field
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.FieldType
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.FieldValue
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.LongField
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure

const val SERIAL_NAME = "games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Field"

class FieldSerializer : KSerializer<Field> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor(SERIAL_NAME) {
        element<FieldType>("fieldType")
        element<UShort>("fieldSize")
        element<FieldValue>("fieldValue")
    }

    override fun serialize(
        encoder: Encoder,
        value: Field
    ) {
        encoder.encodeStructure(descriptor) {
            encodeSerializableElement(descriptor, 0, FieldType.serializer(), value.fieldType)

            val encodedFieldValue = PluginFormat.encodeToByteString(FieldValue.serializer(), value.fieldValue)
            val fieldSize = encodedFieldValue.size.toUShort()
            encodeSerializableElement(descriptor, 1, UShort.serializer(), fieldSize)

            // currently double encoding, consider using encodedFieldvalue somehow
            encodeSerializableElement(descriptor, 2, FieldValue.serializer(), value.fieldValue)
        }
    }

    override fun deserialize(decoder: Decoder): Field {
        require(decoder is ByteStringDecoder)
        return decoder.decodeStructure(descriptor) {
            val fieldType = decodeSerializableElement(descriptor, 0, FieldType.serializer())
            if (fieldType.typeTag.string == LongField.SERIAL_NAME) {
                println("encountered ${LongField.SERIAL_NAME}")
                return@decodeStructure decodeSerializableElement(descriptor, 1, LongFieldSerializer).longField
            }
            val fieldSize = decodeSerializableElement(descriptor, 1, UShort.serializer())
            val byteString = decoder.decodeByteString(fieldSize.toInt())

            Field(fieldType, fieldSize, FieldValue(byteString))
        }
    }
}
