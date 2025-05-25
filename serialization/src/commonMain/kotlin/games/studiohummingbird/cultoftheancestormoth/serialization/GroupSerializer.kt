package games.studiohummingbird.cultoftheancestormoth.serialization

import games.studiohummingbird.cultoftheancestormoth.bytestring.serializer.ByteStringDecoder
import games.studiohummingbird.cultoftheancestormoth.bytestring.serializer.ByteStringSerializer
import games.studiohummingbird.cultoftheancestormoth.bytestring.serializer.decodeFromByteString
import games.studiohummingbird.cultoftheancestormoth.bytestring.serializer.encodeToByteString
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Group
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.GroupHeader
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.GroupSize
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.GroupValueToken
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.RecordAndGroup
import games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Records
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure

class GroupSerializer : KSerializer<Group> {

    override val descriptor: SerialDescriptor = buildClassSerialDescriptor(SERIAL_NAME) {
        element<GroupHeader>("header")
        element("value", ByteStringSerializer.descriptor)
    }

    override fun serialize(encoder: Encoder, value: Group) {
        encoder.encodeStructure(descriptor) {
            val groupValueByteString = PluginFormat.encodeToByteString(Records.serializer(), value.records)
            val header = value.header.copy(
                groupSize = GroupSize((groupValueByteString.size + 24).toUInt())
            )
            encodeSerializableElement(descriptor, 0, GroupHeader.serializer(), header)
            encodeSerializableElement(descriptor, 1, Records.serializer(), value.records)
        }
    }

    override fun deserialize(decoder: Decoder): Group {
        require(decoder is ByteStringDecoder)
        return decoder.decodeStructure(descriptor) {
            val header = decodeSerializableElement(descriptor, 0, GroupHeader.serializer())
            val groupValueBytes = decoder.decodeByteString(header.groupSize.uint.toInt() - 24)

            if (header.groupSize.uint == 0.toUInt()) {
                return@decodeStructure Group(header, Records(emptyList()))
            }

            val groupValue: GroupValueToken =
                when (header.groupProperties.groupType) {
                    0 -> when (header.groupProperties.label.string) {
                        "CELL" -> {
                            PluginFormat.decodeFromByteString(Group.serializer(), groupValueBytes)
                        }
                        else -> PluginFormat.decodeFromByteString(Records.serializer(), groupValueBytes)
                    }
                    2 -> PluginFormat.decodeFromByteString(Group.serializer(), groupValueBytes)
                    3 -> PluginFormat.decodeFromByteString(RecordAndGroup.serializer(), groupValueBytes)
                    6 -> PluginFormat.decodeFromByteString(Group.serializer(), groupValueBytes)
                    8, 9 -> PluginFormat.decodeFromByteString(Records.serializer(), groupValueBytes)
                    else -> PluginFormat.decodeFromByteString(Records.serializer(), groupValueBytes)
                }

            Group(header, groupValue)
        }
    }

    companion object {
        const val SERIAL_NAME = "games.studiohummingbird.cultoftheancestormoth.serialization.tokens.Group"
    }
}
