package com.example.customview.fragment

import androidx.fragment.app.Fragment
import com.example.customview.R
import com.example.customview.anim.AnimFragment

object FragmentFactory {

    fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AnimFragment.newInstance(position)
            1 -> AnimFragment.newInstance(position)
            2 -> AnimFragment.newInstance(position)
            3 -> MaterialEditTextFragment()
            4 -> ViewFragment.newInstance(R.layout.fragment_scalable_imageview)
            else -> Fragment()
        }

    }
}