package io.github.coffeegerm.brew_it

import android.app.Application
import io.github.coffeegerm.brew_it.dagger.AppComponent
import io.github.coffeegerm.brew_it.dagger.AppModule
import io.github.coffeegerm.brew_it.dagger.DaggerAppComponent

/**
 * Created by dYarzebinski on 10/23/2017.
 * TODO: Add class comment header
 */

class BrewItApplication : Application() {

    private val component: AppComponent by lazy {
        DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }
}