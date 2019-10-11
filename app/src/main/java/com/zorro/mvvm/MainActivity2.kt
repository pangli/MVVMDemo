package com.zorro.mvvm

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.zorro.mvvm.ui.main.Constant
import com.zorro.mvvm.ui.main.MainFragment2
import kotlinx.android.synthetic.main.main_activity2.*
import kotlinx.android.synthetic.main.menu_badge.*


class MainActivity2 : AppCompatActivity() {


    private val BOTTOM_INDEX: String = "bottom_index"
    private val FRAGMENT_OFFICE = 0x01
    private val FRAGMENT_CLASSROOM = 0x02
    private val FRAGMENT_ADDRESS_BOOK = 0x03
    private val FRAGMENT_MINE = 0x04

    private var mIndex = FRAGMENT_OFFICE


    private var officeFragment: MainFragment2? = null
    private var classroomFragment: MainFragment2? = null
    private var addressBookFragment: MainFragment2? = null
    private var mineFragment: MainFragment2? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            mIndex = savedInstanceState.getInt(BOTTOM_INDEX)
        }
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity2)
        showFragment(mIndex)
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.container, MainFragment2.newInstance())
//                .commitNow()
//        }
        bottom_navigation.apply {
            itemIconTintList = null
            setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        }
        //获取整个的NavigationView
        val menuView = bottom_navigation.getChildAt(0) as BottomNavigationMenuView

        //这里就是获取所添加的每一个Tab(或者叫menu)，
        val tab = menuView.getChildAt(3)
        val itemView = tab as BottomNavigationItemView

        //加载我们的角标View，新创建的一个布局
        val badge = LayoutInflater.from(this).inflate(R.layout.menu_badge, menuView, false)

        //添加到Tab上
        itemView.addView(badge)
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(BOTTOM_INDEX, mIndex)
    }


    /**
     * NavigationItemSelect监听
     */
    private val onNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            return@OnNavigationItemSelectedListener when (item.itemId) {
                R.id.action_office -> {
                    showFragment(FRAGMENT_OFFICE)

                    true
                }
                R.id.action_classroom -> {
                    showFragment(FRAGMENT_CLASSROOM)
                    tv_dot.visibility = View.VISIBLE
                    true
                }
                R.id.action_address_book -> {
                    showFragment(FRAGMENT_ADDRESS_BOOK)
                    true
                }
                R.id.action_mine -> {
                    showFragment(FRAGMENT_MINE)
                    tv_dot.visibility = View.GONE
                    true
                }
                else -> {
                    false
                }

            }
        }

    /**
     * 展示Fragment
     * @param index
     */
    private fun showFragment(index: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        mIndex = index
        when (index) {
            FRAGMENT_OFFICE // 办公
            -> {
                if (officeFragment == null) {
                    officeFragment = MainFragment2.newInstance()
                    transaction.add(R.id.container, officeFragment!!, Constant.FRAGMENT_OFFICE)
                } else {
                    transaction.show(officeFragment!!)
                }
            }
            FRAGMENT_CLASSROOM // e学院
            -> {
                if (classroomFragment == null) {
                    classroomFragment = MainFragment2.newInstance()
                    transaction.add(
                        R.id.container,
                        classroomFragment!!,
                        Constant.FRAGMENT_CLASSROOM
                    )
                } else {
                    transaction.show(classroomFragment!!)
                }
            }
            FRAGMENT_ADDRESS_BOOK // 通讯录
            -> {
                if (addressBookFragment == null) {
                    addressBookFragment = MainFragment2.newInstance()
                    transaction.add(
                        R.id.container,
                        addressBookFragment!!,
                        Constant.FRAGMENT_ADDRESS_BOOK
                    )
                } else {
                    transaction.show(addressBookFragment!!)
                }
            }
            FRAGMENT_MINE // 我的
            -> {
                if (mineFragment == null) {
                    mineFragment = MainFragment2.newInstance()
                    transaction.add(R.id.container, mineFragment!!, Constant.FRAGMENT_MINE)
                } else {
                    transaction.show(mineFragment!!)
                }
            }
        }
        transaction.commitNow()
    }

    /**
     * 隐藏所有的Fragment
     */
    private fun hideFragments(transaction: FragmentTransaction) {
        classroomFragment?.let { transaction.hide(it) }
        officeFragment?.let { transaction.hide(it) }
        addressBookFragment?.let { transaction.hide(it) }
        mineFragment?.let { transaction.hide(it) }
    }


    override fun onDestroy() {
        classroomFragment = null
        officeFragment = null
        addressBookFragment = null
        mineFragment = null
        super.onDestroy()
    }

}
