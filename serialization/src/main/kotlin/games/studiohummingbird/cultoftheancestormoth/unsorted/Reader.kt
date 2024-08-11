package creationkotlin.unsorted

import arrow.core.Option
import arrow.core.none
import arrow.core.some
import creationkotlin.datatypes.TypeTag
import creationkotlin.adapters.WINDOWS_1252
import creationkotlin.adapters.windows1252
import creationkotlin.recordtypes.Record
import java.nio.ByteBuffer

val ByteBuffer.typeTag: TypeTag
    get() = TypeTag(windows1252(4))

fun ByteBuffer.record(): Option<Record> {
    if (!hasRemaining()) {
        return none()
    }
    val xtypeTag = typeTag
    val xdataSize = int.toUInt()
    val xflags = int.toUInt()
    val misc = ByteArray(12).apply(this::get)
    val xrecordData = ByteArray(xdataSize.toInt()).apply(this::get)

    return Record(
        xtypeTag,
        xdataSize,
        xflags,
        xrecordData
    ).some()
}

fun ByteBuffer.typeTag(value: TypeTag): ByteBuffer =
    apply { put(value.string.toByteArray(WINDOWS_1252)) }
