package creationkotlin.adapters

import java.nio.ByteBuffer
import java.nio.ByteOrder.LITTLE_ENDIAN

fun littleEndianByteBuffer
( capacity: Int, block: ByteBuffer.() -> Unit = { })
: ByteBuffer
= ByteBuffer.allocateDirect(capacity).order(LITTLE_ENDIAN).apply(block)
