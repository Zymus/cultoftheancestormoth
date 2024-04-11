package creationkotlin.adapters

import arrow.typeclasses.Monoid
import java.nio.ByteBuffer
import java.nio.ByteBuffer.allocateDirect

object MonoidByteBuffer
: Monoid<ByteBuffer>
{
    override fun empty
    ( )
    : ByteBuffer
    = allocateDirect(0)

    override infix fun ByteBuffer.combine
    ( b: ByteBuffer )
    : ByteBuffer
    = allocateDirect(limit() + b.limit())
        .put(this)
        .put(b)
        .flip()
}
