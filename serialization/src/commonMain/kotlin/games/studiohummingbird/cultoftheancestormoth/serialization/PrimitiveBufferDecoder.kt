/**
Cult of the Ancestor Moth (PrimitiveBufferDecoder.kt)
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

import kotlinx.io.Buffer
import kotlinx.io.readDoubleLe
import kotlinx.io.readFloatLe
import kotlinx.io.readIntLe
import kotlinx.io.readLongLe
import kotlinx.io.readShortLe
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.AbstractDecoder
import kotlinx.serialization.encoding.CompositeDecoder.Companion.UNKNOWN_NAME
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule

@ExperimentalSerializationApi
open class PrimitiveBufferDecoder(protected val buffer: Buffer) : AbstractDecoder() {
    override val serializersModule: SerializersModule = EmptySerializersModule()

    override fun decodeByte(): Byte = buffer.readByte()

    override fun decodeShort(): Short = buffer.readShortLe()

    override fun decodeInt(): Int = buffer.readIntLe()

    override fun decodeLong(): Long = buffer.readLongLe()

    override fun decodeFloat(): Float = buffer.readFloatLe()

    override fun decodeDouble(): Double = buffer.readDoubleLe()

    override fun decodeElementIndex(descriptor: SerialDescriptor): Int = UNKNOWN_NAME
}
