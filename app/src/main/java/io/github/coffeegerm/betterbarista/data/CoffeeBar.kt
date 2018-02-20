/*
 * Copyright 2017 Coffee and Cream Studios
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.coffeegerm.betterbarista.data

import android.content.res.Resources
import io.github.coffeegerm.betterbarista.BetterBarista
import io.github.coffeegerm.betterbarista.R
import io.github.coffeegerm.betterbarista.data.model.Drink
import io.realm.Realm
import javax.inject.Inject

/**
 * Class full of helper methods for accessing database data
 * Injected into classes using Dagger2
 */

class CoffeeBar {
  
  init {
    BetterBarista.syringe.inject(this)
  }
  
  @Inject
  lateinit var resources: Resources
  
  private var realm = Realm.getDefaultInstance()
  
  fun getAllDrinks(): ArrayList<Drink> = ArrayList(realm.where(Drink::class.java).findAll())
  
  fun getSingleDrinkById(id: Int): Drink? = realm.where(Drink::class.java).equalTo("id", id).findFirst()
  
  fun getInstructionsForDrink(drinkId: Int): Array<out String> {
    return when (drinkId) {
      0 -> resources.getStringArray(R.array.instructions_coffee)
      1 -> resources.getStringArray(R.array.instructions_pour_over)
      2 -> resources.getStringArray(R.array.instructions_french_press)
      3 -> resources.getStringArray(R.array.instructions_cold_brew_coffee)
      4 -> resources.getStringArray(R.array.instructions_aeropress)
      5 -> resources.getStringArray(R.array.instructions_kalita_wave)
      6 -> resources.getStringArray(R.array.instructions_espresso)
      7 -> resources.getStringArray(R.array.instructions_chai_latte)
      8 -> resources.getStringArray(R.array.instructions_dirty_chai)
      9 -> resources.getStringArray(R.array.instructions_americano)
      10 -> resources.getStringArray(R.array.instructions_cappuccino)
      11 -> resources.getStringArray(R.array.instructions_black_eye)
      12 -> resources.getStringArray(R.array.instructions_red_eye)
      13 -> resources.getStringArray(R.array.instructions_green_eye)
      else -> resources.getStringArray(R.array.unable_to_find_array)
    }
  }
  
}