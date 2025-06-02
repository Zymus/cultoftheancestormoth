/**
Cult of the Ancestor Moth (RecordSerializer.kt)
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

import games.studiohummingbird.cultoftheancestormoth.bytestring.serializer.ByteStringDecoder
import games.studiohummingbird.cultoftheancestormoth.bytestring.serializer.ByteStringSerializer
import games.studiohummingbird.cultoftheancestormoth.bytestring.serializer.decodeFromByteString
import games.studiohummingbird.cultoftheancestormoth.bytestring.serializer.encodeToByteString
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.TypeTag
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.CompressedFields
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Fields
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Record
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RecordHeader
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RecordSize
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RecordValue
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

val polymorphicPrimitiveModule = SerializersModule {
    polymorphic(Any::class) {
        subclass(Byte::class, Byte.serializer())
        subclass(Short::class, Short.serializer())
        subclass(Int::class, Int.serializer())
        subclass(Long::class, Long.serializer())
        subclass(Float::class, Float.serializer())
        subclass(Double::class, Double.serializer())
    }
}

class RecordSerializer() : KSerializer<Record> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor(SERIAL_NAME) {
        element<TypeTag>("tag")
        element<RecordHeader>("header")
        element("data", ByteStringSerializer.descriptor)
    }

    override fun deserialize(decoder: Decoder): Record {
        require(decoder is ByteStringDecoder)
        return decoder.decodeStructure(descriptor) {
            val tag = decodeSerializableElement(descriptor, 0, TypeTag.serializer())
            val header = decodeSerializableElement(descriptor, 1, RecordHeader.serializer())
            val recordFieldsBytes = decoder.decodeByteString(header.recordSize.int)

            val recordValue: RecordValue =
                if (header.recordProperties.isDataCompressed) {
                    PluginFormat.decodeFromByteString(CompressedFields.serializer(), recordFieldsBytes)
                } else {
                    PluginFormat.decodeFromByteString(Fields.serializer(), recordFieldsBytes)
                }

            Record(tag, header, recordValue)
        }
    }

    override fun serialize(
        encoder: Encoder,
        value: Record
    ) {
        encoder.encodeStructure(descriptor) {
            val recordValueByteString = PluginFormat.encodeToByteString(value.fields)
            val header = value.header.copy(
                recordSize = RecordSize(recordValueByteString.size)
            )
            encodeSerializableElement(
                descriptor,
                0,
                TypeTag.serializer(),
                value.tag
            )

            encodeSerializableElement(
                descriptor,
                1,
                RecordHeader.serializer(),
                header)

            encodeSerializableElement(
                descriptor,
                2,
                ByteStringSerializer,
                recordValueByteString
            )
        }
    }

    companion object {
        const val SERIAL_NAME = "games.studiohummingbird.cultoftheancestormoth.Record"
    }
}
