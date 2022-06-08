package com.example.productme.service.protect_me.data.remote.response

data class MessageSentRes(
    val account_sid: String,
    val api_version: String,
    val body: String,
    val date_created: String,
    val date_sent: Any,
    val date_updated: String,
    val direction: String,
    val error_code: Any,
    val error_message: Any,
    val from: String,
    val messaging_service_sid: Any,
    val num_media: String,
    val num_segments: String,
    val price: Any,
    val price_unit: Any,
    val sid: String,
    val status: String,
    val subresource_uris: SubresourceUris,
    val to: String,
    val uri: String
)