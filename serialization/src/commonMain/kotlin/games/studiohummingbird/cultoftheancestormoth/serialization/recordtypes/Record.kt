package games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes

import games.studiohummingbird.cultoftheancestormoth.serialization.RecordSerializer
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.TypeTag
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

@OptIn(ExperimentalSerializationApi::class)
@Serializable(with = RecordSerializer::class)
data class Record<TProps : Any> (
    val typeTag: TypeTag,
    val size: Int = 0,
    val flags: Int = 0,
    val formId: Int = 0,
    val timestamp: Short = 0,
    val versionControl: Short = 0,
    val version: Short = 0,
    val unknown: Short = 0,
    val properties: TProps
)
