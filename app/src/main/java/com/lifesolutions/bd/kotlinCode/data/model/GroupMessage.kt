package com.lifesolutions.bd.kotlinCode.data.model

data class GroupMessage(
    val senderId: String? = null,
    val senderName: String? = null,
    val senderImage: String? = null,
    val groupId: String? = null,
    val messageId: String? = null,
    val message: String? = null,
    val type: String? = null,
    val image: String? = null,
    val seen: Boolean? = null,
    val timestamp: Long? = null
)