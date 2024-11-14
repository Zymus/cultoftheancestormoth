package games.studiohummingbird.cultoftheancestormoth.serialization

import games.studiohummingbird.cultoftheancestormoth.recordtypes.*

fun experimentalPlugin(): Plugin {
    val gameSettings = GameSettings(setOf(
        BooleanGameSetting("FirstBoolean", true),
        IntGameSetting("FirstInt", 26),
        FloatGameSetting("FirstFloat", 13.0f),
        StringGameSetting("FirstString", "first string")
    ))

    val effects = listOf<Effect>()
    val effect = Effect("TestEffect", EffectParams(2.6f, 99.toUInt(), 52.toUInt()))
    val potions = Potions(setOf(
        Potion("TestPotionEditorId1", weight = 1.5f, enchantedItem = EnchantedItem(1.toUInt(), 1.toUInt())),
        Potion("TestPotionEditorId2", weight = 2.5f, enchantedItem = EnchantedItem(2.toUInt(), 2.toUInt())),
        Potion("TestPotionEditorId3", weight = 3.5f, enchantedItem = EnchantedItem(3.toUInt(), 3.toUInt()), effects = effects),
    ))

    val plugin = Plugin(
        gameSettings = gameSettings,
        potions = potions
    )

    return plugin
}