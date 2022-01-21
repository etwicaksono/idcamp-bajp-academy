package com.etwicaksono.academy.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.etwicaksono.academy.data.source.local.entity.CourseEntity
import com.etwicaksono.academy.data.source.AcademyRepository

class AcademyViewModel(private val academyRepository:AcademyRepository) : ViewModel() {
    fun getCourses(): LiveData<List<CourseEntity>> = academyRepository.getAllCourses()
}