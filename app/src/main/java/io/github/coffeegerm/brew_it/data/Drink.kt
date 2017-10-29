package io.github.coffeegerm.brew_it.data

/**
 * Created by dYarzebinski on 10/26/2017.
 * TODO: Add class comment header
 */

open class Drink(val name: String,
                 val description: String,
                 val servingSize: Int,
                 val duration: Int, // minutes
                 val instructions: List<String>?,
                 val image: Int?)