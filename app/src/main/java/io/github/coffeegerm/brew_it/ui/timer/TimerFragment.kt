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
import android.os.CountDownTimer
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
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class TimerFragment : Fragment() {
  
  @Inject
  lateinit var drinksRepository: DrinksRepository
  @Inject
  lateinit var utilities: Utilities
  
  private var totalTimeInMillis: Long = 0
  private var isButtonPressed: Boolean = false
  private lateinit var countDownTimer: CountDownTimer
  private lateinit var drinksList: ArrayList<Drink>
  private var currentTimerMillisTillFinished: Long = 0
  
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
    
    reset_timer.setOnClickListener { resetTimer() }
    
    timer_button.setOnClickListener({
      timer_button.text = if (isButtonPressed) getString(R.string.start) else getString(R.string.pause)
      when (timer_button.text) {
        getString(R.string.pause) -> startTimer()
        getString(R.string.start) -> pauseTimer(currentTimerMillisTillFinished)
      }
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
    totalTimeInMillis = (drinkMade.brewDuration * 60 * 1000).toLong()
    createCountdownTimer(totalTimeInMillis)
    circularView.setPercentage(100)
  }
  
  private fun createCountdownTimer(totalTimeInMillis: Long) {
    timer_drink_time.text = convertMillisToMinutes(totalTimeInMillis)
    countDownTimer = object : CountDownTimer(totalTimeInMillis, 1000) {
      override fun onFinish() {
        Timber.i("Timer finished")
        timer_drink_time.text = getString(R.string.final_time_for_timer)
      }
      
      override fun onTick(millisUntilFinished: Long) {
        currentTimerMillisTillFinished = millisUntilFinished
        timer_drink_time.text = convertMillisToMinutes(millisUntilFinished)
        Timber.i(millisUntilFinished.toString())
      }
    }
  }
  
  private fun startTimer() {
    reset_timer.visibility = View.VISIBLE
    countDownTimer.start()
    Timber.i("Timer started")
  }
  
  private fun pauseTimer(currentTimeUntilFinished: Long) {
    reset_timer.visibility = View.GONE
    countDownTimer.cancel()
    createCountdownTimer(currentTimeUntilFinished)
    Timber.i("Timer paused")
  }
  
  private fun resetTimer() {
    circularView.setPercentage(100)
    countDownTimer.cancel()
    timer_button.text = getString(R.string.start)
    Timber.i("Timer reset")
  }
  
  fun getPercentLeft(timeLeftInMillis: Long): Int {
    return (timeLeftInMillis / 60000 * 100).toInt()
  }
  
  fun convertMillisToMinutes(providedMillis: Long): String {
    val minutes = (providedMillis / 1000) / 60
    val seconds = (providedMillis / 1000) % 60
    return minutes.toString() + ":" + seconds.toString()
  }
}