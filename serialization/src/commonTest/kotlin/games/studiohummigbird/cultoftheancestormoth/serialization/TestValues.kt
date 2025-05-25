/**
Cult of the Ancestor Moth (TestValues.kt)
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
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.NullTerminatedString
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.TypeTag
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.CompressedFields
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Field
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.FieldSize
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.FieldType
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.FieldValue
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Fields
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Group
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.GroupHeader
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.GroupProperties
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.GroupSize
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.GroupTag
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Record
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RecordHeader
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RecordProperties
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RecordSize
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RecordType
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Records
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.SubGroups
import kotlinx.io.bytestring.ByteString

const val TEST_VALUE_BYTE: Byte = 26
const val TEST_VALUE_SHORT: Short = 26
const val TEST_VALUE_INT: Int = 26
const val TEST_VALUE_LONG: Long = 26
const val TEST_VALUE_FLOAT: Float = 26f
const val TEST_VALUE_DOUBLE: Double = 26.0
const val TEST_STRING: String = "RENO"

val TEST_NULL_TERMINATED_STRING: NullTerminatedString = NullTerminatedString(TEST_STRING)

val TEST_TYPE_TAG: TypeTag = TypeTag(TEST_STRING)
val TEST_FIELD_TYPE: FieldType = FieldType(TEST_TYPE_TAG)
val TEST_FIELD_SIZE: FieldSize = FieldSize(4.toUShort())
val TEST_FIELD_VALUE_INT: FieldValue = FieldValue(PluginFormat.encodeToByteString(TEST_VALUE_INT))

val TEST_FIELD_INT: Field = Field(
    TEST_FIELD_TYPE,
    TEST_FIELD_SIZE,
    TEST_FIELD_VALUE_INT
)

val TEST_RECORD_TYPE: RecordType = RecordType(TEST_TYPE_TAG)
val TEST_RECORD_SIZE: RecordSize = RecordSize(34)
val TEST_RECORD_PROPERTIES: RecordProperties = RecordProperties(
    TEST_VALUE_INT,
    TEST_VALUE_INT,
    TEST_VALUE_SHORT,
    TEST_VALUE_SHORT,
    TEST_VALUE_SHORT,
    TEST_VALUE_SHORT
)

val TEST_RECORD_HEADER: RecordHeader = RecordHeader(
    TEST_RECORD_TYPE,
    TEST_RECORD_SIZE,
    TEST_RECORD_PROPERTIES
)

val TEST_FIELDS = Fields(listOf(TEST_FIELD_INT))

val TEST_COMPRESSED_FIELDS = CompressedFields(ByteString(TEST_VALUE_BYTE))

val TEST_RECORD_WITH_FIELDS = Record(
    TEST_RECORD_HEADER,
    TEST_FIELDS
)

val TEST_GROUP_SIZE = GroupSize(58.toUInt())

val TEST_GROUP_PROPERTIES = GroupProperties(
    TEST_TYPE_TAG,
    TEST_VALUE_INT,
    TEST_VALUE_SHORT,
    TEST_VALUE_SHORT,
    TEST_VALUE_INT
)

val TEST_GROUP_HEADER = GroupHeader(
    GroupTag,
    TEST_GROUP_SIZE,
    TEST_GROUP_PROPERTIES
)

val TEST_GROUP: Group = Group(
    TEST_GROUP_HEADER,
    Records(listOf(TEST_RECORD_WITH_FIELDS))
)

val TEST_RECORD_GROUP = Record(
    TEST_RECORD_HEADER,
    SubGroups(listOf(TEST_GROUP))
)

val TEST_SUBGROUPS = SubGroups(listOf(
    TEST_GROUP
))

val TEST_RECORD_WITH_SUBGROUPS = Record(
    TEST_RECORD_HEADER,
    TEST_SUBGROUPS
)
