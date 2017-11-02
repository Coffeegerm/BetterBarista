package io.github.coffeegerm.brew_it.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import io.github.coffeegerm.brew_it.R
import io.github.coffeegerm.brew_it.data.Drink
import io.github.coffeegerm.brew_it.ui.drinks.DrinksFragment
import io.github.coffeegerm.brew_it.ui.more.MoreFragment
import io.github.coffeegerm.brew_it.ui.timer.TimerFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_drinks -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, DrinksFragment())
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_timer -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, TimerFragment())
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_more -> {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, MoreFragment())
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                        .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //drinksList = getDrinksList()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, DrinksFragment()).commit()
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

//    fun getDrinksList(): ArrayList<Drink> {
//        drinksList.add(getCoffee())
//        drinksList.add(getMocha())
//        return drinksList
//    }

    fun getCoffee(): Drink {
        return Drink(name = getString(R.string.coffee),
                description = getString(R.string.coffee_description),
                servingSize = 16,
                duration = 6,
                instructions = Arrays.asList(*resources.getStringArray(R.array.instructions_coffee)),
                image = null)
    }

    fun getMocha(): Drink {
        return Drink(name = getString(R.string.mocha),
                description = getString(R.string.mocha_description),
                servingSize = 16,
                duration = 8,
                instructions = Arrays.asList(*resources.getStringArray(R.array.instructions_coffee)),
                image = null)
    }
}
