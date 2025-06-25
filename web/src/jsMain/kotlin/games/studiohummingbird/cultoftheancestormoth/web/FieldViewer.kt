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
package games.studiohummingbird.cultoftheancestormoth.web

import games.studiohummingbird.cultoftheancestormoth.serialization.PluginFormat
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.NullTerminatedString
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.TypeTag
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Field
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.FieldType
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.FieldValue
import kotlinx.io.bytestring.ByteString
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.serializer
import react.FC
import react.Props
import react.dom.events.ChangeEventHandler
import react.dom.html.ReactHTML.article
import react.dom.html.ReactHTML.details
import react.dom.html.ReactHTML.form
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.label
import react.dom.html.ReactHTML.option
import react.dom.html.ReactHTML.output
import react.dom.html.ReactHTML.select
import react.dom.html.ReactHTML.summary
import react.useState
import web.html.HTMLInputElement
import web.html.HTMLSelectElement
import web.html.InputType

external interface PrimitiveNumberInputProps : Props {
    var min: Double
    var max: Double
    var onChange: ChangeEventHandler<HTMLInputElement>
    var value: Double
}

val PrimitiveNumberInput = FC<PrimitiveNumberInputProps> { props ->
    input {
        id = "fieldValue"
        name = "fieldValue"
        type = InputType.number
        min = props.min
        max = props.max
        onChange = props.onChange
        value = props.value
    }
}

val ByteInput = FC<PrimitiveNumberInputProps> { props ->
    PrimitiveNumberInput {
        min = Byte.MIN_VALUE.toDouble()
        max = Byte.MAX_VALUE.toDouble()
        onChange = props.onChange
        value = props.value
    }
}

val ShortInput = FC<PrimitiveNumberInputProps> { props ->
    PrimitiveNumberInput {
        min = Short.MIN_VALUE.toDouble()
        max = Short.MAX_VALUE.toDouble()
        onChange = props.onChange
        value = props.value
    }
}

val IntInput = FC<PrimitiveNumberInputProps> { props ->
    PrimitiveNumberInput {
        min = Int.MIN_VALUE.toDouble()
        max = Int.MAX_VALUE.toDouble()
        onChange = props.onChange
        value = props.value
    }
}

val LongInput = FC<PrimitiveNumberInputProps> { props ->
    PrimitiveNumberInput {
        min = Long.MIN_VALUE.toDouble()
        max = Long.MAX_VALUE.toDouble()
        onChange = props.onChange
        value = props.value
    }
}

val FloatInput = FC<PrimitiveNumberInputProps> { props ->
    PrimitiveNumberInput {
        min = Float.MIN_VALUE.toDouble()
        max = Float.MAX_VALUE.toDouble()
        onChange = props.onChange
        value = props.value
    }
}

val DoubleInput = FC<PrimitiveNumberInputProps> { props ->
    PrimitiveNumberInput {
        min = Double.MIN_VALUE
        max = Double.MAX_VALUE
        onChange = props.onChange
        value = props.value
    }
}

external interface NullTerminatedStringProps : Props {
    var onChange: ChangeEventHandler<HTMLInputElement>
    var value: String
}

val NullTerminatedStringInput = FC<NullTerminatedStringProps> { props ->
    input {
        type = InputType.text
        value = props.value
        onChange = props.onChange
    }
}

data class FieldViewerState(
    val fieldName: String,
    val fieldType: String,
    val value: Any
) {
    companion object {
        val DEFAULT = FieldViewerState("TES4", "NullTerminatedString", "")
    }
}

@ExperimentalSerializationApi
@ExperimentalStdlibApi
val FieldViewer = FC<Props> {
    val (state, setState) = useState(FieldViewerState.DEFAULT)

    val fieldNameChanged: ChangeEventHandler<HTMLInputElement> = {
        val updatedFieldName = it.currentTarget.value
        println("fieldName onChange $updatedFieldName")
        setState(state.copy(fieldName = updatedFieldName))
    }

    val fieldTypeChanged: ChangeEventHandler<HTMLSelectElement> = {
        val updatedFieldType = it.currentTarget.value
        println("fieldType onChange $updatedFieldType $state")
        setState(state.copy(
            fieldType = updatedFieldType,
            value = if (updatedFieldType == "NullTerminatedString") "" else 0.0
        ))
    }

    val setValueAsNumber: ChangeEventHandler<HTMLInputElement> = {
        setState(
            state.copy(value = it.currentTarget.valueAsNumber)
        )
    }

    val setValueAsText: ChangeEventHandler<HTMLInputElement> = {
        setState(
            state.copy(value = it.currentTarget.value)
        )
    }

    article {
        form {
            label {
                +"Field name"
                input {
                    id = "fieldName"
                    name = "fieldName"
                    type = InputType.text
                    value = state.fieldName
                    onChange = fieldNameChanged
                }
            }
            label {
                +"Field type"
                select {
                    id = "fieldType"
                    name = "fieldType"
                    value = state.fieldType
                    onChange = fieldTypeChanged
                    listOf("Byte", "Short", "Int", "Long", "Float", "Double", "NullTerminatedString").forEach {
                        option {
                            key = it
                            value = it
                            +it
                        }
                    }
                }
            }
            label {
                +"Field value"
                when (state.fieldType) {
                    "Byte" -> ByteInput {
                        onChange = setValueAsNumber
                        value = state.value as Double
                    }

                    "Short" -> ShortInput {
                        onChange = setValueAsNumber
                        value = state.value as Double
                    }

                    "Int" -> IntInput {
                        onChange = setValueAsNumber
                        value = state.value as Double
                    }

                    "Long" -> LongInput {
                        onChange = setValueAsNumber
                        value = state.value as Double
                    }

                    "Float" -> FloatInput {
                        onChange = setValueAsNumber
                        value = state.value as Double
                    }

                    "Double" -> DoubleInput {
                        onChange = setValueAsNumber
                        value = state.value as Double
                    }

                    "NullTerminatedString" -> NullTerminatedStringInput {
                        onChange = setValueAsText
                        value = state.value as String
                    }
                }
            }
            details {
                val fieldData: ByteArray = when (state.fieldType) {
                    "Byte" -> PluginFormat.encodeToByteArray(
                        serializer<Byte>(),
                        (state.value as Double).toInt().toByte()
                    )

                    "Short" -> PluginFormat.encodeToByteArray(
                        serializer<Short>(),
                        (state.value as Double).toInt().toShort()
                    )

                    "Int" -> PluginFormat.encodeToByteArray(serializer<Int>(), (state.value as Double).toInt())
                    "Long" -> PluginFormat.encodeToByteArray(serializer<Long>(), (state.value as Double).toLong())
                    "Float" -> PluginFormat.encodeToByteArray(serializer<Float>(), (state.value as Double).toFloat())
                    "Double" -> PluginFormat.encodeToByteArray(serializer<Double>(), state.value as Double)
                    "NullTerminatedString" -> PluginFormat.encodeToByteArray(
                        serializer<NullTerminatedString>(),
                        NullTerminatedString(state.value as String)
                    )

                    else -> ByteArray(0)
                }

                val field = Field(
                    FieldType(TypeTag(state.fieldName)),
                    0.toUShort(),
                    FieldValue(ByteString(fieldData))
                )

                val serializedField = PluginFormat.encodeToByteArray(Field.serializer(), field)

                summary {
                    +"Hexview (${serializedField.size} bytes)"
                }
                output {
                    id = "hexview"
                    name = "hexview"
                    htmlFor = "fieldName fieldType fieldValue"
                    +serializedField.toHexString(HexFormat {
                        bytes {
                            bytesPerLine = 4
                            byteSeparator = " "
                        }
                    })
                }
            }
        }
    }
}
