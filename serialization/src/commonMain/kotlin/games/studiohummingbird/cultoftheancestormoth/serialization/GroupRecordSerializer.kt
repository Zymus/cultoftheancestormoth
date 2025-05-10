/**
Cult of the Ancestor Moth (GroupRecordSerializer.kt)
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
package games.studiohummingbird.cultoftheancestormoth.serialization

import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RecordField
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RecordHeader
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RecordProperties
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RecordSize
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RecordType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure

class GroupRecordSerializer : KSerializer<RecordHeader> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor(SERIAL_NAME) {
        element<RecordType>("type")
        element<RecordSize>("size")
        element<RecordProperties>("properties")
        element<List<RecordField<*>>>("fields")
    }

    override fun serialize(
        encoder: Encoder,
        value: RecordHeader
    ) {
        TODO("Not yet implemented")
    }

    override fun deserialize(decoder: Decoder): RecordHeader {
        decoder.decodeStructure(descriptor) {
            val recordType = decodeSerializableElement(descriptor, 0, RecordType.serializer())
            val recordSize = decodeSerializableElement(descriptor, 1, RecordSize.serializer())
            val recordProperties = decodeSerializableElement(descriptor, 2, RecordProperties.serializer())
        }
        TODO()
    }

    companion object {
        const val SERIAL_NAME = "games.studiohummingbird.cultoftheancestormoth.serialization.tokens.GroupRecord"
    }
}