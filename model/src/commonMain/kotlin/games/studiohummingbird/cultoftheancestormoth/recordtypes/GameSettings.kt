package games.studiohummingbird.cultoftheancestormoth.recordtypes

data class GameSettings
( private val gameSettings: Set<GameSetting> = emptySet() )
: Set<GameSetting> by gameSettings
