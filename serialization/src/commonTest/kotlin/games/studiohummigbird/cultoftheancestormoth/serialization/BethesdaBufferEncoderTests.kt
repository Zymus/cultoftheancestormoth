/**
Cult of the Ancestor Moth (BethesdaBufferEncoderTests.kt)
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

import games.studiohummingbird.cultoftheancestormoth.serialization.bethesdaBufferEncoder
import games.studiohummingbird.cultoftheancestormoth.serialization.field
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertEquals

class BethesdaBufferEncoderTests {

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun testFloatBitsMatchIntBits() {
        val floatValue = 2.0f
        val floatBits = floatValue.toBits()
        val encodedFloat = bethesdaBufferEncoder {
            encodeFloat(floatValue)
        }
        val encodedInt = bethesdaBufferEncoder {
            encodeInt(floatBits)
        }
        println(encodedFloat.toHexString())
        println(encodedInt.toHexString())

        val shortTest: Int = 65530

        println(shortTest.toShort().toHexString())
        println(shortTest.toUShort().toShort().toHexString())
    }

    @OptIn(ExperimentalStdlibApi::class)
    @Test
    fun testField() {
        val dataBytes = bethesdaBufferEncoder { encodeInt(26) }
        val fieldBytes = field("DATA", dataBytes)

        println(fieldBytes.toHexString())
        assertEquals(10, fieldBytes.size)
    }
}
