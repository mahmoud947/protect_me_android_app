package com.example.productme.feature_protect.data.repository

import com.example.productme.feature_protect.data.data_source.local.GuardDao
import com.example.productme.feature_protect.domain.model.Guard
import com.example.productme.feature_protect.domain.repository.ProtectMeRepository
import kotlinx.coroutines.flow.Flow

class ProtectMeRepositoryImpl(
    private val dao: GuardDao,
) : ProtectMeRepository {
    override fun getGuards(): Flow<List<Guard>> =
        dao.getGuards()

    override suspend fun getGuard(gid: Int): Guard? =
        dao.getGuard(gid = gid)

    override suspend fun insertGuard(guard: Guard) =
        dao.insertGuard(guard = guard)

    override suspend fun deleteGuard(guard: Guard) =
        dao.deleteGuard(guard = guard)
}