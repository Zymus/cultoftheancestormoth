package games.studiohummingbird.cultoftheancestormoth.serialization.annotations

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialInfo

/**
 * Can only be applied to String properties.
 */
@ExperimentalSerializationApi
@SerialInfo
@Target(AnnotationTarget.PROPERTY)
annotation class NullTerminatedString
