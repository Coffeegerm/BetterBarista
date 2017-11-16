package io.github.coffeegerm.brew_it.ui.single_drink

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import io.github.coffeegerm.brew_it.BrewItApplication.Companion.syringe
import io.github.coffeegerm.brew_it.Constants
import io.github.coffeegerm.brew_it.Constants.SINGLE_DRINK_REQUEST_CODE
import io.github.coffeegerm.brew_it.R
import io.github.coffeegerm.brew_it.data.Drink
import io.github.coffeegerm.brew_it.data.DrinksRepository
import io.github.coffeegerm.brew_it.ui.drinks.DrinksFragment
import io.github.coffeegerm.brew_it.ui.main.MainActivity
import io.github.coffeegerm.brew_it.ui.timer.TimerFragment
import kotlinx.android.synthetic.main.activity_single_drink.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
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
        drink_description.text = drinkMade.description
        drink_duration.text = drinkMade.brewDuration.toString()
        drink_strength.text = drinkMade.strength
        drink_difficulty.text = drinkMade.difficulty

        start_timer_fab.setOnClickListener({
            val intent = Intent(applicationContext, SingleDrinkActivity::class.java)
            startActivityForResult(intent, SINGLE_DRINK_REQUEST_CODE)
            finish()
        })
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