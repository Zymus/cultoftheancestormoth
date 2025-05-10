package games.studiohummingbird.cultoftheancestormoth.serialization.tokens

import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.TypeTag
import kotlinx.serialization.Serializable

@Serializable
value class RecordType(val typeTag: TypeTag) : RecordToken
