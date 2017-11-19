package io.github.coffeegerm.brew_it.ui.single_drink

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.github.coffeegerm.brew_it.BrewItApplication.Companion.syringe
import io.github.coffeegerm.brew_it.R
import io.github.coffeegerm.brew_it.data.Drink
import io.github.coffeegerm.brew_it.data.DrinksRepository
import io.github.coffeegerm.brew_it.utilities.Constants
import io.github.coffeegerm.brew_it.utilities.Constants.SINGLE_DRINK_REQUEST_CODE
import io.github.coffeegerm.brew_it.utilities.Utilities
import kotlinx.android.synthetic.main.activity_single_drink.*
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by dYarzebinski on 10/28/2017.
 * TODO: Add class comment header
 */
class SingleDrinkActivity : AppCompatActivity() {

    @Inject
    lateinit var drinksRepository: DrinksRepository

    @Inject
    lateinit var utilities: Utilities

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_drink)
        syringe.inject(this)
        val drinkId = intent.getIntExtra(Constants.DRINK_ID_PASSED, 0)
        val drinkMade = drinksRepository.getSingleDrinkById(drinkId)

        drinkMade?.let {
            setupToolbar(drinkMade)
            drink_description.text = drinkMade.description
            drink_duration.text = utilities.convertBrewDuration(drinkMade.brewDuration)
            drink_strength.text = drinkMade.strength
            drink_difficulty.text = drinkMade.difficulty
        }

        start_timer_fab.setOnClickListener({
            val intent = Intent(applicationContext, SingleDrinkActivity::class.java)
            startActivityForResult(intent, SINGLE_DRINK_REQUEST_CODE)
            finish()
        })
    }

    private fun setupToolbar(drinkMade: Drink) {
        single_drink_image.setImageResource(drinkMade.image)
        collapsing_toolbar.title = drinkMade.name
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}