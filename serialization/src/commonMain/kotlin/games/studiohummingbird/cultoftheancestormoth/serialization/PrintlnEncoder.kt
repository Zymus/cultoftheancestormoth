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
package games.studiohummingbird.cultoftheancestormoth.serialization

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.modules.SerializersModule

@ExperimentalSerializationApi
class PrintlnEncoder(private val encoder: Encoder) : Encoder {
    override val serializersModule: SerializersModule = encoder.serializersModule

    override fun beginStructure(descriptor: SerialDescriptor): CompositeEncoder {
        println("beginStructure $descriptor")
        return encoder.beginStructure(descriptor)
    }

    override fun encodeBoolean(value: Boolean) {
        println("encodeBoolean $value")
        encoder.encodeBoolean(value)
    }

    override fun encodeByte(value: Byte) {
        println("encodeByte $value")
        encoder.encodeByte(value)
    }

    override fun encodeChar(value: Char) {
        println("encodeChar $value")
        encoder.encodeChar(value)
    }

    override fun encodeDouble(value: Double) {
        println("encodeDouble $value")
        encoder.encodeDouble(value)
    }

    override fun encodeEnum(enumDescriptor: SerialDescriptor, index: Int) {
        println("encodeEnum $enumDescriptor $index")
        encoder.encodeEnum(enumDescriptor, index)
    }

    override fun encodeFloat(value: Float) {
        println("encodeFloat $value")
        encoder.encodeFloat(value)
    }

    override fun encodeInline(descriptor: SerialDescriptor): Encoder {
        println("encodeInline $descriptor")
        return encoder.encodeInline(descriptor)
    }

    override fun encodeInt(value: Int) {
        println("encodeInt $value")
        encoder.encodeInt(value)
    }

    override fun encodeLong(value: Long) {
        println("encodeLong $value")
        encoder.encodeLong(value)
    }

    @ExperimentalSerializationApi
    override fun encodeNull() {
        println("encodeNull")
        encoder.encodeNull()
    }

    override fun encodeShort(value: Short) {
        println("encodeShort $value")
        encoder.encodeShort(value)
    }

    override fun encodeString(value: String) {
        println("encodeString $value")
        encoder.encodeString(value)
    }

    override fun <T> encodeSerializableValue(serializer: SerializationStrategy<T>, value: T) {
        println("encodeSerializableValue $serializer $value")
        encoder.encodeSerializableValue(serializer, value)
    }

    override fun beginCollection(descriptor: SerialDescriptor, collectionSize: Int): CompositeEncoder {
        println("beginCollection $descriptor $collectionSize")
        return encoder.beginCollection(descriptor, collectionSize)
    }

    override fun encodeNotNullMark() {
        println("encodeNotNullMark")
        encoder.encodeNotNullMark()
    }

    override fun <T : Any> encodeNullableSerializableValue(serializer: SerializationStrategy<T>, value: T?) {
        println("encodeNullableSerializableValue $serializer $value")
        encoder.encodeNullableSerializableValue(serializer, value)
    }
}
