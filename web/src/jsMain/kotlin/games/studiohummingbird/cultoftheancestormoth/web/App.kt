package games.studiohummingbird.cultoftheancestormoth.web

import react.FC
import react.Props
import react.create
import react.dom.client.createRoot
import web.dom.document

fun main() {
    val root = document.getElementById("root") ?: error("couldn't find root element")
    createRoot(root).render(App.create())
}

private val App = FC<Props> {
    +"Hello, world!"
}
