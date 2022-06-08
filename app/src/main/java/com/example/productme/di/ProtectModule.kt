package com.example.productme.di

import android.app.Application
import androidx.room.Room
import com.example.productme.feature_protect.data.data_source.local.ProtectMeDatabase
import com.example.productme.feature_protect.data.repository.ProtectMeRepositoryImpl
import com.example.productme.feature_protect.domain.repository.ProtectMeRepository
import com.example.productme.feature_protect.util.Constants.DATA_BASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun provideProtectMeRepository(protectMeDatabase: ProtectMeDatabase):ProtectMeRepository=
        ProtectMeRepositoryImpl(dao = protectMeDatabase.guardDao)
}