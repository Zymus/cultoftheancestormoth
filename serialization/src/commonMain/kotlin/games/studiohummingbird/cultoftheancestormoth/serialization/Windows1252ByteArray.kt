package games.studiohummingbird.cultoftheancestormoth.serialization

expect fun ByteArray.fromWindows1252ByteArray(): String

expect fun String.toWindows1252ByteArray(): ByteArray
