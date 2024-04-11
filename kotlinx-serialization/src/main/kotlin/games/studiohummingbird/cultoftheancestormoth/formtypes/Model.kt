package creationkotlin.formtypes

class Model

fun modelPath(vararg parts: String): String =
    "${parts.joinToString("\\")}.nif"
