package com.etwicaksono.academy.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.etwicaksono.academy.data.source.remote.RemoteDataSource
import com.etwicaksono.academy.utils.DataDummy
import com.etwicaksono.academy.utils.LiveDataTestUtil
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify

class AcademyRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val academyRepository = FakeAcademyRepository(remote)

    private val courseResponse = DataDummy.generateRemoteDummyCourses()
    private val courseId = courseResponse[0].id
    private val moduleResponse = DataDummy.generateRemoteDummyModules(courseId)
    private val moduleId = moduleResponse[0].moduleId
    private val content = DataDummy.generateRemoteDummyContent(moduleId)

    @Test
    fun getAllCourses() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadCourseCallback)
                .onAllCourseReceived(
                courseResponse
            )
            null
        }.`when`(remote).getAllCourses(any())
        val courseEntities = LiveDataTestUtil.getValue(academyRepository.getAllCourses())
        verify(remote).getAllCourses(any())
        assertNotNull(courseEntities)
        assertEquals(courseResponse.size.toLong(), courseEntities.size.toLong())
    }

    @Test
    fun getAllModulesByCourse() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadModulesCallback).onAllModulreReceived(
                moduleResponse
            )
            null
        }.`when`(remote).getModules(eq(courseId), any())
        val moduleEntities =
            LiveDataTestUtil.getValue(academyRepository.getAllModulesByCourse(courseId))
        verify(remote).getModules(eq(courseId), any())
        assertNotNull(moduleEntities)
        assertEquals(moduleResponse.size.toLong(), moduleEntities.size.toLong())
    }

    @Test
    fun getBookmarkedCourses() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadCourseCallback).onAllCourseReceived(
                courseResponse
            )
            null
        }.`when`(remote).getAllCourses(any())
        val courseEntities = LiveDataTestUtil.getValue(academyRepository.getBookmarkedCourses())
        verify(remote).getAllCourses(any())
        assertNotNull(courseEntities)
        assertEquals(courseResponse.size.toLong(), courseEntities.size.toLong())
    }

    @Test
    fun getContent() {
        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadModulesCallback).onAllModulreReceived(
                moduleResponse
            )
            null
        }.`when`(remote).getModules(eq(courseId), any())

        doAnswer { invocation ->
            (invocation.arguments[1] as RemoteDataSource.LoadContentCallback).onContentReceived(
                content
            )
            null
        }.`when`(remote).getContent(eq(moduleId), any())

        val courseEntitiesContent =
            LiveDataTestUtil.getValue(academyRepository.getContent(courseId, moduleId))
        verify(remote).getModules(eq(courseId), any())
        verify(remote).getContent(eq(moduleId), any())
        assertNotNull(courseEntitiesContent)
        assertNotNull(courseEntitiesContent.contentEntity)
        assertNotNull(courseEntitiesContent.contentEntity?.content)
        assertEquals(content.content, courseEntitiesContent.contentEntity?.content)
    }

    @Test
    fun getCourseWithModules() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadCourseCallback).onAllCourseReceived(
                courseResponse
            )
            null
        }.`when`(remote).getAllCourses(any())
        val courseEntities =
            LiveDataTestUtil.getValue(academyRepository.getCourseWithModules(courseId))
        verify(remote).getAllCourses(any())
        assertNotNull(courseEntities)
        assertNotNull(courseEntities.title)
        assertEquals(courseResponse[0].title, courseEntities.title)
    }
}