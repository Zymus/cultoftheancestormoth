package games.studiohummingbird.cultoftheancestormoth.serialization

import games.studiohummingbird.cultoftheancestormoth.recordtypes.GameSettings
import games.studiohummingbird.cultoftheancestormoth.recordtypes.Potions

data class Plugin
( val gameSettings: GameSettings = GameSettings()
, val potions: Potions = Potions() )

expect fun Plugin.toByteArray(): ByteArray
