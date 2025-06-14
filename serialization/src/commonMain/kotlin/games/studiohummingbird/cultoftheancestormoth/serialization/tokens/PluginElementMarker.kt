/**
Cult of the Ancestor Moth (ElementMarker.kt)
Copyright (C) 2024  Zymus (moore.zyle@gmail.com)

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
 */
package games.studiohummingbird.cultoftheancestormoth.serialization.tokens

import games.studiohummingbird.cultoftheancestormoth.serialization.datatypes.TypeTag
import kotlinx.serialization.Serializable

@Serializable
data class PluginElementMarker(
    val tag: TypeTag,
    val skip: Long,
    val size: Long,
    val type: Type,
    val isDataCompressed: Boolean,
) {
    enum class Type {
        UNKNOWN,
        GROUP,
        RECORD,
        FIELD,
        ;
    }
}




//TypeTag TES4 recordSize 54 78
// this should have something like
//? TypeTag HEDR fieldSize 12 totalSize 18 (skip = 24)
//? TypeTag CNAM fieldSize 6 totalSize 12 (skip = 24 + 18)
// ...
//0 TypeTag GRUP groupSize 96862 (skip = 78)
//1 TypeTag GRUP groupSize 41348 (skip = 78 + 96862)
//2 TypeTag GRUP groupSize 25880 (skip = 78 + 96862 + 41348)
//3 TypeTag GRUP groupSize 3239
//4 TypeTag GRUP groupSize 126505
//5 TypeTag GRUP groupSize 43679
//6 TypeTag GRUP groupSize 15357
//7 TypeTag GRUP groupSize 149072
//8 TypeTag GRUP groupSize 249127
//9 TypeTag GRUP groupSize 24
//10 TypeTag GRUP groupSize 861
//11 TypeTag GRUP groupSize 1086390
//12 TypeTag GRUP groupSize 127655
//13 TypeTag GRUP groupSize 4357
//14 TypeTag GRUP groupSize 327771
//15 TypeTag GRUP groupSize 24
//16 TypeTag GRUP groupSize 6035
//17 TypeTag GRUP groupSize 100551
//18 TypeTag GRUP groupSize 174449
//19 TypeTag GRUP groupSize 22266
//20 TypeTag GRUP groupSize 829232
//21 TypeTag GRUP groupSize 4738
//22 TypeTag GRUP groupSize 902160
//23 TypeTag GRUP groupSize 221930
//24 TypeTag GRUP groupSize 222759
//25 TypeTag GRUP groupSize 52345
//26 TypeTag GRUP groupSize 30816
//27 TypeTag GRUP groupSize 60178
//28 TypeTag GRUP groupSize 75812
//29 TypeTag GRUP groupSize 4956
//30 TypeTag GRUP groupSize 3087977
//31 TypeTag GRUP groupSize 24
//32 TypeTag GRUP groupSize 131499
//33 TypeTag GRUP groupSize 24
//34 TypeTag GRUP groupSize 4689
//35 TypeTag GRUP groupSize 35138
//36 TypeTag GRUP groupSize 24
//37 TypeTag GRUP groupSize 21535
//38 TypeTag GRUP groupSize 115634
//39 TypeTag GRUP groupSize 1332995
//40 TypeTag GRUP groupSize 7790
//41 TypeTag GRUP groupSize 2222898
//42 TypeTag GRUP groupSize 106351
//43 TypeTag GRUP groupSize 72885
//44 TypeTag GRUP groupSize 111703
//45 TypeTag GRUP groupSize 8389
//46 TypeTag GRUP groupSize 105968
//47 TypeTag GRUP groupSize 38894
//48 TypeTag GRUP groupSize 9309
//49 TypeTag GRUP groupSize 3861
//50 TypeTag GRUP groupSize 661403
//51 TypeTag GRUP groupSize 238897
//52 TypeTag GRUP groupSize 878
//53 TypeTag GRUP groupSize 2097
//54 TypeTag GRUP groupSize 7544
//55 TypeTag GRUP groupSize 58837
//56 TypeTag GRUP groupSize 2703662
//57 TypeTag GRUP groupSize 36693002
//58 TypeTag GRUP groupSize 175996057
//59 TypeTag GRUP groupSize 8302711
//60 TypeTag GRUP groupSize 2842762
//61 TypeTag GRUP groupSize 572917
//62 TypeTag GRUP groupSize 3358391
//63 TypeTag GRUP groupSize 29226
//64 TypeTag GRUP groupSize 56534
//65 TypeTag GRUP groupSize 3745
//66 TypeTag GRUP groupSize 7428
//67 TypeTag GRUP groupSize 16909
//68 TypeTag GRUP groupSize 94255
//69 TypeTag GRUP groupSize 32357
//70 TypeTag GRUP groupSize 3289
//71 TypeTag GRUP groupSize 41750
//72 TypeTag GRUP groupSize 284898
//73 TypeTag GRUP groupSize 82124
//74 TypeTag GRUP groupSize 115714
//75 TypeTag GRUP groupSize 24159
//76 TypeTag GRUP groupSize 13526
//77 TypeTag GRUP groupSize 28468
//78 TypeTag GRUP groupSize 12655
//79 TypeTag GRUP groupSize 7603
//80 TypeTag GRUP groupSize 7803
//81 TypeTag GRUP groupSize 9350
//82 TypeTag GRUP groupSize 91912
//83 TypeTag GRUP groupSize 175474
//84 TypeTag GRUP groupSize 339719
//85 TypeTag GRUP groupSize 18576
//86 TypeTag GRUP groupSize 373849
//87 TypeTag GRUP groupSize 54185
//88 TypeTag GRUP groupSize 24
//89 TypeTag GRUP groupSize 2478
//90 TypeTag GRUP groupSize 18078
//91 TypeTag GRUP groupSize 6066
//92 TypeTag GRUP groupSize 9911
//93 TypeTag GRUP groupSize 4788
//94 TypeTag GRUP groupSize 15947
//95 TypeTag GRUP groupSize 98133
//96 TypeTag GRUP groupSize 2064
//97 TypeTag GRUP groupSize 294791
//98 TypeTag GRUP groupSize 39145
//99 TypeTag GRUP groupSize 116450
//100 TypeTag GRUP groupSize 4894
//101 TypeTag GRUP groupSize 10129
//102 TypeTag GRUP groupSize 402
//103 TypeTag GRUP groupSize 43487
//104 TypeTag GRUP groupSize 2557065
//105 TypeTag GRUP groupSize 2371
//106 TypeTag GRUP groupSize 33913
//107 TypeTag GRUP groupSize 34531
//108 TypeTag GRUP groupSize 9811
//109 TypeTag GRUP groupSize 12300
//110 TypeTag GRUP groupSize 24
//111 TypeTag GRUP groupSize 613156
//112 TypeTag GRUP groupSize 227
//113 TypeTag GRUP groupSize 1676
//114 TypeTag GRUP groupSize 7336
//115 TypeTag GRUP groupSize 11002
//116 TypeTag GRUP groupSize 13763
//117 TypeTag GRUP groupSize 741
