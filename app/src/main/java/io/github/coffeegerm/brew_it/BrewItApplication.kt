package io.github.coffeegerm.brew_it

import android.app.Application
import io.github.coffeegerm.brew_it.dagger.AppComponent
import io.github.coffeegerm.brew_it.dagger.AppModule
import io.github.coffeegerm.brew_it.dagger.DaggerAppComponent
import io.github.coffeegerm.brew_it.data.Drink
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by dYarzebinski on 10/23/2017.
 * TODO: Add class comment header
 */

class BrewItApplication : Application() {

    companion object {
        @JvmStatic lateinit var syringe: AppComponent
    }

    private fun createRealmConfiguration() {
        val realmConfig: RealmConfiguration = RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(realmConfig)
    }

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        createRealmConfiguration()

        val realm: Realm = Realm.getDefaultInstance()
        val drink = realm.createObject(Drink::class.java)

        drink.id = 1
        drink.name = "Coffee"
        drink.brewDuration = 6
        drink.strength = "Medium"
        drink.difficulty = "Easy"
        realm.insertOrUpdate(drink)

        syringe = DaggerAppComponent.builder().appModule(AppModule(this)).build()
        syringe.inject(this)
    }
}