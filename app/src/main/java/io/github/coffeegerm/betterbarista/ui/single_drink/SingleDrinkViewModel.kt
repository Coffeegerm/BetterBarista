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

package io.github.coffeegerm.betterbarista.ui.single_drink

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.res.Resources
import io.github.coffeegerm.betterbarista.BetterBarista
import io.github.coffeegerm.betterbarista.R
import io.github.coffeegerm.betterbarista.data.Drink
import io.github.coffeegerm.betterbarista.data.DrinksRepository
import javax.inject.Inject

class SingleDrinkViewModel : ViewModel() {
  
  @Inject lateinit var drinksRepository: DrinksRepository
  @Inject lateinit var resources: Resources
  
  init {
    BetterBarista.syringe.inject(this)
  }
  
  var drinkMade: MutableLiveData<Drink> = MutableLiveData()
  var instructions: MutableLiveData<Array<out String>> = MutableLiveData()
  
  fun getDrink(drinkId: Int) {
    drinkMade.postValue(drinksRepository.getSingleDrinkById(drinkId))
    getInstructions(drinkId)
  }
  
  private fun getInstructions(drinkId: Int) {
    when (drinkId) {
      0 -> instructions.postValue(resources.getStringArray(R.array.instructions_coffee))
      1 -> instructions.postValue(resources.getStringArray(R.array.instructions_french_press))
      2 -> instructions.postValue(resources.getStringArray(R.array.instructions_pour_over))
      3 -> instructions.postValue(resources.getStringArray(R.array.instructions_cold_brew_coffee))
      4 -> instructions.postValue(resources.getStringArray(R.array.instructions_aeropress))
      5 -> instructions.postValue(resources.getStringArray(R.array.instructions_kalita_wave))
      6 -> instructions.postValue(resources.getStringArray(R.array.instructions_espresso))
      7 -> instructions.postValue(resources.getStringArray(R.array.instructions_chai_latte))
      8 -> instructions.postValue(resources.getStringArray(R.array.instructions_dirty_chai))
      9 -> instructions.postValue(resources.getStringArray(R.array.instructions_americano))
      10 -> instructions.postValue(resources.getStringArray(R.array.instructions_cappuccino))
      11 -> instructions.postValue(resources.getStringArray(R.array.instructions_black_eye))
      12 -> instructions.postValue(resources.getStringArray(R.array.instructions_red_eye))
      13 -> instructions.postValue(resources.getStringArray(R.array.instructions_green_eye))
    }
  }
  
}