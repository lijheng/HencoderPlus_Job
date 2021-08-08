package com.example.customview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.example.customview.anim.AnimFragment
import com.example.customview.fragment.FragmentFactory
import com.example.customview.fragment.ViewFragment
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

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
                return FragmentFactory.createFragment(position)
            }

            override fun getCount(): Int {
                return Constant.ANIMATE_TITLES.count()
            }

            override fun getPageTitle(position: Int): CharSequence? {
                return Constant.ANIMATE_TITLES[position]
            }
        }

        tabLayout.setupWithViewPager(viewPager, true)
    }
}