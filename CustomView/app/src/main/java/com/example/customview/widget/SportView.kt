package com.example.customview.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.example.customview.R
import com.example.customview.utils.dp

class SportView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    companion object {
        private val RADIUS = 150.dp
        private val CIRCLE_COLOR = Color.parseColor("#90A4AE")
        private val HIGHLIGHT_COLOR = Color.parseColor("#FF4081")
    }

    private val rect = RectF()

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 100.dp
        textAlign = Paint.Align.CENTER
        typeface = ResourcesCompat.getFont(context, R.font.font)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rect.set(width/2f- RADIUS,height/2f- RADIUS,width/2f+ RADIUS,height/2f+ RADIUS)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        mPaint.strokeWidth = 20.dp
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeCap = Paint.Cap.ROUND
        mPaint.color = CIRCLE_COLOR
        canvas.drawOval(rect,mPaint)

        mPaint.color = HIGHLIGHT_COLOR
        canvas.drawArc(rect,-90f,180f,false,mPaint)

        mPaint.style = Paint.Style.FILL
        canvas.drawText("abab",width/2f,height/2f - (mPaint.fontMetrics.descent+mPaint.fontMetrics.ascent)/2f,mPaint)
    }
}