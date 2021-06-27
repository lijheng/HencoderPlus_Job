package com.example.customview.anim

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.customview.R
import com.example.customview.utils.dp
import java.util.*

class ScaleAnimView(context: Context, attributeSet: AttributeSet?) : View(context, null) {
    constructor(context: Context) : this(context, null)

    var radius = 10.dp
        set(value) {
            field = value
            invalidate()
        }

    val mAnim = ObjectAnimator.ofFloat(this, "radius", 100.dp)
        .setDuration(2000)

    val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = resources.getColor(R.color.purple_200)
        style = Paint.Style.FILL_AND_STROKE
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawCircle(width / 2f, height / 2f, radius, mPaint)
    }

    fun startAnim() {
        radius = 10.dp
        mAnim.start()
    }

}