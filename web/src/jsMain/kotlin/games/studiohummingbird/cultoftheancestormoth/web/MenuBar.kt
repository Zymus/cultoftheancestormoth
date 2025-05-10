package games.studiohummingbird.cultoftheancestormoth.web

import react.*
import react.dom.aria.AriaRole
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.li
import react.dom.html.ReactHTML.menu
import react.dom.html.ReactHTML.nav
import web.html.ButtonType

val MenuBar = FC<Props> {
    nav {
        menu {
            listOf(
                "Directory",
                "Studio"
            ).forEach {
                li {
                    button {
                        +it
                        tabIndex = 0
                        type = ButtonType.button
                    }
                }
            }
        }
    }
}
