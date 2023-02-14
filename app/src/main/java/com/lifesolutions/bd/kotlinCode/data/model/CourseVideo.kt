package com.lifesolutions.bd.kotlinCode.data.model

import java.io.Serializable

data class CourseVideo(
    val _id: String? = null,
    val title: String? = null,
    val category: String? = null,
    val offeredBy: String? = null,
    val videoId: String? = null,
    val thumbnail: String? = null,
    val description: String? = null
): Serializable