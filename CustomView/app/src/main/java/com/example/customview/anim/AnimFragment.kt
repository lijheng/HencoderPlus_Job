package com.example.customview.anim

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.example.customview.Constant
import com.example.customview.R
import com.example.customview.anim.widget.CameraAnimView
import com.example.customview.anim.widget.PointFAnimView
import com.example.customview.anim.widget.ScaleAnimView
import com.example.customview.fragment.ViewFragment

class AnimFragment : Fragment() {

    companion object {
        private const val PARAM = "fragment_position"

        fun newInstance(position: Int): Fragment {
            val fragment = AnimFragment()
            val bundle = Bundle()
            bundle.putInt(PARAM, position)
            fragment.arguments = bundle
            return fragment
        }
    }

    private var mAnimView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.frag_anim, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val position = arguments?.getInt(PARAM, 0) ?: 0
        mAnimView = when (position) {
            0 -> {
                ScaleAnimView(context!!)
            }
            1 -> {
                PointFAnimView(context!!)
            }
            else -> {
                CameraAnimView(context!!)
            }
        }

        view.findViewById<FrameLayout>(R.id.frag_anim_content).addView(mAnimView)

        view.findViewById<Button>(R.id.frag_anim_start).setOnClickListener {
            (mAnimView as AnimControl).start()
        }
    }


}