package games.studiohummingbird.cultoftheancestormoth.serialization.tokens

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class FieldSize(val ushort: UShort) {
    constructor(int: Int) : this(int.toUShort())
}
