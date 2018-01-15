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

package io.github.coffeegerm.betterbarista.ui.children.timer

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import io.github.coffeegerm.betterbarista.R
import kotlinx.android.synthetic.main.fragment_timer.*

class TimerFragment : Fragment(), AdapterView.OnItemSelectedListener {
  
  private lateinit var timerViewModel: TimerViewModel
  
  private var isButtonPressed: Boolean = false
  
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_timer, container, false)
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    timerViewModel = ViewModelProviders.of(this).get(TimerViewModel::class.java)
    timerViewModel.getDrinkNames()
    subscribe()
  
    reset_timer.setOnClickListener { timerViewModel.resetTimer() }
    
    timer_button.setOnClickListener({
      timer_button.text = if (isButtonPressed) getString(R.string.start) else getString(R.string.pause)
      when (timer_button.text) {
        getString(R.string.pause) -> timerViewModel.startTimer()
        getString(R.string.start) -> timerViewModel.pauseTimer()
      }
      isButtonPressed = !isButtonPressed
    })
  }
  
  private fun setupSpinner(drinkListNames: ArrayList<String>) {
    val spinnerAdapter = ArrayAdapter(activity, android.R.layout.simple_spinner_dropdown_item, drinkListNames)
    spinner.adapter = spinnerAdapter
    spinner.onItemSelectedListener = this
  }
  
  override fun onNothingSelected(p0: AdapterView<*>) {}
  
  override fun onItemSelected(parent: AdapterView<*>?, aView: View?, position: Int, aLong: Long) = setDrinkTimerText(position)
  
  private fun subscribe() {
    timerViewModel.isTimerRunning.observe(this, Observer<Boolean> { isTimerRunning -> isTimerRunning?.let { controlStartPause(isTimerRunning) } })
  
    // Observes if the timer is running to control the reset button visibility
    timerViewModel.isTimerRunning.observe(this, Observer<Boolean> { isTimerRunning -> isTimerRunning?.let { resetVisibility(it) } })
  
    // Receives the drink names for the spinner from the ViewModel
    timerViewModel.drinksListNames.observe(this, Observer<ArrayList<String>> { drinkNames -> setupSpinner(drinkNames!!) })
    
    // Observes the changes to remaining time string and set it to the timer.
    timerViewModel.remainingTime.observe(this, Observer<String> { remainingTime -> timer_drink_time.text = remainingTime })
    
    // Observes what drink is chosen and set the text accordingly
    timerViewModel.drinkTimerText.observe(this, Observer<String> { drinkTimerText -> timer_drink_time.text = drinkTimerText })
    
    // Observes what percentage is left in the timer and presents it to the user
    timerViewModel.percentRemaining.observe(this, Observer<Int> { percentageLeft -> percentageLeft?.let { circularView.setPercentage(it) } })
  }
  
  private fun resetVisibility(timerRunning: Boolean) {
    when (timerRunning) {
      true -> reset_timer.visibility = View.VISIBLE
      false -> reset_timer.visibility = View.GONE
    }
  }
  
  private fun controlStartPause(timerRunning: Boolean) {
    when (timerRunning) {
      true -> timer_button.text = getString(R.string.pause)
      false -> timer_button.text = getString(R.string.start)
    }
  }
  
  private fun setDrinkTimerText(drinkResId: Int) = timerViewModel.setDrink(drinkResId)
}