package com.etwicaksono.academy.data.source.remote

import android.os.Handler
import android.os.Looper
import com.etwicaksono.academy.data.source.remote.response.ContentResponse
import com.etwicaksono.academy.data.source.remote.response.CourseResponse
import com.etwicaksono.academy.data.source.remote.response.ModuleResponse
import com.etwicaksono.academy.utils.EspressoIdlingResources
import com.etwicaksono.academy.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler(Looper.getMainLooper())

    fun getAllCourses(callback: LoadCourseCallback) {
        EspressoIdlingResources.increment()
        handler.postDelayed(
            {
                callback.onAllCourseReceived(jsonHelper.loadCourses())
                EspressoIdlingResources.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    fun getModules(courseId: String, callback: LoadModulesCallback) {
        EspressoIdlingResources.increment()
        handler.postDelayed(
            {
                callback.onAllModulreReceived(jsonHelper.loadModule(courseId))
                EspressoIdlingResources.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
    }

    fun getContent(moduleId: String, callback: LoadContentCallback) {
        EspressoIdlingResources.increment()
        handler.postDelayed(
            {
                callback.onContentReceived(jsonHelper.loadContent(moduleId))
                EspressoIdlingResources.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
    }


    interface LoadCourseCallback {
        fun onAllCourseReceived(courseResponse: List<CourseResponse>)
    }

    interface LoadModulesCallback {
        fun onAllModulreReceived(moduleResponse: List<ModuleResponse>)
    }

    interface LoadContentCallback {
        fun onContentReceived(contentResponse: ContentResponse)
    }

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource = instance ?: synchronized(this) {
            instance ?: RemoteDataSource(helper).apply { instance = this }
        }
    }
}