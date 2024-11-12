# Cult of the Ancestor Moth

Cult of the Ancestor Moth is a set of tools to aid in developing Skyrim Plugins.

## Kotlinx Serialization
Uses kotlinx serialization to provide the binary output for the build artifacts.
Records get converted into their plugin representations.
The expected output of a converted plugin is an esp file that is usable in game.

## Gradle Plugin
The gradle plugin is responsible for build the .esp.kts files into their associated plugins.
It creates the build esp files as outputs, sepending on inputs from the src folder.
Stil need to figure out the specifics of the configuration.
Integrated with publishing, somehow, to publish the built artifacts to mod repositories, like nexus.

## Command line interface
Just provides nice command line tooling for plugins. Useful in scripting environments.

cotam plugin create SkoomaRefinement
cotam add ALCH
cotam find IronDagger
cotam publish SkoomaRefinement nexus

cotam adept read SkoomaRefinement
cotam librarian catalog
cotam drudge CBBE

## Web
A web interface for creating plugin files.
Gonna try to get this as close to entire client side as possible, that way there's not
privact concerns about what data is transmitted to the server,
because there won't be any.
Just a tool that can be used in the client.
Will use Kotlin MPP, Kotlin/JS probably, maybe wasm if I get that far.
Uses TextDecoder