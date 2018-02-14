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

package io.github.coffeegerm.betterbarista.dagger

import dagger.Component
import io.github.coffeegerm.betterbarista.ui.MainActivity
import io.github.coffeegerm.betterbarista.ui.children.drinks.DrinksFragment
import io.github.coffeegerm.betterbarista.ui.children.drinks.DrinksViewModel
import io.github.coffeegerm.betterbarista.ui.children.drinks.singleDrink.SingleDrinkViewModel
import io.github.coffeegerm.betterbarista.ui.children.timer.TimerViewModel
import javax.inject.Singleton

/**
 * AppComponent used for DI with Dagger2
 */

@Singleton
@Component(modules = [(BetterBaristaModule::class), (ResourceModule::class)])
interface AppComponent {
  fun inject(mainActivity: MainActivity)
  
  fun inject(singleDrinkViewModel: SingleDrinkViewModel)
  
  fun inject(drinksFragment: DrinksFragment)
  fun inject(drinksViewModel: DrinksViewModel)
  fun inject(timerViewModel: TimerViewModel)
}