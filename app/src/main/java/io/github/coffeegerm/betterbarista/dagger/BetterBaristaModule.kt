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

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import io.github.coffeegerm.betterbarista.BetterBarista
import io.github.coffeegerm.betterbarista.data.CoffeeBar
import io.github.coffeegerm.betterbarista.utilities.Constants.SHARED_PREFERENCES
import javax.inject.Singleton

@Module
class BetterBaristaModule(betterBarista: BetterBarista) {
  
  var app = betterBarista
  
  @Provides
  @Singleton
  fun providesApplication(): Application = app
  
  @Provides
  @Singleton
  fun provideApplicationContext(): Context = app.applicationContext
  
  @Provides
  @Singleton
  fun provideActivityResources(): Resources = app.resources
  
  @Provides
  @Singleton
  fun providesSharedPreferences(): SharedPreferences = app.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)
  
  @Provides
  fun providesCoffeeBar(): CoffeeBar = CoffeeBar(app.resources)
  
}