package com.etwicaksono.academy.ui.bookmark

import com.etwicaksono.academy.data.entity.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)

}
