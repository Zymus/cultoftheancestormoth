/**
Cult of the Ancestor Moth (byteStringFileReader.kt)
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

import js.buffer.ArrayBuffer
import js.typedarrays.Int8Array
import kotlinx.io.bytestring.ByteString
import kotlinx.serialization.ExperimentalSerializationApi
import web.events.EventHandler
import web.file.FileReader

/**
 * @return a [FileReader] that will execute [block] when a Plugin File is read.
 */
@ExperimentalStdlibApi
@ExperimentalSerializationApi
fun byteStringFileReader(block: (ByteString) -> Unit): FileReader =
    FileReader().apply {
        onload = EventHandler { e ->
            val arrayBuffer: ArrayBuffer = result as ArrayBuffer
            val pluginBytes: ByteArray = Int8Array(arrayBuffer).asByteArray()
            val byteString = ByteString(pluginBytes)
            block(byteString)
        }
    }
