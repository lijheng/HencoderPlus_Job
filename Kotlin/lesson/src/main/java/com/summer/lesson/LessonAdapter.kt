package com.summer.lesson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.summer.core.BaseViewHolder
import com.summer.lesson.entity.Lesson

/**
 * Create by summer at 2021/1/10
 * describe:
 */
class LessonAdapter():RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

    private var list = ArrayList<Lesson>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        return LessonViewHolder.onCreate(parent)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    fun updateAndNotify(list: ArrayList<Lesson>) {
        this.list = list
        notifyDataSetChanged()
    }

    class LessonViewHolder(itemView: View) : BaseViewHolder(itemView) {

        companion object {
            internal fun onCreate(parent: ViewGroup): LessonViewHolder {
                return LessonViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_lesson, parent, false)
                )
            }
        }

        internal fun onBind(lesson: Lesson) {
            var data = lesson.data
            setText(R.id.tv_date, data ?: "日期待定")
            setText(R.id.tv_content, lesson.content)

            val state = lesson.state
            if (state != null) {
                setText(R.id.tv_state, state.stateName())
                var colorRes = when (state) {
                    Lesson.State.PLAYBACK -> R.color.playback
                    Lesson.State.LIVE -> R.color.live
                    Lesson.State.WAIT -> R.color.wait
                    else -> R.color.playback
                }
                val backgroundColor = itemView.context.getColor(colorRes)
                getView<TextView>(R.id.tv_state).setBackgroundColor(backgroundColor)
            }
        }
    }


}