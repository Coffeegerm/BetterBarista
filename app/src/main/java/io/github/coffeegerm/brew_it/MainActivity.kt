package io.github.coffeegerm.brew_it

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import io.github.coffeegerm.brew_it.data.Coffee
import io.github.coffeegerm.brew_it.data.Drink
import io.github.coffeegerm.brew_it.data.Mocha
import kotlinx.android.synthetic.main.activity_main.*

var drinksList: ArrayList<Drink> = ArrayList()

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
        drinksList = getDrinksList()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, DrinksFragment()).commit()
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }

    fun getDrinksList(): ArrayList<Drink> {
        drinksList.add(Mocha())
        drinksList.add(Coffee())
        return drinksList
    }

}
