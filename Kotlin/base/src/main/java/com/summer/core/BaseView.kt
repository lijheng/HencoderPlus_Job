package com.summer.core

/**
 * Create by summer at 2021/1/8
 * describe:
 */
interface BaseView<T> {

    fun getPresenter(): T
}