/**
Cult of the Ancestor Moth (ByteStringFormat.kt)
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
import kotlinx.serialization.BinaryFormat
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.serializer

fun <T : Any> BinaryFormat.decodeFromByteString(deserializationStrategy: DeserializationStrategy<T>, byteString: ByteString, start: Int = 0, limit: Int = 0): T {
    val endIndex = if (limit == 0) {
        byteString.size
    } else {
        start + limit
    }
    return decodeFromByteArray(deserializationStrategy, byteString.toByteArray(start, endIndex))
}

inline fun <reified T : Any> BinaryFormat.decodeFromByteString(byteString: ByteString): T =
    decodeFromByteString(serializer(), byteString)

fun <T : Any> BinaryFormat.encodeToByteString(serializationStrategy: SerializationStrategy<T>, value: T) : ByteString =
    ByteString(encodeToByteArray(serializationStrategy, value))

inline fun <reified T : Any> BinaryFormat.encodeToByteString(value: T) : ByteString =
    encodeToByteString(serializer(), value)
