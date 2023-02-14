package com.lifesolutions.bd.kotlinCode.data.model

import java.io.Serializable

data class CallNotificationAction (
    val answer: String? = null,
    val decline: String? = null
): Serializable