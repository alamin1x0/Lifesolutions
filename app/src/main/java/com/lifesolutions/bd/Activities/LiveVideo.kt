package com.lifesolutions.bd.Activities

import java.io.Serializable

data class LiveVideo (
    val authorID: String? = null,
    val authorFirstName: String? = null,
    val authorLastName: String? = null,
    val authorImage: String? = null,
    val date: Any? = null,
    val postDescription: String? = null,
    val index: String? = null

): Serializable