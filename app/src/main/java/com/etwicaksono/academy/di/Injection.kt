package com.etwicaksono.academy.di

import android.content.Context
import com.etwicaksono.academy.data.source.AcademyRepository
import com.etwicaksono.academy.data.source.remote.RemoteDataSource
import com.etwicaksono.academy.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): AcademyRepository {
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        return AcademyRepository.getInstance(remoteDataSource)
    }
}