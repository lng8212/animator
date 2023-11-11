package com.longkd.animation

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnStart
import androidx.core.graphics.drawable.toBitmap


/**
 * @Author: longkd
 * @Since: 10:24 - 11/11/2023
 */
class PreviewImage : View {
    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs)
    }

    private var width: Float = 0f
    private var height: Float = 0f
    private lateinit var image: Bitmap
    private lateinit var rect: RectF
    private fun init(attrs: AttributeSet? = null) {
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.PreviewImage
        )
        image = typedArray.getDrawable(R.styleable.PreviewImage_src_image)?.toBitmap()!!
        typedArray.recycle()
    }

    var blockClickUser: ((isEnableClick: Boolean) -> Unit)? = null

    fun takePhoto() {
        visibility = VISIBLE
        val valueAnimator = ValueAnimator.ofFloat(0f, DURATION.toFloat())
        valueAnimator.duration = DURATION
        valueAnimator.doOnStart {
            blockClickUser?.invoke(false)
        }
        valueAnimator.addUpdateListener { data ->
            rect = RectF(
                (data.animatedValue as Float) * (width - 100) / DURATION,
                (data.animatedValue as Float) * (height - 100) / DURATION,
                width, height
            )
            postInvalidate()
        }
        valueAnimator.doOnEnd {
            blockClickUser?.invoke(true)
            visibility = GONE
        }
        valueAnimator.start()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        width = MeasureSpec.getSize(widthMeasureSpec).toFloat()
        height = MeasureSpec.getSize(heightMeasureSpec).toFloat()
        setData()
    }

    private fun setData() {
        rect = RectF(0f, 0f, width, height)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(image, null, rect, null)
    }

    companion object {
        const val DURATION = 500L
    }
}