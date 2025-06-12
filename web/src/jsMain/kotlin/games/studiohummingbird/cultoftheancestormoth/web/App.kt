package games.studiohummingbird.cultoftheancestormoth.web

import kotlinx.serialization.ExperimentalSerializationApi
import react.FC
import react.Props
import react.create
import react.dom.client.createRoot
import react.dom.html.ReactHTML.a
import react.dom.html.ReactHTML.article
import react.dom.html.ReactHTML.h1
import react.dom.html.ReactHTML.p
import web.dom.document
import kotlin.io.encoding.ExperimentalEncodingApi

@ExperimentalSerializationApi
fun main() {
    val banner = document.getElementById("banner") ?: error("couldn't finder banner")
    createRoot(banner).render(MenuBar.create())

    val root = document.getElementById("root") ?: error("couldn't find root element")
    createRoot(root).render(App.create())
}

@ExperimentalSerializationApi
@OptIn(ExperimentalEncodingApi::class, ExperimentalStdlibApi::class)
val App = FC<Props> {
    article {
        h1 {
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
            +"""
                This is highly experimental at the moment. The mods created here should be tested and verified before use.
                Not that there's anything freaky, just a lot of bare wires, so be careful.
            """.trimIndent()
        }
//    div {
//        h2 { +"The example mod contains the following records" }
//        ul {
//            li {
//                h3 { +"GameSettings (GMST)" }
//                ul {
//                    plugin.gameSettings.forEach {
//                        li {
//                            +it.name
//                        }
//                    }
//                }
//            }
//            li {
//                h3 { +"Potions (ALCH)" }
//                ul {
//                    plugin.potions.forEach {
//                        li {
//                            +it.editorId
//                            h4 { +"Effects" }
//                            ul {
//                                it.effects?.forEach { effect ->
//                                    li {
//                                        +effect.effectId.toHexString()
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//    a {
//        +"Download example mod"
//        download = "example.esp"
//        href = "data:application/octet-stream;base64,${pluginDataBase64}"
//    }
//        FieldViewer()
//        Viewer()
        PluginViewer { }
    }
}
