package io.github.coffeegerm.brew_it

import android.support.v7.widget.RecyclerView
import android.view.View
import io.github.coffeegerm.brew_it.data.Drink
import kotlinx.android.synthetic.main.item_drink.view.*

/**
 * Created by dYarzebinski on 10/26/2017.
 * TODO: Add class comment header
 */
class DrinksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindDrink(item: Drink) {
        itemView.drink_name.text = item.name
    }
}