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
package games.studiohummigbird.cultoftheancestormoth.serialization.datatypes

import games.studiohummingbird.cultoftheancestormoth.serialization.BethesdaBufferDecoder
import games.studiohummingbird.cultoftheancestormoth.serialization.BethesdaBufferEncoder
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.InlineNullTerminatedString
import kotlinx.io.Buffer
import kotlinx.io.readByteArray
import kotlinx.serialization.ExperimentalSerializationApi
import kotlin.test.Test
import kotlin.test.assertEquals

@ExperimentalStdlibApi
@ExperimentalSerializationApi
class InlineNullTerminatedStringTests {
    @Test
    fun `serializable test`() {
        val serializer = InlineNullTerminatedString.serializer()
        val buffer = Buffer()
        val encoder = BethesdaBufferEncoder(buffer)
        val string = InlineNullTerminatedString("TES4")

        serializer.serialize(encoder, string)

        val result = buffer.readByteArray()
        assertEquals(result.size, string.value.length + 1)
    }

    @Test
    fun `deserializable test`() {
        val inlineString = InlineNullTerminatedString("TES4")
        val serializer = InlineNullTerminatedString.serializer()
        val buffer = Buffer()

        val encoder = BethesdaBufferEncoder(buffer)
        serializer.serialize(encoder, inlineString)

        val decoder = BethesdaBufferDecoder(buffer)
        val decodedString = serializer.deserialize(decoder)

        assertEquals(decodedString, inlineString)
    }
}