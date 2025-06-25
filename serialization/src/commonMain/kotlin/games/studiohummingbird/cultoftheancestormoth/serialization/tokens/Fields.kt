package games.studiohummingbird.cultoftheancestormoth.serialization.tokens

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@JvmInline
@Serializable
value class Fields(
    val list: List<Field>
) : RecordValue
