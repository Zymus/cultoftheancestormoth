package games.studiohummingbird.cultoftheancestormoth.datatypes

data class RGBA8
( val red: UByte, val green: UByte, val blue: UByte, val alpha: UByte )

fun rgba
( value: Int )
: RGBA8
= TODO("0x{red}{green}{blue}{alpha}")

fun rgb0
( red: UByte, green: UByte, blue: UByte )
: RGBA8
= TODO("0x{red}{green}{blue}{00}")

fun rgbf
( red: UByte, green: UByte, blue: UByte )
: RGBA8
= TODO("0x{red}{green}{blue}{ff}")
