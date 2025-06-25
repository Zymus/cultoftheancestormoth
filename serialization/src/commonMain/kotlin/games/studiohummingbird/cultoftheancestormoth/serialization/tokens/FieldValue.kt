package games.studiohummingbird.cultoftheancestormoth.serialization.tokens

import games.studiohummingbird.cultoftheancestormoth.bytestring.serializer.ByteStringSerializer
import kotlinx.io.bytestring.ByteString
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@Serializable
@JvmInline
value class FieldValue(val value: @Serializable(with = ByteStringSerializer::class) ByteString)
