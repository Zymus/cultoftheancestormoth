package creationkotlin.datatypes

import creationkotlin.adapters.windows1252
import java.nio.ByteBuffer

data class Header
( val version: Float
, val numberOfRecords: UInt
, val nextObjectId: UInt
)

fun ByteBuffer.readHeader
( )
: Header
= run {
    val headerTag = windows1252(4)
    val structDataSize = short.toUShort()
    val version = float
    val numberOfRecords = int.toUInt()
    val nextObjectId = int.toUInt()

    Header(version, numberOfRecords, nextObjectId)
}