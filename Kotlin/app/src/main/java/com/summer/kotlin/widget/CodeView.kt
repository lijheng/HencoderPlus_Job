package com.summer.kotlin.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import androidx.appcompat.widget.AppCompatTextView
import com.summer.core.utils.Utils
import com.summer.kotlin.R
import kotlin.random.Random

/**
 * Create by summer at 2021/1/9
 * describe:
 */
class CodeView : AppCompatTextView {

    private val paint = Paint()

    private val codeList = arrayListOf<String>(
        "kotlin",
        "android",
        "java",
        "http",
        "https",
        "okhttp",
        "retrofit",
        "tcp/ip"
    )

    constructor(context: Context) : this(context, null) {
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        setTextSize(TypedValue.COMPLEX_UNIT_SP, 18f)
        gravity = Gravity.CENTER
        setBackgroundColor(context.getColor(R.color.colorPrimary))
        setTextColor(Color.WHITE)

        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.setColor(context.getColor(R.color.colorAccent))
        paint.strokeWidth = Utils.dp2px(6f)

        updateCode()
    }

    public fun updateCode() {
        val random = Random.Default.nextInt(codeList.size)
        val code = codeList[random]
        text = code
    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawLine(0f, height.toFloat(), width.toFloat(), 0f, paint)
        super.onDraw(canvas)
    }
}