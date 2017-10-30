package io.github.coffeegerm.brew_it.ui.timer

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.coffeegerm.brew_it.R
import kotlinx.android.synthetic.main.fragment_timer.*

/**
 * Created by david_yarz on 10/24/17.
 */
class TimerFragment : Fragment() {

    private val TAG: String = "TimerFragment"
    var isButtonPressed: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "Fragment Created")
        return inflater.inflate(R.layout.fragment_timer, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        var count = 60
//        val handler = Handler()
//
//        val runnable = object : Runnable {
//            override fun run() {
//                if (count <= 100) {
//                    handler.postDelayed(this, 1000)
//                    circularView.setPercentage(count)
//                    count--
//                }
//            }
//        }
//
//        handler.post(runnable)

        timer_button.setOnClickListener {
            timer_button.text = if (isButtonPressed) getString(R.string.start) else getString(R.string.stop)
            if (isButtonPressed) isButtonPressed = false
            else if (!isButtonPressed) isButtonPressed = true
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