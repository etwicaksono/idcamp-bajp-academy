package com.etwicaksono.academy.di

import android.content.Context
import com.etwicaksono.academy.data.source.AcademyRepository
import com.etwicaksono.academy.data.source.local.LocalDataSource
import com.etwicaksono.academy.data.source.local.room.AcademyDatabase
import com.etwicaksono.academy.data.source.remote.RemoteDataSource
import com.etwicaksono.academy.utils.AppExecutors
import com.etwicaksono.academy.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): AcademyRepository {
        val database=AcademyDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource=LocalDataSource.getInstance(database.academyDao())
        val appExecutors=AppExecutors()
        return AcademyRepository.getInstance(remoteDataSource,localDataSource,appExecutors)
    }
}