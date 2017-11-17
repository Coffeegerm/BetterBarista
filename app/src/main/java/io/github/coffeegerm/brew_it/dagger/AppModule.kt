package io.github.coffeegerm.brew_it.dagger

import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import io.github.coffeegerm.brew_it.BrewItApplication
import io.github.coffeegerm.brew_it.data.DrinksRepository
import io.github.coffeegerm.brew_it.utilities.Utilities
import io.realm.Realm
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
    fun provideApplicationContext(): Context = app.applicationContext

    @Provides
    @Singleton
    @Named("resources")
    fun provideActivityResources(app: Context): Resources = app.resources

    @Provides
    @Singleton
    @Named("realm")
    fun provideRealm(): Realm = Realm.getDefaultInstance()

    @Provides
    @Singleton
    @Named("drinksRepository")
    fun provideDrinksRepository(): DrinksRepository = DrinksRepository()

    @Provides
    @Singleton
    @Named("utilities")
    fun provideUtilities(): Utilities = Utilities()
}