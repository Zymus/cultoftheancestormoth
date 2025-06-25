package games.studiohummingbird.cultoftheancestormoth.serialization

import js.typedarrays.toUint8Array

val textDecoder = js("new TextDecoder('${WINDOWS_1252_CHARSET_NAME}')")

actual fun ByteArray.decodeWindows1252String(): String =
    textDecoder.decode(toUint8Array().buffer) as String

actual fun String.toWindows1252ByteArray(): ByteArray {
    return encodeToByteArray(throwOnInvalidSequence = true)
}
