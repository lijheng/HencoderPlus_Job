package com.summer.core

import android.app.Application
import android.content.Context

/**
 * Create by summer at 2021/1/8
 * describe:
 */
class BaseApplication : Application() {
    companion object {
        private lateinit var currentApplication: Context

        @JvmStatic
        fun currentApplication(): Context {
            return currentApplication
        }
    }


    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        currentApplication = this
    }
}