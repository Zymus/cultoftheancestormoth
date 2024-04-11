package creationkotlin.adapters

import creationkotlin.recordtypes.*
import creationkotlin.unsorted.Plugin
import java.nio.channels.FileChannel
import java.nio.file.Paths
import java.nio.file.StandardOpenOption.CREATE
import java.nio.file.StandardOpenOption.TRUNCATE_EXISTING
import java.nio.file.StandardOpenOption.WRITE
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime


@OptIn(ExperimentalTime::class)
fun main() {
    val gameSettings = GameSettings(setOf(
        BooleanGameSetting("FirstBoolean", true),
        IntGameSetting("FirstInt", 26),
        FloatGameSetting("FirstFloat", 13.0f),
        StringGameSetting("FirstString", "first string")
    ))

    val potions = Potions(setOf(
        Potion("TestPotionEditorId1", weight = 1.5f, enchantedItem = EnchantedItem(1.toUInt(), 1.toUInt())),
        Potion("TestPotionEditorId2", weight = 2.5f, enchantedItem = EnchantedItem(2.toUInt(), 2.toUInt())),
        Potion("TestPotionEditorId3", weight = 3.5f, enchantedItem = EnchantedItem(3.toUInt(), 3.toUInt())),
    ))

    val plugin = Plugin(
        gameSettings = gameSettings,
        potions = potions
    )

    val channel = FileChannel.open(Paths.get("E:", "SteamLibrary", "steamapps", "common", "Skyrim Special Edition", "Data", "gmst_test.esp"), CREATE, WRITE, TRUNCATE_EXISTING)

    measureTime {
        val buffer = plugin.toByteBuffer()
        channel.write(buffer)
    }.also { println(it) }

    channel.close()
}