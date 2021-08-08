package com.example.customview.utils

import android.content.res.Resources
import android.util.TypedValue


val displayMetrics = Resources.getSystem().displayMetrics
val Float.dp: Float
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this, Resources.getSystem().displayMetrics)

val Int.dp: Float
        get() = this.toFloat().dp

