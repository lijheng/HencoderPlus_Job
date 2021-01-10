package com.summer.core

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Create by summer at 2021/1/8
 * describe:
 */
abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val viewHashMap = HashMap<Int, View>()

    @Suppress("UNCHECKED_CAST")
    fun <T : View> getView(id: Int): T {
        var view = viewHashMap[id]
        if (view == null) {
            view = itemView.findViewById(id)
            viewHashMap[id] = view
        }
        return view as T
    }

    protected fun setText(id: Int, text: String?) {
        getView<TextView>(id).text = text
    }
}