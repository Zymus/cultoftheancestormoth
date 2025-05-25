/**
Cult of the Ancestor Moth (ObjectBoundsSerializer.kt)
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
package games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes

import games.studiohummingbird.cultoftheancestormoth.bytestring.serializer.encodeToByteString
import games.studiohummingbird.cultoftheancestormoth.serialization.PluginFormat
import games.studiohummingbird.cultoftheancestormoth.serialization.annotations.FieldAnnotation
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.NullTerminatedString
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.TypeTag
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Field
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.FieldSize
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.FieldType
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.FieldValue
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
//Suppress runtime target warning for Javascript, this should all be compile time
@Suppress("RUNTIME_ANNOTATION_NOT_SUPPORTED")
data class TES4(

    @FieldAnnotation("HEDR")
    val header: Field,

    @FieldAnnotation("CNAM")
    val author: Field,

    @FieldAnnotation("SNAM")
    val description: Field,

    val masters: List<MasterFile>,

    @FieldAnnotation("INTV")
    val numberOfTagifiableValues: Field
) {

    enum class Flags(val value: Int) {
        MASTER_FILE       (0x00_01),
        LOCALIZED         (0x00_80),
        LIGHT_MASTER_FILE (0x02_00),
        ;

        companion object {
            fun toInt(vararg flags: Flags): Int =
                flags.fold(0) { value, flag -> value or flag.value }
        }
    }

    @Serializable
    data class Header(
        val version: Float,
        val recordCount: Int,
        val nextAvailableObjectId: Int
    ) {
        companion object {
            fun Field(version: Float, recordCount: Int, nextAvailableObjectId: Int) = Field(
                FieldType(TypeTag("HEDR")),
                FieldSize(0),
                FieldValue(PluginFormat.encodeToByteString(Header(version, recordCount, nextAvailableObjectId)))
            )
        }
    }

    @JvmInline
    @Serializable
    value class Author(val nullTerminated: NullTerminatedString) {
        init {
            require(nullTerminated.length < MAX_LENGTH)
        }

        companion object {
            const val MAX_LENGTH = 0x0200
            operator fun invoke(value: String) = Author(NullTerminatedString(value))

            fun Field(value: String): Field = Field(
                FieldType(TypeTag("CNAM")),
                FieldSize(0),
                FieldValue(PluginFormat.encodeToByteString(Author(value)))
            )
        }
    }

    @JvmInline
    @Serializable
    value class Description(val nullTerminated: NullTerminatedString) {
        init {
            require(nullTerminated.length < MAX_LENGTH)
        }

        companion object {
            const val MAX_LENGTH = 0x0200
            operator fun invoke(value: String) = Description(NullTerminatedString(value))

            fun Field(value: String): Field = Field(
                FieldType(TypeTag("SNAM")),
                FieldSize(0),
                FieldValue(PluginFormat.encodeToByteString(Description(NullTerminatedString(value))))
            )
        }
    }

    @Serializable
    data class MasterFile(
        @FieldAnnotation("MAST")
        val name: Field,

        @FieldAnnotation("DATA")
        val data: Field = Field(
            FieldType(TypeTag("DATA")),
            FieldSize(0),
            FieldValue(PluginFormat.encodeToByteString(Data.DEFAULT)))
    ) {
        @JvmInline
        @Serializable
        value class Name(val nullTerminated: NullTerminatedString) {
            companion object {
                operator fun invoke(value: String) = Name(NullTerminatedString(value))
            }
        }

        @JvmInline
        @Serializable
        value class Data(val long: Long) {
            companion object {
                val DEFAULT = Data(0L)
            }
        }

        companion object {
            operator fun invoke(name: String, data: Long = Data.DEFAULT.long)  = MasterFile(
                Field(
                    FieldType(TypeTag("MAST")),
                    FieldSize(0),
                    FieldValue(PluginFormat.encodeToByteString(Name(name)))),
                Field(
                    FieldType(TypeTag("MAST")),
                    FieldSize(0),
                    FieldValue(PluginFormat.encodeToByteString(Data(data)))))
        }
    }

    @JvmInline
    @Serializable
    value class TagifiableValues(val int: Int) {
        companion object {
            fun Field(value: Int) = Field(
                FieldType(TypeTag("INTV")),
                FieldSize(0),
                FieldValue(PluginFormat.encodeToByteString(TagifiableValues(value)))
            )
        }
    }
}
