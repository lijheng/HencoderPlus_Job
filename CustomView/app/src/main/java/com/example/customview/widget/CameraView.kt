package com.example.customview.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.customview.R
import com.example.customview.utils.dp

class CameraView(context: Context, attr: AttributeSet) : View(context, attr) {

    companion object {
        private val BITMAP_SIZE = 200.dp
        private val BITMAP_PADDING = 100.dp
    }

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val mBitmap = getImage(BITMAP_SIZE.toInt())

    private val mCamera = Camera()
    
    init {
        mCamera.rotateX(30f)
        mCamera.setLocation(0f,0f,-6*resources.displayMetrics.density)
    }


    //绕X轴翻转总体思路：
    //1.需要将画布的中心移动到Camera的中心点，才能保证投影比例正常
    //2.投影（即翻转）
    //3.将画布移动回去
    //4.在部分翻转时使用几何裁剪来裁剪画布
    //5.
    override fun onDraw(canvas: Canvas) {
//        super.onDraw(canvas)
        //上半部分不围绕X轴翻起
        canvas.save()
        canvas.translate((BITMAP_PADDING + BITMAP_SIZE / 2), (BITMAP_PADDING + BITMAP_SIZE / 2))
        canvas.rotate(-30f)
        canvas.clipRect(-BITMAP_SIZE, -BITMAP_SIZE, BITMAP_SIZE , 0f)
        canvas.rotate(30f)
        canvas.translate(-(BITMAP_PADDING + BITMAP_SIZE / 2), -(BITMAP_PADDING + BITMAP_SIZE / 2))
        canvas.drawBitmap(mBitmap, BITMAP_PADDING, BITMAP_PADDING, mPaint)
        canvas.restore()

        //下半部分围绕X轴翻转
        canvas.save()
        canvas.translate((BITMAP_PADDING + BITMAP_SIZE / 2), (BITMAP_PADDING + BITMAP_SIZE / 2))
        canvas.rotate(-30f)
        mCamera.applyToCanvas(canvas)
        canvas.clipRect(-BITMAP_SIZE, 0f, BITMAP_SIZE,
            BITMAP_SIZE )
        canvas.rotate(30f)
        canvas.translate(-(BITMAP_PADDING + BITMAP_SIZE / 2), -(BITMAP_PADDING + BITMAP_SIZE / 2))
        canvas.drawBitmap(mBitmap, BITMAP_PADDING, BITMAP_PADDING, mPaint)
        canvas.restore()
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