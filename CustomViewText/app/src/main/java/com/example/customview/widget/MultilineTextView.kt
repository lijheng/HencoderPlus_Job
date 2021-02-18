package com.example.customviewtext.widget

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.res.ResourcesCompat
import com.example.customviewtext.R
import com.example.customviewtext.utils.dp
import kotlin.math.max

class MultilineTextView(context: Context, attributeSet: AttributeSet) :
    View(context, attributeSet) {

    companion object {
        private val IMAGE_SIZE = 200.dp
        private val IMAGE_PADDING = 50.dp
    }

    private val mBitmap: Bitmap = getImage(IMAGE_SIZE)

    private val str = resources.getString(R.string.multiline_test_text)

    private val mTextPaint = TextPaint(Paint.ANTI_ALIAS_FLAG).apply {
        textSize = 16.dp
    }

    private val mPaint = TextPaint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.drawBitmap(mBitmap, width - IMAGE_SIZE, IMAGE_PADDING, mPaint)

        var start = 0
        var offset: Float = -mTextPaint.fontMetrics.top
        var maxWidth = width.toFloat()
        val measureWidth = floatArrayOf(0f)
        while (start < str.length) {
            maxWidth =
                if (offset + mTextPaint.fontMetrics.bottom < IMAGE_PADDING || offset + mTextPaint.fontMetrics.top > IMAGE_PADDING + IMAGE_SIZE) {
                    width.toFloat()
                } else {
                    width.toFloat() - IMAGE_SIZE
                }

            val count = mTextPaint.breakText(str, start, str.length, true, maxWidth, measureWidth)
            canvas.drawText(str, start, start + count, 0f, offset, mTextPaint)
            start += count
            offset += mTextPaint.fontSpacing

        }

    }


    private fun getImage(width: Float): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.avatar_rengwuxian, options)
        options.inDensity = options.outWidth
        options.inTargetDensity = width.toInt()
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeResource(resources, R.drawable.avatar_rengwuxian, options)
    }

}