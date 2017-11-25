package io.github.coffeegerm.brew_it.data

import io.realm.RealmObject

/**
 * TODO: Add class comment header
 */

open class Drink : RealmObject() {
    var id: Int = 0
    var name: String = ""
    var description: String = ""
    var image: Int = 0
    var brewDuration: Int = 0 // minutes
    var strength: String = ""
    var difficulty: String = ""
}