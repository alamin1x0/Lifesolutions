package com.lifesolutions.bd.kotlinCode.data.model

data class GroupMember(
    val memberId: String? = null,
    val firstName: String? = null,
    val lastName: String? = null,
    val profileImage: String? = null,
    val joinAt: Long? = null,
    val role: String? = null,
    val lastTimestamps: Any? = null,
    val seen: Boolean? = null
)