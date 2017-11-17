package io.github.coffeegerm.brew_it.ui.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.github.coffeegerm.brew_it.BrewItApplication.Companion.syringe
import io.github.coffeegerm.brew_it.utilities.Constants.SINGLE_DRINK_REQUEST_CODE
import io.github.coffeegerm.brew_it.R
import io.github.coffeegerm.brew_it.data.Drink
import io.github.coffeegerm.brew_it.ui.drinks.DrinksFragment
import io.github.coffeegerm.brew_it.ui.more.MoreFragment
import io.github.coffeegerm.brew_it.ui.timer.TimerFragment
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import javax.inject.Named

class MainActivity : AppCompatActivity() {

    val TAG = MainActivity::class.java.name

    @Inject
    @field:Named("realm") lateinit var realm: Realm

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
        syringe.inject(this)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, DrinksFragment()).commit()
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        initRealmData()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SINGLE_DRINK_REQUEST_CODE) {
            showTimerFragment()
        }
    }

    fun showTimerFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, TimerFragment()).commit()
    }

    private fun initRealmData() {
        realm.executeTransaction {
            realm.deleteAll()
            Log.d(TAG, "All realm entries deleted")
            val drink = Drink()

            drink.id = 1
            drink.name = getString(R.string.coffee)
            drink.description = getString(R.string.coffee_description)
            drink.brewDuration = 6
            drink.strength = getString(R.string.regular)
            drink.difficulty = getString(R.string.easy)
            realm.insertOrUpdate(drink)

            drink.id = 2
            drink.name = getString(R.string.pour_over)
            drink.description = getString(R.string.pour_over_description)
            drink.brewDuration = 10
            drink.strength = getString(R.string.regular_strong)
            drink.difficulty = getString(R.string.intermediate)
            realm.insertOrUpdate(drink)

            drink.id = 3
            drink.name = getString(R.string.french_press)
            drink.description = getString(R.string.french_press_description)
            drink.brewDuration = 10
            drink.strength = getString(R.string.regular)
            drink.difficulty = getString(R.string.intermediate)
            realm.insertOrUpdate(drink)

            drink.id = 4
            drink.name = getString(R.string.aeropress)
            drink.description = getString(R.string.aeropress_description)
            drink.brewDuration = 12
            drink.strength = getString(R.string.strong)
            drink.difficulty = getString(R.string.difficult)
            realm.insertOrUpdate(drink)

            drink.id = 5
            drink.name = getString(R.string.iced_coffee)
            drink.description = getString(R.string.iced_coffee_description)
            drink.brewDuration = 1440
            drink.strength = getString(R.string.regular_strong)
            drink.difficulty = getString(R.string.easy)
            realm.insertOrUpdate(drink)

            Log.d(TAG, "Realm entries created")
        }
    }
}