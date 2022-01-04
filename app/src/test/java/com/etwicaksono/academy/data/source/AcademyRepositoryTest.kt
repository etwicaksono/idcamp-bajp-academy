package com.etwicaksono.academy.data.source

import com.etwicaksono.academy.data.source.remote.RemoteDataSource
import com.etwicaksono.academy.data.source.remote.response.ContentResponse
import com.etwicaksono.academy.data.source.remote.response.CourseResponse
import com.etwicaksono.academy.data.source.remote.response.ModuleResponse
import com.etwicaksono.academy.utils.DataDummy
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify

class AcademyRepositoryTest{

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val academyRepository = FakeAcademyRepository(remote)

    private val courseResponse = DataDummy.generateRemoteDummyCourses()
    private val courseId = courseResponse[0].id
    private val moduleResponse = DataDummy.generateRemoteDummyModules(courseId)
    private val moduleId = moduleResponse[0].moduleId
    private val content = DataDummy.generateRemoteDummyContent(moduleId)

    @Test
    fun getAllCourses(){
        `when`<List<CourseResponse>>(remote.getAllCourses()).thenReturn(courseResponse)
        val courseEntities = academyRepository.getAllCourses()
        verify<RemoteDataSource>(remote).getAllCourses()
        assertNotNull(courseEntities)
        assertEquals(courseResponse.size.toLong(),courseEntities.size.toLong())
    }

    @Test
    fun getAllModulesByCourse(){
        `when`<List<ModuleResponse>>(remote.getModules(courseId)).thenReturn(moduleResponse)
        val moduleEntities = academyRepository.getAllModulesByCourse(courseId)
        verify<RemoteDataSource>(remote).getModules(courseId)
        assertNotNull(moduleEntities)
        assertEquals(moduleResponse.size.toLong(),moduleEntities.size.toLong())
    }

    @Test
    fun getBookmarkedCourses(){
        `when`<List<CourseResponse>>(remote.getAllCourses()).thenReturn(courseResponse)
        val courseEntities = academyRepository.getBookmarkedCourses()
        verify<RemoteDataSource>(remote).getAllCourses()
        assertNotNull(courseEntities)
        assertEquals(courseResponse.size.toLong(),courseEntities.size.toLong())
    }

    @Test
    fun getContent(){
        `when`<List<ModuleResponse>>(remote.getModules(courseId)).thenReturn(moduleResponse)
        `when`<ContentResponse>(remote.getContent(moduleId)).thenReturn(content)
        val resultModule = academyRepository.getContent(courseId,moduleId)
        verify<RemoteDataSource>(remote).getContent(moduleId)
        assertNotNull(resultModule)
        assertEquals(content.content,resultModule.contentEntity?.content)
    }

    @Test
    fun getCourseWithModules(){
        `when`<List<CourseResponse>>(remote.getAllCourses()).thenReturn(courseResponse)
        val resultCourse=academyRepository.getCourseWithModules(courseId)
        verify<RemoteDataSource>(remote).getAllCourses()
        assertNotNull(resultCourse)
        assertEquals(courseResponse[0].title,resultCourse.title)
    }
}