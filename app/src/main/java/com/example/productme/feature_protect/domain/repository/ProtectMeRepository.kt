package com.example.productme.feature_protect.domain.repository

import com.example.productme.feature_protect.domain.model.Guard
import kotlinx.coroutines.flow.Flow

interface ProtectMeRepository {

    fun getGuards(): Flow<List<Guard>>

    suspend fun getGuard(gid: Int): Guard?

    suspend fun insertGuard(guard: Guard)

    suspend fun deleteGuard(guard: Guard)

}