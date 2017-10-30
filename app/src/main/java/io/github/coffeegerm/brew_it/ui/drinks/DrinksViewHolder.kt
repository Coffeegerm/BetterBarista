package io.github.coffeegerm.brew_it.ui.drinks

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import io.github.coffeegerm.brew_it.data.Drink
import kotlinx.android.synthetic.main.item_drink.view.*

/**
 * Created by dYarzebinski on 10/26/2017.
 * TODO: Add class comment header
 */
class DrinksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    fun bindDrink(item: Drink) {
        itemView.drink_name.text = item.name
        itemView.drink_time.text = """${item.duration}:00"""
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        Toast.makeText(v!!.context, "Hello there", Toast.LENGTH_SHORT).show()
    }
}
