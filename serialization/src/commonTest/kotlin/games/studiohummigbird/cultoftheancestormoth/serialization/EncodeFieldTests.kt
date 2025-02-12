/**
Cult of the Ancestor Moth (encodeFieldTests.kt)
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

import games.studiohummingbird.cultoftheancestormoth.serialization.encodeField
import games.studiohummingbird.cultoftheancestormoth.serialization.encodeSerializableField
import games.studiohummingbird.cultoftheancestormoth.serialization.encodeToByteArray
import kotlin.test.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalStdlibApi::class)
class EncodeFieldTests {

    @Test
    fun `encode int field called data`() {
        val fieldBytes = encodeToByteArray {
            encodeField("data") {
                encodeInt(26)
            }
        }

        println(fieldBytes.toHexString())
        assertEquals(10, fieldBytes.size)
    }

    @Test
    fun `encode serializable int field called data`() {
        val fieldBytes = encodeToByteArray {
            encodeSerializableField("data", 26)
        }

        println(fieldBytes.toHexString())
        assertEquals(10, fieldBytes.size)
    }
}