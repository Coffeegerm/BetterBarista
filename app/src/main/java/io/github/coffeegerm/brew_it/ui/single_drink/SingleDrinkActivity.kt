package io.github.coffeegerm.brew_it.ui.single_drink

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import io.github.coffeegerm.brew_it.BrewItApplication.Companion.syringe
import io.github.coffeegerm.brew_it.R
import io.github.coffeegerm.brew_it.data.Drink
import io.github.coffeegerm.brew_it.data.DrinksRepository
import io.github.coffeegerm.brew_it.utilities.Constants
import io.github.coffeegerm.brew_it.utilities.Constants.SINGLE_DRINK_REQUEST_CODE
import io.github.coffeegerm.brew_it.utilities.Utilities
import kotlinx.android.synthetic.main.activity_single_drink.*
import javax.inject.Inject


/**
 * Created by dYarzebinski on 10/28/2017.
 * TODO: Add class comment header
 */

class SingleDrinkActivity : AppCompatActivity() {

    @Inject
    lateinit var drinksRepository: DrinksRepository

    @Inject
    lateinit var utilities: Utilities

    private lateinit var singleDrinkAdapter: SingleDrinkAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_drink)
        syringe.inject(this)
        singleDrinkAdapter = SingleDrinkAdapter()
        val drinkId = intent.getIntExtra(Constants.DRINK_ID_PASSED, 0)
        val drinkMade = drinksRepository.getSingleDrinkById(drinkId)

        drinkMade?.let {
            setupToolbar(drinkMade)
            drink_description.text = drinkMade.description
            drink_duration.text = utilities.convertBrewDuration(drinkMade.brewDuration)
            drink_strength.text = drinkMade.strength
            drink_difficulty.text = drinkMade.difficulty
        }

        single_drink_recycler_view.layoutManager = LinearLayoutManager(applicationContext)
        single_drink_recycler_view.adapter = singleDrinkAdapter
        if (drinkMade != null) {
            getInstructions(drinkMade)
        }

        start_timer_fab.setOnClickListener({
            val intent = Intent(applicationContext, SingleDrinkActivity::class.java)
            startActivityForResult(intent, SINGLE_DRINK_REQUEST_CODE)
            finish()
        })
    }

    private fun setupToolbar(drinkMade: Drink) {
        single_drink_image.setImageResource(drinkMade.image)
        collapsing_toolbar.title = " "
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        single_drink_app_bar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            internal var isShow = false
            internal var scrollRange = -1

            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsing_toolbar.title = drinkMade.name
                    isShow = true
                } else if (isShow) {
                    collapsing_toolbar.title = " "
                    isShow = false
                }
            }
        })
    }

    private fun getInstructions(drinkMade: Drink) {
        when (drinkMade.name) {
            getString(R.string.coffee) -> formatInstructionList(instructionsFromResources = resources.getStringArray(R.array.instructions_coffee))
            getString(R.string.french_press) -> formatInstructionList(instructionsFromResources = resources.getStringArray(R.array.instructions_french_press))
            getString(R.string.pour_over) -> formatInstructionList(instructionsFromResources = resources.getStringArray(R.array.instructions_pour_over))
            getString(R.string.cold_brew) -> formatInstructionList(instructionsFromResources = resources.getStringArray(R.array.instructions_cold_brew_coffee))
        }
    }

    private fun formatInstructionList(instructionsFromResources: Array<out String>) {
        val instructionsToBeUsed: ArrayList<String> = ArrayList()
        (0 until instructionsFromResources.size).mapTo(instructionsToBeUsed) { "${it.plus(1)}. " + instructionsFromResources[it] }
        singleDrinkAdapter.setInstructions(instructionsToBeUsed)
        singleDrinkAdapter.notifyDataSetChanged()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}