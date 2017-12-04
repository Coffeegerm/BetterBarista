/*
 * Copyright 2017 Coffee and Cream Studios
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.coffeegerm.brew_it.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import io.github.coffeegerm.brew_it.BrewItApplication.Companion.syringe
import io.github.coffeegerm.brew_it.R
import io.github.coffeegerm.brew_it.data.Drink
import io.github.coffeegerm.brew_it.ui.drinks.DrinksFragment
import io.github.coffeegerm.brew_it.ui.more.MoreFragment
import io.github.coffeegerm.brew_it.ui.timer.TimerFragment
import io.github.coffeegerm.brew_it.utilities.Constants
import io.realm.Realm
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val drinksFragment: DrinksFragment by lazy { DrinksFragment() }
    private val timerFragment: TimerFragment by lazy { TimerFragment() }
    private val moreFragment: MoreFragment by lazy { MoreFragment() }

    private var currentFragment: Fragment? = null

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
        if (requestCode == Constants.SINGLE_DRINK_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                navigation.selectedItemId = R.id.navigation_timer
            }
        }
    }

    private fun showFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()

        when (fragment.isAdded) {
            true -> fragmentTransaction.show(fragment)
            false -> fragmentTransaction.add(R.id.fragment_container, fragment)
        }

        //old fragment
        currentFragment?.let {
            fragmentTransaction.hide(it)
        }

        fragmentTransaction.commitAllowingStateLoss()

        //set new current fragment
        currentFragment = fragment
    }

    private fun initRealmData() {
        val realm = Realm.getDefaultInstance()
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
            drink.name = getString(R.string.cold_brew)
            drink.description = getString(R.string.cold_brew_description)
            drink.image = R.drawable.iced_coffee
            drink.brewDuration = 1440
            drink.strength = getString(R.string.regular_strong)
            drink.difficulty = getString(R.string.easy)
            realm.insertOrUpdate(drink)

            Timber.d("Realm entries created")
        }
    }
}