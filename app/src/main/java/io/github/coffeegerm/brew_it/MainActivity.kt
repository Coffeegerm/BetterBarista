package io.github.coffeegerm.brew_it

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_container.*

class MainActivity : AppCompatActivity() {

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_drinks -> {
                message.setText(R.string.title_drinks)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_timer -> {
                message.setText(R.string.title_timer)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_more -> {
                message.setText(R.string.title_more)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
    }
}
