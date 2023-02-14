package com.lifesolutions.bd.kotlinCode.data.model


data class GroupMemberRequest(
    val userId: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val image: String? = null,
    val timestamp: Any? = null
)