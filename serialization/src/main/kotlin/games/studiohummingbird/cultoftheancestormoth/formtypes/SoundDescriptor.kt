package creationkotlin.formtypes

data class SoundDescriptor(
    override val editorId: String = ""
) : FormType

class MutableSoundDescriptor {

    var soundDescriptor: SoundDescriptor = SoundDescriptor()
        private set
}

fun sndr(block: MutableSoundDescriptor.() -> Unit): SoundDescriptor =
    MutableSoundDescriptor().apply(block).soundDescriptor
