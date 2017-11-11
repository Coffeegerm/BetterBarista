package io.github.coffeegerm.brew_it.data

import io.realm.RealmModel

/**
 * Created by dYarzebinski on 10/26/2017.
 * TODO: Add class comment header
 */

open class Drink : RealmModel {
    var id: Int = 0
    var name: String = ""
    var brewDuration: Int = 0 // minutes
    var strength: String = ""
    var difficulty: String = ""
}