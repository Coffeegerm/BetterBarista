package io.github.coffeegerm.brew_it.ui.drinks

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import io.github.coffeegerm.brew_it.data.Drink
import io.github.coffeegerm.brew_it.ui.single_drink.SingleDrinkActivity
import io.github.coffeegerm.brew_it.utilities.Constants.DRINK_ID_PASSED
import io.github.coffeegerm.brew_it.utilities.Utilities
import kotlinx.android.synthetic.main.item_drink.view.*

/**
 * Created by dYarzebinski on 10/26/2017.
 * TODO: Add class comment header
 */

class DrinksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindDrink(item: Drink) {
        itemView.drink_name.text = item.name
        itemView.drink_time.text = Utilities().convertBrewDuration(item.brewDuration)
        itemView.drink_image.setImageResource(item.image)
        val context = itemView.context as Activity
        itemView.setOnClickListener({
            val intent = Intent(itemView.context, SingleDrinkActivity::class.java)
            intent.putExtra(DRINK_ID_PASSED, item.id)
            context.startActivityForResult(intent, 1)
        })
    }
}
