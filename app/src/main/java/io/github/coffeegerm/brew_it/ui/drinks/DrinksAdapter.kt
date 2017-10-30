package io.github.coffeegerm.brew_it.ui.drinks

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.coffeegerm.brew_it.R
import io.github.coffeegerm.brew_it.data.Drink
import io.github.coffeegerm.brew_it.drinksList

/**
 * Created by dYarzebinski on 10/26/2017.
 * TODO: Add class comment header
 */
class DrinksAdapter : RecyclerView.Adapter<DrinksViewHolder>() {

    val drinks: ArrayList<Drink> = drinksList

    override fun onBindViewHolder(holder: DrinksViewHolder, position: Int) {
        holder.bindDrink(drinks[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinksViewHolder {
        return DrinksViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_drink, parent, false))
    }

    override fun getItemCount(): Int = drinks.size
}
