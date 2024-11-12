package games.studiohummingbird.cultoftheancestormoth.serialization

import java.nio.charset.Charset

/**
 * Windows-1252 Charset.
 */
val WINDOWS_1252: Charset = Charset.forName("Windows-1252")

actual fun ByteArray.fromWindows1252ByteArray(): String =
    toString(WINDOWS_1252)

actual fun String.toWindows1252ByteArray(): ByteArray =
    toByteArray(WINDOWS_1252)
