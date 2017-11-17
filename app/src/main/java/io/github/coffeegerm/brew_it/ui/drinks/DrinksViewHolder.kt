package io.github.coffeegerm.brew_it.ui.drinks

import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import io.github.coffeegerm.brew_it.utilities.Constants
import io.github.coffeegerm.brew_it.data.Drink
import io.github.coffeegerm.brew_it.ui.single_drink.SingleDrinkActivity
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
        itemView.setOnClickListener({
            val intent = Intent(itemView.context, SingleDrinkActivity::class.java)
            intent.putExtra(Constants.DRINK_ID_PASSED, item.id)
            startActivity(itemView.context, intent, null)
        })
    }
}
