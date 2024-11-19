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
        encodeWindows1252String(value)
    }
}

fun bethesdaBufferEncoder(action: BethesdaBufferEncoder.() -> Unit = {}): ByteArray =
    Buffer()
        .apply { BethesdaBufferEncoder(this).apply(action) }
        .readByteArray()


// region natives

fun BethesdaBufferEncoder.encodeZString(value: String) {
    encodeString(value)
    encodeByte(0)
}

// endregion

// region GMST

typealias EncoderAction = BethesdaBufferEncoder.() -> Unit

fun encoderAction(action: EncoderAction): EncoderAction = action

fun BethesdaBufferEncoder.encodeGameSetting(gameSetting: GameSetting) {
    val (namePrefix: String, encodeSetting: EncoderAction) = when (gameSetting) {
        is BooleanGameSetting -> Pair("b", encoderAction { encodeInt(if (gameSetting.value) 1 else 0) })
        is FloatGameSetting -> Pair("f", encoderAction { encodeFloat(gameSetting.value) })
        is IntGameSetting -> Pair("i", encoderAction { encodeInt(gameSetting.value) })
        is StringGameSetting -> Pair("s", encoderAction { encodeWindows1252String(gameSetting.value) })
    }

    encodeField("EDID") { encodeZString("$namePrefix${gameSetting.name}") }
    encodeField("DATA") { encodeSetting() }
}

// endregion

// region ALCH

fun BethesdaBufferEncoder.encodePotion(potion: Potion) {
    encodeField("EDID") { encodeZString(potion.editorId) }
    encodeField("OBND") { repeat(6) { encodeShort(0) } }
    encodeField("FULL") { encodeZString(potion.name) }
    encodeField("DATA") { encodeFloat(potion.weight) }
    encodeField("ENIT") {
        val potionValue = 0
        val flags = 0
        val addiction = 0
        val addictionChance = 0
        val useSoundFormId = 0

        encodeInt(potionValue)
        encodeInt(flags)
        encodeInt(addiction)
        encodeInt(addictionChance)
        encodeInt(useSoundFormId)
    }

    val effectsData = potion.effects?.map {
        bethesdaBufferEncoder {
            encodeField("EFID") { encodeInt(it.effectId.toInt()) }
            encodeField("EFIT") {
                encodeFloat(it.effectParams.magnitude)
                encodeInt(it.effectParams.areaOfEffect.toInt())
                encodeInt(it.effectParams.duration.toInt())
            }
        }
    } ?: emptyList()

    effectsData.forEach(::encodeBytes)
}

// endregion

// region structural

fun BethesdaBufferEncoder.encodeField(name: String, fieldBufferAction: BethesdaBufferEncoder.() -> Unit) {
    encodeString(name)
    val fieldData = bethesdaBufferEncoder { fieldBufferAction() }
    encodeShort(fieldData.size.toShort())
    encodeBytes(fieldData)
}

fun BethesdaBufferEncoder.encodeRecord(recordTag: String, encodeRecordData: BethesdaBufferEncoder.() -> Unit) {
    val flags = 0
    val formId = 0
    val timeStamp = 0
    val versionControl = 0
    val internalVersion = 0
    val unknown = 0

    val recordData = bethesdaBufferEncoder(encodeRecordData)

    encodeString(recordTag)
    encodeInt(recordData.size)
    encodeInt(flags)
    encodeInt(formId)
    encodeShort(timeStamp.toShort())
    encodeShort(versionControl.toShort())
    encodeShort(internalVersion.toShort())
    encodeShort(unknown.toShort())
    encodeBytes(recordData)
}

fun <T> BethesdaBufferEncoder.encodeGroup(recordTag: String, records: Iterable<T>, encodeRecordData: BethesdaBufferEncoder.(T) -> Unit) {
    val elementsBuffer = bethesdaBufferEncoder {
        records.forEach { encodeRecord(recordTag) { encodeRecordData(it) } }
    }

    val groupType = 0
    val timestamp = 0
    val versionControl = 0
    val unknown = 0

    encodeString("GRUP")
    encodeInt(elementsBuffer.size)
    encodeString(recordTag)
    encodeInt(groupType)
    encodeShort(timestamp.toShort())
    encodeShort(versionControl.toShort())
    encodeInt(unknown)
    encodeBytes(elementsBuffer)
}

// endregion

// region Plugin

fun BethesdaBufferEncoder.encodePlugin(plugin: Plugin) {
    val recordCount = plugin.potions.count() + 1 + plugin.gameSettings.count() + 1
    val tes4RecordData = bethesdaBufferEncoder {
        encodeField("HEDR") {
            val version = 1.7f
            val nextAvailableObjectId = 33000

            encodeFloat(version)
            encodeInt(recordCount)
            encodeInt(nextAvailableObjectId)
        }
        encodeField("CNAM") { encodeZString("Zymus") }
        encodeField("SNAM") { encodeZString("Cult of the Ancestor Moth Example") }
        encodeBytes(bethesdaBufferEncoder {
            encodeField("MAST") { encodeZString("Skyrim.esm") }
            encodeField("DATA") { encodeLong(0) }
        })
        // onam, skipped for now
        encodeField("INTV") { encodeInt(0) }
    }

    // region TES4 record
    encodeString("TES4")
    encodeInt(tes4RecordData.size)
    encodeInt(0)// flags
    encodeInt(0)// record (form) identifier
    encodeShort(0)// timestamps
    encodeShort(0)// version control
    encodeShort(0)// internal version
    encodeShort(0)// unknown
    encodeBytes(tes4RecordData)
    // INCC, skipped for now
    // endregion
    encodeGroup("GMST", plugin.gameSettings, BethesdaBufferEncoder::encodeGameSetting)
    encodeGroup("ALCH", plugin.potions, BethesdaBufferEncoder::encodePotion)
}

// endregion
