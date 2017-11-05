package io.github.coffeegerm.brew_it

import android.app.Application
import io.github.coffeegerm.brew_it.dagger.AppComponent
import io.github.coffeegerm.brew_it.dagger.AppModule
import io.github.coffeegerm.brew_it.dagger.DaggerAppComponent
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by dYarzebinski on 10/23/2017.
 * TODO: Add class comment header
 */

class BrewItApplication : Application() {

    private lateinit var appComponent: AppComponent

    fun getAppComponent(): AppComponent {
        return appComponent
    }

    private fun initDagger(brewItApplication: BrewItApplication): AppComponent {
        return DaggerAppComponent
                .builder()
                .appModule(AppModule(brewItApplication))
                .build()
    }

    private fun initRealm() {
        Realm.init(this)
        val realmConfig: RealmConfiguration = RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(realmConfig)
    }

    override fun onCreate() {
        super.onCreate()
        initRealm()
        appComponent = initDagger(this)
    }
}