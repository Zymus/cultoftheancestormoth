package games.studiohummingbird.cultoftheancestormoth.serialization

import creationkotlin.adapters.windows1252
import creationkotlin.unsorted.record
import creationkotlin.unsorted.typeTag
import games.studiohummingbird.cultoftheancestormoth.datatypes.TypeTag
import java.nio.ByteBuffer
import java.nio.ByteOrder.LITTLE_ENDIAN
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption.READ
import kotlin.io.path.fileSize

data class Record
( val typeTag: TypeTag
, val dataSize: UInt
, val flags: UInt
, val data: ByteArray
)
{
    override fun toString
    ( )
    : String
    = "$typeTag(size: $dataSize, flags: ${flags.toString(16)})"
}

fun ByteBuffer.writeRecord
( record: Record)
: ByteBuffer
= apply {
    typeTag(record.typeTag)
    putInt(record.dataSize.toInt())
    putInt(record.flags.toInt())
    put(record.data)
}

fun ByteBuffer.group
( )
: ByteBuffer
= apply {
    val tag = windows1252(4)
    val size = int.toUInt()
    val label = windows1252(4)
    val groupType = int
    val timestamp = short.toUShort()
    val versionControl = short.toUShort()
    val unknown = int.toUInt()
    val data = ByteArray(size.toInt() - 24).apply(this::get)

    println("$tag(size: $size, label: $label)")
}

fun main
( )
{
    val fileRecordPath = Paths.get("E:", "SteamLibrary", "steamapps", "common", "Skyrim Special Edition", "Data", "ForgetMeNotValley.esp")
    val buffer = ByteBuffer.allocateDirect(fileRecordPath.fileSize().toInt()).order(LITTLE_ENDIAN)
    val channel = Files.newByteChannel(fileRecordPath, READ)
    channel.read(buffer)
    buffer.flip()
    channel.close()

    val tes4Record = buffer.record()
    println(tes4Record)
    while (buffer.hasRemaining()) {
        println("remaining: ${buffer.remaining()}/${buffer.capacity()}")
        buffer.group()
    }
}
