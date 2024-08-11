package creationkotlin.recordtypes

sealed interface GameSetting
: RecordType
{ val name: String }

data class BooleanGameSetting
( override val name: String, val value: Boolean )
: GameSetting

data class IntGameSetting
( override val name: String, val value: Int )
: GameSetting

data class FloatGameSetting
( override val name: String, val value: Float )
: GameSetting

data class StringGameSetting
( override val name: String, val value: String )
: GameSetting
