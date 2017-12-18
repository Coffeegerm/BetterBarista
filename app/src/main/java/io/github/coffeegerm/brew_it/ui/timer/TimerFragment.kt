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

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import io.github.coffeegerm.brew_it.BetterBaristaApp.Companion.syringe
import io.github.coffeegerm.brew_it.R
import io.github.coffeegerm.brew_it.data.Drink
import io.github.coffeegerm.brew_it.data.DrinksRepository
import kotlinx.android.synthetic.main.fragment_timer.*
import javax.inject.Inject

class TimerFragment : Fragment(), AdapterView.OnItemSelectedListener {
  
  @Inject lateinit var drinksRepository: DrinksRepository
  @Inject lateinit var timerViewModel: TimerViewModel
  
  private var isButtonPressed: Boolean = false
  private lateinit var drinksList: ArrayList<Drink>
  private var drinksListNames = ArrayList<String>()
  
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_timer, container, false)
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    syringe.inject(this)
    
    timerViewModel = ViewModelProviders.of(this).get(TimerViewModel::class.java)
    subscribe()
    
    drinksList = drinksRepository.getAllDrinks()
    drinksList.mapTo(drinksListNames) { it.name }
    val spinnerAdapter = ArrayAdapter(activity, android.R.layout.simple_spinner_dropdown_item, drinksListNames)
    spinner.adapter = spinnerAdapter
    spinner.onItemSelectedListener = this
    
    reset_timer.setOnClickListener { resetTimer() }
    
    timer_button.setOnClickListener({
      timer_button.text = if (isButtonPressed) getString(R.string.start) else getString(R.string.pause)
      when (timer_button.text) {
        getString(R.string.pause) -> startTimer()
        getString(R.string.start) -> pauseTimer()
      }
      isButtonPressed = !isButtonPressed
    })
  }
  
  override fun onNothingSelected(p0: AdapterView<*>) {}
  
  override fun onItemSelected(parent: AdapterView<*>, p1: View, position: Int, p3: Long) {
    when (parent.getItemAtPosition(position)) {
      getString(R.string.coffee) -> setDrinkTimerText(drinkResId = R.string.coffee)
      getString(R.string.pour_over) -> setDrinkTimerText(drinkResId = R.string.pour_over)
      getString(R.string.aeropress) -> setDrinkTimerText(drinkResId = R.string.aeropress)
      getString(R.string.french_press) -> setDrinkTimerText(drinkResId = R.string.french_press)
      getString(R.string.cold_brew) -> setDrinkTimerText(drinkResId = R.string.cold_brew)
    }
  }
  
  private fun subscribe() {
    // Observes the changes to remaining time string and set it to the timer.
    val remainingTime = Observer<String> { remainingTime -> timer_drink_time.text = remainingTime }
    timerViewModel.remainingTime.observe(this, remainingTime)
  
    // Observes what drink is chosen and set the text accordingly
    val drinkTimerText = Observer<String> { drinkTimerText -> timer_drink_time.text = drinkTimerText }
    timerViewModel.drinkTimerText.observe(this, drinkTimerText)
  
    // Observes what percentage is left in the timer and presents it to the user
    val percentageLeft = Observer<Int> { percentageLeft -> percentageLeft?.let { circularView.setPercentage(it) } }
    timerViewModel.percentRemaining.observe(this, percentageLeft)
  }
  
  private fun setDrinkTimerText(drinkResId: Int) = timerViewModel.setDrink(drinkResId)
  
  private fun startTimer() {
    reset_timer.visibility = View.VISIBLE
    timerViewModel.startTimer()
  }
  
  private fun pauseTimer() {
    reset_timer.visibility = View.GONE
    timerViewModel.pauseTimer()
  }
  
  private fun resetTimer() {
    circularView.setPercentage(100)
    reset_timer.visibility = View.GONE
    timer_button.setText(R.string.start)
    timerViewModel.resetTimer()
  }
}