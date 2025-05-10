/**
Cult of the Ancestor Moth (PrimitiveBufferEncoderTests.kt)
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
@file:OptIn(ExperimentalSerializationApi::class)

package games.studiohummigbird.cultoftheancestormoth.serialization

import games.studiohummingbird.cultoftheancestormoth.serialization.encoding.ByteSinkEncoder
import kotlinx.io.Buffer
import kotlinx.serialization.ExperimentalSerializationApi
import kotlin.test.Test
import kotlin.test.assertEquals

class ByteSinkEncoderTests {

    @Test
    fun encodeByte() {
        val buffer = Buffer()
        val encoder = ByteSinkEncoder(buffer)
        val expectedValue: Byte = 26

        encoder.encodeByte(expectedValue)

        assertEquals(1, buffer.size)

        val byteValue = buffer.readByte()
        assertEquals(expectedValue, byteValue)
    }
}
