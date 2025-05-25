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
package games.studiohummigbird.cultoftheancestormoth.serialization.recordtypes

import games.studiohummingbird.cultoftheancestormoth.serialization.encoding.BethesdaBufferEncoder
import games.studiohummingbird.cultoftheancestormoth.serialization.polymorphicPrimitiveModule
import games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes.TES4
import kotlinx.io.Buffer
import kotlinx.io.bytestring.toHexString
import kotlinx.io.readByteArray
import kotlinx.io.readByteString
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.serializer
import kotlin.test.Test

@ExperimentalStdlibApi
@ExperimentalSerializationApi
class TES4Tests {

    @Test
    fun testMasterFileSerialization() {
        val masterFile = TES4.MasterFile(
            name = "Skyrim.esm",
            data = 0
        )

        val buffer = Buffer()
        val encoder = BethesdaBufferEncoder(buffer, polymorphicPrimitiveModule)
        serializer<TES4.MasterFile>().serialize(encoder, masterFile)

        val result = buffer.readByteArray()
        println(result.toHexString())
    }

    @Test
    fun testTES4Serialization() {
        val tes4 = TES4(
            header = TES4.Header.Field(
                version = 1.7f,
                recordCount = 0,
                nextAvailableObjectId = 33000
            ),
            author = TES4.Author.Field("Zymus"),
            description = TES4.Description.Field("Cult of the Ancestor Moth Example"),
            masters = listOf("Skyrim.esm").map { TES4.MasterFile(it, 1) },
            numberOfTagifiableValues = TES4.TagifiableValues.Field(2)
        )

        val buffer = Buffer()
        val encoder = BethesdaBufferEncoder(buffer, polymorphicPrimitiveModule)
        serializer<TES4>().serialize(encoder, tes4)

        val serializedResult = buffer.readByteString()
        println(serializedResult.toHexString(HexFormat {
            bytes {
                byteSeparator = " "
                bytesPerLine = 16
            }
        }))

//        val decoder = BethesdaBufferDecoder(Buffer().apply { write(serializedResult) })
//        val deserializedTES4 = serializer<TES4>().deserialize(decoder)
//        println(deserializedTES4)
    }
}
