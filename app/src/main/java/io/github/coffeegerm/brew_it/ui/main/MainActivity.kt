/*
 * Copyright 2017 Coffee and Cream Studios
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.coffeegerm.brew_it.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bumptech.glide.request.RequestOptions
import io.github.coffeegerm.brew_it.R
import io.github.coffeegerm.brew_it.utilities.Constants
import io.github.coffeegerm.brew_it.utilities.FragmentNavigation
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.longToast

val imagePlaceholder = RequestOptions.placeholderOf(R.drawable.placeholder)!!

class MainActivity : AppCompatActivity() {
  
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    navigation.setOnNavigationItemSelectedListener(FragmentNavigation(supportFragmentManager))
  }
  
  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    if (requestCode == Constants.SINGLE_DRINK_REQUEST_CODE) {
      if (resultCode == Activity.RESULT_OK) {
        val drinkIdChosen = data?.getIntExtra(Constants.DRINK_ID_PASSED, 0)
        drinkIdChosen?.minus(1)
        navigation.selectedItemId = R.id.navigation_timer
      }
    }
  }
  
  private var backPressed: Long = 0
  
  override fun onBackPressed() {
    if (backPressed + 2000 > System.currentTimeMillis()) super.onBackPressed()
    else longToast(getString(R.string.close_message))
    backPressed = System.currentTimeMillis()
  }
}