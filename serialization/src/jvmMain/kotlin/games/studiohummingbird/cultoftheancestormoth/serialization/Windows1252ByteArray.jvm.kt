package games.studiohummingbird.cultoftheancestormoth.serialization

import java.nio.charset.Charset

/**
 * Windows-1252 Charset.
 */
val WINDOWS_1252: Charset = Charset.forName(WINDOWS_1252_CHARSET_NAME)

actual fun ByteArray.fromWindows1252ByteArray(): String =
    toString(WINDOWS_1252)

actual fun BethesdaBufferEncoder.encodeWindows1252String(
    value: String
) {
    encodeBytes(value.toByteArray(WINDOWS_1252))
}