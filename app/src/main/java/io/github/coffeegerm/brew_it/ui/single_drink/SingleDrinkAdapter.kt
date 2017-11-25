package io.github.coffeegerm.brew_it.ui.single_drink

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.github.coffeegerm.brew_it.R

/**
 * TODO: Add class comment header
 */
class SingleDrinkAdapter : RecyclerView.Adapter<SingleDrinkViewHolder>() {

    private lateinit var instructionList: ArrayList<String>

    fun setInstructions(providedInstructions: ArrayList<String>) {
        this.instructionList = providedInstructions
    }

    override fun onBindViewHolder(holder: SingleDrinkViewHolder, position: Int) {
        holder.bindInstruction(instructionList[position])
    }

    override fun getItemCount(): Int = instructionList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SingleDrinkViewHolder = SingleDrinkViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_instructions, parent, false))

}