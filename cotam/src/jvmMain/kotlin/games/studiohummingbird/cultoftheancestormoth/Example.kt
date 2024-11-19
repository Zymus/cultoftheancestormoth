package games.studiohummingbird.cultoftheancestormoth

import com.github.ajalt.clikt.core.CliktCommand
import games.studiohummingbird.cultoftheancestormoth.serialization.bethesdaBufferEncoder
import games.studiohummingbird.cultoftheancestormoth.serialization.encodePlugin
import games.studiohummingbird.cultoftheancestormoth.serialization.experimentalPlugin
import java.nio.ByteBuffer
import java.nio.channels.FileChannel
import java.nio.file.Paths
import java.nio.file.StandardOpenOption.*
import kotlin.time.measureTime

class Example : CliktCommand() {
    override fun run() {
        val plugin = experimentalPlugin()

        val channel = FileChannel.open(Paths.get("example.esp"), CREATE, WRITE, TRUNCATE_EXISTING)

        measureTime {
            val buffer = ByteBuffer.wrap(bethesdaBufferEncoder { encodePlugin(plugin) })
            channel.write(buffer)
        }.also { println(it) }

        channel.close()
    }
}