package com.example.productme.core.domian.repository

import com.example.productme.core.data.remote.dto.Activation

interface CoreRepository {
    suspend fun checkActivation(): Activation
}