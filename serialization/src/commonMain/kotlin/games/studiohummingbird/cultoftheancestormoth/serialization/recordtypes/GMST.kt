package games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes

import kotlinx.serialization.Serializable

@Serializable
sealed class GMST(open val name: String)

data class BooleanGameSetting
(override val name: String, val value: Boolean )
: GMST(name)

data class IntGameSetting
( override val name: String, val value: Int )
: GMST(name)

data class FloatGameSetting
( override val name: String, val value: Float )
: GMST(name)

data class StringGameSetting
( override val name: String, val value: String )
: GMST(name)
