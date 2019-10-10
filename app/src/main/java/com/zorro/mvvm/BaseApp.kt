package com.zorro.mvvm

import android.app.Application

/**
 * Created by pangli on 2019/10/9.
 * 备注：
 */
class BaseApp : Application() {
//    private var refWatcher: RefWatcher? = null
//
//    companion object {
//
//
//        fun getRefWatcher(context: Context): RefWatcher? {
//            val app = context.applicationContext as BaseApp
//            return app.refWatcher
//        }
//
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//        refWatcher = setupLeakCanary()
//    }
//
//    private fun setupLeakCanary(): RefWatcher {
//        return if (LeakCanary.isInAnalyzerProcess(this)) {
//            RefWatcher.DISABLED
//        } else LeakCanary.install(this)
//    }
}