package io.github.coffeegerm.brew_it.ui.drinks

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.coffeegerm.brew_it.BrewItApplication.Companion.syringe
import io.github.coffeegerm.brew_it.R
import io.github.coffeegerm.brew_it.data.DrinksRepository
import kotlinx.android.synthetic.main.fragment_drinks.*
import javax.inject.Inject
import javax.inject.Named


/**
 * Created by david_yarz on 10/23/17.
 *
 * Fragment Responsible for controlling the Recycler view of drinks
 * As well as beginning the SingleDrinkActivity
 */
class DrinksFragment : Fragment() {

    private val TAG: String = "DrinksFragment"

    @Inject
    @field:Named("drinksRepository") lateinit var drinksRepository: DrinksRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_drinks, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "OnCreatedView")
        syringe.inject(this)
        toolbar_title.typeface = ResourcesCompat.getFont(context, R.font.raleway_thin)
        setupAdapter()
    }

    private fun setupAdapter() {
        if (isAdded) {
            drinks_recycler_view.layoutManager = GridLayoutManager(activity, 2)
            Log.i(TAG, "layoutManager set")
            val adapter = DrinksAdapter()
            drinks_recycler_view.adapter = adapter
            adapter.setDrinks(drinksRepository.getDrinks())
            Log.i(TAG, "Adapter set")
        }
    }
}