package io.github.coffeegerm.brew_it.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.github.coffeegerm.brew_it.BrewItApplication
import io.github.coffeegerm.brew_it.R
import io.github.coffeegerm.brew_it.data.Drink
import io.github.coffeegerm.brew_it.ui.drinks.DrinksFragment
import io.github.coffeegerm.brew_it.ui.more.MoreFragment
import io.github.coffeegerm.brew_it.ui.timer.TimerFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import javax.inject.Inject
import javax.inject.Named

class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.java.name

    @field:[Inject Named("something")]
    lateinit var something: String

    @field:[Inject Named("DrinksList")]
    lateinit var drinksList: ArrayList<Drink>

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
        BrewItApplication.graph.inject(this)
        Log.d(TAG, "$something has been received successfully")
        Log.d(TAG, "$drinksList has been received successfully")
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
                brewDuration = 6)
    }

    fun getMocha(): Drink {
        return Drink(name = getString(R.string.mocha),
                brewDuration = 8)
    }
}
