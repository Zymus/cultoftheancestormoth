package games.studiohummingbird.cultoftheancestormoth.serialization.tokens

import games.studiohummingbird.cultoftheancestormoth.serialization.recordtypes.TES4
import kotlinx.serialization.Serializable

@Serializable
value class PluginHeader(val tes4: TES4) : PluginToken
