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

package io.github.coffeegerm.betterbarista.ui.children.drinks.singleDrink

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.content.res.Resources
import io.github.coffeegerm.betterbarista.BetterBarista
import io.github.coffeegerm.betterbarista.data.CoffeeBar
import io.github.coffeegerm.betterbarista.data.model.Drink
import javax.inject.Inject

class SingleDrinkViewModel : ViewModel() {
  
  @Inject
  lateinit var coffeeBar: CoffeeBar
  @Inject
  lateinit var resources: Resources
  
  init {
    BetterBarista.syringe.inject(this)
  }
  
  var drinkMade: MutableLiveData<Drink> = MutableLiveData()
  var instructions: MutableLiveData<Array<out String>> = MutableLiveData()
  
  fun getDrink(drinkId: Int) {
    drinkMade.postValue(coffeeBar.getSingleDrinkById(drinkId))
    instructions.postValue(coffeeBar.getInstructionsForDrink(drinkId))
  }
}