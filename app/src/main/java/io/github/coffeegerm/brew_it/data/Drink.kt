package io.github.coffeegerm.brew_it.data

import io.realm.RealmObject

/**
 * Created by dYarzebinski on 10/26/2017.
 * TODO: Add class comment header
 */

open class Drink : RealmObject() {
    var id: Int? = 0
    var name: String? = null
    var brewDuration: Int? = 0 // minutes
    var strength: String? = null
    var difficulty: String? = null
}