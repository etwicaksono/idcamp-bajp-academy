package com.etwicaksono.academy.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.etwicaksono.academy.data.source.AcademyRepository
import com.etwicaksono.academy.di.Injection
import com.etwicaksono.academy.ui.academy.AcademyViewModel
import com.etwicaksono.academy.ui.bookmark.BookmarkViewModel
import com.etwicaksono.academy.ui.detail.DetailCourseViewModel
import com.etwicaksono.academy.ui.reader.CourseReaderViewModel

class ViewModelFactory private constructor(private val mAcademyRepository: AcademyRepository) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when{
            modelClass.isAssignableFrom(AcademyViewModel::class.java)->{
                return AcademyViewModel(mAcademyRepository) as T
            }
            modelClass.isAssignableFrom(DetailCourseViewModel::class.java)->{
                return DetailCourseViewModel(mAcademyRepository) as T
            }
            modelClass.isAssignableFrom(BookmarkViewModel::class.java)->{
                return BookmarkViewModel(mAcademyRepository) as T
            }
            modelClass.isAssignableFrom(CourseReaderViewModel::class.java)->{
                return CourseReaderViewModel(mAcademyRepository) as T
            }
            else-> throw Throwable("Unknown ViewModel class: ${modelClass.name}")
        }
    }

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory = instance ?: synchronized(this) {
            instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                instance = this
            }
        }
    }
}