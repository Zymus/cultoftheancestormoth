package games.studiohummingbird.cultoftheancestormoth.serialization.tokens

import games.studiohummingbird.cultoftheancestormoth.bytestring.serializer.ByteStringSerializer
import kotlinx.io.bytestring.ByteString
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@JvmInline
@Serializable
value class CompressedFields(
    val byteString: @Serializable(with = ByteStringSerializer::class) ByteString
) : RecordValueToken
