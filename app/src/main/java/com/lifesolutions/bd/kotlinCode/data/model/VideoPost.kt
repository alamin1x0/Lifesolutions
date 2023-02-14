package com.lifesolutions.bd.kotlinCode.data.model

import java.io.Serializable

data class VideoPost (
    val authorID: String? = null,
    val authorFirstName: String? = null,
    val authorLastName: String? = null,
    val authorImage: String? = null,
    val type: String? = null,
    val privacy: String? = null,
    val date: Any? = null,
    val postID: String? = null,
    val postDescription: String? = null,
    val postThumbnail: String? = null,
    val videoUrl: String? = null
): Serializable