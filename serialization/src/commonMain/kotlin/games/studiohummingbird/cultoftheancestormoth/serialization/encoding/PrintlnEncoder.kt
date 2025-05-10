/**
Cult of the Ancestor Moth (PrintlnEncoder.kt)
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

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.AbstractEncoder
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule

@ExperimentalSerializationApi
class PrintlnEncoder : AbstractEncoder() {
    override val serializersModule: SerializersModule = EmptySerializersModule()

    override fun beginStructure(descriptor: SerialDescriptor): CompositeEncoder {
        println("beginStructure $descriptor")
        return super.beginStructure(descriptor)
    }

    override fun encodeBoolean(value: Boolean) {
        println("encodeBoolean $value")
        super.encodeBoolean(value)
    }

    override fun encodeByte(value: Byte) {
        println("encodeByte $value")
        super.encodeByte(value)
    }

    override fun encodeChar(value: Char) {
        println("encodeChar $value")
        super.encodeChar(value)
    }

    override fun encodeDouble(value: Double) {
        println("encodeDouble $value")
        super.encodeDouble(value)
    }

    override fun encodeEnum(enumDescriptor: SerialDescriptor, index: Int) {
        println("encodeEnum $enumDescriptor $index")
        super.encodeEnum(enumDescriptor, index)
    }

    override fun encodeFloat(value: Float) {
        println("encodeFloat $value")
        super.encodeFloat(value)
    }

    override fun encodeInline(descriptor: SerialDescriptor): Encoder {
        println("encodeInline $descriptor")
        return super.encodeInline(descriptor)
    }

    override fun encodeInt(value: Int) {
        println("encodeInt $value")
        super.encodeInt(value)
    }

    override fun encodeLong(value: Long) {
        println("encodeLong $value")
        super.encodeLong(value)
    }

    @ExperimentalSerializationApi
    override fun encodeNull() {
        println("encodeNull")
        super.encodeNull()
    }

    override fun encodeShort(value: Short) {
        println("encodeShort $value")
        super.encodeShort(value)
    }

    override fun encodeString(value: String) {
        println("encodeString $value")
        super.encodeString(value)
    }

    override fun <T> encodeSerializableValue(serializer: SerializationStrategy<T>, value: T) {
        println("encodeSerializableValue $serializer $value")
        super.encodeSerializableValue(serializer, value)
    }

    override fun beginCollection(descriptor: SerialDescriptor, collectionSize: Int): CompositeEncoder {
        println("beginCollection $descriptor $collectionSize")
        return super.beginCollection(descriptor, collectionSize)
    }

    override fun encodeNotNullMark() {
        println("encodeNotNullMark")
        super.encodeNotNullMark()
    }

    override fun <T : Any> encodeNullableSerializableValue(serializer: SerializationStrategy<T>, value: T?) {
        println("encodeNullableSerializableValue $serializer $value")
        super.encodeNullableSerializableValue(serializer, value)
    }
}
