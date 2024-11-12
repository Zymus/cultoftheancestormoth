package games.studiohummingbird.cultoftheancestormoth.adapters

import games.studiohummingbird.cultoftheancestormoth.adapters.MonoidByteBuffer.fold
import creationkotlin.adapters.littleEndianByteBuffer
import games.studiohummingbird.cultoftheancestormoth.recordtypes.*
import games.studiohummingbird.cultoftheancestormoth.serialization.WINDOWS_1252
import java.nio.ByteBuffer

// region natives

fun String.toWindows1252ByteBuffer
( )
: ByteBuffer
= littleEndianByteBuffer(length)
{
    put(toByteArray(WINDOWS_1252))
    flip()
}

fun String.toZStringByteBuffer
( )
: ByteBuffer
= littleEndianByteBuffer(length + 1)
{
    put(toWindows1252ByteBuffer())
    put(0)
    flip()
}

fun Byte.toByteBuffer
( )
: ByteBuffer
= littleEndianByteBuffer(1)
{
    put(this@toByteBuffer)
    flip()
}

fun Short.toByteBuffer
( )
: ByteBuffer
= littleEndianByteBuffer(2)
{
    putShort(this@toByteBuffer)
    flip()
}

fun Int.toByteBuffer
( )
: ByteBuffer
= littleEndianByteBuffer(4)
{
    putInt(this@toByteBuffer)
    flip()
}

fun Long.toByteBuffer
( )
: ByteBuffer
= littleEndianByteBuffer(8)
{
    putLong(this@toByteBuffer)
    flip()
}

fun Float.toByteBuffer
( )
: ByteBuffer
= littleEndianByteBuffer(4)
{
    putFloat(this@toByteBuffer)
    flip()
}

fun Double.toByteBuffer
( )
: ByteBuffer
= littleEndianByteBuffer(8)
{
    putDouble(this@toByteBuffer)
    flip()
}

fun UByte.toByteBuffer
( )
: ByteBuffer
= toByte().toByteBuffer()

fun UShort.toByteBuffer
( )
: ByteBuffer
= toShort().toByteBuffer()

fun UInt.toByteBuffer
( )
: ByteBuffer
= toInt().toByteBuffer()

fun ULong.toByteBuffer
( )
: ByteBuffer
= toLong().toByteBuffer()

// endregion

// region GMST
fun GameSetting.toByteBuffer
( )
: ByteBuffer
{
    val (namePrefix: String, valueBuffer: ByteBuffer) = when (this) {
        is BooleanGameSetting -> Pair("b", (if (value) 1 else 0).toByteBuffer())
        is FloatGameSetting -> Pair("f", value.toByteBuffer())
        is IntGameSetting -> Pair("i", value.toByteBuffer())
        is StringGameSetting -> Pair("s", value.toWindows1252ByteBuffer())
    }

    val editorId = field("EDID", "$namePrefix$name".toZStringByteBuffer())
    val value = field("DATA", valueBuffer)

    return fold(listOf(
        editorId,
        value
    ))
}

fun GameSettings.toByteBuffer
( )
: ByteBuffer
= group("GMST", GameSetting::toByteBuffer)

// endregion

// region ALCH

fun Potion.toByteBuffer
( )
: ByteBuffer
{
    val editorId = field("EDID", editorId.toZStringByteBuffer())
    val objectBounds = field("OBND", littleEndianByteBuffer(12))
    val name = field("FULL", name.toZStringByteBuffer())
    val weight = field("DATA", weight.toByteBuffer())
    val enchantedItem = field("ENIT", littleEndianByteBuffer(20))

    return fold(listOf(
        editorId,
        objectBounds,
        name,
        weight,
        enchantedItem
    ))
}

fun Potions.toByteBuffer
( )
: ByteBuffer
= group("ALCH", Potion::toByteBuffer)

// endregion

// region structural

fun field
( name: String, fieldData: ByteBuffer )
: ByteBuffer
= fold(listOf(
    name.toWindows1252ByteBuffer(),
    fieldData.limit().toUShort().toByteBuffer(),
    fieldData
))

fun record
( recordTag: String, recordData: ByteBuffer )
: ByteBuffer
{
    val recordTagBuffer = recordTag.toWindows1252ByteBuffer()
    val size = recordData.limit().toUInt().toByteBuffer()
    val flags = 0.toUInt().toByteBuffer()
    val recordFormId = 0.toUInt().toByteBuffer()
    val timestamp = 0.toUShort().toByteBuffer()
    val versionControl = 0.toUShort().toByteBuffer()
    val internalVersion = 0.toUShort().toByteBuffer()
    val unknown = 0.toUShort().toByteBuffer()

    return fold(listOf(
        recordTagBuffer,
        size,
        flags,
        recordFormId,
        timestamp,
        versionControl,
        internalVersion,
        unknown,
        recordData
    ))
}

fun <T> Iterable<T>.group
( recordTag: String, action: (T) -> ByteBuffer )
: ByteBuffer
{
    val elementsBuffer = fold(map { record(recordTag, action(it)) })
    val groupTag ="GRUP".toWindows1252ByteBuffer()
    val size = elementsBuffer.limit().toUInt().toByteBuffer()
    val name = recordTag.toWindows1252ByteBuffer()
    val groupType = 0.toByteBuffer()
    val timestamp = 0.toUShort().toByteBuffer()
    val versionControl = 0.toUShort().toByteBuffer()
    val unknown = 0.toUInt().toByteBuffer()

    return fold(listOf(
        groupTag,
        size,
        name,
        groupType,
        timestamp,
        versionControl,
        unknown,
        elementsBuffer
    ))
}

// endregion
