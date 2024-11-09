package games.studiohummingbird.cultoftheancestormoth

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.main
import com.github.ajalt.clikt.parameters.arguments.argument
import com.github.ajalt.clikt.parameters.types.file

class Cotam : CliktCommand() {
    val pluginPath by argument().file(mustExist = true)

    override fun run() {
        println("records on ${pluginPath}")
    }
}

fun main(args: Array<String>) = Cotam().main(args)
