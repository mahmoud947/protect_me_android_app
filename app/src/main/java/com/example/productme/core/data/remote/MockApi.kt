package com.example.productme.core.data.remote

import com.example.productme.core.data.remote.dto.Activation
import retrofit2.http.GET

interface MockApi {
    @GET("activation")
    suspend fun checkActivation():Activation
}