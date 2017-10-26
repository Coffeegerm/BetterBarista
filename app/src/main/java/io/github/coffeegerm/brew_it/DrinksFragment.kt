package io.github.coffeegerm.brew_it

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_container.*
import kotlinx.android.synthetic.main.fragment_container.view.*

/**
 * Created by david_yarz on 10/23/17.
 */
class DrinksFragment : Fragment() {

    private val TAG: String = "MoreFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        Log.d(TAG, "Fragment Created")
        val view = inflater.inflate(R.layout.fragment_drinks, container, false)
        return view
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