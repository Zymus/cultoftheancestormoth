package games.studiohummingbird.cultoftheancestormoth.serialization.annotations

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialInfo
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlin.annotation.AnnotationTarget.CLASS
import kotlin.annotation.AnnotationTarget.PROPERTY

@OptIn(ExperimentalSerializationApi::class)
@SerialInfo
@Target(PROPERTY, CLASS)
annotation class FieldAnnotation(val name: String)

@OptIn(ExperimentalSerializationApi::class)
val SerialDescriptor.isField: Boolean
    get() = annotations.filterIsInstance<FieldAnnotation>().any()

@OptIn(ExperimentalSerializationApi::class)
val SerialDescriptor.field: FieldAnnotation
    get() = annotations.filterIsInstance<FieldAnnotation>().single()

@OptIn(ExperimentalSerializationApi::class)
fun SerialDescriptor.elementIsField(index: Int): Boolean =
    getElementAnnotations(index).filterIsInstance<FieldAnnotation>().any()
