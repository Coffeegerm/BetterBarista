package io.github.coffeegerm.brew_it

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import io.github.coffeegerm.brew_it.data.Drink

/**
 * Created by dYarzebinski on 10/26/2017.
 * TODO: Add class comment header
 */
class DrinksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val drinkName = itemView.findViewById<TextView>(R.id.drink_name)
    private val drinkTime = itemView.findViewById<TextView>(R.id.drink_time)
    private val drinkImage = itemView.findViewById<ImageView>(R.id.drink_image)

    fun bindDrink(item: Drink) {

    }
}