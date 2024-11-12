package creationkotlin.adapters

import games.studiohummingbird.cultoftheancestormoth.serialization.fromWindows1252ByteArray
import java.nio.ByteBuffer
import java.nio.charset.Charset

/**
 * Relative get to read a [WINDOWS_1252] encoded String of the specified [length]
 * from this [ByteBuffer].
 */
fun ByteBuffer.windows1252
( length: Int )
: String
= ByteArray(length).apply(this::get).fromWindows1252ByteArray()

