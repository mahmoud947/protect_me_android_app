package com.example.productme.service.ptotect_me.service

import com.example.productme.service.ptotect_me.data.remote.request.SendMessageReqBody

object ProtectMeServiceComm {
    //Service constants
    //service action
    const val ACTION_START_OR_RESUME_SERVICE="ACTION_START_OR_RESUME_SERVICE"
    const val ACTION_PAUSE_SERVICE="ACTION_PAUSE_SERVICE"
    const val ACTION_STOP_SERVICE="ACTION_STOP_SERVICE"
    //service notification
    const val NOTIFICATION_CHANNEL_ID="protect_me_channel"
    const val NOTIFICATION_CHANNEL_NAME="protect_me"
    const val NOTIFICATION_ID=1

    val sentMessageReqBody=SendMessageReqBody(
        To = "whatsapp:+201152484675",
        From = "whatsapp:+14155238886",
        Body = "this message sent from an android service"
    )
}