package com.etwicaksono.academy.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.etwicaksono.academy.data.CourseEntity
import com.etwicaksono.academy.data.ModuleEntity
import com.etwicaksono.academy.data.source.AcademyRepository
import com.etwicaksono.academy.utils.DataDummy

class DetailCourseViewModel(private val academyRepository:AcademyRepository) : ViewModel() {
    private lateinit var courseId: String

    fun setSelectedCourse(courseId: String) {
        this.courseId = courseId
    }

    fun getCourse(): LiveData<CourseEntity> =academyRepository.getCourseWithModules(courseId)

    fun getModules(): LiveData<List<ModuleEntity>> = academyRepository.getAllModulesByCourse(courseId)
}