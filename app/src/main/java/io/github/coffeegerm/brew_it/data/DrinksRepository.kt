package io.github.coffeegerm.brew_it.data

import io.github.coffeegerm.brew_it.BrewItApplication.Companion.syringe
import io.realm.Realm
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by david_yarz on 11/1/17.
 *
 * Repository full of helper methods for accessing database data
 * Injected into classes using Dagger2
 */

class DrinksRepository {

    init {
        syringe.inject(this)
    }

    @Inject
    @field:Named("realm") lateinit var realm: Realm

    fun getAllDrinks(): ArrayList<Drink> = ArrayList(realm.where(Drink::class.java).findAll())

    fun getSingleDrinkById(id: Int): Drink? = realm.where(Drink::class.java).equalTo("id", id).findFirst()

    fun getSingleDrinkByName(name: String): Drink? = realm.where(Drink::class.java).equalTo("name", name).findFirst()
}