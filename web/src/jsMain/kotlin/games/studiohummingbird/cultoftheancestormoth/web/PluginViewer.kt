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

import games.studiohummingbird.cultoftheancestormoth.bytestring.serializer.decodeFromByteString
import games.studiohummingbird.cultoftheancestormoth.serialization.PluginFormat
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.NullTerminatedString
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Fields
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.PluginElementMarker
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.PluginRecord
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.PluginToken
import js.buffer.ArrayBuffer
import js.typedarrays.Int8Array
import kotlinx.coroutines.delay
import kotlinx.io.bytestring.ByteString
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.PolymorphicSerializer
import react.FC
import react.Props
import react.dom.html.ReactHTML.form
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.label
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.progress
import react.useEffect
import react.useState
import web.events.EventHandler
import web.file.FileReader
import web.html.InputType.Companion.file
import kotlin.time.measureTime

@ExperimentalSerializationApi
@ExperimentalStdlibApi
val PluginViewer = FC<Props> { props ->
    val (byteString, setByteString) = useState(ByteString())
    val (elementMarkers, setElementMarkers) = useState<List<PluginElementMarker>>(emptyList())
    val (lastElementMarkerRead, setLastElementMarkerRead) = useState<PluginElementMarker>()

    useEffect(byteString, setElementMarkers, setLastElementMarkerRead) {
        val elementMarkerSequence: Sequence<PluginElementMarker> =
            PluginFormat.decodeMarkerSequenceFromByteString(byteString)

        val tokenList: MutableList<PluginElementMarker> = mutableListOf()
        val toListTime = measureTime {
            elementMarkerSequence
                .chunked(10000)
                .forEach { chunk ->
                    tokenList.addAll(chunk)
                    setLastElementMarkerRead(chunk.last())
                    delay(1)
                }
        }

        println("toList time $toListTime ${tokenList.size}")

        setElementMarkers(tokenList)
    }

    useEffect(elementMarkers) {
        val groupsToRead = listOf(
            "COBJ",
            "ALCH"
        )

        groupsToRead.forEach { groupToRead ->
            measureTime {
                elementMarkers
                    .filter { marker -> marker.tag.string == groupToRead }
                    .map { marker ->
                        PluginFormat.decodeFromByteString(
                            PolymorphicSerializer(PluginToken::class),
                            byteString,
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
                    .forEach(::println)
            }.also {
                println("$groupToRead $it")
            }
        }
    }

    form {
        p {
            +"""
                Select the Scroll that you would like to read.
            """.trimIndent()
        }
        input {
            id = "plugin-upload"
            type = file
            name = "plugin-upload"
            onChange = { event ->
                console.log("length", event.target.files?.length)
                console.log("item0", event.target.files?.item(0))

                val file = event.target.files?.item(0)
                if (file != null) {
                    val reader = FileReader()

                    reader.onload = EventHandler { e ->
                        val arrayBuffer: ArrayBuffer = reader.result as ArrayBuffer
                        val pluginBytes: ByteArray = Int8Array(arrayBuffer).asByteArray()
                        val byteString = ByteString(pluginBytes)
                        setByteString(byteString)
                    }

                    reader.onprogress = EventHandler { e ->
                        console.log("reader onprogress", e.lengthComputable, e.loaded, e.total)
                    }

                    reader.readAsArrayBuffer(file)
                }
            }
        }

        val loadingProgressId = "loading-progress"
        val loadingProgressLabel = "Reading scroll"
        label {
            +loadingProgressLabel
            hidden = byteString.size == 0
            progress {
                id = loadingProgressId
                max = byteString.size.toDouble()
                value = lastElementMarkerRead?.skip ?: 0
            }
        }

        PluginFC {
            name = "Skyrim.esm"
        }
        ElementMarkerList {
            this.byteString = byteString
            pluginElementMarkers = elementMarkers
        }
    }
}
