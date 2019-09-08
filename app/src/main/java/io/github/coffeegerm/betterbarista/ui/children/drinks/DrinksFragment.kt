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

package io.github.coffeegerm.betterbarista.ui.children.drinks

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.coffeegerm.betterbarista.BetterBarista.Companion.syringe
import io.github.coffeegerm.betterbarista.R
import io.github.coffeegerm.betterbarista.data.model.Drink
import kotlinx.android.synthetic.main.fragment_drinks.*


/**
 * Fragment Responsible for controlling the Recycler view of drinks
 * As well as beginning the SingleDrinkActivity
 */

class DrinksFragment : androidx.fragment.app.Fragment() {
  
  private lateinit var drinksViewModel: DrinksViewModel
  
  private val drinksAdapter = DrinksAdapter()
  
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
        inflater.inflate(R.layout.fragment_drinks, container, false)
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    syringe.inject(this)
    drinksViewModel = ViewModelProviders.of(this).get(DrinksViewModel::class.java)
    drinks_recycler_view.layoutManager = androidx.recyclerview.widget.GridLayoutManager(activity, 2)
    drinks_recycler_view.adapter = drinksAdapter
    subscribe()
  }
  
  private fun subscribe() = drinksViewModel.drinks.observe(this, Observer<ArrayList<Drink>> { drinks -> setAdapterDrinks(drinks!!) })
  
  private fun setAdapterDrinks(drinks: ArrayList<Drink>) = drinksAdapter.setDrinks(drinks)
  
}