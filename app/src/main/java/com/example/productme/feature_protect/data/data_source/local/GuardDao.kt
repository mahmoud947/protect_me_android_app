package com.example.productme.feature_protect.data.data_source.local

import androidx.room.*
import com.example.productme.feature_protect.domain.model.Guard
import kotlinx.coroutines.flow.Flow

@Dao
interface GuardDao {

    @Query("SELECT * FROM GUARD_TABLE")
    fun getGuards(): Flow<List<Guard>>

    @Query("SELECT * FROM guard_table WHERE gid=:gid")
    suspend fun getGuard(gid: Int): Guard?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGuard(guard: Guard)

    @Delete
    suspend fun deleteGuard(guard: Guard)
}