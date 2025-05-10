/**
Cult of the Ancestor Moth (LittleEndianSinkEncoderTests.kt)
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
import games.studiohummingbird.cultoftheancestormoth.serialization.encoding.LittleEndianSinkEncoder
import kotlinx.io.Buffer
import kotlinx.io.Sink
import kotlinx.io.readDoubleLe
import kotlinx.io.readFloatLe
import kotlinx.io.readIntLe
import kotlinx.io.readLongLe
import kotlinx.io.readShortLe
import kotlin.test.Test
import kotlin.test.assertEquals

class LittleEndianSinkEncoderTests {

    @Test
    fun `encodeShort can be read with readShortLe`() {
        val buffer = Buffer()
        val sink: Sink = buffer
        val encoder = LittleEndianSinkEncoder(sink)

        encoder.encodeShort(TEST_VALUE_SHORT)

        assertEquals(TEST_VALUE_SHORT, buffer.readShortLe())
    }

    @Test
    fun `encodeInt can be read with readIntLe`() {
        val buffer = Buffer()
        val sink: Sink = buffer
        val encoder = LittleEndianSinkEncoder(sink)

        encoder.encodeInt(TEST_VALUE_INT)

        assertEquals(TEST_VALUE_INT, buffer.readIntLe())
    }

    @Test
    fun `encodeLong can be read with readLongLe`() {
        val buffer = Buffer()
        val sink: Sink = buffer
        val encoder = LittleEndianSinkEncoder(sink)

        encoder.encodeLong(TEST_VALUE_LONG)

        assertEquals(TEST_VALUE_LONG, buffer.readLongLe())
    }

    @Test
    fun `encodeFloat can be read with readFloatLe`() {
        val buffer = Buffer()
        val sink: Sink = buffer
        val encoder = LittleEndianSinkEncoder(sink)

        encoder.encodeFloat(TEST_VALUE_FLOAT)

        assertEquals(TEST_VALUE_FLOAT, buffer.readFloatLe())
    }

    @Test
    fun `encodeDouble can be read with readDoubleLe`() {
        val buffer = Buffer()
        val sink: Sink = buffer
        val encoder = LittleEndianSinkEncoder(sink)

        encoder.encodeDouble(TEST_VALUE_DOUBLE)

        assertEquals(TEST_VALUE_DOUBLE, buffer.readDoubleLe())
    }
}
