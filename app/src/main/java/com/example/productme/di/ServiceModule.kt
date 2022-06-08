package com.example.productme.di

import android.app.Application
import com.example.productme.core.comm.Constants
import com.example.productme.core.util.network.BasicAuthInterceptor
import com.example.productme.core.util.network.ConnectivityLiveData
import com.example.productme.service.protect_me.data.remote.TwilioApi
import com.example.productme.service.protect_me.data.repository.ProtectMeServiceRepositoryImpl
import com.example.productme.service.protect_me.domain.repository.ProtectMeServiceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideHttpClint():OkHttpClient=
        OkHttpClient.Builder()
            .addInterceptor(BasicAuthInterceptor(user = "AC221e7596abcc96312798a567a4349380", password = "40aa2fde202bb0640aee8a400f83cac6"))
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10,TimeUnit.SECONDS)
            .build()

    @Provides
    @Singleton
    fun provideTwilioApi(httpClient: OkHttpClient):TwilioApi=
        Retrofit.Builder()
            .baseUrl(Constants.TWILIO_BASE_URL)
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TwilioApi::class.java)

    @Provides
    @Singleton
    fun provideProtectMeServiceRepository(api: TwilioApi):ProtectMeServiceRepository=
        ProtectMeServiceRepositoryImpl(api = api)

}