package games.studiohummingbird.cultoftheancestormoth.web

import games.studiohummingbird.cultoftheancestormoth.serialization.experimentalPlugin
import games.studiohummingbird.cultoftheancestormoth.serialization.toByteArray
import react.FC
import react.Props
import react.create
import react.dom.client.createRoot
import react.dom.html.ReactHTML.a
import react.dom.html.ReactHTML.p
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
    console.log(pluginDataBase64)

    p {
        +"This is an example Skyrim plugin, created by Zymus, generated using Kotlin Multiplatform."
    }
    p {
        +"""
            It uses a common model project, js browser react project (this page), multiplatform serialization (soon, adapting the jvm bytebuffer code didn't go easily and it looks jank), and a JVM CLI using Clikt.
            For more information, visit
        """.trimIndent()
        a {
            +"the Github repository"
            href = "https://www.github.com/Zymus/cultoftheancestormoth"
        }
    }
    p {
        +"This is highly experimental at the moment. The mods created here should be tested and verified before use."
        +"Not that there's anything freaky, just a lot of bare wires, so be careful."
    }
    a {
        +"Download example mod"
        download = "example.esp"
        href = "data:application/octet-stream;base64,${pluginDataBase64}"
    }
}
