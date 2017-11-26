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

package io.github.coffeegerm.brew_it.ui.timer

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import io.github.coffeegerm.brew_it.BrewItApplication.Companion.syringe
import io.github.coffeegerm.brew_it.R
import io.github.coffeegerm.brew_it.data.Drink
import io.github.coffeegerm.brew_it.data.DrinksRepository
import io.github.coffeegerm.brew_it.utilities.Utilities
import kotlinx.android.synthetic.main.fragment_timer.*
import org.jetbrains.anko.sdk25.coroutines.onItemSelectedListener
import org.jetbrains.anko.support.v4.toast
import timber.log.Timber
import javax.inject.Inject

/**
 * Fragment for handling Timer Activity
 */
class TimerFragment : Fragment() {

    private var isButtonPressed: Boolean = false
    private var totalTime: Long = 0

    @Inject
    lateinit var drinksRepository: DrinksRepository
    @Inject
    lateinit var utilities: Utilities
    private lateinit var drinksList: ArrayList<Drink>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_timer, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        syringe.inject(this)
        drinksList = drinksRepository.getAllDrinks()
        val drinksListNames = ArrayList<String>()
        drinksList.mapTo(drinksListNames) { it.name }
        val adapter = ArrayAdapter(activity, android.R.layout.simple_spinner_dropdown_item, drinksListNames)
        spinner.adapter = adapter
        spinner.onItemSelectedListener {
            onItemSelected { parent, _, position, _ ->
                when (parent?.getItemAtPosition(position)) {
                    getString(R.string.coffee) -> setDrinkTimerText(drinkResId = R.string.coffee)
                    getString(R.string.pour_over) -> setDrinkTimerText(drinkResId = R.string.pour_over)
                    getString(R.string.aeropress) -> setDrinkTimerText(drinkResId = R.string.aeropress)
                    getString(R.string.french_press) -> setDrinkTimerText(drinkResId = R.string.french_press)
                    getString(R.string.cold_brew) -> setDrinkTimerText(drinkResId = R.string.cold_brew)
                }
            }
        }

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
            when (timer_button.text) {
                getString(R.string.stop) -> startTimer()
                getString(R.string.start) -> stopTimer()
            }
            Timber.d(isButtonPressed.toString())
            isButtonPressed = !isButtonPressed
        })
    }

    private fun setDrinkTimerText(drinkResId: Int) {
        val drink = drinksRepository.getSingleDrinkByName(getString(drinkResId))

        drink?.let { setTotalTime(it) }

        drink?.let {
            timer_drink_time.text = utilities.convertBrewDurationForTimer(drink.brewDuration)
        } ?: showDrinkTimerTextError()
    }

    private fun showDrinkTimerTextError() =
            toast(getString(R.string.timer_error))


    private fun setTotalTime(drinkMade: Drink) {
        totalTime = (drinkMade.brewDuration * 60).toLong()
        Timber.i(totalTime.toString())
        circularView.setPercentage(100)
    }

    private fun startTimer() {
        Timber.i("Timer started")
    }

    private fun stopTimer() {
        Timber.i("Timer stopped")
    }
}