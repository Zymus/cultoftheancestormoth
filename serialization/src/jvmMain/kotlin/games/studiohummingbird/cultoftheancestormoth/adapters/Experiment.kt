package games.studiohummingbird.cultoftheancestormoth.adapters

import games.studiohummingbird.cultoftheancestormoth.serialization.experimentalPlugin
import games.studiohummingbird.cultoftheancestormoth.serialization.toByteArray
import java.nio.ByteBuffer
import java.nio.channels.FileChannel
import java.nio.file.Paths
import java.nio.file.StandardOpenOption.CREATE
import java.nio.file.StandardOpenOption.TRUNCATE_EXISTING
import java.nio.file.StandardOpenOption.WRITE
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime


@OptIn(ExperimentalTime::class)
fun main(vararg args: String) {
    val plugin = experimentalPlugin()

    val channel = FileChannel.open(Paths.get(args[0]), CREATE, WRITE, TRUNCATE_EXISTING)

    measureTime {
        val buffer = ByteBuffer.wrap(plugin.toByteArray())
        channel.write(buffer)
    }.also { println(it) }

    channel.close()
}