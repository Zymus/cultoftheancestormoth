/**
Cult of the Ancestor Moth (RecordSerializerTests.kt)
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

import games.studiohummingbird.cultoftheancestormoth.bytestring.serializer.encodeToByteString
import games.studiohummingbird.cultoftheancestormoth.serialization.PluginFormat
import games.studiohummingbird.cultoftheancestormoth.serialization.RecordSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.TypeTag
import games.studiohummingbird.cultoftheancestormoth.serialization.encoding.BethesdaBufferEncoder
import games.studiohummingbird.cultoftheancestormoth.serialization.polymorphicPrimitiveModule
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Field
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.FieldType
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.FieldValue
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Fields
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Record
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RecordHeader
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RecordProperties
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RecordSize
import kotlinx.io.Buffer
import kotlinx.io.InternalIoApi
import kotlinx.io.readByteArray
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.PolymorphicSerializer
import kotlin.test.Test

@OptIn(ExperimentalStdlibApi::class, InternalIoApi::class, ExperimentalSerializationApi::class)
class RecordSerializerTests {

    @Test
    fun `serialize test`() {
        val buffer = Buffer()
        val encoder = BethesdaBufferEncoder(buffer, polymorphicPrimitiveModule)
        val serializer = RecordSerializer()
        val record = Record(
            TypeTag("TEST"),
            RecordHeader(RecordSize(0), RecordProperties(0, 0, 0, 0, 0, 0)),
            Fields(
                listOf(
                    Field(FieldType(TypeTag("DATA")), 4.toUShort(), FieldValue(PluginFormat.encodeToByteString(26)))
                )
            )
        )

        serializer.serialize(encoder, record)
        println(buffer.readByteArray().toHexString(HexFormat {
            bytes {
                byteSeparator = " "
                bytesPerLine = 16
            }
        }))
    }

    @Test
    fun `format fields value`() {
        val record: Fields = TEST_FIELDS
        val encoded = PluginFormat.encodeToSink(Fields.serializer(), record)
        val decoded = PluginFormat.decodeFromSource(Fields.serializer(), encoded.buffer)
    }

    @Test
    fun `format fields value polymorphically`() {
        val record: Any = TEST_FIELDS
        val encoded = PluginFormat.encodeToSink(PolymorphicSerializer(Any::class), record)
//        val decoded: RecordValueToken = PluginFormat.decodeFromSource(PolymorphicSerializer(RecordValueToken::class), encoded.buffer)
    }
}
