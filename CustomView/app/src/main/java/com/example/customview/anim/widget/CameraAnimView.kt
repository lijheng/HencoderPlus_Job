package com.example.customview.anim.widget

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.customview.R
import com.example.customview.anim.AnimControl
import com.example.customview.utils.dp
import com.example.customview.widget.CameraView

class CameraAnimView(context: Context, attributeSet: AttributeSet?) : View(context, attributeSet),
    AnimControl {

    constructor(context: Context) : this(context, null)

    companion object {
        private val BITMAP_SIZE = 200.dp
        private val BITMAP_PADDING = 100.dp
    }

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val mBitmap = getImage(BITMAP_SIZE.toInt())

    private val mCamera = Camera()

    var rotate = 0f
        set(value) {
            field = value
            invalidate()
        }
    var bottomFlip = 0f
        set(value) {
            field = value
            invalidate()
        }

    var topFlip = 0f
        set(value) {
            field = value
            invalidate()
        }


    private lateinit var mAnimator: ObjectAnimator
    init {
        mCamera.setLocation(0f, 0f, -6 * resources.displayMetrics.density)

        val bottomFlipHolder = PropertyValuesHolder.ofFloat("bottomFlip",60f)
        val topFlipHolder = PropertyValuesHolder.ofFloat("topFlip", -60f)
        val rotateHolder = PropertyValuesHolder.ofFloat("rotate",270f)
        mAnimator = ObjectAnimator.ofPropertyValuesHolder(this,bottomFlipHolder,topFlipHolder,rotateHolder)
            .setDuration(2000)
    }


    //绕X轴翻转总体思路：
    //1.需要将画布的中心移动到Camera的中心点，才能保证投影比例正常
    //2.投影（即翻转）
    //3.将画布移动回去
    //4.在部分翻转时使用几何裁剪来裁剪画布
    //5.
    override fun onDraw(canvas: Canvas) {
        //上班部分
        canvas.save()
        val trans = BITMAP_PADDING + BITMAP_SIZE / 2;
        canvas.translate(trans, trans)
        mCamera.save()

        mCamera.rotateX(topFlip)
        canvas.rotate(-rotate)
        mCamera.applyToCanvas(canvas)
        canvas.clipRect(-BITMAP_SIZE, -BITMAP_SIZE,  BITMAP_SIZE, 0f)
        canvas.rotate(rotate)
        canvas.translate(-trans, -trans)
        canvas.drawBitmap(mBitmap, BITMAP_PADDING, BITMAP_PADDING, mPaint)
        mCamera.restore()
        canvas.restore()

        //下半部分
        canvas.save()
        mCamera.save()
        canvas.translate(trans, trans)
        mCamera.rotateX(bottomFlip)
        canvas.rotate(-rotate)
        mCamera.applyToCanvas(canvas)
        canvas.clipRect(-BITMAP_SIZE, 0f, BITMAP_SIZE,
            BITMAP_SIZE
        )
        canvas.rotate(rotate)
        canvas.translate(-trans, -trans)
        canvas.drawBitmap(mBitmap, BITMAP_PADDING, BITMAP_PADDING, mPaint)
        mCamera.restore()
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

    override fun start() {
        mAnimator.start()
    }
}