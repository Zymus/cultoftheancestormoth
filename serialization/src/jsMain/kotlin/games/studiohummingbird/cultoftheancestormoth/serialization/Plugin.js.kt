package games.studiohummingbird.cultoftheancestormoth.serialization

actual fun Plugin.toByteArray(): ByteArray {

    val recordCount = potions.count() + 1 + gameSettings.count() + 1
    val tes4RecordData = fold(listOf(
        field("HEDR", fold(listOf(
            1.7f.toByteArray(),// version
            recordCount.toUInt().toByteArray(),
            33000.toUInt().toByteArray()// next available object id
        ))
        ),
        field("CNAM", "Zymus".toZStringByteArray()),
        field("SNAM", "Creation Kotlin Plugin Description".toZStringByteArray()),
        fold(listOf(
            field("MAST", "Skyrim.esm".toZStringByteArray()),
            field("DATA", 0.toULong().toByteArray())
        )),
        // onam, skipped for now
        field("INTV", 0.toUInt().toByteArray())
    ))

    val buffer = fold(listOf(
        // region TES4 record
        "TES4".toWindows1252ByteArray(),
        tes4RecordData.size.toUInt().toByteArray(),
        0.toUInt().toByteArray(),// flags
        0.toUInt().toByteArray(),// record (form) identifier
        0.toUShort().toByteArray(),// timestamps
        0.toUShort().toByteArray(),// version control
        0.toUShort().toByteArray(),// internal version
        0.toUShort().toByteArray(),// unknown
        tes4RecordData,
        // INCC, skipped for now
        // endregion
        gameSettings.toByteArray(),
        potions.toByteArray(),
    ))

    return buffer
}