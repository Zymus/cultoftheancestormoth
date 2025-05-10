/**
Cult of the Ancestor Moth (LittleEndianSourceDecoderTests.kt)
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
package games.studiohummigbird.cultoftheancestormoth.serialization.encoding

import games.studiohummigbird.cultoftheancestormoth.serialization.TEST_VALUE_DOUBLE
import games.studiohummigbird.cultoftheancestormoth.serialization.TEST_VALUE_FLOAT
import games.studiohummigbird.cultoftheancestormoth.serialization.TEST_VALUE_INT
import games.studiohummigbird.cultoftheancestormoth.serialization.TEST_VALUE_LONG
import games.studiohummigbird.cultoftheancestormoth.serialization.TEST_VALUE_SHORT
import games.studiohummingbird.cultoftheancestormoth.serialization.encoding.LittleEndianSourceDecoder
import kotlinx.io.Buffer
import kotlinx.io.Source
import kotlinx.io.writeDoubleLe
import kotlinx.io.writeFloatLe
import kotlinx.io.writeIntLe
import kotlinx.io.writeLongLe
import kotlinx.io.writeShortLe
import kotlin.test.Test
import kotlin.test.assertEquals

class LittleEndianSourceDecoderTests {

    @Test
    fun `decodeShort can read from writeShortLe`() {
        val buffer = Buffer().apply {
            writeShortLe(TEST_VALUE_SHORT)
        }
        val source: Source = buffer
        val decoder = LittleEndianSourceDecoder(source)

        assertEquals(TEST_VALUE_SHORT, decoder.decodeShort())
    }
    
    @Test
    fun `decodeInt can read from writeIntLe`() {
        val buffer = Buffer().apply {
            writeIntLe(TEST_VALUE_INT)
        }
        val source: Source = buffer
        val decoder = LittleEndianSourceDecoder(source)

        assertEquals(TEST_VALUE_INT, decoder.decodeInt())
    }

    @Test
    fun `decodeLong can read from writeLongLe`() {
        val buffer = Buffer().apply {
            writeLongLe(TEST_VALUE_LONG)
        }
        val source: Source = buffer
        val decoder = LittleEndianSourceDecoder(source)

        assertEquals(TEST_VALUE_LONG, decoder.decodeLong())
    }

    @Test
    fun `decodeFloat can read from writeFloatLe`() {
        val buffer = Buffer().apply {
            writeFloatLe(TEST_VALUE_FLOAT)
        }
        val source: Source = buffer
        val decoder = LittleEndianSourceDecoder(source)

        assertEquals(TEST_VALUE_FLOAT, decoder.decodeFloat())
    }

    @Test
    fun `decodeDouble can read from writeDoubleLe`() {
        val buffer = Buffer().apply {
            writeDoubleLe(TEST_VALUE_DOUBLE)
        }
        val source: Source = buffer
        val decoder = LittleEndianSourceDecoder(source)

        assertEquals(TEST_VALUE_DOUBLE, decoder.decodeDouble())
    }
}
