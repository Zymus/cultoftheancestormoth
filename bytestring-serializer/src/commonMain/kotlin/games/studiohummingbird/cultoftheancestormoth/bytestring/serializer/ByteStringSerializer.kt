/**
Cult of the Ancestor Moth (ByteStringSerializer.kt)
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
package games.studiohummingbird.cultoftheancestormoth.bytestring.serializer

import kotlinx.io.bytestring.ByteString
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.ByteArraySerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object ByteStringSerializer : KSerializer<ByteString> {
    override val descriptor: SerialDescriptor = ByteArraySerializer().descriptor

    override fun serialize(encoder: Encoder, value: ByteString) {
        ByteArraySerializer().serialize(encoder, value.toByteArray())
    }

    override fun deserialize(decoder: Decoder): ByteString {
        return ByteString(ByteArraySerializer().deserialize(decoder))
    }
}
