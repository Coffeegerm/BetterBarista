package io.github.coffeegerm.brew_it.ui.timer

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import io.github.coffeegerm.brew_it.R

/**
 * Created by dYarzebinski on 10/27/2017.
 * TODO: Add class comment header
 */
class CircularProgressView(context: Context?, attrs: AttributeSet) : View(context, attrs) {

    private val arcColor: Int = ContextCompat.getColor(context, R.color.colorPrimaryDark)
    private val fillColor: Int = ContextCompat.getColor(context, R.color.lightGreen)

    // required to draw the arcs
    private val rectF = RectF()

    // Used to draw pretty much anything on a canvas, which is what we will be drawing on
    private val paint = Paint().apply {
        // how we want the arcs to be draw, we want to make sure the arc centers are not colored
        // so we use a STROKE instead.
        style = Paint.Style.STROKE
        strokeCap = Paint.Cap.ROUND
    }

    // default percentage set to 0
    private var percentage = 0

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.let {
            rectF.apply {
                val width = (it.width.div(2)).toFloat() // center X of the canvas
                val height = (it.height.div(2)).toFloat() // center Y of the canvas

                // place the rectF it at the center of the screen with height and width of 200dp
                set(width - 300, height - 300, width + 300, height + 300)
            }

            // draw an arc that will represent an empty loading view
            it.drawArc(rectF, 0f, 360f, false, paint.apply {
                color = arcColor

                // how wide the stroke should be, typically more than or equal to the strokeWidth
                // of the arc representing a filled percentage
                strokeWidth = 5f
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

    // todo fecator this to calculate for time
    fun setPercentage(percentage: Int) {
        this.percentage = percentage
        invalidate()
    }
}