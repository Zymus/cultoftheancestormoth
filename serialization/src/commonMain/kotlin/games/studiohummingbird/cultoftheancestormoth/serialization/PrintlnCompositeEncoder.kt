/**
Cult of the Ancestor Moth (PrintlnCompositeEncoder.kt)
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
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule

class PrintlnCompositeEncoder(private val compositeEncoder: CompositeEncoder) : CompositeEncoder {
    override val serializersModule: SerializersModule = EmptySerializersModule()

    override fun encodeBooleanElement(
        descriptor: SerialDescriptor,
        index: Int,
        value: Boolean
    ) {
        println("encodeBooleanElement $descriptor $index $value")
        compositeEncoder.encodeBooleanElement(descriptor, index, value)
    }

    override fun encodeByteElement(
        descriptor: SerialDescriptor,
        index: Int,
        value: Byte
    ) {
        println("encodeByteElement $descriptor $index $value")
        compositeEncoder.encodeByteElement(descriptor, index, value)
    }

    override fun encodeCharElement(
        descriptor: SerialDescriptor,
        index: Int,
        value: Char
    ) {
        println("encodeCharElement $descriptor $index $value")
        compositeEncoder.encodeCharElement(descriptor, index, value)
    }

    override fun encodeDoubleElement(
        descriptor: SerialDescriptor,
        index: Int,
        value: Double
    ) {
        println("encodeDoubleElement $descriptor $index $value")
        compositeEncoder.encodeDoubleElement(descriptor, index, value)
    }

    override fun encodeFloatElement(
        descriptor: SerialDescriptor,
        index: Int,
        value: Float
    ) {
        println("encodeFloatElement $descriptor $index $value")
        compositeEncoder.encodeFloatElement(descriptor, index, value)
    }

    override fun encodeInlineElement(
        descriptor: SerialDescriptor,
        index: Int
    ): Encoder {
        println("encodeInlineElement $descriptor $index")
        return compositeEncoder.encodeInlineElement(descriptor, index)
    }

    override fun encodeIntElement(
        descriptor: SerialDescriptor,
        index: Int,
        value: Int
    ) {
        println("encodeIntElement $descriptor $index $value")
        compositeEncoder.encodeIntElement(descriptor, index, value)
    }

    override fun encodeLongElement(
        descriptor: SerialDescriptor,
        index: Int,
        value: Long
    ) {
        println("encodeLongElement $descriptor $index $value")
        compositeEncoder.encodeLongElement(descriptor, index, value)
    }

    @ExperimentalSerializationApi
    override fun <T : Any> encodeNullableSerializableElement(
        descriptor: SerialDescriptor,
        index: Int,
        serializer: SerializationStrategy<T>,
        value: T?
    ) {
        println("encodeNullableSerializableElement $descriptor $index $serializer $value")
        compositeEncoder.encodeNullableSerializableElement(descriptor, index, serializer, value)
    }

    override fun <T> encodeSerializableElement(
        descriptor: SerialDescriptor,
        index: Int,
        serializer: SerializationStrategy<T>,
        value: T
    ) {
        println("encodeNullableSerializableElement $descriptor $index $serializer $value")
        compositeEncoder.encodeSerializableElement(descriptor, index, serializer, value)
    }

    override fun encodeShortElement(
        descriptor: SerialDescriptor,
        index: Int,
        value: Short
    ) {
        println("encodeShortElement $descriptor $index $value")
        compositeEncoder.encodeShortElement(descriptor, index, value)
    }

    override fun encodeStringElement(
        descriptor: SerialDescriptor,
        index: Int,
        value: String
    ) {
        println("encodeStringElement $descriptor $index $value")
        compositeEncoder.encodeStringElement(descriptor, index, value)
    }

    override fun endStructure(descriptor: SerialDescriptor) {
        println("endStructure $descriptor")
        compositeEncoder.endStructure(descriptor)
    }
}
