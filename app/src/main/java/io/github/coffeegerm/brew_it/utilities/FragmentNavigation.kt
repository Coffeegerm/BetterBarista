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

package io.github.coffeegerm.brew_it.utilities

import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.view.MenuItem
import io.github.coffeegerm.brew_it.R
import io.github.coffeegerm.brew_it.ui.drinks.DrinksFragment
import io.github.coffeegerm.brew_it.ui.more.MoreFragment
import io.github.coffeegerm.brew_it.ui.timer.TimerFragment

class FragmentNavigation(private val supportFragmentManager: FragmentManager) : BottomNavigationView.OnNavigationItemSelectedListener {
  
  private var coldLaunch = true
  
  private val drinksFragment: DrinksFragment by lazy { DrinksFragment() }
  private val timerFragment: TimerFragment by lazy { TimerFragment() }
  private val moreFragment: MoreFragment by lazy { MoreFragment() }
  
  init {
    if (coldLaunch) showFragment(drinksFragment)
    coldLaunch = false
  }
  
  override fun onNavigationItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.navigation_drinks -> {
        showFragment(drinksFragment)
        return true
      }
      R.id.navigation_timer -> {
        showFragment(timerFragment)
        return true
      }
      R.id.navigation_more -> {
        showFragment(moreFragment)
        return true
      }
    }
    return true
  }
  
  private fun showFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commitNowAllowingStateLoss()
  }
}