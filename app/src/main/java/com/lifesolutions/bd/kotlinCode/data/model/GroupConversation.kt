package com.lifesolutions.bd.kotlinCode.data.model

data class GroupConversation(
    val groupId: String? = null,
    val groupName: String? = null,
    val groupCat: String? = null,
    val createdTime: Long? = null,
    val groupImage: String? = null,
    val privacy: String? = null,
    val creatorId: String? = null,
    val lastTimestamps: Any? = null,
    val seen: Boolean? = null,
    val lastConversation: String = ""
)