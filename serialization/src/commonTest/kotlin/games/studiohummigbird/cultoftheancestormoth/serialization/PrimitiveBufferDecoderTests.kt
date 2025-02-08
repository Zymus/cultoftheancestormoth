/**
Cult of the Ancestor Moth (PrimitiveBufferDecoderTests.kt)
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

import games.studiohummingbird.cultoftheancestormoth.serialization.PrimitiveBufferDecoder
import kotlinx.io.Buffer
import kotlinx.io.writeDoubleLe
import kotlinx.io.writeFloatLe
import kotlinx.io.writeIntLe
import kotlinx.io.writeLongLe
import kotlinx.io.writeShortLe
import kotlinx.serialization.ExperimentalSerializationApi
import kotlin.test.Test
import kotlin.test.assertEquals

class PrimitiveBufferDecoderTests {

    @Test
    fun encodeByte() {
        val expectedValue: Byte = 26
        val buffer = Buffer().apply { writeByte(expectedValue) }
        val decoder = PrimitiveBufferDecoder(buffer)

        val byteValue = decoder.decodeByte()

        assertEquals(expectedValue, byteValue)
    }

    @Test
    fun encodeShort() {
        val expectedValue: Short = 26
        val buffer = Buffer().apply { writeShortLe(expectedValue) }
        val decoder = PrimitiveBufferDecoder(buffer)

        val shortValue = decoder.decodeShort()

        assertEquals(expectedValue, shortValue)
    }

    @Test
    fun encodeInt() {
        val expectedValue = 26
        val buffer = Buffer().apply { writeIntLe(expectedValue) }
        val decoder = PrimitiveBufferDecoder(buffer)

        val intValue = decoder.decodeInt()

        assertEquals(expectedValue, intValue)
    }

    @Test
    fun encodeLong() {
        val expectedValue: Long = 26
        val buffer = Buffer().apply { writeLongLe(expectedValue) }
        val decoder = PrimitiveBufferDecoder(buffer)

        val longValue = decoder.decodeLong()

        assertEquals(expectedValue, longValue)
    }

    @Test
    fun encodeFloat() {
        val expectedValue: Float = 26f
        val buffer = Buffer().apply { writeFloatLe(expectedValue) }
        val decoder = PrimitiveBufferDecoder(buffer)

        val floatValue = decoder.decodeFloat()

        assertEquals(expectedValue, floatValue)
    }

    @Test
    fun encodeDouble() {
        val expectedValue: Double = 26.0
        val buffer = Buffer().apply { writeDoubleLe(expectedValue) }
        val decoder = PrimitiveBufferDecoder(buffer)

        val doubleValue = decoder.decodeDouble()

        assertEquals(expectedValue, doubleValue)
    }
}
