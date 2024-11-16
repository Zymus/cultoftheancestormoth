package games.studiohummingbird.cultoftheancestormoth.serialization

import games.studiohummingbird.cultoftheancestormoth.recordtypes.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.encoding.AbstractEncoder
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule

@OptIn(ExperimentalSerializationApi::class)
data class BethesdaByteStream(val size: Int) : AbstractEncoder() {
    val byteArray = ByteArray(size)
    private var position = 0

    fun put(array: ByteArray): BethesdaByteStream = apply {
        array.copyInto(byteArray, position)
        position += array.size
    }

    fun put(byte: Byte) = apply {
        byteArray[position++] = byte
    }

    fun put(int: Int) = apply {
        put((int and 0xff).toByte())
    }

    fun putShort(short: Short) = apply {
        val intValue = short.toInt()
        put(intValue)
        put(intValue shr 8)
    }

    fun putInt(int: Int) = apply {
        put(int)
        put(int shr 8)
        put(int shr 16)
        put(int shr 24)
    }

    fun putLong(long: Long) = apply {
        val highInt = (long shr 32).toInt()
        val lowInt = (long and 0xffffffff).toInt()
        put(lowInt)
        put(highInt)
    }

    fun putFloat(float: Float) = apply {
        putInt(float.toRawBits())
    }

    fun putDouble(double: Double) = apply {
        putLong(double.toRawBits())
    }

    override val serializersModule: SerializersModule = EmptySerializersModule()

    override fun encodeByte(value: Byte) {
        put(value)
    }

    override fun encodeInt(value: Int) {
        putInt(value)
    }

    override fun encodeLong(value: Long) {
        putLong(value)
    }

    override fun encodeShort(value: Short) {
        putShort(value)
    }

    override fun encodeFloat(value: Float) {
        putFloat(value)
    }

    override fun encodeDouble(value: Double) {
        putDouble(value)
    }

    override fun encodeString(value: String) {
        put(value.toWindows1252ByteArray())
    }
}

fun littleEndianByteArray(size: Int, action: BethesdaByteStream.() -> Unit = {}): ByteArray =
    BethesdaByteStream(size).apply(action).byteArray


// region natives

fun String.toZStringByteArray()
        : ByteArray = littleEndianByteArray(length + 1)
{
    put(toWindows1252ByteArray())
    put(0)
}

fun Byte.toByteArray()
        : ByteArray = littleEndianByteArray(1)
{
    put(this@toByteArray)
}

fun Short.toByteArray()
        : ByteArray = littleEndianByteArray(2)
{
    putShort(this@toByteArray)
}

fun Int.toByteArray()
        : ByteArray = littleEndianByteArray(4)
{
    putInt(this@toByteArray)
}

fun Long.toByteArray()
        : ByteArray = littleEndianByteArray(8)
{
    putLong(this@toByteArray)
}

fun Float.toByteArray()
        : ByteArray = littleEndianByteArray(4)
{
    putFloat(this@toByteArray)
}

fun Double.toByteArray()
        : ByteArray = littleEndianByteArray(8)
{
    putDouble(this@toByteArray)
}

fun UByte.toByteArray()
        : ByteArray = toByte().toByteArray()

fun UShort.toByteArray()
        : ByteArray = toShort().toByteArray()

fun UInt.toByteArray()
        : ByteArray = toInt().toByteArray()

fun ULong.toByteArray()
        : ByteArray = toLong().toByteArray()

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
    val objectBounds = field("OBND", littleEndianByteArray(12))
    val name = field("FULL", name.toZStringByteArray())
    val weight = field("DATA", weight.toByteArray())
    val enchantedItem = field("ENIT", littleEndianByteArray(20))
    val effectsData = effects?.map {
        fold(listOf(
            field("EFID", it.effectId.toByteArray()),
            field("EFIT", fold(listOf(
                it.effectParams.magnitude.toByteArray(),
                it.effectParams.areaOfEffect.toByteArray(),
                it.effectParams.duration.toByteArray()
            )))
        ))
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

fun field(name: String, fieldData: ByteArray)
        : ByteArray = fold(
    listOf(
        name.toWindows1252ByteArray(),
        fieldData.size.toUShort().toByteArray(),
        fieldData
    )
)

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
