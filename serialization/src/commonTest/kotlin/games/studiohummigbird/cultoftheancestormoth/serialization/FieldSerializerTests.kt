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

import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.NullTerminatedString
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.TypeTag
import games.studiohummingbird.cultoftheancestormoth.serialization.encoding.BethesdaBufferEncoder
import kotlinx.io.Buffer
import kotlinx.io.readByteArray
import kotlinx.serialization.builtins.ByteArraySerializer
import kotlinx.serialization.builtins.PairSerializer
import kotlinx.serialization.builtins.serializer
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalStdlibApi
class FieldSerializerTests {

    private val fieldName = "DATA"
    
    @Test
    fun `serializable field`() {
        val serializer = FieldSerializer<ByteArray>(ByteArraySerializer())
        val type = TypeTag("NAME")
        val data = ByteArray(5)
        val field = Field(type, data)

        val buffer = Buffer()
        val encoder = BethesdaBufferEncoder(buffer)

        serializer.serialize(encoder, field)

        val typeLength = field.typeTag.string.length
        val sizeLength = 2
        val bufferSize = field.value.size.toLong()
        val expectedSize = typeLength + sizeLength + bufferSize

        assertEquals(expectedSize, buffer.size)
    }

    @Test
    fun `serializable int field`() {
        val serializer = FieldSerializer<Int>(Int.serializer())
        val intField = Field<Int>(TypeTag(fieldName), 26)
        val buffer = Buffer()
        val encoder = BethesdaBufferEncoder(buffer)

        serializer.serialize(encoder, intField)

        assertEquals(10, buffer.size)
        println(buffer.readByteArray().contentToString())
    }

    @Test
    fun `serializable double field`() {
        val serializer = FieldSerializer<Double>(Double.serializer())
        val doubleField = Field<Double>(TypeTag(fieldName), 26.0)
        val buffer = Buffer()
        val encoder = BethesdaBufferEncoder(buffer)

        serializer.serialize(encoder, doubleField)

        assertEquals(14, buffer.size)
        println(buffer.readByteArray().contentToString())
    }

    @Test
    fun `serializable string field`() {
        val serializer = FieldSerializer<String>(String.serializer())
        val stringField = Field<String>(TypeTag(fieldName), "Zymus")
        val buffer = Buffer()
        val encoder = BethesdaBufferEncoder(buffer)

        serializer.serialize(encoder, stringField)

        assertEquals(11, buffer.size)
        println(buffer.readByteArray().contentToString())
    }

    @Test
    fun `serializable null terminated string field`() {
        val serializer = FieldSerializer<NullTerminatedString>(NullTerminatedString.serializer())
        val stringField = Field<NullTerminatedString>(TypeTag(fieldName), NullTerminatedString("Zymus"))
        val buffer = Buffer()
        val encoder = BethesdaBufferEncoder(buffer)

        serializer.serialize(encoder, stringField)

        assertEquals(12, buffer.size)
        println(buffer.readByteArray().contentToString())
    }

    @Test
    fun `serializable string pair field`() {
        val serializer = FieldSerializer(PairSerializer(String.serializer(), String.serializer()))
        val stringPairField = Field(TypeTag(fieldName), "name" to "value")
        val buffer = Buffer()
        val encoder = BethesdaBufferEncoder(buffer)

        serializer.serialize(encoder, stringPairField)

        val expectedLength = stringPairField.typeTag.string.length + 2 + stringPairField.value.first.length + stringPairField.value.second.length
        assertEquals(expectedLength.toLong(), buffer.size)
        println(buffer.readByteArray().contentToString())
    }
}
