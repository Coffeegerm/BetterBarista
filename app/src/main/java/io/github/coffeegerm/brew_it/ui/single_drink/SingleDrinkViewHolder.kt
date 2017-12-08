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
import android.view.View
import kotlinx.android.synthetic.main.item_instructions.view.*

/**
 * TODO: Add class comment header
 */

class SingleDrinkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  
  fun bindInstruction(item: String) {
    itemView.single_drink_instructions.text = item
  }
}