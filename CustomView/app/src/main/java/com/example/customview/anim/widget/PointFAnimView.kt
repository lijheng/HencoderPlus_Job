package com.example.customview.anim.widget

import android.animation.ObjectAnimator
import android.animation.TypeEvaluator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import com.example.customview.anim.AnimControl
import com.example.customview.utils.dp

class PointFAnimView(context: Context, attributeSet: AttributeSet?) : View(context, attributeSet),
    AnimControl {

    constructor(context: Context) : this(context, null)

    var mPointF = PointF(0f, 0f)
        set(value) {
            field = value
            invalidate()
        }

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#ffff00ff")
        strokeWidth = 20.dp
        strokeCap = Paint.Cap.ROUND
    }

    private val mAnim =
        ObjectAnimator.ofObject(this, "mPointF", PointFTypeEvaluator(), PointF(200.dp, 200.dp))
            .setDuration(1000)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawPoint(mPointF.x, mPointF.y, mPaint)
    }

    override fun start() {
        mAnim.start()
    }

    private class PointFTypeEvaluator : TypeEvaluator<PointF> {

        private val mPointF = PointF()

        override fun evaluate(fraction: Float, startValue: PointF, endValue: PointF): PointF {
            mPointF.x = startValue.x + (endValue.x - startValue.x) * fraction
            mPointF.y = startValue.y + (endValue.y - startValue.y) * fraction
            return mPointF
        }
    }
}