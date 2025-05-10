@file:Plugin(
    Author("Zymus"),
    Description("Recipe for crafting Skooma")
)

val alchemyLab = "WICraftingAlchemy"
val moonSugar = "MoonSugar"
val nightshade = "Nightshade"
val skooma = "Skooma"

// hmm, alchemy is closer to enchanting than smithing. Will that be an issue?
val skoomaRecipe = recipe {
    bench { alchemyLab }
    inputs {
        +moonSugar
        +nightshade
    }
    output {
        skooma
    }
}
