package com.lifesolutions.bd.kotlinCode.data.model

data class CallLog(
    val pushId: String? = null,
    val uid: String? = null,
    val username: String? = null,
    val userProfileThumb: String? = null,
    val callType: String? = null,
    val callStatus: String? = null,
    val duration: Long? = null,
    val lastTimestamps: Any? = null
)