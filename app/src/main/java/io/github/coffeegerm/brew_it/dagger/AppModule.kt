package io.github.coffeegerm.brew_it.dagger

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import io.github.coffeegerm.brew_it.BrewItApplication
import io.github.coffeegerm.brew_it.Constants.SHARED_PREFERENCES
import io.github.coffeegerm.brew_it.data.Drink
import io.github.coffeegerm.brew_it.data.DrinksRepository
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by david_yarz on 11/1/17.
 *
 * Dagger AppModule that provides items for injection
 */

@Module
class AppModule(brewItApplication: BrewItApplication) {
    var app: BrewItApplication = brewItApplication

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return app.applicationContext
    }

    @Provides
    @Singleton
    fun provideActivityResources(app: Context): Resources {
        return app.resources
    }

    @Provides
    fun provideSharedPreferences(app: Context): SharedPreferences {
        return app.getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideDrinksRepository(): DrinksRepository {
        return DrinksRepository()
    }
}