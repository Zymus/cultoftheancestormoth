package games.studiohummingbird.cultoftheancestormoth.serialization

const val WINDOWS_1252_CHARSET_NAME = "windows-1252"

expect fun ByteArray.fromWindows1252ByteArray(): String

expect fun BethesdaBufferEncoder.encodeWindows1252String(value: String)
