package games.studiohummingbird.cultoftheancestormoth.serialization.tokens

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("RecordProperties")
data class RecordProperties(
    val flags: Int,
    val recordId: Int,
    val timestamp: Short,
    val versionControl: Short,
    val recordVersion: Short,
    val unknown: Short
) {
    val isMaster: Boolean
        get() = flags and 1 == 1

    val isDeletedGroup: Boolean
        get() = flags and 0x10 == 0x10

    val isDeletedRecord: Boolean
        get() = flags and 0x20 == 0x20

    val isLightMaster: Boolean
        get() = flags and 0x200 == 0x200

    val isInitiallyDisabled: Boolean
        get() = flags and 0x800 == 0x800

    val isIgnored: Boolean
        get() = flags and 0x1000 == 0x1000

    val isVisibleWhenDistant: Boolean
        get() = flags and 0x8000 == 0x8000

    val isDataCompressed: Boolean
        get() = flags and 0x40000 == 0x40000
}
