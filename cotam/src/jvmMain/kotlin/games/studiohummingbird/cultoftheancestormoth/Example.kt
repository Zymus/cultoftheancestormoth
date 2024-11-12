package games.studiohummingbird.cultoftheancestormoth

import com.github.ajalt.clikt.core.CliktCommand

class Example : CliktCommand() {
    override fun run() {
        games.studiohummingbird.cultoftheancestormoth.adapters.main("example.esp")
    }
}