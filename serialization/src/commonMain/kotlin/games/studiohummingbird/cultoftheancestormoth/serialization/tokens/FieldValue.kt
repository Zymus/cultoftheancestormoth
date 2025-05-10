package games.studiohummingbird.cultoftheancestormoth.serialization.tokens

import kotlinx.serialization.Serializable

@Serializable
value class FieldValue<T : Any>(val value: T) : FieldToken
