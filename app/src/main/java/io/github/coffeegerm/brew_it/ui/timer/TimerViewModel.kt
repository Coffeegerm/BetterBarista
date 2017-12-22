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
import android.os.Handler
import io.github.coffeegerm.brew_it.BetterBaristaApp
import io.github.coffeegerm.brew_it.data.Drink
import io.github.coffeegerm.brew_it.data.DrinksRepository
import javax.inject.Inject


class TimerViewModel : ViewModel() {
  
  init {
    BetterBaristaApp.syringe.inject(this)
  }
  
  @Inject lateinit var drinksRepository: DrinksRepository
  
  private var constantInitialTime: Long = 0
  private var runningTime: Long = 0
  
  val handler = Handler()
  private val runnable = object : Runnable {
    override fun run() {
      remainingTime.postValue(convertMillisToMinutes(runningTime))
      if (timerRunning) {
        runningTime -= 1000
        percentRemaining.postValue(getPercentLeft(runningTime))
      }
      handler.postDelayed(this, 1000)
    }
  }
  
  private var timerRunning = false
  
  var remainingTime: MutableLiveData<String> = MutableLiveData()
  var drinkTimerText: MutableLiveData<String> = MutableLiveData()
  var percentRemaining: MutableLiveData<Int> = MutableLiveData()
  var drinksListNames: MutableLiveData<ArrayList<String>> = MutableLiveData()
  
  fun getDrinkNames() {
    val allDrinks = drinksRepository.getAllDrinks()
    val drinkNames = ArrayList<String>()
    allDrinks.mapTo(drinkNames) { it.name }
    drinksListNames.postValue(drinkNames)
  }
  
  fun setDrink(drinkId: Int) {
    val drinkMade = drinksRepository.getSingleDrinkById(drinkId)
    drinkMade?.let { setTotalTime(it) }
    drinkMade?.let { drinkTimerText.postValue(convertBrewDurationForTimer(drinkMade.brewDuration)) }
  }
  
  private fun setTotalTime(drinkMade: Drink) {
    constantInitialTime = (drinkMade.brewDuration * 60 * 1000).toLong()
    runningTime = (drinkMade.brewDuration * 60 * 1000).toLong()
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
  
  fun startTimer() {
    timerRunning = true
    handler.post(runnable)
  }
  
  fun pauseTimer() {
    handler.removeCallbacks(runnable)
    timerRunning = false
  }
  
  fun resetTimer() {
    runningTime = constantInitialTime
    timerRunning = false
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