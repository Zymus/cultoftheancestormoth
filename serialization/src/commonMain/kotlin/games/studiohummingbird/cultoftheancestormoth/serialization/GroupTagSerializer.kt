/**
Cult of the Ancestor Moth (GroupTagSerializer.kt)
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

import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.GroupTag
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.StructureKind.OBJECT
import kotlinx.serialization.descriptors.buildSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure

@OptIn(InternalSerializationApi::class, ExperimentalSerializationApi::class)
object GroupTagSerializer : KSerializer<GroupTag> {
    const val SERIAL_NAME: String = "games.studiohummingbird.cultoftheancestormoth.serialization.tokens.GroupTag"

    override val descriptor: SerialDescriptor = buildSerialDescriptor(SERIAL_NAME, OBJECT)

    override fun serialize(
        encoder: Encoder,
        value: GroupTag
    ) {
        encoder.encodeStructure(descriptor) { }
    }

    override fun deserialize(decoder: Decoder): GroupTag =
        decoder.decodeStructure(descriptor) { GroupTag }
}
