package games.studiohummingbird.cultoftheancestormoth.serialization

import arrow.typeclasses.Monoid

object MonoidByteArray
: Monoid<ByteArray> {
    override fun empty(): ByteArray =
        ByteArray(0)

    override fun ByteArray.combine(b: ByteArray): ByteArray =
        arrayOf(this, b).fold(ByteArray(size + b.size)) { result, array ->
            array.copyInto(result)
        }
}
