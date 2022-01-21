package com.etwicaksono.academy.data.source.remote

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.etwicaksono.academy.data.source.remote.response.ContentResponse
import com.etwicaksono.academy.data.source.remote.response.CourseResponse
import com.etwicaksono.academy.data.source.remote.response.ModuleResponse
//import com.etwicaksono.academy.utils.EspressoIdlingResources
import com.etwicaksono.academy.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    private val handler = Handler(Looper.getMainLooper())

    fun getAllCourses():LiveData<ApiResponse<List<CourseResponse>>> {
//        EspressoIdlingResources.increment()
        val resultCourse=MutableLiveData<ApiResponse<List<CourseResponse>>>()
        handler.postDelayed(
            {
                resultCourse.value= ApiResponse.success(jsonHelper.loadCourses())
//                EspressoIdlingResources.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
        return resultCourse
    }

    fun getModules(courseId: String):LiveData<ApiResponse<List<ModuleResponse>>> {
//        EspressoIdlingResources.increment()
        val resultModules=MutableLiveData<ApiResponse<List<ModuleResponse>>>()
        handler.postDelayed(
            {
                resultModules.value= ApiResponse.success(jsonHelper.loadModule(courseId))
//                EspressoIdlingResources.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
        return resultModules
    }

    fun getContent(moduleId: String):LiveData<ApiResponse<ContentResponse>> {
//        EspressoIdlingResources.increment()
        val resultContent = MutableLiveData<ApiResponse<ContentResponse>>()
        handler.postDelayed(
            {
                resultContent.value=ApiResponse.success(jsonHelper.loadContent(moduleId))
//                EspressoIdlingResources.decrement()
            },
            SERVICE_LATENCY_IN_MILLIS
        )
        return resultContent
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