package com.example.customview.anim.widget

import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.customview.R
import com.example.customview.anim.AnimControl
import com.example.customview.utils.dp

class ScaleAnimView(context: Context, attributeSet: AttributeSet?) : View(context, null),AnimControl {
    constructor(context: Context) : this(context, null)

    var radius = 10.dp
        set(value) {
            field = value
            invalidate()
        }

    private val mAnim = ObjectAnimator.ofFloat(this, "radius", 100.dp)
        .setDuration(2000)

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = resources.getColor(R.color.purple_200)
        style = Paint.Style.FILL_AND_STROKE
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawCircle(width / 2f, height / 2f, radius, mPaint)
    }

    override fun start() {
        radius = 10.dp
        mAnim.start()
    }

}