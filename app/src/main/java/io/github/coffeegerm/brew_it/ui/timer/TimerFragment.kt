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
import io.github.coffeegerm.brew_it.R
import kotlinx.android.synthetic.main.fragment_timer.*

class TimerFragment : Fragment(), AdapterView.OnItemSelectedListener {
  
  lateinit var timerViewModel: TimerViewModel
  
  private var isButtonPressed: Boolean = false
  
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_timer, container, false)
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    timerViewModel = ViewModelProviders.of(this).get(TimerViewModel::class.java)
    timerViewModel.getDrinkNames()
    subscribe()
    
    reset_timer.setOnClickListener { resetTimer() }
  
    // todo refactor this
    timer_button.setOnClickListener({
      timer_button.text = if (isButtonPressed) getString(R.string.start) else getString(R.string.pause)
      when (timer_button.text) {
        getString(R.string.pause) -> startTimer()
        getString(R.string.start) -> pauseTimer()
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
    val isTimerRunning = Observer<Boolean> { isTimerRunning -> isTimerRunning?.let { resetVisibility(it) } }
    timerViewModel.isTimerRunning.observe(this, isTimerRunning)
    
    val drinkNames = Observer<ArrayList<String>> { drinkNames -> setupSpinner(drinkNames!!) }
    timerViewModel.drinksListNames.observe(this, drinkNames)
    
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
  
  private fun resetVisibility(timerRunning: Boolean) {
    when (timerRunning) {
      true -> reset_timer.visibility = View.VISIBLE
      false -> reset_timer.visibility = View.GONE
    }
  }
  
  private fun setDrinkTimerText(drinkResId: Int) = timerViewModel.setDrink(drinkResId)
  
  private fun startTimer() = timerViewModel.startTimer()
  
  private fun pauseTimer() = timerViewModel.pauseTimer()
  
  private fun resetTimer() = timerViewModel.resetTimer()
  
}