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
import react.Props
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.details
import react.dom.html.ReactHTML.form
import react.dom.html.ReactHTML.input
import react.dom.html.ReactHTML.label
import react.dom.html.ReactHTML.summary
import web.cssom.ClassName
import web.html.ButtonType
import web.html.InputType

external interface RecordFCProps : Props {
    var tag: String
}

val RecordDetails = FC<RecordFCProps> { props ->
    details {
        className = ClassName("record-details")
        summary { +props.tag }
        form {
            label {
                +"Tag"
                input {
                    disabled = true
                    name = "tag"
                    type = InputType.text
                    value = props.tag
                }
            }
            button {
                +"Save ${props.tag}"
                type = ButtonType.button
            }
        }
    }
}
