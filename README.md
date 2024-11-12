# Cult of the Ancestor Moth

Cult of the Ancestor Moth is a set of tools to aid in developing Skyrim Plugins.

## License (GNU Affero General Public License)
     Cult of the Ancestor Moth
    Copyright (C) 2024  Zyle Moore (moore.zyle@gmail.com)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as published
    by the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.

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