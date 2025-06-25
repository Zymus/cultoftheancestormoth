package games.studiohummingbird.cultoftheancestormoth.serialization.annotations

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialInfo
import kotlinx.serialization.descriptors.SerialDescriptor

@ExperimentalSerializationApi
@SerialInfo
@Target(AnnotationTarget.CLASS)
annotation class RecordAnnotation(val name: String)

@ExperimentalSerializationApi
fun SerialDescriptor.isRecord(): Boolean = annotations.any { it is RecordAnnotation }

@ExperimentalSerializationApi
fun SerialDescriptor.getRecordAnnotation(): RecordAnnotation = annotations.single { it is RecordAnnotation } as RecordAnnotation
