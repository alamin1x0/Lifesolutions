package com.lifesolutions.bd.kotlinCode.data.model

data class UserCallData(
    val audioCall: CallHistory? = null,
    val videoCall: CallHistory? = null,
    val randomCall: CallHistory? = null
)