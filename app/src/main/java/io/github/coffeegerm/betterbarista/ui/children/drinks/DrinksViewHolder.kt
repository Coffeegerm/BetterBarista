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

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.github.coffeegerm.betterbarista.R
import io.github.coffeegerm.betterbarista.data.model.Drink
import io.github.coffeegerm.betterbarista.ui.children.drinks.singleDrink.SingleDrinkActivity
import io.github.coffeegerm.betterbarista.utilities.Constants.DRINK_ID_PASSED
import kotlinx.android.synthetic.main.item_drink.view.*

class DrinksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
  
  private val imagePlaceholder = RequestOptions.placeholderOf(R.drawable.placeholder)!!
  
  fun bindDrink(item: Drink) {
    itemView.drink_name.text = item.name
    itemView.drink_time.text = convertBrewDuration(item.brewDuration)
    Glide.with(itemView).load(item.image).apply(imagePlaceholder).into(itemView.drink_image)
    val context = itemView.context as Activity
    itemView.setOnClickListener({
      val intent = Intent(itemView.context, SingleDrinkActivity::class.java)
      intent.putExtra(DRINK_ID_PASSED, item.id)
      context.startActivityForResult(intent, 1)
    })
  }
  
  private fun convertBrewDuration(originalValue: Int): String {
    return if (originalValue >= 60) {
      val simplifiedTime = originalValue / 60
      simplifiedTime.toString() + ":00 hrs"
    } else {
      originalValue.toString() + ":00 min"
    }
  }
  
}
