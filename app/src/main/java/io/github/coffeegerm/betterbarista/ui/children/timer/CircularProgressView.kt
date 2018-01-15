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

package io.github.coffeegerm.betterbarista.ui.children.timer

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import io.github.coffeegerm.betterbarista.R

/**
 * TODO: Add class comment header
 */
class CircularProgressView(context: Context, attrs: AttributeSet) : View(context, attrs) {
  
  private val arcColor: Int = ContextCompat.getColor(context, R.color.deepBrown)
  private val fillColor: Int = ContextCompat.getColor(context, R.color.lightGreen)
  // default percentage set to 0
  private var percentage = 0
  
  // required to draw the arcs
  private val rectF = RectF()
  
  private val paint = Paint().apply {
    style = Paint.Style.STROKE
    strokeCap = Paint.Cap.ROUND
  }
  
  override fun onDraw(canvas: Canvas?) {
    super.onDraw(canvas)
    
    canvas?.let {
      rectF.apply {
        val width = (it.width.div(2)).toFloat() // center X of the canvas
        val height = (it.height.div(2)).toFloat() // center Y of the canvas
        
        // place the rectF it at the center of the screen with height and width of 200dp
        set(width - 250, height - 250, width + 250, height + 250)
      }
      
      it.drawArc(rectF, 0f, 360f, false, paint.apply {
        color = arcColor
        
        // how wide the stroke should be, typically more than or equal to the strokeWidth
        // of the arc representing a filled percentage
        strokeWidth = 8f
      })
      
      // get the actual percentage as a float
      val fillPercentage = (360 * (percentage / 100.0)).toFloat()
      
      // draw the arc that will represent the percentage filled up
      it.drawArc(rectF, 270f, fillPercentage, false, paint.apply {
        color = fillColor // filled percentage color
        
        // how wide the stroke should be, typically less than or equal to the strokeWidth
        // of the empty arc
        strokeWidth = 15f
      })
    }
  }
  
  fun setPercentage(percentage: Int) {
    this.percentage = percentage
    invalidate()
  }
}