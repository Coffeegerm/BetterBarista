package io.github.coffeegerm.brew_it.dagger

import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import io.github.coffeegerm.brew_it.BrewItApplication
import io.github.coffeegerm.brew_it.data.DrinksRepository
import io.github.coffeegerm.brew_it.utilities.Utilities
import javax.inject.Singleton

/**
 * Dagger AppModule that provides items for injection
 */

@Module
class AppModule(brewItApplication: BrewItApplication) {
    var app: BrewItApplication = brewItApplication

    @Provides
    @Singleton
    fun provideApplicationContext(): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideActivityResources(app: Context): Resources = app.resources

    @Provides
    @Singleton
    fun provideDrinksRepository(): DrinksRepository = DrinksRepository()

    @Provides
    @Singleton
    fun provideUtilities(): Utilities = Utilities()
}