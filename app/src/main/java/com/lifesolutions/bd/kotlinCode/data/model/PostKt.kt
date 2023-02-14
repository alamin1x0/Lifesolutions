package com.lifesolutions.bd.kotlinCode.data.model

import java.io.Serializable

data class PostKt (
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
    val postImage: String? = null,
    val postTextColor: String? = null,
    val permission: Boolean? = null,
    val hasLink: Boolean? = null,
    val linkThumb: String? = null,
    val linkTitle: String? = null,
    val linkRaw: String? = null,
    val linkSource: String? = null,
    val videoUrl: String? = null,
    val multiImage: List<String>? = null
): Serializable