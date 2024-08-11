package creationkotlin.unsorted

import creationkotlin.recordtypes.GameSettings

data class RecordContainer
( val gameSettings: GameSettings = GameSettings() )
{
    operator fun invoke
    ( block: RecordContainer.() -> Unit )
    = apply(block)
}

