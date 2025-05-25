package games.studiohummingbird.cultoftheancestormoth.serialization.tokens

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class PluginHeader(val tes4: Record) : PluginToken
