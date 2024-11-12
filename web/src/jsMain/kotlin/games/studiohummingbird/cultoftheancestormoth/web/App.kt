package games.studiohummingbird.cultoftheancestormoth.web

import games.studiohummingbird.cultoftheancestormoth.serialization.experimentalPlugin
import games.studiohummingbird.cultoftheancestormoth.serialization.toByteArray
import react.FC
import react.Props
import react.create
import react.dom.client.createRoot
import react.dom.html.ReactHTML.a
import web.dom.document
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

fun main() {
    val root = document.getElementById("root") ?: error("couldn't find root element")
    createRoot(root).render(App.create())
}

@OptIn(ExperimentalEncodingApi::class)
val App = FC<Props> {
    val plugin = experimentalPlugin()
    val pluginData = plugin.toByteArray()
    val pluginDataBase64 = Base64.encode(pluginData)
    console.log(pluginData)
    +"Hello, worldaaaaa!"
    MenuBar()
    a {
        +"Download"
        download = "example.esp"
        href = "data:application/octet-stream;base64,${pluginDataBase64}"
    }
}
