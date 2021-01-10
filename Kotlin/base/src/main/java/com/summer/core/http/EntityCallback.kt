package com.summer.core.http

/**
 * Create by summer at 2021/1/9
 * describe:
 */
interface EntityCallback<T> {

    fun onSuccess(entity: T)

    fun onFailure(message: String?)
}