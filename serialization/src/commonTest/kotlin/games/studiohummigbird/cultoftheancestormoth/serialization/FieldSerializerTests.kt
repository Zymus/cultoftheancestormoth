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
package games.studiohummigbird.cultoftheancestormoth.serialization

import games.studiohummingbird.cultoftheancestormoth.bytestring.serializer.encodeToByteString
import games.studiohummingbird.cultoftheancestormoth.serialization.PluginFormat
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.NullTerminatedString
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.TypeTag
import games.studiohummingbird.cultoftheancestormoth.serialization.encoding.BethesdaBufferDecoder
import games.studiohummingbird.cultoftheancestormoth.serialization.encoding.BethesdaBufferEncoder
import games.studiohummingbird.cultoftheancestormoth.serialization.polymorphicPrimitiveModule
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Field
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.FieldType
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.FieldValue
import kotlinx.io.Buffer
import kotlinx.io.readByteArray
import kotlinx.serialization.ExperimentalSerializationApi
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalSerializationApi
@ExperimentalStdlibApi
class FieldSerializerTests {

    private val fieldName = "DATA"

    @Test
    fun `serializable field`() {
        val serializer = Field.serializer()
        val type = TypeTag("NAME")
        val data = ByteArray(5)
        val field = Field(
            FieldType(type),
            0.toUShort(),
            FieldValue(PluginFormat.encodeToByteString(data)))

        val buffer = Buffer()
        val encoder = BethesdaBufferEncoder(buffer, polymorphicPrimitiveModule)

        serializer.serialize(encoder, field)

        val typeLength = field.fieldType.typeTag.string.length
        val sizeLength = 2
        val bufferSize = field.fieldValue.value.size.toLong()
        val expectedSize = typeLength + sizeLength + bufferSize

        assertEquals(expectedSize, buffer.size)
    }

    @Test
    fun `serializable int field`() {
        val serializer = Field.serializer()
        val intField = Field(
            FieldType(TypeTag(fieldName)),
            0.toUShort(),
            FieldValue(PluginFormat.encodeToByteString(26)))
        val buffer = Buffer()
        val encoder = BethesdaBufferEncoder(buffer, polymorphicPrimitiveModule)

        serializer.serialize(encoder, intField)

        assertEquals(10, buffer.size)
        println(buffer.readByteArray().contentToString())
    }

    @Test
    fun `serializable double field`() {
        val serializer = Field.serializer()
        val doubleField = Field(
            FieldType(TypeTag(fieldName)),
            0.toUShort(),
            FieldValue(PluginFormat.encodeToByteString(26.0)))
        val buffer = Buffer()
        val encoder = BethesdaBufferEncoder(buffer, polymorphicPrimitiveModule)

        serializer.serialize(encoder, doubleField)

        assertEquals(14, buffer.size)
        println(buffer.readByteArray().contentToString())
    }

    @Test
    fun `serializable string field`() {
        val serializer = Field.serializer()
        val stringField = Field(
            FieldType(TypeTag(fieldName)),
            0.toUShort(),
            FieldValue(PluginFormat.encodeToByteString("Zymus")))
        val buffer = Buffer()
        val encoder = BethesdaBufferEncoder(buffer, polymorphicPrimitiveModule)

        serializer.serialize(encoder, stringField)

        assertEquals(11, buffer.size)
        println(buffer.readByteArray().contentToString())
    }

    @Test
    fun `serializable null terminated string field`() {
        val serializer = Field.serializer()
        val stringField = Field(
            FieldType(TypeTag(fieldName)),
            0.toUShort(),
            FieldValue(PluginFormat.encodeToByteString(NullTerminatedString("Zymus"))))
        val buffer = Buffer()
        val encoder = BethesdaBufferEncoder(buffer, polymorphicPrimitiveModule)

        serializer.serialize(encoder, stringField)

        assertEquals(12, buffer.size)
        println(buffer.readByteArray().contentToString())
    }

    @Test
    fun `deserialize test`() {
        val serializer = Field.serializer()
        val stringPairField = Field(
            FieldType(TypeTag(fieldName)),
            4.toUShort(),
            FieldValue(PluginFormat.encodeToByteString(26)))
        val buffer = Buffer()
        val encoder = BethesdaBufferEncoder(buffer, polymorphicPrimitiveModule)
        serializer.serialize(encoder, stringPairField)

        // buffer now has serialized field

        val decoder = BethesdaBufferDecoder(buffer, polymorphicPrimitiveModule, serializer.descriptor)
        val deserializedField = serializer.deserialize(decoder)

        assertEquals(stringPairField.fieldType, deserializedField.fieldType)
        assertEquals(stringPairField.fieldSize, deserializedField.fieldSize)
        assertEquals(stringPairField.fieldValue, deserializedField.fieldValue)

        assertEquals(0L, buffer.size)
    }
}
