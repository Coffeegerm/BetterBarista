package io.github.coffeegerm.brew_it.dagger

import android.content.Context
import dagger.Module
import dagger.Provides
import io.github.coffeegerm.brew_it.BrewItApplication
import io.github.coffeegerm.brew_it.data.DrinksRepository
import javax.inject.Singleton

/**
 * Created by david_yarz on 11/1/17.
 */

@Module
class AppModule(brewItApplication: BrewItApplication) {
    var app: BrewItApplication = brewItApplication

    @Provides
    fun provideApplicationContext(): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideDrinksList() = DrinksRepository()
}