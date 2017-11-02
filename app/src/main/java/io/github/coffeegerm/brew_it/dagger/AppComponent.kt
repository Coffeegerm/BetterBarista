package io.github.coffeegerm.brew_it.dagger

import dagger.Component
import io.github.coffeegerm.brew_it.BrewItApplication
import io.github.coffeegerm.brew_it.data.DrinksRepository
import io.github.coffeegerm.brew_it.ui.main.MainActivity
import javax.inject.Singleton

/**
 * Created by david_yarz on 11/1/17.
 */


@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(brewItApplication: BrewItApplication)
    fun inject(mainActivity: MainActivity)
    fun inject(drinksList: DrinksRepository)
}