package com.example.productme.core.data.repository

import com.example.productme.core.data.remote.MockApi
import com.example.productme.core.data.remote.dto.Activation
import com.example.productme.core.domian.repository.CoreRepository
import javax.inject.Inject

class CoreRepositoryImpl @Inject constructor(
    private val api:MockApi
):CoreRepository {
    override suspend fun checkActivation(): Activation {
       return api.checkActivation()
    }
}