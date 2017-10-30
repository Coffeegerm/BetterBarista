package io.github.coffeegerm.brew_it.ui.drinks

import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import io.github.coffeegerm.brew_it.data.Drink
import io.github.coffeegerm.brew_it.ui.single_drink.SingleDrinkActivity
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

    override fun onClick(v: View) {
        val intent = Intent(v.context, SingleDrinkActivity::class.java)
        startActivity(v.context, intent, null)
    }
}
