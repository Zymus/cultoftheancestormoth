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

import games.studiohummingbird.cultoftheancestormoth.serialization.PrimitiveBufferEncoder
import kotlinx.io.Buffer
import kotlinx.io.readDoubleLe
import kotlinx.io.readFloatLe
import kotlinx.io.readIntLe
import kotlinx.io.readLongLe
import kotlinx.io.readShortLe
import kotlinx.serialization.ExperimentalSerializationApi
import kotlin.test.Test
import kotlin.test.assertEquals

class PrimitiveBufferEncoderTests {

    @Test
    fun encodeByte() {
        val buffer = Buffer()
        val encoder = PrimitiveBufferEncoder(buffer)
        val expectedValue: Byte = 26

        encoder.encodeByte(expectedValue)

        assertEquals(1, buffer.size)

        val byteValue = buffer.readByte()
        assertEquals(expectedValue, byteValue)
    }

    @Test
    fun encodeShort() {
        val buffer = Buffer()
        val encoder = PrimitiveBufferEncoder(buffer)
        val expectedValue: Short = 26

        encoder.encodeShort(expectedValue)

        assertEquals(2, buffer.size)

        val shortValue = buffer.readShortLe()
        assertEquals(expectedValue, shortValue)
    }

    @Test
    fun encodeInt() {
        val buffer = Buffer()
        val encoder = PrimitiveBufferEncoder(buffer)
        val expectedValue = 26

        encoder.encodeInt(expectedValue)

        assertEquals(4, buffer.size)

        val intValue = buffer.readIntLe()
        assertEquals(expectedValue, intValue)
    }

    @Test
    fun encodeLong() {
        val buffer = Buffer()
        val encoder = PrimitiveBufferEncoder(buffer)
        val expectedValue: Long = 26

        encoder.encodeLong(expectedValue)

        assertEquals(8, buffer.size)

        val longValue = buffer.readLongLe()
        assertEquals(expectedValue, longValue)
    }

    @Test
    fun encodeFloat() {
        val buffer = Buffer()
        val encoder = PrimitiveBufferEncoder(buffer)
        val expectedValue: Float = 26f

        encoder.encodeFloat(expectedValue)

        assertEquals(4, buffer.size)

        val floatValue = buffer.readFloatLe()
        assertEquals(expectedValue, floatValue)
    }

    @Test
    fun encodeDouble() {
        val buffer = Buffer()
        val encoder = PrimitiveBufferEncoder(buffer)
        val expectedValue: Double = 26.0

        encoder.encodeDouble(expectedValue)

        assertEquals(8, buffer.size)

        val doubleValue = buffer.readDoubleLe()
        assertEquals(expectedValue, doubleValue)
    }
}
