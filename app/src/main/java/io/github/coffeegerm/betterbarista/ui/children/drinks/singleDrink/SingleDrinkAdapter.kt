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

package io.github.coffeegerm.betterbarista.ui.children.drinks.singleDrink

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.coffeegerm.betterbarista.R

/**
 * TODO: Add class comment header
 */
class SingleDrinkAdapter : androidx.recyclerview.widget.RecyclerView.Adapter<SingleDrinkViewHolder>() {
  
  private var instructionList: ArrayList<String> = mutableListOf<String>() as ArrayList<String>
  
  fun setInstructions(providedInstructions: ArrayList<String>) {
    this.instructionList = providedInstructions
  }
  
  override fun onBindViewHolder(holder: SingleDrinkViewHolder, position: Int) = holder.bindInstruction(instructionList[position])
  
  override fun getItemCount(): Int = instructionList.size
  
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleDrinkViewHolder = SingleDrinkViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_instructions, parent, false))
  
}