package com.etwicaksono.academy.ui.academy

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.etwicaksono.academy.data.CourseEntity
import com.etwicaksono.academy.data.source.AcademyRepository
import com.etwicaksono.academy.utils.DataDummy

class AcademyViewModel(private val academyRepository:AcademyRepository) : ViewModel() {
    fun getCourses(): LiveData<List<CourseEntity>> = academyRepository.getAllCourses()
}