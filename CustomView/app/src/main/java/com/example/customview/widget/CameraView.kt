package com.example.customview.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.customview.R
import com.example.customview.utils.dp

class CameraView(context: Context, attr: AttributeSet) : View(context, attr) {

    companion object {
        private val IMAGE_SIZE = 200.dp
        private val IMAGE_PADDING = 100.dp
    }

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val mBitmap = getImage(IMAGE_SIZE)

    private val mCamera = Camera().apply {
        rotateX(30f)
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val count = canvas.save()
        canvas.translate(-(IMAGE_PADDING + IMAGE_SIZE/2),-(IMAGE_PADDING+ IMAGE_SIZE/2))
        canvas.drawBitmap(mBitmap, IMAGE_PADDING, IMAGE_PADDING, mPaint)
        mCamera.applyToCanvas(canvas)
        canvas.translate((IMAGE_PADDING + IMAGE_SIZE/2),(IMAGE_PADDING+ IMAGE_SIZE/2))
        canvas.restoreToCount(count)
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