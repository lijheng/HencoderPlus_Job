package com.example.customview.widget

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.customview.R
import com.example.customview.utils.dp

class ScalableImageView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val IMAGE_SIZE = 400.dp
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mBitmap = getImage(IMAGE_SIZE.toInt())

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(mBitmap, (width - IMAGE_SIZE) / 2f, (height - IMAGE_SIZE) / 2f, mPaint)
    }


    private fun getImage(width: Int): Bitmap {
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeResource(resources, R.drawable.avatar_rengwuxian, options)
        options.inDensity = options.outWidth
        options.inTargetDensity = width
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeResource(resources, R.drawable.avatar_rengwuxian, options)
    }

}