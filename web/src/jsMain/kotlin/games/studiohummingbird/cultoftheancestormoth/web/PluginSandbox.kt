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
import react.dom.events.DragEventHandler
import react.dom.events.ReactEventHandler
import react.dom.html.ReactHTML.details
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.summary
import react.useState
import web.cssom.ClassName
import web.html.HTMLDetailsElement
import web.html.HTMLDivElement

external interface PluginProps : Props {
    var name: String
}

sealed interface Side
data object Left : Side
data object Right: Side

data class PluginFCState(
    val side: Side = Left,
    val isOpen: Boolean? = null
)

external interface DraggableProps : Props {
    var name: String
    var isOpen: Boolean?
    var onToggle: ReactEventHandler<HTMLDetailsElement>
}

val DraggableDetails = FC<DraggableProps> { props ->
    details {
        draggable = true
        onDragStart = { }
        onToggle = props.onToggle
        open = props.isOpen
        summary { +props.name }
        div {
            +"Contents of ${props.name}"
        }
    }
}

val PluginFC = FC<PluginProps> {
    val (state, setState) = useState(PluginFCState())
    val (side) = state
    val pluginName = "Skyrim.esm"

    val moveLeft: DragEventHandler<HTMLDivElement> = { e ->
        console.log("moveLeft")
        setState(state.copy(side = Left))
    }
    val moveRight: DragEventHandler<HTMLDivElement> = { e ->
        console.log("moveRight")
        setState(state.copy(side = Right))
    }
    val toggleOpen: ReactEventHandler<HTMLDetailsElement> = { e ->
        console.log("toggleOpen", e.currentTarget.open)
        setState(state.copy(isOpen = e.currentTarget.open))
    }
    val preventDefault: (Side) -> (String) -> DragEventHandler<HTMLDivElement> = { fromSide -> { name -> { e ->
        console.log("preventDefault", fromSide.toString(), name)
        e.preventDefault()
    } } }

    div {
        className = ClassName("plugin-sandbox")
        div {
            onDragEnter = preventDefault(Left)("onDragEnter")
            onDragOver = preventDefault(Left)("onDragOver")
            onDrop = moveLeft

            if (side == Left) {
                DraggableDetails {
                    name = pluginName
                    isOpen = state.isOpen
                    onToggle = toggleOpen
                }
            }
        }
        div {
            onDragEnter = preventDefault(Right)("onDragEnter")
            onDragOver = preventDefault(Right)("onDragOver")
            onDrop = moveRight

            if (side == Right) {
                DraggableDetails {
                    name = pluginName
                    isOpen = state.isOpen
                    onToggle = toggleOpen
                }
            }
        }
    }
}
