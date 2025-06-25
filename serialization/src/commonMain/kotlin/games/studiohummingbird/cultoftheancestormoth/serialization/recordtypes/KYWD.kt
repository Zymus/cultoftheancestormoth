package games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes

import games.studiohummingbird.cultoftheancestormoth.serialization.annotations.FieldAnnotation
import games.studiohummingbird.cultoftheancestormoth.serialization.annotations.RecordAnnotation
import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.RGBA8
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Required
import kotlinx.serialization.Serializable

@ExperimentalSerializationApi
@RecordAnnotation("KYWD")
@Serializable
data class KYWD
(
    @FieldAnnotation("EDID")
    @Required
    val string: String,

    @FieldAnnotation("CNAM")
    val editorColor: RGBA8,
)
