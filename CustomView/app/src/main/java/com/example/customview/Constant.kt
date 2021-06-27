package com.example.customview

import com.example.customview.anim.widget.CameraAnimView
import com.example.customview.anim.widget.ScaleAnimView

object Constant {

    val VIEW_LAYOUT_RES =
        intArrayOf(R.layout.view_sport, R.layout.view_multiline_text, R.layout.view_camera)
    val VIEW_TITLES = arrayListOf("SportView", "MultilineTextView", "CameraView")

    val ANIMATE_LAYOUTS_CLASS =
        arrayListOf(ScaleAnimView::class, ScaleAnimView::class, CameraAnimView::class)
    val ANIMATE_TITLES = arrayListOf("Scale", "PointF", "AnimateSet")
}