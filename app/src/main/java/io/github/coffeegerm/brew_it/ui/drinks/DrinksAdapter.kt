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

package io.github.coffeegerm.brew_it.ui.drinks

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.coffeegerm.brew_it.R
import io.github.coffeegerm.brew_it.data.Drink

/**
 * TODO: Add class comment header
 */

class DrinksAdapter : RecyclerView.Adapter<DrinksViewHolder>() {
  
  private lateinit var drinks: ArrayList<Drink>
  
  fun setDrinks(providedDrinks: ArrayList<Drink>) {
    this.drinks = providedDrinks
  }
  
  override fun onBindViewHolder(holder: DrinksViewHolder, position: Int) {
    holder.bindDrink(drinks[position])
  }
  
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinksViewHolder =
        DrinksViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_drink, parent, false))
  
  override fun getItemCount(): Int = drinks.size
}
