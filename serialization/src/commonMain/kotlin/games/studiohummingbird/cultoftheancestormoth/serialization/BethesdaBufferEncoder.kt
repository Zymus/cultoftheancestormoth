package games.studiohummingbird.cultoftheancestormoth.serialization

import games.studiohummingbird.cultoftheancestormoth.serialization.annotations.FieldAnnotation
import games.studiohummingbird.cultoftheancestormoth.serialization.annotations.NullTerminatedString
import games.studiohummingbird.cultoftheancestormoth.serialization.annotations.RecordAnnotation
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.InlineNullTerminatedString
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.nullTerminatedStringEncoder
import games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes.ALCH
import games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes.BooleanGameSetting
import games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes.FloatGameSetting
import games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes.GMST
import games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes.GRUP
import games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes.IntGameSetting
import games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes.StringGameSetting
import games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes.TES4
import kotlinx.io.Buffer
import kotlinx.io.Sink
import kotlinx.io.Source
import kotlinx.io.readByteArray
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.AbstractEncoder
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule

@OptIn(ExperimentalSerializationApi::class)
class BethesdaBufferEncoder(private val sink: Sink = Buffer()) : AbstractEncoder() {

    override val serializersModule: SerializersModule = EmptySerializersModule()

    private val primitiveBufferEncoder by lazy { PrimitiveBufferEncoder(sink) }

    override fun encodeInline(descriptor: SerialDescriptor): Encoder {
        println("encodeInline kind=${descriptor.kind} ${descriptor.serialName}")

        if (descriptor == InlineNullTerminatedString.serializer().descriptor) {
            return nullTerminatedStringEncoder(sink)
        }

        return this
    }

    override fun encodeByte(value: Byte) {
        println("encodeByte $value")
        primitiveBufferEncoder.encodeByte(value)
    }

    fun encodeBytes(byteArray: ByteArray) {
        println("encodeBytes size=${byteArray.size}")
        sink.write(byteArray)
    }

    override fun encodeShort(value: Short) {
        println("encodeShort $value")
        primitiveBufferEncoder.encodeShort(value)
    }

    override fun encodeInt(value: Int) {
        println("encodeInt $value")
        primitiveBufferEncoder.encodeInt(value)
    }

    override fun encodeLong(value: Long) {
        println("encodeLong $value")
        primitiveBufferEncoder.encodeLong(value)
    }

    override fun encodeFloat(value: Float) {
        println("encodeFloat $value")
        primitiveBufferEncoder.encodeFloat(value)
    }

    override fun encodeDouble(value: Double) {
        println("encodeDouble $value")
        primitiveBufferEncoder.encodeDouble(value)
    }

    override fun encodeString(value: String) {
        println("encodeString $value")
        sink.encodeWindows1252().encodeString(value)
    }

    override fun beginCollection(descriptor: SerialDescriptor, collectionSize: Int): CompositeEncoder {
        println("beginCollection ${descriptor.serialName} $collectionSize")
        return beginStructure(descriptor)
    }

    override fun beginStructure(descriptor: SerialDescriptor): CompositeEncoder {
        println("beginStructure ${descriptor.serialName}")
        println("- kind=${descriptor.kind}")
        println("- annotations=${descriptor.annotations}")
        println("- elementsCount=${descriptor.elementsCount}")
        println(". isRecord=${descriptor.isRecord()}")

        if (descriptor == Field.serializer().descriptor) {
            return fieldEncoder(sink)
        }

        return this
    }

    override fun endStructure(descriptor: SerialDescriptor) {
        println("endStructure kind=${descriptor.kind} ${descriptor.serialName}")

    }
}

fun Source.readUntil(value: Byte): Buffer =
    Buffer().also {
        var currentByte = readByte()
        while (currentByte != value) {
            it.writeByte(currentByte)
            currentByte = readByte()
        }
    }

@ExperimentalSerializationApi
fun SerialDescriptor.isNullTerminatedString(index: Int): Boolean =
    getElementAnnotations(index).any { it is NullTerminatedString }

@ExperimentalSerializationApi
fun SerialDescriptor.isField(index: Int): Boolean =
    getElementAnnotations(index).any { it is FieldAnnotation }

@ExperimentalSerializationApi
fun SerialDescriptor.getFieldAnnotation(index: Int): FieldAnnotation =
    getElementAnnotations(index).single { it is FieldAnnotation } as FieldAnnotation

@ExperimentalSerializationApi
fun SerialDescriptor.isRecord(): Boolean = annotations.any { it is RecordAnnotation }

@ExperimentalSerializationApi
fun SerialDescriptor.getRecordAnnotation(): RecordAnnotation = annotations.single { it is RecordAnnotation } as RecordAnnotation

fun bethesdaBufferEncoder(action: BethesdaBufferEncoder.() -> Unit): ByteArray =
    Buffer()
        .apply { BethesdaBufferEncoder(this).apply(action) }
        .readByteArray()

@OptIn(ExperimentalStdlibApi::class, ExperimentalSerializationApi::class)
fun bethesdaBufferDecoder(action: BethesdaBufferDecoder.() -> Unit): ByteArray =
    Buffer()
        .apply { BethesdaBufferDecoder(this).apply(action) }
        .readByteArray()

// region GMST

typealias EncoderAction = BethesdaBufferEncoder.() -> Unit

fun encoderAction(action: EncoderAction): EncoderAction = action

@OptIn(ExperimentalSerializationApi::class)
fun BethesdaBufferEncoder.encodeGameSetting(gameSetting: GMST) {
    val (namePrefix: String, encodeSetting: EncoderAction) = when (gameSetting) {
        is BooleanGameSetting -> "b" to encoderAction { encodeInt(if (gameSetting.value) 1 else 0) }
        is FloatGameSetting -> "f" to encoderAction { encodeFloat(gameSetting.value) }
        is IntGameSetting -> "i" to encoderAction { encodeInt(gameSetting.value) }
        is StringGameSetting -> "s" to encoderAction { encodeString(gameSetting.value) }
    }

    encodeField("EDID") { encodeInline(InlineNullTerminatedString.serializer().descriptor).encodeString("$namePrefix${gameSetting.name}") }
    encodeField("DATA") { encodeSetting() }
}

// endregion

// region ALCH

@OptIn(ExperimentalSerializationApi::class)
fun BethesdaBufferEncoder.encodePotion(potion: ALCH) {
    encodeField("EDID") { encodeSerializableValue(InlineNullTerminatedString.serializer(), potion.editorId) }
    encodeField("OBND") { repeat(6) { encodeShort(0) } }
    encodeField("FULL") { encodeSerializableValue(InlineNullTerminatedString.serializer(), potion.name) }
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

    potion.effects?.forEach {
        encodeField("EFID") { encodeInt(it.effectId.toInt()) }
        encodeField("EFIT") {
            encodeFloat(it.effectParams.magnitude)
            encodeInt(it.effectParams.areaOfEffect.toInt())
            encodeInt(it.effectParams.duration.toInt())
        }
    }
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

@OptIn(ExperimentalSerializationApi::class)
fun <T> BethesdaBufferEncoder.encodeGroup(
    recordTag: String,
    group: GRUP<T>,
    encodeRecordData: BethesdaBufferEncoder.(T) -> Unit
) {
    val elementsBuffer = bethesdaBufferEncoder {
        group.records.forEach { encodeRecord(recordTag) { encodeRecordData(it) } }
    }

//    encodeString(group.typeTag)
//    encodeInt(elementsBuffer.size)
//    encodeString(group.label)
//    encodeInt(group.groupType)
//    encodeShort(group.timestamp)
//    encodeShort(group.versionControl)
//    encodeInt(group.unknown.toInt())
//    encodeBytes(elementsBuffer)
}

// endregion

// region Plugin

@ExperimentalStdlibApi
@OptIn(ExperimentalSerializationApi::class)
fun BethesdaBufferEncoder.encodePlugin(plugin: Plugin) {
    val tes4Record = plugin.header
    val tes4RecordData = bethesdaBufferEncoder {
        encodeField("HEDR") {
            encodeFloat(tes4Record.header.version)
            encodeInt(tes4Record.header.recordCount)
            encodeInt(tes4Record.header.nextAvailableObjectId)
        }
        encodeField("CNAM") { encodeSerializableValue(TES4.Author.serializer(), tes4Record.author) }
        encodeField("SNAM") { encodeSerializableValue(TES4.Description.serializer(), tes4Record.description) }

        tes4Record.masters.forEach {
            encodeField("MAST") { encodeSerializableValue(TES4.MasterFile.Name.serializer(), it.name) }
            encodeField("DATA") { encodeLong(it.data) }
        }

        // onam, skipped for now
        encodeField("INTV") { encodeInt(tes4Record.numberOfTagifiableValues) }
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
//    encodeGroup("GMST", plugin.gameSettings, BethesdaBufferEncoder::encodeGameSetting)
//    encodeGroup("ALCH", plugin.potions, BethesdaBufferEncoder::encodePotion)
}

// endregion
