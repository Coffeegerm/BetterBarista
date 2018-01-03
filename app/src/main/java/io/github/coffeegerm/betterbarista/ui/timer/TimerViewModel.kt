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

package io.github.coffeegerm.betterbarista.ui.timer

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.Handler
import io.github.coffeegerm.betterbarista.BetterBarista
import io.github.coffeegerm.betterbarista.data.Drink
import io.github.coffeegerm.betterbarista.data.DrinksRepository
import javax.inject.Inject


class TimerViewModel : ViewModel() {
  
  init {
    BetterBarista.syringe.inject(this)
  }
  
  @Inject lateinit var drinksRepository: DrinksRepository
  
  val oneSecond: Long = 1000
  
  private var constantInitialTime: Long = 0
  private var runningTime: Long = 0
  
  val handler = Handler()
  private val runnable = object : Runnable {
    override fun run() {
      if (timerRunning) {
        if (runningTime > 0) {
          runningTime -= oneSecond
          remainingTime.postValue(convertMillisToMinutes(runningTime))
          percentRemaining.postValue(getPercentLeft(runningTime))
        } else {
          remainingTime.postValue(convertMillisToMinutes(0))
        }
      }
      handler.postDelayed(this, oneSecond)
    }
  }
  
  var timerRunning = false
  
  var remainingTime: MutableLiveData<String> = MutableLiveData()
  var drinkTimerText: MutableLiveData<String> = MutableLiveData()
  var percentRemaining: MutableLiveData<Int> = MutableLiveData()
  var drinksListNames: MutableLiveData<ArrayList<String>> = MutableLiveData()
  var isTimerRunning: MutableLiveData<Boolean> = MutableLiveData()
  
  fun getDrinkNames() {
    val allDrinks = drinksRepository.getAllDrinks()
    val drinkNames = ArrayList<String>()
    allDrinks.mapTo(drinkNames) { it.name }
    drinksListNames.postValue(drinkNames)
  }
  
  fun setDrink(drinkId: Int) {
    if (timerRunning) {
      timerRunning = false
      isTimerRunning.postValue(timerRunning)
    }
    percentRemaining.postValue(100)
    val drinkMade = drinksRepository.getSingleDrinkById(drinkId)
    drinkMade?.let { setTotalTime(it) }
    drinkMade?.let { drinkTimerText.postValue(convertBrewDurationForTimer(drinkMade.brewDuration)) }
  }
  
  private fun setTotalTime(drinkMade: Drink) {
    constantInitialTime = (drinkMade.brewDuration * 60 * 1000).toLong()
    runningTime = (drinkMade.brewDuration * 60 * 1000).toLong()
  }
  
  fun startTimer() {
    timerRunning = true
    isTimerRunning.postValue(timerRunning)
    handler.post(runnable)
  }
  
  fun pauseTimer() {
    handler.removeCallbacks(runnable)
    timerRunning = false
    isTimerRunning.postValue(timerRunning)
  }
  
  fun resetTimer() {
    runningTime = constantInitialTime
    drinkTimerText.postValue(convertMillisToMinutes(runningTime))
    percentRemaining.postValue(100)
    timerRunning = false
    isTimerRunning.postValue(timerRunning)
  }
  
  private fun convertMillisToMinutes(providedMillis: Long): String {
    val minutes = ((providedMillis / 1000) / 60)
    val seconds = ((providedMillis / 1000) % 60)
    return if (seconds < 10) {
      minutes.toString() + ":" + "0" + seconds.toString()
    } else {
      minutes.toString() + ":" + seconds.toString()
    }
  }
  
  private fun convertBrewDurationForTimer(originalValue: Int): String {
    return if (originalValue >= 60) {
      val simplifiedTime = originalValue / 60
      simplifiedTime.toString() + ":00 hrs"
    } else {
      originalValue.toString() + ":00"
    }
  }
  
  fun getPercentLeft(timeLeftInMillis: Long): Int = ((timeLeftInMillis.toFloat() / constantInitialTime.toFloat()) * 100).toInt()
  
}