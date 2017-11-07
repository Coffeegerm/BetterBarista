package io.github.coffeegerm.brew_it.data

/**
 * Created by david_yarz on 11/1/17.
 */

class DrinksRepository {

    fun getDrinks(): ArrayList<Drink> {
        val drinks: ArrayList<Drink> = ArrayList()
        drinks.add(Drink(name = "Coffee", brewDuration = 6))
        drinks.add(Drink(name = "Mocha", brewDuration = 10))
        return drinks
    }
}