package games.studiohummingbird.cultoftheancestormoth.serialization

import games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes.TES4
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

@ExperimentalStdlibApi
@ExperimentalSerializationApi
@Serializable
data class Plugin(
    val header: TES4
) {
}

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
