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

import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.StreamingToken
import kotlinx.serialization.ExperimentalSerializationApi
import react.FC
import react.Props
import react.dom.html.ReactHTML.form
import react.dom.html.ReactHTML.input
import react.useState
import web.events.EventHandler
import web.html.InputType.Companion.file
import kotlin.time.measureTime

@ExperimentalSerializationApi
@ExperimentalStdlibApi
val PluginViewer = FC<Props> {
    val (plugin, setPlugin) = useState<Collection<StreamingToken>>(emptyList())
    form {
        input {
            id = "plugin-upload"
            type = file
            name = "plugin-upload"
            onChange = {
                console.log("length", it.target.files?.length)
                console.log("item0", it.target.files?.item(0))

                val file = it.target.files?.item(0)
                if (file != null) {
                    val reader = pluginFileReader { sequence ->
                        measureTime {
                            val list = ArrayList<StreamingToken>(5_000_000)
                            val chunked: Sequence<List<StreamingToken>>

                            measureTime {
                                chunked = sequence.chunked(1_000)
                            }
                                .also { chunkedDuration -> println("chunked $chunkedDuration") }

                            measureTime {
                                chunked.forEach(list::addAll)
                            }
                                .also { chunkedDuration -> println("addAll $chunkedDuration") }

                            list.count().run(::println)
                        }
                            .also(::println)
//                        setPlugin(sequence)
                    }.apply {
                        onprogress = EventHandler { e ->
                            console.log("reader onprogress", e.lengthComputable, e.loaded, e.total)
                        }
                    }
                    reader.readAsArrayBuffer(file)
                }
            }
        }
        PluginFC {
            name = "Skyrim.esm"
        }
    }
}
