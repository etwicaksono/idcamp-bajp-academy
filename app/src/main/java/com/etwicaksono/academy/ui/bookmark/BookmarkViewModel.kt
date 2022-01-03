package com.etwicaksono.academy.ui.bookmark

import androidx.lifecycle.ViewModel
import com.etwicaksono.academy.data.CourseEntity
import com.etwicaksono.academy.data.source.AcademyRepository
import com.etwicaksono.academy.utils.DataDummy

class BookmarkViewModel(private val academyRepository:AcademyRepository) : ViewModel() {
    fun getBookmarks(): List<CourseEntity> = academyRepository.getBookmarkedCourses()
}