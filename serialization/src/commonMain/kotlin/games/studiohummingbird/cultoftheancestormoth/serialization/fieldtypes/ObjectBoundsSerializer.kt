/**
 *  Cult of the Ancestor Moth (ObjectBoundsSerializer)
 *  Copyright (C) 2024  Zymus (moore.zyle@gmail.com)
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as published
 *  by the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package games.studiohummingbird.cultoftheancestormoth.serialization.fieldtypes

import games.studiohummingbird.cultoftheancestormoth.fieldtypes.ObjectBounds
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.encodeStructure

object ObjectBoundsSerializer : KSerializer<ObjectBounds> {
    override val descriptor: SerialDescriptor
        get() = TODO("Not yet implemented")

    override fun deserialize(decoder: Decoder): ObjectBounds = ObjectBounds(
        x1 = decoder.decodeShort(),
        y1 = decoder.decodeShort(),
        z1 = decoder.decodeShort(),
        x2 = decoder.decodeShort(),
        y2 = decoder.decodeShort(),
        z2 = decoder.decodeShort(),
    )

    override fun serialize(encoder: Encoder, value: ObjectBounds) {
        encoder.encodeStructure(descriptor) {
            encoder.encodeShort(value.x1)
            encoder.encodeShort(value.y1)
            encoder.encodeShort(value.z1)
            encoder.encodeShort(value.x2)
            encoder.encodeShort(value.y2)
            encoder.encodeShort(value.z2)
        }
    }
}