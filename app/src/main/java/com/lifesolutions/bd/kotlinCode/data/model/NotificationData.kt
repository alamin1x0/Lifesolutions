package com.lifesolutions.bd.kotlinCode.data.model

data class NotificationData(
    val user: String? = null,
    val body: String? = null,
    val title: String? = null,
    val sent: String? = null,
    val type: String? = null,
    val extra: String? = null,
    val icon: Int? = null
)