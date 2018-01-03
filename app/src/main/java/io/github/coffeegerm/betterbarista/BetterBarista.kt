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

package io.github.coffeegerm.betterbarista

import android.app.Application
import io.github.coffeegerm.betterbarista.dagger.AppComponent
import io.github.coffeegerm.betterbarista.dagger.BetterBaristaModule
import io.github.coffeegerm.betterbarista.dagger.DaggerAppComponent
import io.github.coffeegerm.betterbarista.dagger.ResourceModule
import io.github.coffeegerm.betterbarista.data.Drink
import io.realm.Realm
import io.realm.RealmConfiguration
import timber.log.Timber
import timber.log.Timber.DebugTree

class BetterBarista : Application() {
  
  companion object {
    lateinit var syringe: AppComponent
  }
  
  override fun onCreate() {
    super.onCreate()
    Realm.init(this)
    syringe = DaggerAppComponent.builder()
          .betterBaristaModule(BetterBaristaModule(this))
          .resourceModule(ResourceModule())
          .build()
  
    val realmConfig: RealmConfiguration = RealmConfiguration
          .Builder()
          .schemaVersion(3)
          .deleteRealmIfMigrationNeeded()
          .build()
    Realm.setDefaultConfiguration(realmConfig)
    initRealmData()
    
    if (BuildConfig.DEBUG) {
      Timber.plant(DebugTree())
    } else {
      //custom production tree. set up so logs are sent to crashlytics along with the stack trace or something
    }
  }
  
  private fun initRealmData() {
    val realm = Realm.getDefaultInstance()
    realm.executeTransaction {
      realm.deleteAll()
      val drink = Drink()
      
      drink.id = 0
      drink.name = getString(R.string.coffee)
      drink.description = getString(R.string.coffee_description)
      drink.temperature = getString(R.string.hot)
      drink.image = R.drawable.coffee
      drink.brewDuration = 6
      drink.strength = getString(R.string.regular)
      drink.difficulty = getString(R.string.easy)
      realm.insertOrUpdate(drink)
      
      drink.id = 1
      drink.name = getString(R.string.pour_over)
      drink.description = getString(R.string.pour_over_description)
      drink.temperature = getString(R.string.hot)
      drink.brewDuration = 10
      drink.image = R.drawable.pour_over
      drink.strength = getString(R.string.regular_strong)
      drink.difficulty = getString(R.string.intermediate)
      realm.insertOrUpdate(drink)
      
      drink.id = 2
      drink.name = getString(R.string.french_press)
      drink.description = getString(R.string.french_press_description)
      drink.temperature = getString(R.string.hot)
      drink.image = R.drawable.french_press
      drink.brewDuration = 10
      drink.strength = getString(R.string.regular)
      drink.difficulty = getString(R.string.intermediate)
      realm.insertOrUpdate(drink)
      
      drink.id = 3
      drink.name = getString(R.string.aeropress)
      drink.description = getString(R.string.aeropress_description)
      drink.temperature = getString(R.string.hot)
      drink.image = R.drawable.aeropress
      drink.brewDuration = 12
      drink.strength = getString(R.string.strong)
      drink.difficulty = getString(R.string.difficult)
      realm.insertOrUpdate(drink)
      
      drink.id = 4
      drink.name = getString(R.string.cold_brew)
      drink.description = getString(R.string.cold_brew_description)
      drink.temperature = getString(R.string.cold)
      drink.image = R.drawable.iced_coffee
      drink.brewDuration = 1440
      drink.strength = getString(R.string.regular_strong)
      drink.difficulty = getString(R.string.easy)
      realm.insertOrUpdate(drink)
      
      drink.id = 5
      drink.name = getString(R.string.kalita_wave)
      drink.description = getString(R.string.kalita_wave_description)
      drink.temperature = getString(R.string.hot)
      drink.image = R.drawable.kalita_wave
      drink.brewDuration = 10
      drink.strength = getString(R.string.regular_strong)
      drink.difficulty = getString(R.string.intermediate)
      realm.insertOrUpdate(drink)
      
      drink.id = 6
      drink.name = getString(R.string.espresso)
      drink.description = getString(R.string.espresso_description)
      drink.temperature = getString(R.string.hot)
      drink.image = R.drawable.espresso
      drink.brewDuration = 5
      drink.strength = getString(R.string.strong_potent)
      drink.difficulty = getString(R.string.intermediate)
      realm.insertOrUpdate(drink)
      
      drink.id = 7
      drink.name = getString(R.string.chai_latte)
      drink.description = getString(R.string.chai_latte_description)
      drink.temperature = getString(R.string.hot_or_cold)
      drink.image = R.drawable.chai_latte
      drink.brewDuration = 3
      drink.strength = getString(R.string.weak)
      drink.difficulty = getString(R.string.easy)
      realm.insertOrUpdate(drink)
      
      drink.id = 8
      drink.name = getString(R.string.dirty_chai)
      drink.description = getString(R.string.dirty_chai_description)
      drink.temperature = getString(R.string.hot_or_cold)
      drink.image = R.drawable.chai_latte
      drink.brewDuration = 8
      drink.strength = getString(R.string.weak_regular)
      drink.difficulty = getString(R.string.easy_intermediate)
      realm.insertOrUpdate(drink)
      
      drink.id = 9
      drink.name = getString(R.string.americano)
      drink.description = getString(R.string.americano_description)
      drink.temperature = getString(R.string.hot)
      drink.image = R.drawable.americano
      drink.brewDuration = 3
      drink.strength = getString(R.string.regular_strong)
      drink.difficulty = getString(R.string.easy)
      realm.insertOrUpdate(drink)
      
      drink.id = 10
      drink.name = getString(R.string.cappuccino)
      drink.description = getString(R.string.cappuccino_description)
      drink.temperature = getString(R.string.hot_or_cold)
      drink.image = R.drawable.cappuccino
      drink.brewDuration = 6
      drink.strength = getString(R.string.regular_strong)
      drink.difficulty = getString(R.string.intermediate)
      realm.insertOrUpdate(drink)
      
      drink.id = 11
      drink.name = getString(R.string.black_eye)
      drink.description = getString(R.string.black_eye_description)
      drink.temperature = getString(R.string.hot)
      drink.image = R.drawable.placeholder
      drink.brewDuration = 5
      drink.strength = getString(R.string.strong)
      drink.difficulty = getString(R.string.intermediate)
      realm.insertOrUpdate(drink)
      
      drink.id = 12
      drink.name = getString(R.string.red_eye)
      drink.description = getString(R.string.red_eye_description)
      drink.temperature = getString(R.string.hot)
      drink.image = R.drawable.placeholder
      drink.brewDuration = 5
      drink.strength = getString(R.string.strong_potent)
      drink.difficulty = getString(R.string.intermediate)
      realm.insertOrUpdate(drink)
      
      drink.id = 13
      drink.name = getString(R.string.green_eye)
      drink.description = getString(R.string.green_eye_description)
      drink.temperature = getString(R.string.hot)
      drink.image = R.drawable.placeholder
      drink.brewDuration = 5
      drink.strength = getString(R.string.potent)
      drink.difficulty = getString(R.string.intermediate)
      realm.insertOrUpdate(drink)
    }
  }
  
}