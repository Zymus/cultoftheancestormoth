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
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.PluginElementMarker
import js.buffer.ArrayBuffer
import js.typedarrays.Int8Array
import kotlinx.coroutines.delay
import kotlinx.io.bytestring.ByteString
import kotlinx.serialization.ExperimentalSerializationApi
import react.FC
import react.Props
import react.dom.html.ReactHTML.form
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.label
import react.dom.html.ReactHTML.option
import react.dom.html.ReactHTML.p
import react.dom.html.ReactHTML.progress
import react.dom.html.ReactHTML.select
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
    val (typeTags, setTypeTags) = useState<List<Pair<String, Int>>>(emptyList())
    val (selectedTypeTag, setSelectedTypeTag) = useState<String>()

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

    useEffect(elementMarkers, setTypeTags) {
        val typeTags = elementMarkers
            .filter { it.type == PluginElementMarker.Type.RECORD && !it.isDataCompressed }
            .groupBy { it.tag.string }
            .map { it.key to it.value.count() }
            .toList()

        setTypeTags(typeTags)
        setSelectedTypeTag(typeTags.first().first)
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

        select {
            name = "selectedTypeTag"
            value = selectedTypeTag ?: ""
            onChange = { event ->
                val value = event.target.value
                console.log(value)
                setSelectedTypeTag(value)
            }

            typeTags
                .forEach {
                option {
                    +"${it.first} (Count: ${it.second})"
                    value = it.first
                }
            }
        }

        ElementMarkerList {
            this.byteString = byteString
            pluginElementMarkers = elementMarkers.filter { it.tag.string == selectedTypeTag }
        }
    }
}
