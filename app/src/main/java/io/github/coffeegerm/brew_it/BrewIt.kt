package io.github.coffeegerm.brew_it

import android.app.Application
import io.github.coffeegerm.brew_it.data.Drink
import java.util.*

/**
 * Created by dYarzebinski on 10/23/2017.
 * TODO: Add class comment header
 */

var drinksList: ArrayList<Drink> = ArrayList()

class BrewIt : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}