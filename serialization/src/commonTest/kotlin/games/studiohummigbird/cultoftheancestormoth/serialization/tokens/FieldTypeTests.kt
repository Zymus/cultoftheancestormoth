/**
Cult of the Ancestor Moth (FieldTypeTests.kt)
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
package games.studiohummigbird.cultoftheancestormoth.serialization.tokens

import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.TypeTag
import games.studiohummingbird.cultoftheancestormoth.serialization.encoding.BethesdaBufferEncoder
import games.studiohummingbird.cultoftheancestormoth.serialization.polymorphicPrimitiveModule
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.FieldType
import kotlinx.io.Buffer
import kotlin.test.Test
import kotlin.test.assertEquals

class FieldTypeTests {

    @Test
    fun `serialized size is 4 bytes`() {
        val serializer = FieldType.serializer()
        val buffer = Buffer()
        val encoder = BethesdaBufferEncoder(buffer, polymorphicPrimitiveModule)
        val fieldType = FieldType(TypeTag("DATA"))

        serializer.serialize(encoder, fieldType)

        assertEquals(4, buffer.size)
    }
}
