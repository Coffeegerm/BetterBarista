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
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Named


/**
 * Created by david_yarz on 10/23/17.
 *
 * Fragment Responsible for controlling the Recycler view of drinks
 * As well as beginning the SingleDrinkActivity
 */
class DrinksFragment : Fragment() {

    @Inject
    lateinit var drinksRepository: DrinksRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_drinks, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.i("onViewCreated")
        syringe.inject(this)
        toolbar_title.typeface = ResourcesCompat.getFont(context, R.font.raleway_thin)
        setupAdapter()
    }

    private fun setupAdapter() {
        if (isAdded) {
            drinks_recycler_view.layoutManager = GridLayoutManager(activity, 2)
            Timber.i("layoutManager set")
            val adapter = DrinksAdapter()
            drinks_recycler_view.adapter = adapter
            adapter.setDrinks(drinksRepository.getAllDrinks())
            Timber.i("Adapter set")
        }
    }
}