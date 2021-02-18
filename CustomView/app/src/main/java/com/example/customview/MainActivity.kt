package com.example.customview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.customview.fragment.ViewFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    private val mResList = intArrayOf(R.layout.view_sport, R.layout.view_multiline_text,R.layout.view_camera)
    private val mTiles = arrayListOf("SportView", "MultilineTextView","CameraView")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.act_main_tabLayout)
        viewPager = findViewById(R.id.act_main_viewpager)

        viewPager.adapter = object : FragmentStatePagerAdapter(
            supportFragmentManager,
            BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        ) {
            override fun getItem(position: Int): Fragment {
                return ViewFragment.newInstance(mResList[position])
            }

            override fun getCount(): Int {
                return mResList.count()
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return mTiles[position]
            }
        }

        tabLayout.setupWithViewPager(viewPager, true)
    }
}