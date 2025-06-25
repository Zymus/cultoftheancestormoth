package games.studiohummingbird.cultoftheancestormoth.serialization

import kotlinx.serialization.ExperimentalSerializationApi
import java.nio.charset.Charset

/**
 * Windows-1252 Charset.
 */
val WINDOWS_1252: Charset = Charset.forName(WINDOWS_1252_CHARSET_NAME)

actual fun ByteArray.decodeWindows1252String(): String =
    toString(WINDOWS_1252)

actual fun String.toWindows1252ByteArray(): ByteArray =
    toByteArray(WINDOWS_1252)
