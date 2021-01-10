package com.summer.core.http

import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.lang.reflect.Type

/**
 * Create by summer at 2021/1/10
 * describe:
 */
object HttpClient : OkHttpClient() {

    val gson = Gson()

    private fun <T> convert(json: String?, type: Type?): T {
        return gson.fromJson(json, type)
    }

    fun <T> get(path: String, type: Type?, entityCallback: EntityCallback<T>) {
        val request = Request.Builder()
            .url("https://api.hencoder.com/${path}")
            .build()
        val call = this.newCall(request)
        call.enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                val code = response.code()

                if (code in 200..299) {
                    val body = response.body()
                    var json: String? = null
                    try {
                        json = body?.string()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                    entityCallback.onSuccess(convert(json, type) as T)
                } else if (code in 400..499) {
                    entityCallback.onFailure("客户端错误")
                } else if (code in 500..599) {
                    entityCallback.onFailure("服务器错误")
                } else {
                    entityCallback.onFailure("位置错误")
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                entityCallback.onFailure("网络异常")
            }
        })
    }


}