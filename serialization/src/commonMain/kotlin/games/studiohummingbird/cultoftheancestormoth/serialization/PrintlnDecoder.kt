/**
Cult of the Ancestor Moth (PrintlnDecoder.kt)
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

import games.studiohummingbird.cultoftheancestormoth.serialization.annotations.isRecord
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.modules.SerializersModule

class PrintlnDecoder(private val decoder: Decoder) : Decoder {
    override val serializersModule: SerializersModule = decoder.serializersModule

    @ExperimentalSerializationApi
    override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder {
        println("beginStructure")
        println("- kind=${descriptor.kind}")
        println("- serialName=${descriptor.serialName}")
        println(". isRecord=${descriptor.isRecord()}")
        println("- annotations=${descriptor.annotations}")
        println("- elementsCount=${descriptor.elementsCount}")
        repeat(descriptor.elementsCount) {
            println(" - elementDescriptor$it=${descriptor.getElementDescriptor(it)}")
        }

        return decoder.beginStructure(descriptor)
    }

    override fun decodeBoolean(): Boolean {
        print("decodeBoolean ")
        val value = decoder.decodeBoolean()
        println(value)
        return value
    }

    override fun decodeByte(): Byte {
        print("decodeByte ")
        val value = decoder.decodeByte()
        println(value)
        return value
    }

    override fun decodeChar(): Char {
        print("decodeChar ")
        val value = decoder.decodeChar()
        println(value)
        return value
    }

    override fun decodeDouble(): Double {
        print("decodeDouble ")
        val value = decoder.decodeDouble()
        println(value)
        return value
    }

    override fun decodeEnum(enumDescriptor: SerialDescriptor): Int {
        print("decodeEnum $enumDescriptor")
        val value = decoder.decodeEnum(enumDescriptor)
        println(value)
        return value
    }

    override fun decodeFloat(): Float {
        print("decodeFloat ")
        val value = decoder.decodeFloat()
        println(value)
        return value
    }

    override fun decodeInline(descriptor: SerialDescriptor): Decoder {
        print("decodeInline $descriptor")
        val value = decoder.decodeInline(descriptor)
        println(value)
        return value
    }

    override fun decodeInt(): Int {
        print("decodeInt ")
        val value = decoder.decodeInt()
        println(value)
        return value
    }

    override fun decodeLong(): Long {
        print("decodeLong ")
        val value = decoder.decodeLong()
        println(value)
        return value
    }

    @ExperimentalSerializationApi
    override fun decodeNotNullMark(): Boolean {
        print("decodeNotNullMark ")
        val value = decoder.decodeNotNullMark()
        println(value)
        return value
    }

    @ExperimentalSerializationApi
    override fun decodeNull(): Nothing? {
        print("decodeNull ")
        val value = decoder.decodeNull()
        println(value)
        return value
    }

    override fun decodeShort(): Short {
        print("decodeShort ")
        val value = decoder.decodeShort()
        println(value)
        return value
    }

    override fun decodeString(): String {
        print("decodeString ")
        val value = decoder.decodeString()
        println(value)
        return value
    }
}
