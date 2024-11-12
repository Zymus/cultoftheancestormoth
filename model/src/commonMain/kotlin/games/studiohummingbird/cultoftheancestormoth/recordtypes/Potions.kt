package games.studiohummingbird.cultoftheancestormoth.recordtypes

data class Potions
( private val potions: Set<Potion> = emptySet() )
: Set<Potion> by potions
