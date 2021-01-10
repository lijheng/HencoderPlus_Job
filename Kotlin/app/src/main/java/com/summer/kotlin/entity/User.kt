package com.summer.kotlin.entity

/**
 * Create by summer at 2021/1/9
 * describe:
 */
data class User(var username: String?, var password: String?, var code: String?) {

    constructor():this(null,null,null) {
    }

}