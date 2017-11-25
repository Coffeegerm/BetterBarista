package io.github.coffeegerm.brew_it.dagger

import dagger.Component
import io.github.coffeegerm.brew_it.ui.drinks.DrinksFragment
import io.github.coffeegerm.brew_it.ui.main.MainActivity
import io.github.coffeegerm.brew_it.ui.single_drink.SingleDrinkActivity
import io.github.coffeegerm.brew_it.ui.timer.TimerFragment
import javax.inject.Singleton

/**
 * AppComponent used for DI with Dagger2
 */

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(drinksFragment: DrinksFragment)
    fun inject(timerFragment: TimerFragment)
    fun inject(singleDrinkActivity: SingleDrinkActivity)
}