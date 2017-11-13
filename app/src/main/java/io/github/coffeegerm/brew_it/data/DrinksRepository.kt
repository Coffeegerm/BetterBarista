package io.github.coffeegerm.brew_it.data

import io.realm.Realm

/**
 * Created by david_yarz on 11/1/17.
 * Repository full of helper methods for accessing database data
 * Injected into classes using Dagger2
 */

class DrinksRepository {

    val realm: Realm = Realm.getDefaultInstance()

    fun getDrinks(): ArrayList<Drink> {
        return ArrayList(realm.where(Drink::class.java).findAll())
    }
}