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
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.descriptors.buildSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@OptIn(ExperimentalSerializationApi::class, InternalSerializationApi::class)
object ByteStringSerializer : KSerializer<ByteString> {
    const val SERIAL_NAME = "games.studiohummingbird.cultoftheancestormoth.bytestring.serializer.ByteString"

    override val descriptor: SerialDescriptor = buildSerialDescriptor(SERIAL_NAME, StructureKind.LIST) {
        element<Byte>("type")
    }

    override fun serialize(encoder: Encoder, value: ByteString) {
        require(encoder is ByteStringEncoder)
        encoder.encodeByteString(value)
    }

    override fun deserialize(decoder: Decoder): ByteString {
        require(decoder is ByteStringDecoder)
        return decoder.decodeByteString()
    }
}
