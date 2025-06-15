/**
Cult of the Ancestor Moth (ElementMarkerList.kt)
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
package games.studiohummingbird.cultoftheancestormoth.web

import games.studiohummingbird.cultoftheancestormoth.bytestring.serializer.decodeFromByteString
import games.studiohummingbird.cultoftheancestormoth.serialization.PluginFormat
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.NullTerminatedString
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Fields
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.PluginElementMarker
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.PluginRecord
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.PluginToken
import kotlinx.io.bytestring.ByteString
import kotlinx.io.bytestring.toHexString
import kotlinx.serialization.PolymorphicSerializer
import react.FC
import react.Props
import react.dom.html.ReactHTML.code
import react.dom.html.ReactHTML.details
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.ol
import react.dom.html.ReactHTML.summary
import react.dom.html.ReactHTML.table
import react.dom.html.ReactHTML.tbody
import react.dom.html.ReactHTML.td
import react.dom.html.ReactHTML.th
import react.dom.html.ReactHTML.thead
import react.dom.html.ReactHTML.tr
import react.useEffect
import react.useState

external interface ElementMarkerListProps : Props {
    var byteString: ByteString
    var pluginElementMarkers: List<PluginElementMarker>
}

@OptIn(ExperimentalStdlibApi::class)
val ElementMarkerList = FC<ElementMarkerListProps> { props ->
    val (records, setRecords) = useState<List<PluginRecord>>(emptyList())

    useEffect(props.byteString, props.pluginElementMarkers) {
        val tokenRecords = props.pluginElementMarkers
            .map { marker ->
                PluginFormat.decodeFromByteString(
                    PolymorphicSerializer(PluginToken::class),
                    props.byteString,
                    marker.skip.toInt(),
                    marker.size.toInt()
                )
            }
            .filterIsInstance<PluginRecord>()

        setRecords(tokenRecords)
    }

    ol {
        records
            .map { it.fields as Fields }
            .forEach { fields ->
                li {
                    details {
                        summary {
                            val edidField = fields.list.singleOrNull { it.fieldType.typeTag.string == "EDID" }
                            if (edidField == null) {
                                "No EDID"
                            }
                            else {
                                val edidValue = PluginFormat.decodeFromByteString(
                                    NullTerminatedString.serializer(),
                                    edidField.fieldValue.value
                                ).string
                                +"EDID: $edidValue"
                            }
                        }
                        table {
                            thead {
                                tr {
                                    th { +"Name" }
                                    th { +"Size" }
                                    th { +"Value" }
                                }
                            }
                            tbody {
                                fields.list.forEach { field ->
                                    tr {
                                        td { +field.fieldType.typeTag.string }
                                        td { +field.fieldSize.toInt().toString() }
                                        td { code { +field.fieldValue.value.toHexString() } }
                                    }
                                }
                            }
                        }
                    }
                }
            }
    }
}
