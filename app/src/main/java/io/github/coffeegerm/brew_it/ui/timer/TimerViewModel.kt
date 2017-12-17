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

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.res.Resources
import android.os.CountDownTimer
import io.github.coffeegerm.brew_it.BetterBaristaApp
import io.github.coffeegerm.brew_it.R
import io.github.coffeegerm.brew_it.data.Drink
import io.github.coffeegerm.brew_it.data.DrinksRepository
import io.github.coffeegerm.brew_it.utilities.Utilities
import javax.inject.Inject

class TimerViewModel : ViewModel() {
  
  init {
    BetterBaristaApp.syringe.inject(this)
  }
  
  @Inject lateinit var drinksRepository: DrinksRepository
  @Inject lateinit var resources: Resources
  @Inject lateinit var utils: Utilities
  
  private var initialTime: Long = 0
  private var currentTimerMillisTillFinished: Long = 0
  private lateinit var countDownTimer: CountDownTimer
  
  var remainingTime: MutableLiveData<String> = MutableLiveData()
  var drinkTimerText: MutableLiveData<String> = MutableLiveData()
  var percentRemaining: MutableLiveData<Int> = MutableLiveData()
  
  fun setDrink(drinkResId: Int) {
    val drinkMade = drinksRepository.getSingleDrinkByName(resources.getString(drinkResId))
    drinkMade?.let { setTotalTime(it) }
    drinkMade?.let {
      drinkTimerText.postValue(utils.convertBrewDurationForTimer(drinkMade.brewDuration))
    }
  }
  
  private fun setTotalTime(drinkMade: Drink) {
    initialTime = (drinkMade.brewDuration * 60 * 1000).toLong()
    createCountdownTimer(initialTime)
  }
  
  private fun createCountdownTimer(totalTimeInMillis: Long) {
    countDownTimer = object : CountDownTimer(totalTimeInMillis, 1000) {
      override fun onFinish() {
        remainingTime.postValue(resources.getString(R.string.final_time_for_timer))
      }
      
      override fun onTick(millisUntilFinished: Long) {
        remainingTime.postValue(convertMillisToMinutes(millisUntilFinished))
        percentRemaining.postValue(getPercentLeft(millisUntilFinished))
        currentTimerMillisTillFinished = millisUntilFinished
      }
    }
  }
  
  private fun convertMillisToMinutes(providedMillis: Long): String {
    val minutes = (providedMillis / 1000) / 60
    val seconds = (providedMillis / 1000) % 60
    return minutes.toString() + ":" + seconds.toString()
  }
  
  fun startTimer() {
    countDownTimer.start()
  }
  
  fun pauseTimer() {
    countDownTimer.cancel()
    createCountdownTimer(currentTimerMillisTillFinished)
  }
  
  fun resetTimer() {
    countDownTimer.cancel()
    createCountdownTimer(initialTime)
  }
  
  fun getPercentLeft(timeLeftInMillis: Long): Int = ((timeLeftInMillis.toFloat() / initialTime.toFloat()) * 100).toInt()
  
}