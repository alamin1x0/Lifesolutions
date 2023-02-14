package com.lifesolutions.bd.kotlinCode.data.model

data class Conversation(
    val uid: String? = null,
    val username: String? = null,
    val userProfileThumb: String? = null,
    val lastMessage: String? = null,
    val messageType: String? = null,
    val lastTimestamps: Any? = null,
    val typing: Boolean? = null,
    val seen: Boolean? = null
)