package creationkotlin.unsorted

import creationkotlin.recordtypes.GameSettings
import creationkotlin.recordtypes.Potions

data class Plugin
( val gameSettings: GameSettings = GameSettings()
, val potions: Potions = Potions() )
