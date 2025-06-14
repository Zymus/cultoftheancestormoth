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
import kotlinx.serialization.PolymorphicSerializer
import react.FC
import react.Props
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.ol

external interface ElementMarkerListProps : Props {
    var byteString: ByteString
    var pluginElementMarkers: List<PluginElementMarker>
}

val ElementMarkerList = FC<ElementMarkerListProps> { props ->
    ol {
        props.pluginElementMarkers
            .filter { it.tag.string == "COBJ" }
            .map { marker ->
                PluginFormat.decodeFromByteString(
                    PolymorphicSerializer(PluginToken::class),
                    props.byteString,
                    marker.skip.toInt(),
                    marker.size.toInt()
                )
            }
            .map { token ->
                when (token) {
                    is PluginRecord -> token.fields as Fields
                    else -> TODO()
                }
            }
            .flatMap { it.list }
            .filter { it.fieldType.typeTag.string == "EDID" }
            .map {
                PluginFormat.decodeFromByteString(
                    NullTerminatedString.serializer(),
                    it.fieldValue.value
                ).string
            }
            .forEach {
            li {
                +it
            }
//            console.log("${elementMarker.tag.string} ${elementMarker.skip} ${elementMarker.size}")
        }
    }
}
