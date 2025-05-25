/**
Cult of the Ancestor Moth (Record.kt)
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
package games.studiohummingbird.cultoftheancestormoth.serialization.tokens

import games.studiohummingbird.cultoftheancestormoth.serialization.RecordSerializer
import kotlinx.io.bytestring.ByteString
import kotlinx.serialization.Serializable

@Serializable(with = RecordSerializer::class)
data class Record(
    val header: RecordHeader,
    val value: RecordValueToken,
) : GroupToken {
    val fields: List<Field>
        get() = if (value is Fields) {
            value.fields
        } else {
            emptyList()
        }

    val compressedFields: ByteString
        get() = if (value is CompressedFields) {
            value.byteString
        } else {
            ByteString()
        }
}
