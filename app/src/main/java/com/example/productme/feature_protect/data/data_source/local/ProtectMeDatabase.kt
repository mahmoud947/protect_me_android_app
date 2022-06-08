package com.example.productme.feature_protect.data.data_source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.productme.feature_protect.domain.model.Guard


@Database(
    entities = [Guard::class],
    version = 1
)
abstract class ProtectMeDatabase :RoomDatabase(){
abstract val guardDao:GuardDao
}