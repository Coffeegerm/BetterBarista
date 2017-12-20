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

package io.github.coffeegerm.brew_it.ui.more

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.github.coffeegerm.brew_it.BetterBaristaApp
import io.github.coffeegerm.brew_it.R
import kotlinx.android.synthetic.main.fragment_more.*

class MoreFragment : Fragment() {
  
  lateinit var moreViewModel: MoreViewModel
  
  init {
    BetterBaristaApp.syringe.inject(this)
  }
  
  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_more, container, false)
  
  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    moreViewModel = ViewModelProviders.of(this).get(MoreViewModel::class.java)
    subscribe()
    feedback.setOnClickListener { sendEmail() }
  }
  
  private fun subscribe() {
  
  }
  
  private fun sendEmail() {
    val mailto = "mailto:coffeeandcreamstudios@gmail.com"
    val emailIntent = Intent(Intent.ACTION_SENDTO)
    emailIntent.data = Uri.parse(mailto)
    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Better Barista feedback")
    startActivity(emailIntent)
  }
}