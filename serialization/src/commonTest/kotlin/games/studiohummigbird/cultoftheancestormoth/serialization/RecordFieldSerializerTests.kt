/**
Cult of the Ancestor Moth (RecordFieldSerializerTests.kt)
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
package games.studiohummigbird.cultoftheancestormoth.serialization

import games.studiohummingbird.cultoftheancestormoth.serialization.RecordFieldSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.TypeTag
import games.studiohummingbird.cultoftheancestormoth.serialization.encoding.BethesdaBufferDecoder
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.FieldValue
import kotlinx.io.Buffer
import kotlinx.io.Source
import kotlinx.io.writeIntLe
import kotlinx.io.writeShortLe
import kotlinx.io.writeString
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.builtins.serializer
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalStdlibApi
@ExperimentalSerializationApi
class RecordFieldSerializerTests {

    @Test
    fun `deserialize`() {
        val typeTag = TypeTag("DATA")
        val fieldSize: Short = 4
        val intValue = 26
        val buffer = Buffer().apply {
            writeString(typeTag.string)
            writeShortLe(fieldSize)
            writeIntLe(intValue)
        }
        val source: Source = buffer
        val decoder = BethesdaBufferDecoder(source)
        val serializer = RecordFieldSerializer(FieldValue.serializer(Int.serializer()))

        val deserialized = serializer.deserialize(decoder)

        assertEquals(typeTag, deserialized.fieldType.typeTag)
        assertEquals(fieldSize, deserialized.fieldSize.short)
        assertEquals(intValue, deserialized.fieldValue.value)
    }
}
