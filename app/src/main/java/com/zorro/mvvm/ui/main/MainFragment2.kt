package com.zorro.mvvm.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.zorro.mvvm.R
import kotlinx.android.synthetic.main.main_fragment2.*

class MainFragment2 : Fragment() {

    companion object {
        fun newInstance() = MainFragment2()
    }

    private val mTitle = mutableListOf("最新", "快讯", "深度", "大事件")
    private var onTabSelectedListener: MyOnTabSelectedListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root = inflater.inflate(R.layout.main_fragment2, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val fragmentList =
            mutableListOf<Fragment>(
                MainFragment3.newInstance(),
                MainFragment3.newInstance(),
                MainFragment3.newInstance(),
                MainFragment3.newInstance()
            )
        vp_fragment.adapter = TestPagerAdapter(childFragmentManager, mTitle, fragmentList)
        vp_fragment.offscreenPageLimit = mTitle.size
        tab_layout.setupWithViewPager(vp_fragment)
        for (i in 0 until tab_layout.tabCount) {
            val tab = tab_layout.getTabAt(i)//获得每一个tab
            if (tab != null) {
                tab.setCustomView(R.layout.item_tab)//给每一个tab设置view
                val item = tab.customView as TextView
                item.textSize = if (i == 0) 22f else 15f
                item.isSelected = i == 0
                item.text = mTitle[i]//设置tab上的文字
            }
        }
        onTabSelectedListener = MyOnTabSelectedListener()
        tab_layout.addOnTabSelectedListener(onTabSelectedListener!!)
    }

    private inner class MyOnTabSelectedListener : TabLayout.OnTabSelectedListener {

        override fun onTabSelected(tab: TabLayout.Tab) {
            val item = tab.customView as TextView
            item.textSize = 22f
            item.isSelected = true
        }

        override fun onTabUnselected(tab: TabLayout.Tab) {
            val item = tab.customView as TextView
            item.textSize = 15f
            item.isSelected = false
        }

        override fun onTabReselected(tab: TabLayout.Tab) {

        }
    }

    override fun onDestroy() {
        if (onTabSelectedListener != null)
            tab_layout.removeOnTabSelectedListener(onTabSelectedListener!!)
        super.onDestroy()
    }
}
