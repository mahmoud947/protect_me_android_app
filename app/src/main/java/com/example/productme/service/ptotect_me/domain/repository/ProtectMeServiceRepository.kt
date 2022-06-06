package com.example.productme.service.ptotect_me.domain.repository

import com.example.productme.service.ptotect_me.data.remote.request.SendMessageReqBody
import com.example.productme.service.ptotect_me.data.remote.response.MessageSentRes

interface ProtectMeServiceRepository {
    suspend fun sendMessage(sendMessageReqBody: SendMessageReqBody):MessageSentRes
}