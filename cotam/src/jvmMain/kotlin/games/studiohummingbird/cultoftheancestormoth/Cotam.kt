package games.studiohummingbird.cultoftheancestormoth

import com.github.ajalt.clikt.core.CliktCommand
import com.github.ajalt.clikt.core.main
import com.github.ajalt.clikt.core.subcommands
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.ExperimentalSerializationApi

class Cotam : CliktCommand() {
    override fun run() {
        println("cotam")
    }
}

@OptIn(ExperimentalSerializationApi::class)
fun main(args: Array<String>) = runBlocking {
    mainLoad(args)
}

fun mainCotam(args: Array<String>) =
    Cotam().subcommands(
        Drudge(),
        Librarian(),
        Adept(),
        Example(),
        Load()).main(args)

fun mainLoad(args: Array<String>) =
    Load().main(args)

