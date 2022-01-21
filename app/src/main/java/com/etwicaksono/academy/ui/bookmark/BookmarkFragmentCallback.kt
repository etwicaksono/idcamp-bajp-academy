package com.etwicaksono.academy.ui.bookmark

import com.etwicaksono.academy.data.source.local.entity.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)

}
