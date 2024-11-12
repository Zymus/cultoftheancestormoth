package games.studiohummingbird.cultoftheancestormoth.recordtypes

import games.studiohummingbird.cultoftheancestormoth.annotations.Field
import games.studiohummingbird.cultoftheancestormoth.annotations.Record
import games.studiohummingbird.cultoftheancestormoth.datatypes.RGBA8

@Record
data class Keyword
(
    @Field("EDID")
    val string: String,

    @Field("CNAM")
    val editorColor: RGBA8,
)
: RecordType