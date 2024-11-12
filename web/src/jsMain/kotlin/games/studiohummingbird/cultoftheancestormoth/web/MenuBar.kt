package games.studiohummingbird.cultoftheancestormoth.web

import react.FC
import react.Props
import react.dom.html.ReactHTML
import web.cssom.ClassName

val MenuBar = FC<Props> {
    ReactHTML.div {
        className = ClassName("menubar")
        ReactHTML.ul {
            ReactHTML.li {
                value = "asdf"
                +"Asdf"
            }
        }
    }
}
