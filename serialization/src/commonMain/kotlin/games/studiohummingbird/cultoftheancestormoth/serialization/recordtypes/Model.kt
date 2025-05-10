package games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes

class Model

fun modelPath(vararg parts: String): String =
    "${parts.joinToString("\\")}.nif"
