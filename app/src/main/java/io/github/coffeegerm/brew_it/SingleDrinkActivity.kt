package io.github.coffeegerm.brew_it

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * Created by dYarzebinski on 10/28/2017.
 * TODO: Add class comment header
 */
class SingleDrinkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_drink)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}