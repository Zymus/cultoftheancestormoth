package games.studiohummingbird.cultoftheancestormoth.serialization.datatypes

import kotlin.jvm.JvmInline

@JvmInline
value class TerminatedString(val value: String) {
    override fun toString(): String = value
}
