package com.etwicaksono.academy.ui.bookmark

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.etwicaksono.academy.data.entity.CourseEntity
import com.etwicaksono.academy.data.source.AcademyRepository

class BookmarkViewModel(private val academyRepository:AcademyRepository) : ViewModel() {
    fun getBookmarks(): LiveData<List<CourseEntity>> = academyRepository.getBookmarkedCourses()
}