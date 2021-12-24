package com.etwicaksono.academy.ui.bookmark

import androidx.lifecycle.ViewModel
import com.etwicaksono.academy.data.CourseEntity
import com.etwicaksono.academy.utils.DataDummy

class BookmarkViewModel : ViewModel() {
    fun getBookmarks(): List<CourseEntity> = DataDummy.generateDummyCourse()
}