package games.studiohummingbird.cultoftheancestormoth.serialization.annotations

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialInfo
import kotlinx.serialization.descriptors.SerialDescriptor

@OptIn(ExperimentalSerializationApi::class)
@SerialInfo
annotation class FixedLength(
    val length: Int
)

@OptIn(ExperimentalSerializationApi::class)
val SerialDescriptor.isFixedLength: Boolean
    get() = annotations.filterIsInstance<FixedLength>().any()

@OptIn(ExperimentalSerializationApi::class)
val SerialDescriptor.fixedLength: FixedLength
    get() = annotations.filterIsInstance<FixedLength>().single()
