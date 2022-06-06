package com.example.productme.service.ptotect_me.data.remote

import com.example.productme.service.ptotect_me.data.remote.response.MessageSentRes
import retrofit2.http.*

interface TwilioApi {
    @FormUrlEncoded
    @POST("Messages.json")
    suspend fun sendMessage(
        @Field("To") to:String,
        @Field("From") from:String,
        @Field("Body")body:String
    ):MessageSentRes
}