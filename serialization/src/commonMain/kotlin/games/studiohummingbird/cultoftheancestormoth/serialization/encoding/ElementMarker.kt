/**
Cult of the Ancestor Moth (ElementMarker.kt)
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
package games.studiohummingbird.cultoftheancestormoth.serialization.encoding

import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.TypeTag
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.GRUP
import kotlinx.serialization.SerialName

sealed interface ElementMarker {
    val type: TypeTag
    val skip: Long
    val size: Long
}


@SerialName(GRUP.SERIAL_NAME)
data class GroupMarker(
    override val type: TypeTag,
    override val skip: Long,
    override val size: Long,
) : ElementMarker


data class RecordMarker(
    override val type: TypeTag,
    override val skip: Long,
    override val size: Long,
) : ElementMarker
