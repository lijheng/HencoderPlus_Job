package com.example.customview.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.customview.R
import com.example.customview.utils.dp
import kotlin.math.sqrt

class CameraView(context: Context, attr: AttributeSet) : View(context, attr) {

    companion object {
        private val IMAGE_SIZE = 200.dp
        private val IMAGE_PADDING = 100.dp
    }

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val mBitmap = getImage(IMAGE_SIZE.toInt())

    private val mCamera = Camera().apply {
        rotateX(30f)
        translate(0f ,0f, -6 * resources.displayMetrics.density)
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
        canvas.translate((IMAGE_PADDING + IMAGE_SIZE / 2), (IMAGE_PADDING + IMAGE_SIZE / 2))
        canvas.rotate(-30f)
        canvas.clipRect(-IMAGE_SIZE, -IMAGE_SIZE, IMAGE_SIZE , 0f)
        canvas.rotate(30f)
        canvas.translate(-(IMAGE_PADDING + IMAGE_SIZE / 2), -(IMAGE_PADDING + IMAGE_SIZE / 2))
        canvas.drawBitmap(mBitmap, IMAGE_PADDING, IMAGE_PADDING, mPaint)
        canvas.restore()

        //下半部分围绕X轴翻转
        canvas.save()
        canvas.translate((IMAGE_PADDING + IMAGE_SIZE / 2), (IMAGE_PADDING + IMAGE_SIZE / 2))
        canvas.rotate(-30f)
        mCamera.applyToCanvas(canvas)
        canvas.clipRect(-IMAGE_SIZE, 0f, IMAGE_SIZE,
            IMAGE_SIZE )
        canvas.rotate(30f)
        canvas.translate(-(IMAGE_PADDING + IMAGE_SIZE / 2), -(IMAGE_PADDING + IMAGE_SIZE / 2))
        canvas.drawBitmap(mBitmap, IMAGE_PADDING, IMAGE_PADDING, mPaint)
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