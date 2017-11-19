package io.github.coffeegerm.brew_it.ui.timer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import io.github.coffeegerm.brew_it.BrewItApplication.Companion.syringe
import io.github.coffeegerm.brew_it.R
import io.github.coffeegerm.brew_it.data.Drink
import io.github.coffeegerm.brew_it.data.DrinksRepository
import io.github.coffeegerm.brew_it.utilities.Utilities
import kotlinx.android.synthetic.main.fragment_timer.*
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by david_yarz on 10/24/17.
 *
 * Fragment for handling Timer Activity
 */
class TimerFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private var isButtonPressed: Boolean = false

    @Inject
    @field:Named("drinksRepository") lateinit var drinksRepository: DrinksRepository
    @Inject
    @field:Named("utilities") lateinit var utilities: Utilities
    private lateinit var drinksList: ArrayList<Drink>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_timer, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        syringe.inject(this)
        drinksList = drinksRepository.getAllDrinks()
        val drinksListNames = ArrayList<String>()
        drinksList.mapTo(drinksListNames) { it.name }
        val adapter = ArrayAdapter(activity, android.R.layout.simple_spinner_dropdown_item, drinksListNames)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this
//        var count = 60
//        val handler = Handler()
//
//        val runnable = object : Runnable {
//            override fun run() {
//                if (count <= 100) {
//                    handler.postDelayed(this, 1000)
//                    circularView.setPercentage(count)
//                    count--
//                }
//            }
//        }
//
//        handler.post(runnable)

        timer_button.setOnClickListener({
            timer_button.text = if (isButtonPressed) getString(R.string.start) else getString(R.string.stop)

            Timber.d(isButtonPressed.toString())
            isButtonPressed = !isButtonPressed
        })
    }

    override fun onNothingSelected(p0: AdapterView<*>) {

    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {

        when (parent.getItemAtPosition(position)) {
            getString(R.string.coffee) -> setDrinkTimerText(drinkResId = R.string.coffee)
            getString(R.string.pour_over) -> setDrinkTimerText(drinkResId = R.string.pour_over)
            getString(R.string.aeropress) -> setDrinkTimerText(drinkResId = R.string.aeropress)
            getString(R.string.french_press) -> setDrinkTimerText(drinkResId = R.string.french_press)
            getString(R.string.iced_coffee) -> setDrinkTimerText(drinkResId = R.string.iced_coffee)
        }
    }

    private fun setDrinkTimerText(drinkResId: Int) {

        val drink = drinksRepository.getSingleDrinkByName(getString(drinkResId))

        drink?.let {
            timer_drink_time.text = utilities.convertBrewDurationForTimer(drink.brewDuration)
        } ?: showDrinkTimerTextError()
    }

    private fun showDrinkTimerTextError() {
        val toast = Toast.makeText(context, "Could not get timer duration for this drink", Toast.LENGTH_SHORT)
        toast.show()
    }
}