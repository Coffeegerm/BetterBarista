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

package io.github.coffeegerm.brew_it

import android.app.Application
import io.github.coffeegerm.brew_it.dagger.AppComponent
import io.github.coffeegerm.brew_it.dagger.AppModule
import io.realm.Realm
import io.realm.RealmConfiguration
import timber.log.Timber
import timber.log.Timber.DebugTree


/**
 * Application class that builds dagger component,
 * inits realm and plants tree for logs
 */

class BrewItApplication : Application() {
  
  companion object {
    lateinit var syringe: AppComponent
  }
  
  override fun onCreate() {
    super.onCreate()
    initRealm()
    syringe = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    
    if (BuildConfig.DEBUG) {
      Timber.plant(DebugTree())
    } else {
      //custom production tree. set up so logs are sent to crashlytics along with the stack trace or something
    }
  }
  
  private fun initRealm() {
    Realm.init(this)
    val realmConfig: RealmConfiguration = RealmConfiguration
          .Builder()
          .deleteRealmIfMigrationNeeded()
          .build()
    Realm.setDefaultConfiguration(realmConfig)
  }
}