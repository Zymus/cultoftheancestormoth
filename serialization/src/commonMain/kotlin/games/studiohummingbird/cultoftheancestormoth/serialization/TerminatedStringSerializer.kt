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
package games.studiohummingbird.cultoftheancestormoth.serialization

import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.TerminatedString
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object TerminatedStringSerializer : KSerializer<TerminatedString> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("StringWithTerminator") {
        element<String>("value")
        element<Byte>("terminator")
    }

    override fun deserialize(decoder: Decoder): TerminatedString {
        TODO("Not yet implemented")
    }

    override fun serialize(encoder: Encoder, value: TerminatedString) {
        encoder.encodeString(value.toString())
        encoder.encodeByte(0)
    }
}