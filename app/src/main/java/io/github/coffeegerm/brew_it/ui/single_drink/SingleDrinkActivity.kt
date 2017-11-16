package io.github.coffeegerm.brew_it.ui.single_drink

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.github.coffeegerm.brew_it.BrewItApplication.Companion.syringe
import io.github.coffeegerm.brew_it.Constants
import io.github.coffeegerm.brew_it.R
import io.github.coffeegerm.brew_it.data.Drink
import io.github.coffeegerm.brew_it.data.DrinksRepository
import kotlinx.android.synthetic.main.activity_single_drink.*
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by dYarzebinski on 10/28/2017.
 * TODO: Add class comment header
 */
class SingleDrinkActivity : AppCompatActivity() {

    val TAG = SingleDrinkActivity::class.java.simpleName

    @Inject
    @field:Named("drinksRepository") lateinit var drinksRepository: DrinksRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_drink)
        syringe.inject(this)
        val drinkId = intent.getIntExtra(Constants.DRINK_ID_PASSED, 0)
        val drinkMade = drinksRepository.getSingleDrink(drinkId)
        setupToolbar(drinkMade)
    }

    private fun setupToolbar(drinkMade: Drink) {
        collapsing_toolbar.title = drinkMade.name
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}