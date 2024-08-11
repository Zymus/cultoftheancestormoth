package creationkotlin.recordtypes

import creationkotlin.annotations.Field
import creationkotlin.annotations.Record
import creationkotlin.datatypes.RGBA8

@Record
data class Keyword
(
    @Field("EDID")
    val string: String,

    @Field("CNAM")
    val editorColor: RGBA8,
)
: RecordType