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

package io.github.coffeegerm.brew_it.dagger

import dagger.Component
import io.github.coffeegerm.brew_it.ui.drinks.DrinksFragment
import io.github.coffeegerm.brew_it.ui.drinks.DrinksViewModel
import io.github.coffeegerm.brew_it.ui.main.MainActivity
import io.github.coffeegerm.brew_it.ui.more.MoreFragment
import io.github.coffeegerm.brew_it.ui.more.MoreViewModel
import io.github.coffeegerm.brew_it.ui.single_drink.SingleDrinkActivity
import io.github.coffeegerm.brew_it.ui.single_drink.SingleDrinkViewModel
import io.github.coffeegerm.brew_it.ui.timer.TimerFragment
import io.github.coffeegerm.brew_it.ui.timer.TimerViewModel
import javax.inject.Singleton

/**
 * AppComponent used for DI with Dagger2
 */

@Singleton
@Component(modules = [(BetterBaristaModule::class), (ResourceModule::class)])
interface AppComponent {
  fun inject(mainActivity: MainActivity)
  fun inject(singleDrinkActivity: SingleDrinkActivity)
  fun inject(singleDrinkViewModel: SingleDrinkViewModel)
  
  fun inject(drinksFragment: DrinksFragment)
  fun inject(drinksViewModel: DrinksViewModel)
  fun inject(timerViewModel: TimerViewModel)
  fun inject(moreFragment: MoreFragment)
  fun inject(moreViewModel: MoreViewModel)
}