package io.github.coffeegerm.brew_it.ui.drinks

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.res.ResourcesCompat
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.coffeegerm.brew_it.R
import kotlinx.android.synthetic.main.fragment_drinks.*

/**
 * Created by david_yarz on 10/23/17.
 *
 * Fragment Responsible for controlling the Recycler view of drinks
 */
class DrinksFragment : Fragment() {

    private val TAG: String = "DrinksFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_drinks, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "OnCreatedView")
        toolbar_title.typeface = ResourcesCompat.getFont(context, R.font.raleway_thin)
        setupAdapter()
    }

    private fun setupAdapter() {
        if (isAdded) {
            drinks_recycler_view.layoutManager = GridLayoutManager(activity, 2)
            drinks_recycler_view.adapter = DrinksAdapter()
            Log.i(TAG, "layoutManager set")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onAttachFragment(childFragment: Fragment?) {
        super.onAttachFragment(childFragment)
    }
}