package games.studiohummingbird.cultoftheancestormoth.serialization

import games.studiohummingbird.cultoftheancestormoth.adapters.MonoidByteBuffer.fold
import games.studiohummingbird.cultoftheancestormoth.adapters.field
import games.studiohummingbird.cultoftheancestormoth.adapters.toByteBuffer
import games.studiohummingbird.cultoftheancestormoth.adapters.toWindows1252ByteBuffer
import games.studiohummingbird.cultoftheancestormoth.adapters.toZStringByteBuffer

actual fun Plugin.toByteArray(): ByteArray {
    val recordCount = potions.count() + 1 + gameSettings.count() + 1
    val tes4RecordData = fold(listOf(
        field("HEDR", fold(listOf(
            1.7f.toByteBuffer(),// version
            recordCount.toUInt().toByteBuffer(),
            33000.toUInt().toByteBuffer()// next available object id
        ))
        ),
        field("CNAM", "Zymus".toZStringByteBuffer()),
        field("SNAM", "Creation Kotlin Plugin Description".toZStringByteBuffer()),
        fold(listOf(
            field("MAST", "Skyrim.esm".toZStringByteBuffer()),
            field("DATA", 0.toULong().toByteBuffer())
        )),
        // onam, skipped for now
        field("INTV", 0.toUInt().toByteBuffer())
    ))

    val buffer = fold(listOf(
        // region TES4 record
        "TES4".toWindows1252ByteBuffer(),
        tes4RecordData.limit().toUInt().toByteBuffer(),
        0.toUInt().toByteBuffer(),// flags
        0.toUInt().toByteBuffer(),// record (form) identifier
        0.toUShort().toByteBuffer(),// timestamps
        0.toUShort().toByteBuffer(),// version control
        0.toUShort().toByteBuffer(),// internal version
        0.toUShort().toByteBuffer(),// unknown
        tes4RecordData,
        // INCC, skipped for now
        // endregion
        gameSettings.toByteBuffer(),
        potions.toByteBuffer(),
    ))

    return if (buffer.hasArray()) {
        buffer.array()
    }
    else {
        val array = ByteArray(buffer.limit())
        buffer.get(array)
        return array
    }
}