package com.example.customview.widget

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.TextUtils
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import com.example.customview.utils.dp

class MaterialEditText(context: Context, attrs: AttributeSet?) :
    AppCompatEditText(context, attrs) {

    private val VERTICAL_OFFSET = 10.dp
    private val HORIZONAL_OFFSET = 10.dp
    private val DEFAULT_HINT_TEXT_SIZE = 12.dp
    private var mHintHeight = 0
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val mAnimator = ValueAnimator.ofFloat(0f, 1f)
    private var mAnimatorFraction = 0f
    private var showHint = false


    init {
        mPaint.textSize = DEFAULT_HINT_TEXT_SIZE
        mHintHeight = (mPaint.fontMetrics.bottom - mPaint.fontMetrics.top).toInt()
        setPadding(
            paddingLeft,
            (paddingTop + VERTICAL_OFFSET + mHintHeight).toInt(),
            paddingRight,
            paddingBottom
        )
        mAnimator.addUpdateListener {
            mAnimatorFraction = it.animatedFraction
            invalidate()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (showHint) {
            val locationY = mHintHeight + ((1 - mAnimatorFraction) * VERTICAL_OFFSET)
            canvas.drawText(hint.toString(), HORIZONAL_OFFSET, locationY, mPaint)
        }
    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        if (showHint && TextUtils.isEmpty(text)) {
            showHint = false
            mAnimator.reverse()
        } else if (!showHint && !TextUtils.isEmpty(text)) {
            showHint = true
            mAnimator.start()
        }
    }


}