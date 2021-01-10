package com.summer.lesson

import com.google.gson.reflect.TypeToken
import com.summer.core.http.EntityCallback
import com.summer.core.http.HttpClient
import com.summer.core.utils.Utils
import com.summer.lesson.entity.Lesson

/**
 * Create by summer at 2021/1/9
 * describe:
 */
class LessonPresenter(private var activity: LessonActivity) {
    companion object {
        private const val LESSON_PATH = "lessons"
    }

    private var lessons = ArrayList<Lesson>()

    private val type =
        object : TypeToken<ArrayList<Lesson>>() {}.type

    internal fun fetchData() {
        HttpClient.get(LESSON_PATH, type, object : EntityCallback<ArrayList<Lesson>> {
            override fun onFailure(message: String?) {
                activity.runOnUiThread {
                    if (message != null) {
                        Utils.toast(message)
                    }
                }
            }

            override fun onSuccess(lessons: ArrayList<Lesson>) {
                this@LessonPresenter.lessons = lessons
                activity.runOnUiThread {
                    activity.showResult(lessons)
                }
            }
        })
    }

    internal fun showPlayback() {
        var playbackLessons = ArrayList<Lesson>()
        playbackLessons.addAll(lessons.filter {
            it.state === Lesson.State.PLAYBACK
        })
        activity.showResult(playbackLessons)
    }


}