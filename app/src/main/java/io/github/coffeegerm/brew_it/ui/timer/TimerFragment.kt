package io.github.coffeegerm.brew_it.ui.timer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import io.github.coffeegerm.brew_it.BrewItApplication.Companion.syringe
import io.github.coffeegerm.brew_it.R
import io.github.coffeegerm.brew_it.data.Drink
import io.github.coffeegerm.brew_it.data.DrinksRepository
import io.github.coffeegerm.brew_it.utilities.Utilities
import kotlinx.android.synthetic.main.fragment_timer.*
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by david_yarz on 10/24/17.
 *
 * Fragment for handling Timer Activity
 */
class TimerFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private val TAG: String = "TimerFragment"
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
            Log.d(TAG, isButtonPressed.toString())
            if (isButtonPressed) isButtonPressed = false
            else if (!isButtonPressed) isButtonPressed = true
        })
    }

    override fun onNothingSelected(p0: AdapterView<*>) {

    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
        when (parent.getItemAtPosition(position)) {
            getString(R.string.coffee) -> timer_drink_time.text = utilities.convertBrewDurationForTimer(drinksRepository.getSingleDrinkByName(getString(R.string.coffee)).brewDuration)
            getString(R.string.pour_over) -> timer_drink_time.text = utilities.convertBrewDurationForTimer(drinksRepository.getSingleDrinkByName(getString(R.string.pour_over)).brewDuration)
            getString(R.string.aeropress) -> timer_drink_time.text = utilities.convertBrewDurationForTimer(drinksRepository.getSingleDrinkByName(getString(R.string.aeropress)).brewDuration)
            getString(R.string.french_press) -> timer_drink_time.text = utilities.convertBrewDurationForTimer(drinksRepository.getSingleDrinkByName(getString(R.string.french_press)).brewDuration)
            getString(R.string.iced_coffee) -> timer_drink_time.text = utilities.convertBrewDurationForTimer(drinksRepository.getSingleDrinkByName(getString(R.string.iced_coffee)).brewDuration)
        }
    }
}