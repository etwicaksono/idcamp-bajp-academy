package com.etwicaksono.academy.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.etwicaksono.academy.data.source.local.entity.CourseEntity
import com.etwicaksono.academy.data.source.local.entity.ModuleEntity
import com.etwicaksono.academy.data.source.AcademyRepository
import com.etwicaksono.academy.data.source.local.entity.CourseWithModule
import com.etwicaksono.academy.utils.DataDummy
import com.etwicaksono.academy.vo.Resource
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailCourseViewModelTest {

    private lateinit var viewModel: DetailCourseViewModel
    private val dummyCourse = DataDummy.generateDummyCourses()[0]
    private val courseId=dummyCourse.courseId
    private val dummyModules = DataDummy.generateDummyModules(courseId)
    
    @get:Rule
    var instantTaskExecutorRule=InstantTaskExecutorRule()

    @Mock
    private lateinit var academyRepository: AcademyRepository
    
    @Mock
    private lateinit var courseObserver: Observer<CourseEntity>
    
    @Mock
    private lateinit var modulesObserver:Observer<List<ModuleEntity>>

    @Mock
    private lateinit var observer: Observer<Resource<CourseWithModule>>

    @Before
    fun setup(){
        viewModel= DetailCourseViewModel(academyRepository)
        viewModel.setSelectedCourse(courseId)
    }

    @Test
    fun getCourseWithModule(){
        val dummyCourseWithModule=Resource.success(DataDummy.generateDummyCourseWithModules(dummyCourse,true))
        val course = MutableLiveData<Resource<CourseWithModule>>()
        course.value=dummyCourseWithModule

        `when`(academyRepository.getCourseWithModules(courseId)).thenReturn(course)

        viewModel.courseModule.observeForever(observer)

        verify(observer).onChanged(dummyCourseWithModule)
    }
}