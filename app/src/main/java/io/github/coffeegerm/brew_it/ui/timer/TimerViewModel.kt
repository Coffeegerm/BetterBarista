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
import android.os.CountDownTimer
import io.github.coffeegerm.brew_it.BrewItApplication
import io.github.coffeegerm.brew_it.data.Drink

/**
 * TODO create class header
 */
class TimerViewModel : ViewModel() {
  
  init {
    BrewItApplication.syringe.inject(this)
  }
  
  private var initialTime: Long = 0
  var remainingTime: MutableLiveData<Long> = MutableLiveData()
  
  private var currentTimerMillisTillFinished: Long = 0
  
  private lateinit var countDownTimer: CountDownTimer
  
  fun setTotalTime(drinkMade: Drink) {
    initialTime = (drinkMade.brewDuration * 60 * 1000).toLong()
    createCountdownTimer(initialTime)
  }
  
  private fun createCountdownTimer(totalTimeInMillis: Long) {
    countDownTimer = object : CountDownTimer(totalTimeInMillis, 1000) {
      override fun onFinish() {
      }
      
      override fun onTick(millisUntilFinished: Long) {
        remainingTime.postValue(millisUntilFinished)
        currentTimerMillisTillFinished = millisUntilFinished
      }
    }
  }
  
  fun startTimer() {
    countDownTimer.start()
  }
  
  fun pauseTimer() {
    countDownTimer.cancel()
  }
  
  fun resetTimer() {
    countDownTimer.cancel()
  }
  
  fun getPercentLeft(timeLeftInMillis: Long): Int {
    return (timeLeftInMillis / 60000 * 100).toInt()
  }

}