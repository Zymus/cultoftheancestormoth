/**
Cult of the Ancestor Moth (RecordFieldsDecoder.kt)
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

import games.studiohummingbird.cultoftheancestormoth.serialization.PluginFormat
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.CompressedFields
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RecordHeader
import kotlinx.io.Source
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.AbstractDecoder
import kotlinx.serialization.encoding.CompositeDecoder.Companion.DECODE_DONE
import kotlinx.serialization.modules.SerializersModule

@OptIn(ExperimentalSerializationApi::class)
class CompressedFieldsDecoder(
    override val serializersModule: SerializersModule,
    private val source: Source,
) : AbstractDecoder() {

    private var elementIndex = 0

    override fun decodeElementIndex(descriptor: SerialDescriptor): Int =
        if (elementIndex == descriptor.elementsCount) {
            DECODE_DONE
        } else {
            elementIndex++
        }

    override fun <T> decodeSerializableElement(
        descriptor: SerialDescriptor,
        index: Int,
        deserializer: DeserializationStrategy<T>,
        previousValue: T?
    ): T =
        when (index) {
            0 -> PluginFormat.decodeFromSource(RecordHeader.serializer(), source)
            1 -> PluginFormat.decodeFromSource(CompressedFields.serializer(), source)
            else -> TODO("unrecognized index")
        } as? T ?: TODO("uncastable value")
}
