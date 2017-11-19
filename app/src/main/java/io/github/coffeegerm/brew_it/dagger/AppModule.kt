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


    //what are you using named parameters for? i only ever see one type of implementation per dependency
    //@named is really good for when you have an interface and have multiple classes that implement that interface

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
    //TODO: REFACTOR THIS
    // if you keep a realm instance at the application scope it will be garbage collected in the background, and sometimes you will get null pointer exceptions after not using the app for awhile.
    // basically when you come back to the app, it will crash immediately
    // i know this because i have made the mistake in the past.
    // you need the realm instance to be tied to the activity lifecycle somehow, and not the application lifecycle



    @Provides
    @Singleton
    @Named("drinksRepository")
    fun provideDrinksRepository(): DrinksRepository = DrinksRepository()

    @Provides
    @Singleton
    @Named("utilities")
    fun provideUtilities(): Utilities = Utilities()
}