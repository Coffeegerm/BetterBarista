package io.github.coffeegerm.brew_it.ui.main

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.github.coffeegerm.brew_it.BrewItApplication.Companion.syringe
import io.github.coffeegerm.brew_it.R
import io.github.coffeegerm.brew_it.data.Drink
import io.github.coffeegerm.brew_it.ui.drinks.DrinksFragment
import io.github.coffeegerm.brew_it.ui.more.MoreFragment
import io.github.coffeegerm.brew_it.ui.timer.TimerFragment
import io.github.coffeegerm.brew_it.utilities.Constants.SINGLE_DRINK_REQUEST_CODE
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

class MainActivity : AppCompatActivity() {

    private val drinksFragment: DrinksFragment by lazy { DrinksFragment() }
    private val timerFragment: TimerFragment by lazy { TimerFragment() }
    private val moreFragment: MoreFragment by lazy { MoreFragment() }

    private var currentFragment: Fragment? = null

    private lateinit var realm: Realm

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_drinks -> {
                showFragment(drinksFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_timer -> {
                showFragment(timerFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_more -> {
                showFragment(moreFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        syringe.inject(this)
        initRealmData()

        showFragment(drinksFragment)
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == SINGLE_DRINK_REQUEST_CODE) {
            showFragment(timerFragment)
        }
    }

    private fun showFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        fragmentTransaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)

        when (fragment.isAdded) {
            true -> fragmentTransaction.show(fragment)
            false -> fragmentTransaction.add(R.id.fragment_container, fragment)
        }

        //old fragment
        currentFragment?.let {
            fragmentTransaction.hide(it)
        }

        fragmentTransaction.commit()

        //set new current fragment
        currentFragment = fragment
    }

    private fun initRealmData() {
        realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            realm.deleteAll()
            Timber.d("All realm entries deleted")
            val drink = Drink()

            drink.id = 1
            drink.name = getString(R.string.coffee)
            drink.description = getString(R.string.coffee_description)
            drink.image = R.drawable.coffee
            drink.brewDuration = 6
            drink.strength = getString(R.string.regular)
            drink.difficulty = getString(R.string.easy)
            realm.insertOrUpdate(drink)

            drink.id = 2
            drink.name = getString(R.string.pour_over)
            drink.description = getString(R.string.pour_over_description)
            drink.brewDuration = 10
            drink.image = R.drawable.pour_over
            drink.strength = getString(R.string.regular_strong)
            drink.difficulty = getString(R.string.intermediate)
            realm.insertOrUpdate(drink)

            drink.id = 3
            drink.name = getString(R.string.french_press)
            drink.description = getString(R.string.french_press_description)
            drink.image = R.drawable.french_press
            drink.brewDuration = 10
            drink.strength = getString(R.string.regular)
            drink.difficulty = getString(R.string.intermediate)
            realm.insertOrUpdate(drink)

            drink.id = 4
            drink.name = getString(R.string.aeropress)
            drink.description = getString(R.string.aeropress_description)
            drink.image = R.drawable.aeropress
            drink.brewDuration = 12
            drink.strength = getString(R.string.strong)
            drink.difficulty = getString(R.string.difficult)
            realm.insertOrUpdate(drink)

            drink.id = 5
            drink.name = getString(R.string.iced_coffee)
            drink.description = getString(R.string.iced_coffee_description)
            drink.image = R.drawable.iced_coffee
            drink.brewDuration = 1440
            drink.strength = getString(R.string.regular_strong)
            drink.difficulty = getString(R.string.easy)
            realm.insertOrUpdate(drink)

            Timber.d("Realm entries created")
        }
    }
}