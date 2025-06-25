// skooma recipe from moonsugar and nightshade
// skooma refining alchemy table workbench
// skooma workbench keyword

// I want to be able to manipulate these game elements as easily as json elements
// so I need to figure out the dsl
// # example
// val camila = lucan.sister
// camila.schedule {
//   wakeUp { 6.am }
// }
// camila relationshipWith sven { negative }
// camila.health += 50.hp
// Restoration.perkTree {
//   +"Huge Rez" {
//     description = "Resurrected allies are temporarily invincible."
//     requiredLevel = 40
//     dependsOn { ApprenticeRestoration }
//   }
// }
// forsworn relationshipWith stormcloaks { pissed }
// forsworn relationshipWith Player { if (Player wearing forsworn) chill else pissed }
// plant(4, 3) { RedMountainFlower }
// Dragonsreach.doors.locked
// Bribe.cost *= 2
//
// From these examples, everything should basically be primitive
//
