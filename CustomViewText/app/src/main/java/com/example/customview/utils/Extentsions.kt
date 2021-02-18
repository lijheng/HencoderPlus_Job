package com.example.customviewtext.utils

import android.content.res.Resources


val displayMetrics = Resources.getSystem().displayMetrics
val Float.dp: Float
    get() = displayMetrics.density * this + 0.5f

val Int.dp: Float
    get() = displayMetrics.density * this + 0.5f