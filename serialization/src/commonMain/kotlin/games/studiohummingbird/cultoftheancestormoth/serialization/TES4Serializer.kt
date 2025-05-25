/**
Cult of the Ancestor Moth (TES4Serializer.kt)
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

import games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes.TES4
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure

class TES4Serializer : KSerializer<TES4> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor(SERIAL_NAME) {
        element<String>("recordType")
    }

    override fun serialize(
        encoder: Encoder,
        value: TES4
    ) {
        TODO("Not yet implemented")
    }

    override fun deserialize(decoder: Decoder): TES4 {
        decoder.decodeStructure(descriptor) {
            // [ "TES4", [ flags, formId, timestamp, versionControl, recordVersion, unknown ] ],
        }

        val fields = emptyList<String>()
        /*
        [ "HEDR", [ 1.7, 0, 0 ] ],
        [ "CNAM", "Zymus" ],
        [ "SNAM", "TES4 JSON Example" ],
        [ "MAST", "Skyrim.esm" ],
        [ "DATA", 0 ],
        [ "MAST", "Update.esm" ],
        [ "DATA", 0 ],
        [ "MAST", "Hearthfires.esm" ],
        [ "DATA", 0 ],
        [ "ONAM", [ 0 ] ],
        [ "INTV", 0 ],
        [ "INCC", 0 ]
        */
        fields.forEachIndexed { index, field ->
            decoder.decodeStructure(descriptor) {

            }
        }

        TODO()
    }

    companion object {
        const val SERIAL_NAME = "games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes.TES4"
    }
}
