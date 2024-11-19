package games.studiohummingbird.cultoftheancestormoth.serialization

import games.studiohummingbird.cultoftheancestormoth.recordtypes.*
import kotlinx.io.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encoding.AbstractEncoder
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule

@OptIn(ExperimentalSerializationApi::class)
class BethesdaBufferEncoder(private val buffer: Buffer = Buffer()) : AbstractEncoder() {

    override val serializersModule: SerializersModule = EmptySerializersModule()

    fun encodeBytes(byteArray: ByteArray) {
        buffer.write(byteArray)
    }

    override fun encodeByte(value: Byte) {
        buffer.writeByte(value)
    }

    override fun encodeInt(value: Int) {
        buffer.writeIntLe(value)
    }

    override fun encodeLong(value: Long) {
        buffer.writeLongLe(value)
    }

    override fun encodeShort(value: Short) {
        buffer.writeShortLe(value)
    }

    override fun encodeFloat(value: Float) {
        buffer.writeFloatLe(value)
    }

    override fun encodeDouble(value: Double) {
        buffer.writeDoubleLe(value)
    }

    override fun encodeString(value: String) {
        buffer.write(value.toWindows1252ByteArray())
    }
}

fun bethesdaBufferEncoder(action: BethesdaBufferEncoder.() -> Unit = {}): ByteArray =
    Buffer().apply {
        BethesdaBufferEncoder(this).apply(action)
    }
        .readByteArray()


// region natives

fun String.toZStringByteArray()
        : ByteArray = bethesdaBufferEncoder()
{
    encodeString(this@toZStringByteArray)
    encodeByte(0)
}

fun Byte.toByteArray()
        : ByteArray = bethesdaBufferEncoder()
{
    encodeByte(this@toByteArray)
}

fun Short.toByteArray()
        : ByteArray = bethesdaBufferEncoder()
{
    encodeShort(this@toByteArray)
}

fun Int.toByteArray()
        : ByteArray = bethesdaBufferEncoder()
{
    encodeInt(this@toByteArray)
}

fun Long.toByteArray()
        : ByteArray = bethesdaBufferEncoder()
{
    encodeLong(this@toByteArray)
}

fun Float.toByteArray()
        : ByteArray = bethesdaBufferEncoder()
{
    encodeFloat(this@toByteArray)
}

fun Double.toByteArray(): ByteArray =
    bethesdaBufferEncoder() {
        encodeDouble(this@toByteArray)
    }

fun UByte.toByteArray(): ByteArray =
    toByte().toByteArray()

fun UShort.toByteArray(): ByteArray =
    toShort().toByteArray()

fun UInt.toByteArray(): ByteArray =
    toInt().toByteArray()

fun ULong.toByteArray(): ByteArray =
    toLong().toByteArray()

// endregion

// region GMST
fun GameSetting.toByteArray()
        : ByteArray {
    val (namePrefix: String, valueBuffer: ByteArray) = when (this) {
        is BooleanGameSetting -> Pair("b", (if (value) 1 else 0).toByteArray())
        is FloatGameSetting -> Pair("f", value.toByteArray())
        is IntGameSetting -> Pair("i", value.toByteArray())
        is StringGameSetting -> Pair("s", value.toWindows1252ByteArray())
    }

    val editorId = field("EDID", "$namePrefix$name".toZStringByteArray())
    val value = field("DATA", valueBuffer)

    return fold(
        listOf(
            editorId,
            value
        )
    )
}

fun GameSettings.toByteArray()
        : ByteArray = group("GMST", GameSetting::toByteArray)

// endregion

fun fold(byteArrays: Iterable<ByteArray>): ByteArray =
    ByteArray(byteArrays.sumOf(ByteArray::size)).apply {
        var position = 0
        byteArrays.forEach {
            it.copyInto(this, position)
            position += it.size
        }
    }

// region ALCH

fun Potion.toByteArray()
        : ByteArray {
    val editorId = field("EDID", editorId.toZStringByteArray())
    val objectBounds = field("OBND", bethesdaBufferEncoder())
    val name = field("FULL", name.toZStringByteArray())
    val weight = field("DATA", weight.toByteArray())
    val enchantedItem = field("ENIT", bethesdaBufferEncoder())
    val effectsData = effects?.map {
        fold(
            listOf(
                field("EFID", it.effectId.toByteArray()),
                field(
                    "EFIT", fold(
                        listOf(
                            it.effectParams.magnitude.toByteArray(),
                            it.effectParams.areaOfEffect.toByteArray(),
                            it.effectParams.duration.toByteArray()
                        )
                    )
                )
            )
        )
    } ?: emptyList()

    return fold(
        listOf(
            editorId,
            objectBounds,
            name,
            weight,
            enchantedItem,
            fold(effectsData)
        )
    )
}

fun Potions.toByteArray()
        : ByteArray = group("ALCH", Potion::toByteArray)

// endregion

// region structural

fun field(name: String, fieldData: ByteArray): ByteArray =
    bethesdaBufferEncoder {
        encodeString(name)
        encodeShort(fieldData.size.toShort())
        encodeBytes(fieldData)
    }

fun record(recordTag: String, recordData: ByteArray)
        : ByteArray {
    val recordTagBuffer = recordTag.toWindows1252ByteArray()
    val size = recordData.size.toUInt().toByteArray()
    val flags = 0.toUInt().toByteArray()
    val recordFormId = 0.toUInt().toByteArray()
    val timestamp = 0.toUShort().toByteArray()
    val versionControl = 0.toUShort().toByteArray()
    val internalVersion = 0.toUShort().toByteArray()
    val unknown = 0.toUShort().toByteArray()

    return fold(
        listOf(
            recordTagBuffer,
            size,
            flags,
            recordFormId,
            timestamp,
            versionControl,
            internalVersion,
            unknown,
            recordData
        )
    )
}

fun <T> Iterable<T>.group(recordTag: String, action: (T) -> ByteArray)
        : ByteArray {
    val elementsBuffer = fold(map { record(recordTag, action(it)) })
    val groupTag = "GRUP".toWindows1252ByteArray()
    val size = elementsBuffer.size.toUInt().toByteArray()
    val name = recordTag.toWindows1252ByteArray()
    val groupType = 0.toByteArray()
    val timestamp = 0.toUShort().toByteArray()
    val versionControl = 0.toUShort().toByteArray()
    val unknown = 0.toUInt().toByteArray()

    return fold(
        listOf(
            groupTag,
            size,
            name,
            groupType,
            timestamp,
            versionControl,
            unknown,
            elementsBuffer
        )
    )
}

// endregion
