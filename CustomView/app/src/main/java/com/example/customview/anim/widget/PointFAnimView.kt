package com.example.customview.anim

import android.content.Context
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View

class PointFAnimView(context: Context, attributeSet: AttributeSet?) : View(context, attributeSet) {

    constructor(context: Context) : this(context, null)

    var mPointF = PointF(0f, 0f)
        set(value) {
            field = value
            invalidate()
        }
}