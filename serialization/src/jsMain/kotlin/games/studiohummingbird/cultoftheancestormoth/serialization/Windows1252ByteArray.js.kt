package games.studiohummingbird.cultoftheancestormoth.serialization

val textDecoder = js("new TextDecoder('${WINDOWS_1252_CHARSET_NAME}')")

actual fun ByteArray.fromWindows1252ByteArray(): String =
    textDecoder.decode(this) as String

actual fun BethesdaBufferEncoder.encodeWindows1252String(
    value: String
) {
    encodeBytes(value.encodeToByteArray())
}