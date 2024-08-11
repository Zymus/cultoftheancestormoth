package creationkotlin.adapters

import java.nio.ByteBuffer
import java.nio.charset.Charset

/**
 * Windows-1252 Charset.
 */
val WINDOWS_1252
: Charset
= Charset.forName("Windows-1252")

/**
 * Converts a [ByteArray] to a [String] using the [WINDOWS_1252] Charset.
 */
fun ByteArray.windows1252
( )
: String
= toString(WINDOWS_1252)

/**
 * Relative get to read a [WINDOWS_1252] encoded String of the specified [length]
 * from this [ByteBuffer].
 */
fun ByteBuffer.windows1252
( length: Int )
: String
= ByteArray(length).apply(this::get).windows1252()
