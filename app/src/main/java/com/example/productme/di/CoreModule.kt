package com.example.productme.di

import com.example.productme.core.comm.Constants
import com.example.productme.core.data.remote.MockApi
import com.example.productme.core.data.repository.CoreRepositoryImpl
import com.example.productme.core.domian.repository.CoreRepository
import com.example.productme.core.domian.use_case.CheckActivationUseCase
import com.example.productme.core.domian.use_case.CoreUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {
    @Provides
    @Singleton
    fun provideScannerCurativePisApi(): MockApi =
        Retrofit.Builder()
            .baseUrl(Constants.MOCK_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MockApi::class.java)
    @Provides
    @Singleton
    fun provideCoreRepository(api: MockApi):CoreRepository=
        CoreRepositoryImpl(api = api)

    @Provides
    @Singleton
    fun provideCoreUseCases(repository: CoreRepository):CoreUseCases=
        CoreUseCases(
            checkActivationUseCase = CheckActivationUseCase(repository = repository)
        )
}