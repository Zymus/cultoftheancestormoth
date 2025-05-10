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

import react.FC
import react.create
import react.dom.html.ReactHTML.details
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.summary

val PluginDetails = FC {
    val pluginName = "Skyrim.esm"
    val groups = listOf("ALCH")
    val records = List(groups.size) { index ->
        RecordDetails.create {
            key = "$index"
            tag = "TES4"
        }
    }
    val groupSummary = listOf(groups.size, groups.singleOrNull()?.let { "Group" } ?: "Groups")
        .joinToString(separator = " ")

    val recordSummary = listOf(records.size, records.singleOrNull()?.let { "Record" } ?: "Records").joinToString(separator = " ")
    val pluginSummary = listOf(pluginName, groupSummary, recordSummary).joinToString(separator = ", ")
    details {
        summary {
            +pluginSummary
        }
        div {
            records.forEach { +it }
        }
    }
}
