package com.etwicaksono.academy.bookmark

import com.etwicaksono.academy.data.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course:CourseEntity)

}
