package com.example.customviewtext.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class ViewFragment : Fragment() {

    companion object {
        private const val PARAM = "fragment_layout_resource"

        fun newInstance(layoutRes:Int):Fragment{
            val fragment = ViewFragment()
            val bundle = Bundle()
            bundle.putInt(PARAM,layoutRes)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (arguments?.getInt(PARAM,-1) != -1) {
            return inflater.inflate(arguments?.getInt(PARAM)!!,container,false)
        } else {
            return null
        }
    }
}