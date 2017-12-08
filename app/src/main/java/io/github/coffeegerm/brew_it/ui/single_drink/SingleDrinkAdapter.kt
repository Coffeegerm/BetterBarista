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

package io.github.coffeegerm.brew_it.ui.single_drink

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.coffeegerm.brew_it.R

/**
 * TODO: Add class comment header
 */
class SingleDrinkAdapter : RecyclerView.Adapter<SingleDrinkViewHolder>() {
  
  private lateinit var instructionList: ArrayList<String>
  
  fun setInstructions(providedInstructions: ArrayList<String>) {
    this.instructionList = providedInstructions
  }
  
  override fun onBindViewHolder(holder: SingleDrinkViewHolder, position: Int) {
    holder.bindInstruction(instructionList[position])
  }
  
  override fun getItemCount(): Int = instructionList.size
  
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleDrinkViewHolder = SingleDrinkViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_instructions, parent, false))
  
}