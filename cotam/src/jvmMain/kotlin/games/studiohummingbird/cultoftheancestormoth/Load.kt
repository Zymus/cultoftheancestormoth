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
import com.github.ajalt.clikt.parameters.options.default
import com.github.ajalt.clikt.parameters.options.option
import games.studiohummingbird.cultoftheancestormoth.bytestring.serializer.decodeFromByteString
import games.studiohummingbird.cultoftheancestormoth.serialization.PluginFormat
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.NullTerminatedString
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Fields
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.PluginRecord
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.PluginToken
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.StreamingToken
import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import kotlinx.io.readByteString
import kotlinx.serialization.PolymorphicSerializer
import kotlin.time.measureTime

class Load : CliktCommand() {

    val groupName: String? by option()
        .default("COBJ")

    override fun run() {
        val bufferedMasterFile = SystemFileSystem
            .source(
                Path(
                    "/",
                    "media",
                    "zymus",
                    "5516E98402BDA1A5",
                    "SteamLibrary",
                    "steamapps",
                    "common",
                    "Skyrim Special Edition",
                    "Data",
//                    "Update.esm"     //  153724
                    "Skyrim.esm"     // 4387995
//                    "HearthFires.esm"//  127665
                )
            )
            .buffered()

        val transferByteString = bufferedMasterFile.readByteString()
        println(transferByteString.size)

        var sequence: Sequence<StreamingToken>

        repeat(1) {
            measureTime {
                sequence = PluginFormat.decodeMarkerSequenceFromByteString(transferByteString)
                sequence
                    .toList()
//                    .forEach(::println)
                    .filter { marker -> marker.tag.string == groupName }
                    .map {
                        PluginFormat.decodeFromByteString(
                            PolymorphicSerializer(PluginToken::class),
                            transferByteString,
                            it.skip.toInt(),
                            it.size.toInt())
                    }
                    .map {
                        when (it) {
                            is PluginRecord -> it.fields as Fields
                            else -> TODO()
                        }
                    }
                    .flatMap { it.list }
                    .filter { it.fieldType.typeTag.string == "EDID" }
                    .map { PluginFormat.decodeFromByteString(NullTerminatedString.serializer(), it.fieldValue.value).string }
                    .forEach(::println)
            }
                .also { println("toList time $it") }
        }
    }
}
