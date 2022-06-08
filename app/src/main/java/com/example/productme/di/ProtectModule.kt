package com.example.productme.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.productme.feature_protect.data.data_source.local.ProtectMeDatabase
import com.example.productme.feature_protect.data.repository.ProtectMeRepositoryImpl
import com.example.productme.feature_protect.domain.repository.ProtectMeRepository
import com.example.productme.feature_protect.domain.use_case.*
import com.example.productme.feature_protect.domain.use_case.validation.ValidateGuardName
import com.example.productme.feature_protect.domain.use_case.validation.ValidateGuardPhone
import com.example.productme.feature_protect.util.Constants.DATA_BASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProtectModule {

    @Provides
    @Singleton
    fun provideProtectMeDatabase(application: Application): ProtectMeDatabase =
        Room.databaseBuilder(
            application,
            ProtectMeDatabase::class.java,
            DATA_BASE_NAME
        ).build()

    @Provides
    @Singleton
    fun provideProtectMeRepository(protectMeDatabase: ProtectMeDatabase): ProtectMeRepository =
        ProtectMeRepositoryImpl(dao = protectMeDatabase.guardDao)


    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context):SharedPreferences=
        context.getSharedPreferences("preferences_name",Context.MODE_PRIVATE)


    @Provides
    @Singleton
    fun provideProtectMeUseCases(repository: ProtectMeRepository,pref:SharedPreferences): ProtectMeUseCases =
        ProtectMeUseCases(
            addGuardUseCase = AddGuardUseCase(repository = repository),
            getGuardsUseCase = GetGuardsUseCase(repository = repository),
            deleteGuardUseCase = DeleteGuardUseCase(repository = repository),
            getGuardUseCase = GetGuardUseCase(repository = repository),
            validateGuardName = ValidateGuardName(),
            validateGuardPhone = ValidateGuardPhone(),
            saveServiceStateUseCase = SaveServiceStateUseCase(pref = pref),
            getServiceStateUseCase = GetServiceStateUseCase(pref = pref)
        )


}