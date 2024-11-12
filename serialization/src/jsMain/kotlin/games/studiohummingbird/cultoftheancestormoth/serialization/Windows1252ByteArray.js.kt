package games.studiohummingbird.cultoftheancestormoth.serialization

val textDecoder = js("new TextDecoder('Windows-1252')")

actual fun ByteArray.fromWindows1252ByteArray(): String =
    textDecoder.decode(this) as String

actual fun String.toWindows1252ByteArray(): ByteArray =
    this.encodeToByteArray()
