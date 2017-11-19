package io.github.coffeegerm.brew_it

import android.app.Application
import android.util.Log
import io.github.coffeegerm.brew_it.dagger.AppComponent
import io.github.coffeegerm.brew_it.dagger.AppModule
import io.github.coffeegerm.brew_it.dagger.DaggerAppComponent
import io.github.coffeegerm.brew_it.data.Drink
import io.realm.Realm
import io.realm.RealmConfiguration
import timber.log.Timber
import timber.log.Timber.DebugTree



/**
 * Created by dYarzebinski on 10/23/2017.
 * TODO: Add class comment header
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