/**
Cult of the Ancestor Moth (LongFieldSerializer.kt)
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

object LongFieldSerializer : KSerializer<LongField> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor(LongField.SERIAL_NAME) {
        element<UShort>("fieldSize")
        element<Int>("actualSize")
        element<FieldType>("fieldType")
        element<UShort>("emptyFieldSize")
        element<FieldValue>("value")
    }

    override fun serialize(
        encoder: Encoder,
        value: LongField
    ) {
        TODO("Not yet implemented")
    }

    override fun deserialize(decoder: Decoder): LongField {
        require(decoder is ByteStringDecoder)
        return decoder.decodeStructure(descriptor) {
            val fieldSize = decodeSerializableElement(descriptor, 0, UShort.serializer())
            val actualSize = decodeIntElement(descriptor, 1)
            val fieldType = decodeSerializableElement(descriptor, 2, FieldType.serializer())
            val emptyFieldSize = decodeSerializableElement(descriptor, 3, UShort.serializer())

            val valueByteString = decoder.decodeByteString(actualSize)
            val value = FieldValue(valueByteString)

            LongField(fieldSize, actualSize, Field(fieldType, emptyFieldSize, value))
        }
    }
}
