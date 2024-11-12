package games.studiohummingbird.cultoftheancestormoth.unsorted

import games.studiohummingbird.cultoftheancestormoth.recordtypes.GameSettings

data class RecordContainer
( val gameSettings: GameSettings = GameSettings() )
{
    operator fun invoke
    ( block: RecordContainer.() -> Unit )
    = apply(block)
}
