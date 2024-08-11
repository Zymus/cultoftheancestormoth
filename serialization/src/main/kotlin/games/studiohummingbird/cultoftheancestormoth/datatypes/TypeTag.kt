package creationkotlin.datatypes

import creationkotlin.adapters.WINDOWS_1252

data class TypeTag
( val string: String )
{
    val size: Int
        get() = _byteArray.size

    val byteArray: ByteArray
        get() = _byteArray.copyOf()

    private val _byteArray: ByteArray = string.toByteArray(WINDOWS_1252)

    init { require(string.length == 4) }
}
