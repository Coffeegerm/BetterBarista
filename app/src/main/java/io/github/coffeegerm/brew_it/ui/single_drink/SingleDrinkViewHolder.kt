package io.github.coffeegerm.brew_it.ui.single_drink

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_instructions.view.*

/**
 * Created by dYarzebinski on 11/18/2017.
 * TODO: Add class comment header
 */

class SingleDrinkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindInstruction(item: String) {
        itemView.single_drink_instructions.text = item
    }
}