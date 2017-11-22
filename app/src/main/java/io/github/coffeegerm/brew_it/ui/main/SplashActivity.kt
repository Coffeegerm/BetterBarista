package io.github.coffeegerm.brew_it.ui.main

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.github.coffeegerm.brew_it.R

/**
 * Created by david_yarz on 11/22/17.
 */

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}