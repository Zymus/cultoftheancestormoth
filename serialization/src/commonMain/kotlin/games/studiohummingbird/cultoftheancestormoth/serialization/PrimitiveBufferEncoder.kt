/**
Cult of the Ancestor Moth (PrimitiveBufferEncoder.kt)
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

import kotlinx.io.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encoding.AbstractEncoder
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule

@ExperimentalSerializationApi
class PrimitiveBufferEncoder(private val sink: Sink) : AbstractEncoder() {
    override val serializersModule: SerializersModule = EmptySerializersModule()

    override fun encodeByte(value: Byte) = sink.writeByte(value)

    override fun encodeShort(value: Short) = sink.writeShortLe(value)

    override fun encodeInt(value: Int) = sink.writeIntLe(value)

    override fun encodeLong(value: Long) = sink.writeLongLe(value)

    override fun encodeFloat(value: Float) = sink.writeFloatLe(value)

    override fun encodeDouble(value: Double) = sink.writeDoubleLe(value)
}
