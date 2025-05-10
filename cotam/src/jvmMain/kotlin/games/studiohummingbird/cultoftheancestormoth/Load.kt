/**
Cult of the Ancestor Moth (Load.kt)
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
package games.studiohummingbird.cultoftheancestormoth

import com.github.ajalt.clikt.core.CliktCommand
import games.studiohummingbird.cultoftheancestormoth.serialization.PluginFormat
import kotlinx.serialization.decodeFromByteArray
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.time.measureTime

class Load : CliktCommand() {
    override fun run() {
        val path = Paths.get("example.esp")

        measureTime {
            val bytes = Files.readAllBytes(path)
            val plugin = PluginFormat.decodeFromByteArray<Plugin>(bytes).also(::println)
        }.also(::println)
    }
}
