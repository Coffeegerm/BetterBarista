package io.github.coffeegerm.brew_it.dagger

import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import io.github.coffeegerm.brew_it.BrewItApplication
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
    @Named("context")
    fun provideApplicationContext(): Context {
        return app.applicationContext
    }

    @Provides
    @Singleton
    @Named("resources")
    fun provideActivityResources(app: Context): Resources {
        return app.resources
    }

    @Provides
    @Named("drinksRepository")
    fun provideDrinksRepository(): DrinksRepository {
        return DrinksRepository()
    }
}