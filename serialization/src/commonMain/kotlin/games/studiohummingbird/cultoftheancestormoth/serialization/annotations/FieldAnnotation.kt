package games.studiohummingbird.cultoftheancestormoth.serialization.annotations

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialInfo
import kotlinx.serialization.descriptors.SerialDescriptor

@ExperimentalSerializationApi
@SerialInfo
@Target(AnnotationTarget.PROPERTY)
annotation class FieldAnnotation(val name: String)

@ExperimentalSerializationApi
fun SerialDescriptor.isField(index: Int): Boolean =
    getElementAnnotations(index).any { it is FieldAnnotation }

@ExperimentalSerializationApi
fun SerialDescriptor.getFieldAnnotation(index: Int): FieldAnnotation =
    getElementAnnotations(index).single { it is FieldAnnotation } as FieldAnnotation
