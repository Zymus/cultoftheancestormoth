package games.studiohummingbird.cultoftheancestormoth

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.main
import com.github.ajalt.clikt.core.subcommands
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.types.file

class Cotam : CliktCommand() {
    override fun run() {
        println("cotam")
    }
}

fun main(args: Array<String>) = Cotam().subcommands(Drudge(), Librarian(), Adept(), Example()).main(args)
