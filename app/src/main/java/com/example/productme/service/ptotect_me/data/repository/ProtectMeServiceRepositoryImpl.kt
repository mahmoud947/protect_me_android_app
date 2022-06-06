package com.example.productme.service.ptotect_me.data.repository

import com.example.productme.service.ptotect_me.data.remote.TwilioApi
import com.example.productme.service.ptotect_me.data.remote.request.SendMessageReqBody
import com.example.productme.service.ptotect_me.data.remote.response.MessageSentRes
import com.example.productme.service.ptotect_me.domain.repository.ProtectMeServiceRepository
import javax.inject.Inject

class ProtectMeServiceRepositoryImpl @Inject constructor(
    private val api: TwilioApi
) :ProtectMeServiceRepository {
    override suspend fun sendMessage(sendMessageReqBody: SendMessageReqBody): MessageSentRes {
        return api.sendMessage(
            to = sendMessageReqBody.To,
            from = sendMessageReqBody.From,
            body = sendMessageReqBody.Body
        )
    }
}