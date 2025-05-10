/**
Cult of the Ancestor Moth (RecordSerializer.kt)
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

import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.TypeTag
import games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes.Record
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.CompositeDecoder.Companion.DECODE_DONE
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure

@ExperimentalSerializationApi
class RecordSerializer<T : Any>(val propertiesSerializer: KSerializer<T>) : KSerializer<Record<T>> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor(SERIAL_NAME) {
        element<TypeTag>("type")
        element<Int>("len")
        element<Int>("flags")
        element<Int>("formId")
        element<Short>("timestamp")
        element<Short>("versionControl")
        element<Short>("version")
        element<Short>("unknown")
        element("fields", propertiesSerializer.descriptor)
    }

    override fun deserialize(decoder: Decoder): Record<T> {
        var typeTag = ""
        var len = 0
        var flags = 0b0
        var formId = 0
        var timestamp: Short = 0
        var versionControl: Short = 0
        var version: Short = 0
        var unknown: Short = 0
        var properties: T? = null

        decoder.decodeStructure(descriptor) {
            while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    DECODE_DONE -> break
                    ElementIndex.TYPE -> typeTag = decodeInlineElement(descriptor, index).decodeString()
                    ElementIndex.LENGTH -> len = decodeIntElement(descriptor, index)
                    ElementIndex.FLAGS -> flags = decodeIntElement(descriptor, index)
                    ElementIndex.FORM_ID -> formId = decodeIntElement(descriptor, index)
                    ElementIndex.TIMESTAMP -> timestamp = decodeShortElement(descriptor, index)
                    ElementIndex.VERSION_CONTROL -> versionControl = decodeShortElement(descriptor, index)
                    ElementIndex.VERSION -> version = decodeShortElement(descriptor, index)
                    ElementIndex.UNKNOWN -> unknown = decodeShortElement(descriptor, index)
                    ElementIndex.FIELDS -> properties = decodeSerializableElement(descriptor, index, propertiesSerializer)
                    else -> TODO("unhandled index $index")
                }
            }
        }

        check(properties != null)

        return Record(
            TypeTag(typeTag),
            len,
            flags,
            formId,
            timestamp,
            versionControl,
            version,
            unknown,
            properties
        )
    }

    override fun serialize(encoder: Encoder, value: Record<T>) {
        encoder.encodeStructure(descriptor) {
            encodeStringElement(descriptor, ElementIndex.TYPE, elementValue(ElementIndex.TYPE, value) as String)
            encodeIntElement(descriptor, ElementIndex.LENGTH, elementValue(ElementIndex.LENGTH, value) as Int)
            encodeIntElement(descriptor, ElementIndex.FLAGS, elementValue(ElementIndex.FLAGS, value) as Int)
            encodeIntElement(descriptor, ElementIndex.FORM_ID, elementValue(ElementIndex.FORM_ID, value) as Int)
            encodeShortElement(descriptor, ElementIndex.TIMESTAMP, elementValue(ElementIndex.TIMESTAMP, value) as Short)
            encodeShortElement(descriptor, ElementIndex.VERSION_CONTROL, elementValue(ElementIndex.VERSION_CONTROL, value) as Short)
            encodeShortElement(descriptor, ElementIndex.VERSION, elementValue(ElementIndex.VERSION, value) as Short)
            encodeShortElement(descriptor, ElementIndex.UNKNOWN, elementValue(ElementIndex.UNKNOWN, value) as Short)
            encodeSerializableElement<T>(descriptor, ElementIndex.FIELDS, propertiesSerializer, elementValue(ElementIndex.FIELDS, value) as T)
            TODO()
        }
    }

    fun elementValue(index: Int, value: Record<T>): Any? =
        when (index) {
//            ElementIndex.TYPE -> value.typeTag
//            ElementIndex.LENGTH -> value.size
//            ElementIndex.FLAGS -> value.flags
//            ElementIndex.FORM_ID -> value.formId
//            ElementIndex.TIMESTAMP -> value.timestamp
//            ElementIndex.VERSION_CONTROL -> value.versionControl
//            ElementIndex.VERSION -> value.version
//            ElementIndex.UNKNOWN -> value.unknown
            ElementIndex.FIELDS -> value
            else -> TODO()
        }

    companion object {
        const val SERIAL_NAME = "games.studiohummingbird.cultoftheancestormoth.Record"

        object ElementIndex {
            const val TYPE = 0
            const val LENGTH = 1
            const val FLAGS = 2
            const val FORM_ID = 3
            const val TIMESTAMP = 4
            const val VERSION_CONTROL = 5
            const val VERSION = 6
            const val UNKNOWN = 7
            const val FIELDS = 8
        }
    }
}
