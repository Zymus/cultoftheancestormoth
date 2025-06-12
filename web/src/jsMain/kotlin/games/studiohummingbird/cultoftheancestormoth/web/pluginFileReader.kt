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
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.StreamingToken
import js.buffer.ArrayBuffer
import js.typedarrays.Int8Array
import kotlinx.io.bytestring.ByteString
import kotlinx.serialization.ExperimentalSerializationApi
import web.events.EventHandler
import web.file.FileReader
import kotlin.time.measureTime

/**
 * @return a [FileReader] that will execute [block] when a Plugin File is read.
 */
@ExperimentalStdlibApi
@ExperimentalSerializationApi
fun pluginFileReader(block: (Pair<ByteString, Sequence<StreamingToken>>) -> Unit): FileReader =
    FileReader().apply {
        onload = EventHandler { e ->
            val arrayBuffer: ArrayBuffer
            measureTime { arrayBuffer = result as ArrayBuffer }
                .also { println("$it arrayBuffer ${arrayBuffer.maxByteLength}") }

            val pluginBytes: ByteArray
            measureTime { pluginBytes = Int8Array(arrayBuffer).asByteArray()}
                .also { println("$it  pluginBytes ${pluginBytes.size}") }

            measureTime {
                var count = 0
                var result = 0
                for (byte in pluginBytes) {
                    // do nothing
                    count++
                    result = result xor byte.toInt()
                }
                println("$count $result")
            }
                .also(::println)

            val byteString = ByteString(pluginBytes)
            measureTime {
                var count = 0
                var result = 0
                for (byte in byteString.toByteArray()) {
                    // do nothing
                    count++
                    result = result xor byte.toInt()
                }
                println("bytestring $count $result")
            }
                .also(::println)

            val decodedPlugin: Sequence<StreamingToken>
            measureTime { decodedPlugin = PluginFormat.decodeMarkerSequenceFromByteString(byteString) }
                .also { println("$it decodedPlugin") }

            block(byteString to decodedPlugin)
        }
    }
