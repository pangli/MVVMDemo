package com.zorro.mvvm.ui.main


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * Created by pangli on 2019/10/10.
 * 备注：
 */
class TestPagerAdapter(
    fm: FragmentManager,
    private val titles: MutableList<String>,
    private val fragmentList: MutableList<Fragment>
) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment = fragmentList[position]
    override fun getPageTitle(position: Int): CharSequence? = titles[position]
    override fun getCount(): Int = titles.size
}