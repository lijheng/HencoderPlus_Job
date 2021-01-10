package com.summer.core.utils

import android.content.Context
import com.summer.core.BaseApplication
import com.summer.core.R

/**
 * Create by summer at 2021/1/8
 * describe:
 */
object CacheUtils {

    private val context = BaseApplication.currentApplication()

    private val SP =
        context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    fun save(key: String, value: String?) {
        SP.edit().putString(key, value).apply()
    }

    fun get(key: String): String? {
        return SP.getString(key, null)
    }
}