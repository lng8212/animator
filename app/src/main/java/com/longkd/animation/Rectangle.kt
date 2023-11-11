package com.longkd.animation

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

/**
 * @Author: longkd
 * @Since: 08:41 - 11/11/2023
 */
class Rectangle : View {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init()
    }

    private lateinit var paint: Paint
    private lateinit var path: Path
    private var oldRect: RectangleView? = null

    private val listRectangle = listOf(
        RectangleView(
            Point(0f, 0f), Point(0f, 800f),
            Point(800f, 800f), Point(800f, 0f)
        ),
        RectangleView(
            Point(100f, 300f), Point(50f, 500f),
            Point(750f, 600f), Point(700f, 150f)
        ),
        RectangleView(
            Point(100f, 150f), Point(200f, 400f),
            Point(600f, 430f), Point(400f, 50f)
        ),
    )

    private fun init() {
        paint = Paint()
        paint.apply {
            color = Color.RED
            strokeWidth = 10f
            style = Paint.Style.STROKE
        }
        path = Path()
    }


    fun updateData(pos: Int) {
        val listData = listRectangle
        if (oldRect != null) {
            val p1 = DataHandle(
                (listData[pos].p1.x - oldRect!!.p1.x) / DURATION,
                (listData[pos].p1.y - oldRect!!.p1.y) / DURATION
            )
            val p2 = DataHandle(
                (listData[pos].p2.x - oldRect!!.p2.x) / DURATION,
                (listData[pos].p2.y - oldRect!!.p2.y) / DURATION
            )
            val p3 = DataHandle(
                (listData[pos].p3.x - oldRect!!.p3.x) / DURATION,
                (listData[pos].p3.y - oldRect!!.p3.y) / DURATION
            )
            val p4 = DataHandle(
                (listData[pos].p4.x - oldRect!!.p4.x) / DURATION,
                (listData[pos].p4.y - oldRect!!.p4.y) / DURATION
            )
            val valueAnimator = ValueAnimator.ofFloat(0f, DURATION.toFloat())
            valueAnimator.duration = DURATION
            val rectangleView = oldRect!!
            valueAnimator.addUpdateListener { data ->
                path.reset()
                path.apply {
                    path.moveTo(
                        rectangleView.p1.x + p1.lengthX * (data.animatedValue as Float),
                        rectangleView.p1.y + p1.lengthY * (data.animatedValue as Float)
                    )
                    path.lineTo(
                        rectangleView.p2.x + p2.lengthX * (data.animatedValue as Float),
                        rectangleView.p2.y + p2.lengthY * (data.animatedValue as Float)
                    )
                    path.lineTo(
                        rectangleView.p3.x + p3.lengthX * (data.animatedValue as Float),
                        rectangleView.p3.y + p3.lengthY * (data.animatedValue as Float)
                    )
                    path.lineTo(
                        rectangleView.p4.x + p4.lengthX * (data.animatedValue as Float),
                        rectangleView.p4.y + p4.lengthY * (data.animatedValue as Float)
                    )
                    path.lineTo(
                        rectangleView.p1.x + p1.lengthX * (data.animatedValue as Float),
                        rectangleView.p1.y + p1.lengthY * (data.animatedValue as Float)
                    )
                }
                postInvalidate()
            }
            valueAnimator.start()
            oldRect = listData[pos]
        } else {
            path.apply {
                path.moveTo(listData[pos].p1.x, listData[pos].p1.y)
                path.lineTo(listData[pos].p2.x, listData[pos].p2.y)
                path.lineTo(listData[pos].p3.x, listData[pos].p3.y)
                path.lineTo(listData[pos].p4.x, listData[pos].p4.y)
                path.lineTo(listData[pos].p1.x, listData[pos].p1.y)
            }
            oldRect = listData[pos]
            postInvalidate()
        }


    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(path, paint)
    }

    companion object {
        const val DURATION = 300L
    }
}


data class DataHandle(val lengthX: Float, val lengthY: Float)
data class Point(val x: Float, val y: Float)
data class RectangleView(var p1: Point, var p2: Point, var p3: Point, var p4: Point)